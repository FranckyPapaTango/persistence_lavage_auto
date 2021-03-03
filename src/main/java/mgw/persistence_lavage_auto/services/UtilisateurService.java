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

import mgw.persistence_lavage_auto.repository.UtilisateurRepository;
import java.util.List;
import mgw.domaine_lavage_auto.entities.Utilisateur;
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
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    //@Autowired 
    private Utilisateur utilisateur;

    //@Autowired
    public List<Utilisateur> getAllUtilisateurs() {
        return this.utilisateurRepository.findAll();
    }

    /**
     * retourne une liste pageable de Utilisateur prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Utilisateur> findAllUtilisateurPage(int p, int s) {
        return utilisateurRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfUtilisateurByIdUtilisateur(Long idUtilisateur) {
//        Utilisateur c = utilisateurRepository.getOne(idUtilisateur);
//        return (List<Module>) c.getModuleCollection();
//    }

//    public Utilisateur findUtilisateurById(long idUtilisateur) {
//        return utilisateurRepository.getOne(idUtilisateur);
//    }
    //@Autowired
    public Utilisateur findUtilisateurById(Long idUtilisateur) {
        return utilisateurRepository.findOne(idUtilisateur);
    }

    //@Autowired  
    @Transactional
    public void saveOrUpdateUtilisateur(
            Utilisateur utilisateur,//from web view fields
            Utilisateur adm) {//from original in DB
        if (utilisateur == null && adm != null) {//cas d'update "direct" à partir de la base originale adm 
            utilisateurRepository.saveAndFlush(adm);
        } else if (adm == null) {//cas de creation : adm est inexistant car il est à créer par saisies nouvelles
            utilisateurRepository.saveAndFlush(utilisateur);
        } else {//cas d'update
            this.utilisateur = adm;//update Utilisateur(libelleUtilisateur, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            adm.setCodePostale(utilisateur.getCodePostale());
            adm.setCssRawColorCode(utilisateur.getCssRawColorCode());
            adm.setComplement(utilisateur.getComplement());
            adm.setUtilisateurId(utilisateur.getUtilisateurId());
            adm.setDatePersistence(utilisateur.getDatePersistence());
            adm.setNumVoieTypeVoieLibelleVoie(utilisateur.getNumVoieTypeVoieLibelleVoie());
            adm.setLogin(utilisateur.getLogin());
            adm.setNom(utilisateur.getNom());
            adm.setPassword(utilisateur.getPassword());
            adm.setPays(utilisateur.getPays());
            adm.setUsername(utilisateur.getUsername());
            adm.setStatut(utilisateur.getStatut());
            adm.setTelFixe(utilisateur.getTelFixe());
            adm.setTelMobile(utilisateur.getTelMobile());
            adm.setVille(utilisateur.getVille());
            // SETTINGS FROM ADDED ATTRIBUTES'S ENTITIES : ************
            adm.setSessionId(utilisateur.getSessionId()); // <==ceci est un ID de session !!!!
//            adm.setDateAdded(utilisateur.getDateAdded());
            adm.setLogin(utilisateur.getLogin());
            adm.setPathDle(utilisateur.getPathDle());
            adm.setUsername(utilisateur.getUsername());
            utilisateurRepository.saveAndFlush(adm);

        }
    }

    //@Autowired
    public void deleteUtilisateur(Utilisateur adm) {
        this.utilisateur = adm;
        utilisateurRepository.delete(utilisateur);
    }

    //@Autowired
    public Utilisateur findUtilisateurByLoginAndPassword(String email, String password) {
        return utilisateurRepository.findUtilisateurByLoginAndPassword(email, password);
    }

    // METHODS FROM ADDED ATTRIBUTE'S ENTITY : ****************/
    //@Autowired
//    public List<Utilisateur> getAllUtilisateurs() {
//        return this.utilisateurRepository.findAll();
//    }
    // METHODS FROM ADDED ATTRIBUTE'S ENTITY : ****************/
    //@Autowired
//    public List<Utilisateur> getAllUtilisateurs() {
//        return this.utilisateurRepository.findAll();
//    }
    /**
     * retourne une liste pageable de Utilisateur prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
//    public Page<Utilisateur> findAllUtilisateurPage(int p, int s) {
//        return utilisateurRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
//    }
//    //@Autowired
//    public List<Module> findAllModulesOfUtilisateurByIdUtilisateur(Long idUtilisateur) {
//        Utilisateur c = utilisateurRepository.findOne(idUtilisateur);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
//    public Utilisateur findUtilisateurBySessionId(String idUtilisateur) {
//        return utilisateurRepository.findUtilisateurBySessionId(idUtilisateur);
//    }


    //@Autowired
//    @Transactional
//    public void saveOrUpdateUtilisateur(
//            Utilisateur utilisateur,//from web view fields
//            Utilisateur chat) {//from original in DB
//        if (utilisateur == null && chat != null) {//cas d'update "direct" à partir de la base originale chat 
//            utilisateurRepository.save(chat);
//        } else if (chat == null) {//cas de creation : chat est inexistant car il est à créer par saisies nouvelles
//            utilisateurRepository.save(utilisateur);
//        } else {//cas d'update
//            this.utilisateur = chat;//update Utilisateur(libelleUtilisateur, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
//            chat.setSessionId(utilisateur.getSessionId()); // <==ceci est un ID de session !!!!
//            chat.setDateAdded(utilisateur.getDateAdded());
//            chat.setLogin(utilisateur.getLogin());
//            chat.setPathDle(utilisateur.getPathDle());
//            chat.setUsername(utilisateur.getUsername());
////            chat.setMessageCollection(utilisateur.getMessageCollection());
//
//// LES ATTRIBUTS QUI SUIVENT SONT REMPLIS ET GERES PAR LA SUPER CLASSE "AbstractEntity.java":            
////            chat.setDatePersistence(utilisateur.getDatePersistence());            
////            chat.setCssRawColorCode(utilisateur.getCssRawColorCode());
////            chat.setUpdatedVersionDate(utilisateur.getUpdatedVersionDate());            
////            
//        }
//    }
    //@Autowired
//    public void deleteUtilisateur(Utilisateur chat) {
//        this.utilisateur = chat;
//        utilisateurRepository.delete(utilisateur);
//    }
    public List<Utilisateur> findUtilisateurByKeyWord(String search) {
        List<Utilisateur> liste = (List<Utilisateur>) utilisateurRepository.findUtilisateurByKeyWord(search);
        return liste;
    }

//    public List<Utilisateur> findUtilisateurByLoginAndPassword(String email, String password) {
//        return utilisateurRepository.findUtilisateurByLoginAndPassword(email,password);
//    }
    public Utilisateur findUtilisateurByLogin(String email) {
        return utilisateurRepository.findUtilisateurByLogin(email);
    }

}
