package co.edu.usco.parci_pw_2025.repository;

import co.edu.usco.parci_pw_2025.entity.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query; // Para consultas JPQL o nativas más complejas
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Long> {

    /**
     * Busca todas las asignaturas asociadas a un ID de docente específico.
     * Spring Data JPA interpreta "findBy" + "Docente" (el campo en AsignaturaEntity) + "Id" (la propiedad Id de DocenteEntity).
     *
     * @param docenteId El ID del DocenteEntity por el cual filtrar.
     * @return Una lista de AsignaturaEntity asociadas a ese docente. Estará vacía si no hay ninguna.
     */
    List<AsignaturaEntity> findByDocenteId(Long docenteId);

    /**
     * Ejemplo alternativo usando el objeto DocenteEntity directamente (si lo tienes disponible).
     * Busca todas las asignaturas asociadas a una instancia de DocenteEntity.
     *
     * @param docente La instancia de DocenteEntity.
     * @return Una lista de AsignaturaEntity.
     */
    // List<AsignaturaEntity> findByDocente(DocenteEntity docente);


    // Ejemplo de búsqueda más compleja (si fuera necesaria):
    // Buscar asignaturas por nombre que contengan una cadena, ignorando mayúsculas/minúsculas.
    // List<AsignaturaEntity> findByNombreContainingIgnoreCase(String nombre);

    // Ejemplo con consulta JPQL explícita (más control):
    // @Query("SELECT a FROM AsignaturaEntity a WHERE a.salon = :numeroSalon AND a.docente.id = :idDocente")
    // List<AsignaturaEntity> buscarPorSalonYDocente(@Param("numeroSalon") Integer salon, @Param("idDocente") Long docenteId);

}