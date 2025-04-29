package co.edu.usco.parci_pw_2025.service.impl;

import co.edu.usco.parci_pw_2025.entity.DocenteEntity;
import co.edu.usco.parci_pw_2025.repository.DocenteRepository;
import co.edu.usco.parci_pw_2025.service.DocenteService;
import lombok.RequiredArgsConstructor; // Para inyección de dependencias por constructor
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Para la gestión de transacciones

import java.util.List;
import java.util.Optional;

@Service // Marca esta clase como un bean de servicio de Spring
@RequiredArgsConstructor // Lombok: genera un constructor con los campos final (para inyección)
public class DocenteServiceImpl implements DocenteService {

    // Inyección de dependencia del repositorio (final para que @RequiredArgsConstructor funcione)
    private final DocenteRepository docenteRepository;

    @Override
    @Transactional(readOnly = true) // Transacción de solo lectura para optimizar consultas
    public List<DocenteEntity> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocenteEntity> findById(Long id) {
        return docenteRepository.findById(id);
    }

    @Override
    @Transactional // Transacción de lectura/escritura (default)
    public DocenteEntity save(DocenteEntity docente) {
        // Aquí podrías añadir lógica de negocio antes de guardar, si fuera necesario
        return docenteRepository.save(docente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // ¡Consideración importante! ¿Qué pasa si el docente tiene asignaturas?
        // Por defecto, si intentas borrar un docente con asignaturas,
        // la base de datos probablemente dará un error de restricción de clave foránea (FK).
        // Opciones:
        // 1. No permitir borrar si tiene asignaturas (requiere verificar antes).
        // 2. Poner el campo docente_id en asignaturas como NULL (requiere cambiar la FK a NULLABLE).
        // 3. Eliminar en cascada (peligroso, configurarías en @OneToMany en DocenteEntity).
        // Por ahora, lo dejamos simple, pero es un punto a revisar según las reglas de negocio.

        // Primero verificamos si existe para evitar excepciones si el ID no es válido
        if (docenteRepository.existsById(id)) {
            docenteRepository.deleteById(id);
        } else {
            // Podrías lanzar una excepción personalizada aquí si lo prefieres
            System.err.println("Intento de eliminar docente con ID inexistente: " + id);
            // o throw new EntityNotFoundException("Docente con id " + id + " no encontrado.");
        }
    }
}