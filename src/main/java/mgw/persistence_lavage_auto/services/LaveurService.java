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
import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.persistence_lavage_auto.repository.LaveurRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BigWave
 */
@Transactional
@Service
public class LaveurService {

    @Autowired
    private LaveurRepository laveurRepository;

    //@Autowired 
    private Laveur laveur;

    //@Autowired
    public List<Laveur> getAllLaveurs() {
        return this.laveurRepository.findAll();
    }

    /**
     * retourne une liste pageable de Laveur prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Laveur> findAllLaveurPage(int p, int s) {
        return laveurRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfLaveurByIdLaveur(Long idLaveur) {
//        Laveur c = laveurRepository.getOne(idLaveur);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Laveur findLaveurById(long idLaveur) {
        return laveurRepository.getOne(idLaveur);
    }

    //@Autowired
    public void saveOrUpdateLaveur(
            Laveur laveur,//from web view fields
            Laveur lav) {//from original in DB
        if (laveur == null && lav != null) {//cas d'update "direct" à partir de la base originale lav 
            laveurRepository.saveAndFlush(lav);
        } else if (lav == null) {//cas de creation : lav est inexistant car il est à créer par saisies nouvelles
            laveurRepository.saveAndFlush(laveur);
        } else {//cas d'update
            this.laveur = lav;//update Laveur(libelleLaveur, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            lav.setCodePostale(laveur.getCodePostale());
            lav.setCssRawColorCode(laveur.getCssRawColorCode());
            lav.setComplement(laveur.getComplement());
            lav.setIdLaveur(laveur.getIdLaveur());
            lav.setDatePersistence(laveur.getDatePersistence());
            lav.setNumVoieTypeVoieLibelleVoie(laveur.getNumVoieTypeVoieLibelleVoie());
            lav.setLoginEmail(laveur.getLoginEmail());
            lav.setNom(laveur.getNom());
            lav.setPassword(laveur.getPassword());
            lav.setPays(laveur.getPays());
            lav.setPrenom(laveur.getPrenom());
            lav.setStatut(laveur.getStatut());
            lav.setTelFixe(laveur.getTelFixe());
            lav.setTelMobile(laveur.getTelMobile());
//            lav.setCommandeCollection(laveur.getCommandeCollection());
            lav.setVille(laveur.getVille());
            lav.setUpdatedVersionDate(laveur.getUpdatedVersionDate());

        }
    }

    //@Autowired
    public void deleteLaveur(Laveur lav) {
        this.laveur = lav;
        laveurRepository.delete(laveur);
    }

//    public Page<Laveur> findLaveurByIdUtilisateurPage(int p, int s, long idUtilisateur) {
//        Pageable pageable = new PageRequest(p, s);
//        //List<Laveur> liste = (List<Laveur>) laveurRepository.findByIdUtilisateur(utilisateurService.findUtilisateurById(idUtilisateur));
//        List<Laveur> liste = (List<Laveur>) laveurRepository.findByUtilis(utilisateurService.findUtilisateurById(idUtilisateur));
////Page<Module> pagem = cursusRepository.findModuleCollectionByIdCursus(idCursus, pageable);       
//        Page<Laveur> pagem = new PageImpl<>(liste, pageable, liste.size());
//        return pagem;
//    }
    public List<Laveur> findLaveurByLoginEmailAndPassword(String email,String password) {
        return laveurRepository.findLaveurByLoginEmailAndPassword(email,password);
    }


}
