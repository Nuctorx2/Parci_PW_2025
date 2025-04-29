package co.edu.usco.parci_pw_2025.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // Constructor sin argumentos (requerido por JPA)
@AllArgsConstructor // Constructor con todos los argumentos (útil para creación)
@Entity // Indica que es una entidad JPA
@Table(name = "roles") // Nombre explícito de la tabla en la BD
public class RoleEntity {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental gestionado por la BD (PostgreSQL soporta IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // Columna no nula, única y con longitud máxima
    private String nombre; // Ej: "ROLE_RECTOR", "ROLE_DOCENTE", "ROLE_ESTUDIANTE" (prefijo ROLE_ es convención de Spring Security)

    // Constructor útil si no usas @AllArgsConstructor o para casos específicos
    public RoleEntity(String nombre) {
        this.nombre = nombre;
    }
}