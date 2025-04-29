package co.edu.usco.parci_pw_2025.service.impl;

import co.edu.usco.parci_pw_2025.entity.RoleEntity;
import co.edu.usco.parci_pw_2025.repository.RoleRepository;
import co.edu.usco.parci_pw_2025.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleEntity> findByNombre(String nombre) {
        return roleRepository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public RoleEntity save(RoleEntity role) {
        // Podrías añadir validación si el nombre ya existe, aunque el constraint unique de la BD ayuda.
        return roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    // Implementación ejemplo de findOrCreate:
    /*
    @Override
    @Transactional
    public RoleEntity findOrCreate(String nombre) {
        return findByNombre(nombre).orElseGet(() -> save(new RoleEntity(nombre)));
    }
    */
}