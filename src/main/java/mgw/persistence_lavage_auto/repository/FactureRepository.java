package mgw.persistence_lavage_auto.repository;

import mgw.domaine_lavage_auto.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BigWave
 */
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>{
    
}
