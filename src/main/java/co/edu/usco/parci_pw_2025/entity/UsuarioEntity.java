package co.edu.usco.parci_pw_2025.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set; // Para la colección de roles

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username; // O email, según prefieras para el login

    @Column(nullable = false)
    private String password; // ¡Importante! Guardar siempre encriptada (BCrypt)

    @Column(nullable = false)
    private boolean enabled = true; // Para activar/desactivar cuentas de usuario

    // Relación Muchos-a-Muchos con RoleEntity
    // FetchType.EAGER: Carga los roles junto con el usuario. Simple para pocos roles,
    //                  pero considera LAZY si el rendimiento se ve afectado.
    // CascadeType.MERGE: Si actualizas un usuario y cambias sus roles (existentes), se actualiza la relación.
    // CascadeType.PERSIST: Si creas un usuario con roles *nuevos* que no existen, se intentarían crear (quizás no deseado aquí).
    //                    Mejor manejar la creación de roles por separado o usar solo roles existentes.
    //                    Considera solo MERGE o ninguna cascada y manejar las relaciones manualmente en el servicio.
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "usuarios_roles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), // FK a esta entidad (UsuarioEntity)
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // FK a la otra entidad (RoleEntity)
    )
    private Set<RoleEntity> roles;

    // Podrías añadir otros campos como: nombre, apellido, email (si username no es el email), fecha_creacion, etc.
    // @Column(length = 100)
    // private String nombreCompleto;

}