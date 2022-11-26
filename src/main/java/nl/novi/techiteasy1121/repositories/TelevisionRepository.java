package nl.novi.techiteasy1121.repositories;

import nl.novi.techiteasy1121.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// We gebruiken hier een JpaRepository. Vergeet niet tussen de diamandjes "<>" als eerst het type van je entiteit
// mee te geven en vervolgens het type van het @Id-geannoteerde veld in die entiteit.
public interface TelevisionRepository extends JpaRepository<Television, Long> {
    List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(String brand);
    List<Television> findTelevisionByBluetooth(boolean bluetooth);
}
