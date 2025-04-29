package co.edu.usco.parci_pw_2025.service;

import co.edu.usco.parci_pw_2025.entity.RoleEntity; // Importa la entidad

import java.util.List;
import java.util.Optional;

public interface RoleService {

    /**
     * Busca un rol por su nombre exacto (ej: "ROLE_RECTOR").
     * @param nombre El nombre del rol.
     * @return Optional con el RoleEntity si existe.
     */
    Optional<RoleEntity> findByNombre(String nombre);

    /**
     * Guarda un rol. Útil si necesitas añadir roles dinámicamente (poco común).
     * @param role El RoleEntity a guardar.
     * @return El rol guardado.
     */
    RoleEntity save(RoleEntity role);

    /**
     * Obtiene todos los roles disponibles.
     * @return Lista de todos los RoleEntity.
     */
    List<RoleEntity> findAll();

    // Podrías añadir un método "findOrCreate" si quieres asegurar que un rol exista
    // RoleEntity findOrCreate(String nombre);

}