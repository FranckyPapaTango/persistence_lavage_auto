/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.persistence_lavage_auto.repository;

import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.entities.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author BigWave
 */
public interface PrestationRepository extends JpaRepository<Prestation, Long>{

    public Prestation findPrestationByLibellePrestation(String libellePrestation);
    
//    @Query("DELETE FROM prestation")
//    public void truncateTablePrestation();
    
}
