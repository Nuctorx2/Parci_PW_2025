package co.edu.usco.parci_pw_2025.service.impl;

import co.edu.usco.parci_pw_2025.entity.RoleEntity;
import co.edu.usco.parci_pw_2025.entity.UsuarioEntity;
import co.edu.usco.parci_pw_2025.repository.RoleRepository; // Necesitamos RoleRepository
import co.edu.usco.parci_pw_2025.repository.UsuarioRepository;
import co.edu.usco.parci_pw_2025.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // Importa PasswordEncoder
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository; // Inyecta RoleRepository
    private final PasswordEncoder passwordEncoder; // Inyecta el codificador de contraseñas

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioEntity> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UsuarioEntity saveUsuario(UsuarioEntity usuario) {
        // IMPORTANTE: Codificar la contraseña antes de guardarla
        // Asumimos que si la contraseña no está vacía, debe ser codificada.
        // Podrías necesitar lógica más compleja si permites actualizar usuarios sin cambiar contraseña.
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            // Solo codifica si no parece ya codificada (simple check, BCrypt empieza con $2a$, $2b$, etc.)
            // Una mejor aproximación sería tener un DTO para la entrada y saber si la contraseña cambió.
            if (!usuario.getPassword().startsWith("$2a$") && !usuario.getPassword().startsWith("$2b$")) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
        } else if (usuario.getId() != null) {
            // Si estamos actualizando (ID no es nulo) y la contraseña viene vacía/nula,
            // NO queremos borrarla, queremos mantener la existente.
            // Recuperamos la contraseña actual de la BD.
            UsuarioEntity existingUser = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado para actualizar contraseña"));
            usuario.setPassword(existingUser.getPassword()); // Mantenemos la contraseña existente
        } else {
            // Si es un usuario nuevo y la contraseña es nula/vacía, lanzamos error.
            throw new IllegalArgumentException("La contraseña no puede estar vacía para un nuevo usuario.");
        }


        return usuarioRepository.save(usuario);
    }


    @Override
    @Transactional
    public UsuarioEntity crearUsuarioConRoles(UsuarioEntity usuario, Set<String> nombresRoles) {
        // 1. Verificar si el username ya existe
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("Error: El nombre de usuario '" + usuario.getUsername() + "' ya está en uso.");
        }

        // 2. Codificar contraseña (asegurarse que no sea nula/vacía)
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía para un nuevo usuario.");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // 3. Buscar y asignar roles
        Set<RoleEntity> rolesAsignados = new HashSet<>();
        if (nombresRoles != null && !nombresRoles.isEmpty()) {
            rolesAsignados = nombresRoles.stream()
                    .map(nombreRol -> roleRepository.findByNombre(nombreRol)
                            .orElseThrow(() -> new RuntimeException("Error: Rol '" + nombreRol + "' no encontrado.")))
                    .collect(Collectors.toSet());
        }
        usuario.setRoles(rolesAsignados);

        // 4. Habilitar usuario por defecto
        usuario.setEnabled(true);

        // 5. Guardar usuario
        return usuarioRepository.save(usuario);
    }


    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }
}