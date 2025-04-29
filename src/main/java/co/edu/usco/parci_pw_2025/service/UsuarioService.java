package co.edu.usco.parci_pw_2025.service;

import co.edu.usco.parci_pw_2025.entity.UsuarioEntity; // Importa la entidad

import java.util.List;
import java.util.Optional;
import java.util.Set; // Para los nombres de roles

public interface UsuarioService {

    /**
     * Busca un usuario por su nombre de usuario (username).
     * @param username El username a buscar.
     * @return Optional con el UsuarioEntity si existe.
     */
    Optional<UsuarioEntity> findByUsername(String username);

    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * Es RESPONSABILIDAD de este método asegurar que la contraseña se codifique
     * antes de persistirla usando el PasswordEncoder configurado.
     * @param usuario El UsuarioEntity a guardar (con contraseña en texto plano si es nuevo o se está cambiando).
     * @return El UsuarioEntity guardado (con la contraseña codificada).
     */
    UsuarioEntity saveUsuario(UsuarioEntity usuario);


    /**
     * Crea un nuevo usuario con los roles especificados.
     * Este método simplifica la creación asegurando la codificación de contraseña
     * y la asignación de roles existentes.
     * @param usuario El UsuarioEntity con datos básicos (username, password en plano).
     * @param nombresRoles Un conjunto de Strings con los nombres de los roles a asignar (ej: "ROLE_DOCENTE").
     * @return El UsuarioEntity creado y persistido.
     * @throws RuntimeException si un rol especificado no existe o si el username ya está en uso.
     */
    UsuarioEntity crearUsuarioConRoles(UsuarioEntity usuario, Set<String> nombresRoles);


    /**
     * Obtiene todos los usuarios. (Usar con precaución si hay muchos usuarios).
     * @return Lista de todos los UsuarioEntity.
     */
    List<UsuarioEntity> findAll();

    // Podrías añadir métodos para actualizar perfil, cambiar contraseña (requiere contraseña actual), etc.
}