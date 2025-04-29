package co.edu.usco.parci_pw_2025.repository;

import co.edu.usco.parci_pw_2025.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    /**
     * Busca un usuario por su nombre de usuario (username).
     * Es crucial para el proceso de autenticación de Spring Security (UserDetailsService).
     *
     * @param username El nombre de usuario a buscar.
     * @return Un Optional que contiene el UsuarioEntity si se encuentra, o vacío si no.
     */
    Optional<UsuarioEntity> findByUsername(String username);

    /**
     * Verifica si existe un usuario con un determinado username.
     * Puede ser útil para validaciones antes de crear un nuevo usuario.
     *
     * @param username El nombre de usuario a verificar.
     * @return true si existe un usuario con ese username, false en caso contrario.
     */
    boolean existsByUsername(String username);

    // También hereda todos los métodos CRUD de JpaRepository.
}