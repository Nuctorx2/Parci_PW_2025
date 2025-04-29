package co.edu.usco.parci_pw_2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Para validación de Strings no vacíos
import jakarta.validation.constraints.NotNull; // Para validación de objetos/tipos no nulos
import jakarta.validation.constraints.Size; // Para validación de tamaño/longitud
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalTime; // Tipo adecuado para almacenar solo la hora

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asignaturas")
public class AsignaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la asignatura no puede estar vacío")
    @Size(max = 30, message = "El nombre no debe superar los {max} caracteres")
    @Column(nullable = false, length = 30)
    private String nombre;

    @Size(max = 100, message = "La descripción no debe superar los {max} caracteres")
    @Column(length = 100) // La descripción puede ser nula si no es obligatoria
    private String descripcion;

    @NotNull(message = "El salón es requerido")
    @Column(nullable = false) // Campo numérico para el salón
    private Integer salon;

    @NotNull(message = "La hora de inicio es requerida")
    @Column(nullable = false /*, columnDefinition = "TIME" // Opcional, para asegurar el tipo TIME en BD */)
    private LocalTime horaInicio;

    @NotNull(message = "La hora de finalización es requerida")
    @Column(nullable = false /*, columnDefinition = "TIME" */)
    private LocalTime horaFin;

    // --- Relación Muchos-a-Uno con DocenteEntity ---
    // Muchas asignaturas pueden ser impartidas por un docente.
    @NotNull(message = "Debe seleccionar un docente encargado")
    @ManyToOne(fetch = FetchType.LAZY) // LAZY es preferible para no cargar el docente siempre innecesariamente.
    @JoinColumn(name = "docente_id", nullable = false) // Nombre de la columna FK en esta tabla (asignaturas). No puede ser nula.
    private DocenteEntity docente;

}