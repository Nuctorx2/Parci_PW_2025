package co.edu.usco.parci_pw_2025.service;

import co.edu.usco.parci_pw_2025.entity.AsignaturaEntity; // Importa la entidad

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AsignaturaService {

    /**
     * Obtiene todas las asignaturas registradas.
     * @return Lista de AsignaturaEntity.
     */
    List<AsignaturaEntity> findAll();

    /**
     * Busca una asignatura por su ID.
     * @param id El ID de la asignatura.
     * @return Optional con la AsignaturaEntity si se encuentra.
     */
    Optional<AsignaturaEntity> findById(Long id);

    /**
     * Guarda una nueva asignatura o actualiza una existente.
     * @param asignatura La AsignaturaEntity a guardar.
     * @return La AsignaturaEntity guardada.
     */
    AsignaturaEntity save(AsignaturaEntity asignatura);

    /**
     * Elimina una asignatura por su ID.
     * @param id El ID de la asignatura a eliminar.
     */
    void deleteById(Long id);

    /**
     * Busca todas las asignaturas impartidas por un docente específico.
     * @param docenteId El ID del docente.
     * @return Lista de AsignaturaEntity para ese docente.
     */
    List<AsignaturaEntity> findByDocenteId(Long docenteId);

    /**
     * Actualiza el horario (hora inicio y hora fin) de una asignatura específica.
     * Este método encapsula la lógica requerida para el rol DOCENTE.
     *
     * @param asignaturaId El ID de la asignatura a actualizar.
     * @param nuevaHoraInicio La nueva hora de inicio.
     * @param nuevaHoraFin La nueva hora de finalización.
     * @param docenteIdVerificacion El ID del docente que realiza la operación (para verificar permisos).
     * @return La AsignaturaEntity actualizada.
     * @throws SecurityException Si el docente que intenta actualizar no es el asignado.
     * @throws IllegalArgumentException Si los datos del horario son inválidos (ej: fin antes de inicio) o la asignatura no existe.
     */
    AsignaturaEntity updateHorario(Long asignaturaId, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin, Long docenteIdVerificacion)
            throws SecurityException, IllegalArgumentException;

}