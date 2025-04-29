package co.edu.usco.parci_pw_2025.service.impl;

import co.edu.usco.parci_pw_2025.entity.AsignaturaEntity;
import co.edu.usco.parci_pw_2025.repository.AsignaturaRepository;
import co.edu.usco.parci_pw_2025.service.AsignaturaService;
import jakarta.persistence.EntityNotFoundException; // Excepción estándar de JPA
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    // Podrías inyectar DocenteService si necesitaras lógica de docentes aquí

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaEntity> findAll() {
        return asignaturaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AsignaturaEntity> findById(Long id) {
        return asignaturaRepository.findById(id);
    }

    @Override
    @Transactional
    public AsignaturaEntity save(AsignaturaEntity asignatura) {
        // Lógica de negocio ANTES de guardar:
        // Validar que horaFin sea posterior a horaInicio
        if (asignatura.getHoraInicio() != null && asignatura.getHoraFin() != null &&
                !asignatura.getHoraFin().isAfter(asignatura.getHoraInicio())) {
            // Es mejor lanzar una excepción aquí que será capturada más arriba (en el controlador)
            // o manejarla devolviendo un error específico. Lanzar excepción es común.
            throw new IllegalArgumentException("La hora de finalización debe ser posterior a la hora de inicio.");
        }
        // Validar que el docente no sea nulo (aunque @NotNull en la entidad ayuda)
        if (asignatura.getDocente() == null || asignatura.getDocente().getId() == null) {
            throw new IllegalArgumentException("Se requiere un docente válido para la asignatura.");
        }

        return asignaturaRepository.save(asignatura);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (asignaturaRepository.existsById(id)) {
            asignaturaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Asignatura con id " + id + " no encontrada para eliminar.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaEntity> findByDocenteId(Long docenteId) {
        return asignaturaRepository.findByDocenteId(docenteId);
    }

    @Override
    @Transactional
    public AsignaturaEntity updateHorario(Long asignaturaId, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin, Long docenteIdVerificacion)
            throws SecurityException, IllegalArgumentException {

        // 1. Validar horas
        if (nuevaHoraInicio == null || nuevaHoraFin == null || !nuevaHoraFin.isAfter(nuevaHoraInicio)) {
            throw new IllegalArgumentException("Horario inválido. La hora de finalización debe ser posterior a la de inicio.");
        }

        // 2. Buscar la asignatura
        AsignaturaEntity asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new IllegalArgumentException("Asignatura con id " + asignaturaId + " no encontrada."));


        // 3. Verificar permiso (que el docente que modifica sea el asignado)
        //    Cargamos el docente asociado a la asignatura (FetchType.LAZY requiere estar dentro de una transacción)
        if (asignatura.getDocente() == null || !asignatura.getDocente().getId().equals(docenteIdVerificacion)) {
            throw new SecurityException("El usuario no tiene permiso para modificar el horario de esta asignatura.");
        }

        // 4. Actualizar y guardar
        asignatura.setHoraInicio(nuevaHoraInicio);
        asignatura.setHoraFin(nuevaHoraFin);
        return asignaturaRepository.save(asignatura); // save() también actualiza si la entidad tiene ID
    }
}