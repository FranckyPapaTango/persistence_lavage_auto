/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.persistence_lavage_auto.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import mgw.domaine_lavage_auto.entities.Facture;
import mgw.persistence_lavage_auto.repository.FactureRepository;
import java.util.List;
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
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    //@Autowired 
    private Facture facture;



    //@Autowired
    public List<Facture> getAllFactures() {
        return this.factureRepository.findAll();
    }

    /**
     * retourne une liste pageable de Facture prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Facture> findAllFacturePage(int p, int s) {
        return factureRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfFactureByIdFacture(Long idFacture) {
//        Facture c = factureRepository.getOne(idFacture);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Facture findFactureById(long idFacture) {
        return factureRepository.getOne(idFacture);
    }

    //@Autowired
    public void saveOrUpdateFacture(
            Facture facture,//from web view fields
            Facture fac) {//from original in DB
        if (facture == null && fac != null) {//cas d'update "direct" à partir de la base originale fac 
            factureRepository.save(fac);
        } else if (fac == null) {//cas de creation : fac est inexistant car il est à créer par saisies nouvelles
            factureRepository.save(facture);
        } else {//cas d'update
            this.facture = fac;//update Facture(libelleFacture, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            fac.setAdresseFacturation(facture.getAdresseFacturation());
            fac.setIdCommande(facture.getIdCommande());
            fac.setIdFacture(facture.getIdFacture());
            fac.setIdTva(facture.getIdTva());
            fac.setCommissionAdmin(facture.getCommissionAdmin());
            fac.setCommissionMicroentrepreneur(facture.getCommissionMicroentrepreneur());
            fac.setCssRawColorCode(facture.getCssRawColorCode());
            fac.setDatePersistence(facture.getDatePersistence());            
            fac.setUpdatedVersionDate(facture.getUpdatedVersionDate());
           
        }
    }

    //@Autowired
    public void deleteFacture(Facture fac) {
        this.facture = fac;
        factureRepository.delete(facture);
    }

//    public Page<Facture> findFactureByIdUtilisateurPage(int p, int s, long idUtilisateur) {
//        Pageable pageable = new PageRequest(p, s);
//        //List<Facture> liste = (List<Facture>) factureRepository.findByIdUtilisateur(utilisateurService.findUtilisateurById(idUtilisateur));
//        List<Facture> liste = (List<Facture>) factureRepository.findByUtilis(utilisateurService.findUtilisateurById(idUtilisateur));
////Page<Module> pagem = cursusRepository.findModuleCollectionByIdCursus(idCursus, pageable);       
//        Page<Facture> pagem = new PageImpl<>(liste, pageable, liste.size());
//        return pagem;
//    }

}

