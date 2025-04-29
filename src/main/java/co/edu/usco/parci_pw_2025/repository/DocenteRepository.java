package co.edu.usco.parci_pw_2025.repository;

import co.edu.usco.parci_pw_2025.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {

    // Por el momento, los métodos estándar de JpaRepository son suficientes.
    // Si necesitaras buscar docentes por nombre, podrías añadir:
    // List<DocenteEntity> findByNombreCompletoContainingIgnoreCase(String parteDelNombre);

}
