/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.persistence_lavage_auto.services;

import java.util.Collection;
import java.util.List;
import mgw.domaine_lavage_auto.entities.Commande;
import mgw.domaine_lavage_auto.entities.Prestation;
import mgw.persistence_lavage_auto.repository.CommandeRepository;
import mgw.persistence_lavage_auto.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BigWave
 */
@Transactional
@Service
public class PrestationService {

    @Autowired//only for interfaces "instanciation"
    private PrestationRepository prestationRepository;

    @Autowired//only for interfaces "instanciation"
    private CommandeRepository commandeRepository;



    //@Autowired
    public Prestation getPrestationByIdCommande(Long idCommande) {
        Commande co = commandeRepository.getOne(idCommande);
        //System.out.println("liste size ="+st.getPrestationCollection().size());//TEST
//        return prestationRepository.getOne(co.getIdPrestation());
        return co.getIdPrestation();
    }

//@Autowired
    public Page<Prestation> findAllPrestationPage(int p, int s, long idCommande) {
        Pageable pageable = new PageRequest(p, s);
        List<Prestation> liste = (List<Prestation>) getPrestationByIdCommande(idCommande);
        //Page<Prestation> pagem = commandeRepository.findPrestationCollectionByIdCommande(idCommande, pageable);       
        Page<Prestation> pagem = new PageImpl<>(liste, pageable, liste.size());
        return pagem;
    }
 //@Autowired
    public Page<Prestation> findAllPrestation(int p, int s) {
        Pageable pageable = new PageRequest(p, s);
        List<Prestation> liste = prestationRepository.findAll();             
        //Page<Prestation> pagem = commandeRepository.findPrestationCollectionByIdCommande(idCommande, pageable);       
        Page<Prestation> pagem = new PageImpl<>(liste, pageable, liste.size());
        return pagem;
    }
//@Autowired
    public Prestation findPrestationByIdPrestation(Long idPrestation){
    return prestationRepository.getOne(idPrestation);
    }

   
    
    
    //@Autowired
    public void saveOrUpdatePrestation(
            Prestation prestation,//from web view fields
            Prestation prest) {//from original in DB
        if (prest == null) {//cas de creation : prest est inexistant car il est à créer par saisies nouvelles
            prestationRepository.save(prestation);
        } else {//cas d'update
            System.out.println("ENTITY FROM DB ISNOTNULL (ok)");
            //this.prestation = prest;//update Prestation(libellePrestation, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, prestationCollection, prestationCollection);
//            prest.setCommandeCollection(prestation.getCommandeCollection());
            prest.setCssRawColorCode(prestation.getCssRawColorCode());           
            prest.setDatePersistence(prestation.getDatePersistence());
            prest.setDetailPrestation(prestation.getDetailPrestation());
            prest.setIdPrestation(prestation.getIdPrestation());           
            prest.setLibellePrestation(prestation.getLibellePrestation());
            prest.setPaypalId(prestation.getPaypalId());
            prest.setPrixPrestation(prestation.getPrixPrestation());
            prest.setUpdatedVersionDate(prestation.getUpdatedVersionDate());
            //prest.setIdPrestation(0);
            prestationRepository.save(prest);
        }

    }

//    public long getPK(long idInscription) {
//        return prestationRepository.findOne(idInscription);
//    }

    public void deletePrestation(Prestation prestation) {
       prestationRepository.delete(prestation);
    }

//    public Prestation findPrestationByLibellePrestation(String libellePrestation) {
//        return prestationRepository.findPrestationByLibellePrestation(libellePrestation);
//                }
//    public void saveOrUpdateTransient(Collection<Prestation> prestationCollection) {
//        List<PrestationPK> listPK = new ArrayList<>();
//        for (Prestation ts : prestationCollection) {
//            listPK.add(ts.getPrestationPK());
//            prestationRepository.save(ts);
//        }
//        
//    }

    public Collection<Prestation> findAll() {
     return prestationRepository.findAll();
    }

    public Prestation getPrestationByLibelle(String libellePrestation) {
        return prestationRepository.findPrestationByLibellePrestation(libellePrestation);
                }

    public Page<Prestation> findAllPrestationPage(int p, int s) {
        return prestationRepository.findAll(new PageRequest(p,s));//<=(int page, int size)	
    }

 

   

    
}
