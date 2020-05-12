package mgw.persistence_lavage_auto.repository;

import java.util.List;
import mgw.domaine_lavage_auto.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BigWave
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    @Query(nativeQuery = true)
    public List<Client> findClientByLoginEmailAndPassword(@Param("email") String email,@Param("password") String password);
    
}
