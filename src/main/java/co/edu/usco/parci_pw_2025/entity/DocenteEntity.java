package co.edu.usco.parci_pw_2025.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List; // Para las asignaturas asociadas

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "docentes")
public class DocenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombreCompleto;

    // --- Relación Inversa con AsignaturaEntity ---
    // Un docente puede tener muchas asignaturas.
    // 'mappedBy = "docente"': Indica que la relación es gestionada por el campo 'docente' en AsignaturaEntity.
    // FetchType.LAZY: Las asignaturas no se cargan automáticamente al cargar un docente (bueno para rendimiento).
    // CascadeType: Generalmente no se necesita cascada desde Docente a Asignatura (no eliminas/creas asignaturas al manipular un docente).
    //              Podrías necesitar MERGE si modificas un docente y quieres que se refleje en sus asignaturas (poco común).
    //              REMOVE podría ser peligroso (¿eliminar todas las asignaturas si borras al docente?). Mejor manejarlo en el servicio.
    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY, cascade = {}) // Sin cascada por defecto
    private List<AsignaturaEntity> asignaturas;


    // --- Opcional: Relación con UsuarioEntity ---
    // Si un Docente SIEMPRE corresponde a un Usuario del sistema para login:
    // @OneToOne(fetch = FetchType.LAZY) // Un docente es un usuario
    // @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true) // FK en la tabla docentes
    // private UsuarioEntity usuario;
    // Si decides añadir esto, asegúrate de manejar la creación/asociación correctamente.
    // Por ahora, lo mantenemos simple como tabla independiente de información.

    // Constructor útil
    public DocenteEntity(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}