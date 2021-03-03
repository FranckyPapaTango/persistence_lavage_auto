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



import mgw.persistence_lavage_auto.repository.TvaRepository;
import java.util.List;
import mgw.domaine_lavage_auto.entities.Tva;
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
public class TvaService {

    @Autowired
    private TvaRepository tvaRepository;

    //@Autowired 
    private Tva tva;



    //@Autowired
    public List<Tva> getAllTvas() {
        return this.tvaRepository.findAll();
    }

    /**
     * retourne une liste pageable de Tva prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Tva> findAllTvaPage(int p, int s) {
        return tvaRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfTvaByIdTva(Long idTva) {
//        Tva c = tvaRepository.getOne(idTva);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Tva findTvaById(long idTva) {
        return tvaRepository.getOne(idTva);
    }

    //@Autowired
    public void saveOrUpdateTva(
            Tva tva,//from web view fields
            Tva tv) {//from original in DB
        if (tva == null && tv != null) {//cas d'update "direct" à partir de la base originale tv 
            tvaRepository.save(tv);
        } else if (tv == null) {//cas de creation : tv est inexistant car il est à créer par saisies nouvelles
            tvaRepository.save(tva);
        } else {//cas d'update
            this.tva = tv;//update Tva(libelleTva, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            tv.setDateEnVigueur(tva.getDateEnVigueur());
            tv.setValeur(tva.getValeur());
            
            tv.setFactureCollection(tva.getFactureCollection());
            
            tv.setIdTva(tva.getIdTva());
          
            tv.setCssRawColorCode(tva.getCssRawColorCode());
            tv.setDatePersistence(tva.getDatePersistence());            
            tv.setUpdatedVersionDate(tva.getUpdatedVersionDate());
           
        }
    }

    //@Autowired
    public void deleteTva(Tva tv) {
        this.tva = tv;
        tvaRepository.delete(tva);
    }

//    public Page<Tva> findTvaByIdUtilisateurPage(int p, int s, long idUtilisateur) {
//        Pageable pageable = new PageRequest(p, s);
//        //List<Tva> liste = (List<Tva>) tvaRepository.findByIdUtilisateur(utilisateurService.findUtilisateurById(idUtilisateur));
//        List<Tva> liste = (List<Tva>) tvaRepository.findByUtilis(utilisateurService.findUtilisateurById(idUtilisateur));
////Page<Module> pagem = cursusRepository.findModuleCollectionByIdCursus(idCursus, pageable);       
//        Page<Tva> pagem = new PageImpl<>(liste, pageable, liste.size());
//        return pagem;
//    }

}


