package mgw.persistence_lavage_auto.repository;

import java.util.List;
import mgw.domaine_lavage_auto.entities.Laveur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BigWave
 */
@Repository
public interface LaveurRepository extends JpaRepository<Laveur, Long>{

    @Query(nativeQuery = true)
    public List<Laveur> findLaveurByLoginEmailAndPassword(@Param("email") String email,@Param("password") String password );


    
    
}
