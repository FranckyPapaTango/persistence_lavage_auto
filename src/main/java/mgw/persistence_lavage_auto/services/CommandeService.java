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
import mgw.domaine_lavage_auto.entities.Commande;
import mgw.persistence_lavage_auto.repository.CommandeRepository;
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
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    //@Autowired 
    private Commande commande;

    //@Autowired
    public List<Commande> getAllCommandes() {
        return this.commandeRepository.findAll();
    }

    /**
     * retourne une liste pageable de Commande prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Commande> findAllCommandePage(int p, int s) {
        return commandeRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfCommandeByIdCommande(Long idCommande) {
//        Commande c = commandeRepository.getOne(idCommande);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Commande findCommandeById(long idCommande) {
        return commandeRepository.getOne(idCommande);
    }

    //@Autowired
    public void saveOrUpdateCommande(
            Commande commande,//from web view fields
            Commande com) {//from original in DB
        if (commande == null && com != null) {//cas d'update "direct" à partir de la base originale com 
            commandeRepository.save(com);
        } else if (com == null) {//cas de creation : com est inexistant car il est à créer par saisies nouvelles
            commandeRepository.save(commande);
        } else {//cas d'update
            this.commande = com;//update Commande(libelleCommande, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            com.setCreneauHoraireF(commande.getCreneauHoraireF());
            com.setCreneauHoraireI(commande.getCreneauHoraireI());
            com.setDateLavage(commande.getDateLavage());
            com.setDatePersistence(commande.getDatePersistence());
            com.setUpdatedVersionDate(commande.getUpdatedVersionDate());
            com.setCssRawColorCode(commande.getCssRawColorCode());

            com.setIdClient(commande.getIdClient());
            com.setIdCommande(commande.getIdCommande());
            com.setIdLaveur(commande.getIdLaveur());
            com.setIdPrestation(commande.getIdPrestation());
//            com.setFactureCollection(commande.getFactureCollection());
//            com.setFormulePrestation(commande.getFormulePrestation());

        }
    }

    //@Autowired
    public void deleteCommande(Commande com) {
        this.commande = com;
        commandeRepository.delete(commande);
    }

//    public Page<Commande> findCommandeByIdUtilisateurPage(int p, int s, long idUtilisateur) {
//        Pageable pageable = new PageRequest(p, s);
//        //List<Commande> liste = (List<Commande>) commandeRepository.findByIdUtilisateur(utilisateurService.findUtilisateurById(idUtilisateur));
//        List<Commande> liste = (List<Commande>) commandeRepository.findByUtilis(utilisateurService.findUtilisateurById(idUtilisateur));
////Page<Module> pagem = cursusRepository.findModuleCollectionByIdCursus(idCursus, pageable);       
//        Page<Commande> pagem = new PageImpl<>(liste, pageable, liste.size());
//        return pagem;
//    }
    public Page<Commande> findCommandeByKeyWord(String search, int p, int s) {
        Pageable pageable = new PageRequest(p, s);
        List<Commande> liste = (List<Commande>) commandeRepository.findCommandeByKeyWord(search);
        Page<Commande> pagem = new PageImpl<>(liste, pageable, liste.size());
        return pagem;
    }

    public Page<Commande> findCommandeOfLaveurByKeyWord(long id, String search, int p, int s) {
        Pageable pageable = new PageRequest(p, s);
        List<Commande> liste = (List<Commande>) commandeRepository.findCommandeOfLaveurByKeyWord(id, search);
        Page<Commande> pagem = new PageImpl<>(liste, pageable, liste.size());
        return pagem;
    }

    public Page<Commande> findAllCommandeOfLaveurPage(long id, int p, int s) {
        Pageable pageable = new PageRequest(p, s);
        List<Commande> liste = (List<Commande>) commandeRepository.findAllCommandeOfLaveur(id);
        Page<Commande> pagem = new PageImpl<>(liste, pageable, liste.size());
        return pagem;
    }// Cette méthode est équivalente à celle qui suit :

    public Page<Commande> findByIdLaveurPage(long id, int p, int s) {
        Pageable pageable = new PageRequest(p, s);
        List<Commande> liste = (List<Commande>) commandeRepository.findCommandeByIdLaveur(id);
        Page<Commande> pagem = new PageImpl<>(liste, pageable, liste.size());
        return pagem;
    }

}
