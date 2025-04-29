package co.edu.usco.parci_pw_2025.service;

import co.edu.usco.parci_pw_2025.entity.DocenteEntity; // Importa la entidad correcta

import java.util.List;
import java.util.Optional;

public interface DocenteService {

    /**
     * Obtiene todos los docentes registrados.
     * Útil para llenar listas desplegables en los formularios.
     * @return Una lista de DocenteEntity.
     */
    List<DocenteEntity> findAll();

    /**
     * Busca un docente por su ID.
     * @param id El ID del docente a buscar.
     * @return Un Optional con el DocenteEntity si se encuentra, o vacío si no.
     */
    Optional<DocenteEntity> findById(Long id);

    /**
     * Guarda un nuevo docente o actualiza uno existente.
     * @param docente El DocenteEntity a guardar.
     * @return El DocenteEntity guardado (puede incluir el ID generado si es nuevo).
     */
    DocenteEntity save(DocenteEntity docente);

    /**
     * Elimina un docente por su ID.
     * (Considerar lógica adicional si un docente tiene asignaturas asociadas).
     * @param id El ID del docente a eliminar.
     */
    void deleteById(Long id);

    // Podrías añadir otros métodos si son necesarios, como buscar por nombre, etc.
}