package co.edu.usco.parci_pw_2025.repository;
import co.edu.usco.parci_pw_2025.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Opcional, pero buena práctica

import java.util.Optional;

@Repository // Indica que es un componente de repositorio gestionado por Spring
public interface RoleRepository extends JpaRepository<RoleEntity, Long> { // <Entidad, Tipo de la Clave Primaria>

    /**
     * Busca un rol por su nombre.
     * Spring Data JPA generará la consulta SQL automáticamente basándose en el nombre del método.
     * Devuelve un Optional para manejar de forma segura el caso en que el rol no exista.
     *
     * @param nombre El nombre del rol a buscar (ej: "ROLE_RECTOR").
     * @return Un Optional que contiene el RoleEntity si se encuentra, o un Optional vacío si no.
     */
    Optional<RoleEntity> findByNombre(String nombre);

    // Con JpaRepository ya tienes métodos como:
    // - save(RoleEntity entity) -> Guarda o actualiza una entidad
    // - findById(Long id) -> Busca por ID (devuelve Optional<RoleEntity>)
    // - findAll() -> Devuelve List<RoleEntity>
    // - deleteById(Long id) -> Elimina por ID
    // - count() -> Cuenta el número de entidades
    // - existsById(Long id) -> Verifica si existe por ID
    // ... y muchos más.
}