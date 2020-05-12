package mgw.persistence_lavage_auto.repository;

import java.util.List;
import mgw.domaine_lavage_auto.entities.Adminn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BigWave
 */
@Repository
public interface AdminnRepository extends JpaRepository<Adminn, Long> {

//    public Adminn findByLoginEmail(String email);

    @Query(nativeQuery=true)
    public List<Adminn> findAdminnByLoginEmailAndPassword(@Param("email") String loginEmail,@Param("password") String password);

}
