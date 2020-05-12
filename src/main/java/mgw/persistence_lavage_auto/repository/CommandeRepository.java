package mgw.persistence_lavage_auto.repository;

import java.util.List;
import mgw.domaine_lavage_auto.entities.Commande;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BigWave
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{

    
    @Query(nativeQuery=true)
    public List<Commande> findCommandeByKeyWord(@Param("search") String search);
    
    @Query(nativeQuery=true)
    public List<Commande> findCommandeOfLaveurByKeyWord(@Param("id") long id,@Param("search") String search);
    
    @Query(nativeQuery=true)// éqivalent à findByIdLaveur
    public List<Commande> findAllCommandeOfLaveur(@Param("id") long id);
    
    public Commande findCommandeByIdLaveur(Long idLaveur);
    
}

