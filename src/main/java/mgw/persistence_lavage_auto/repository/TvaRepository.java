package mgw.persistence_lavage_auto.repository;

import mgw.domaine_lavage_auto.entities.Tva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BigWave
 */
@Repository
public interface TvaRepository extends JpaRepository<Tva, Long>{
    
}