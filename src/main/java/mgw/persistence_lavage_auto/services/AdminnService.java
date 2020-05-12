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


import mgw.domaine_lavage_auto.entities.Adminn;
import mgw.persistence_lavage_auto.repository.AdminnRepository;
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
public class AdminnService {

    @Autowired
    private AdminnRepository adminRepository;

    //@Autowired 
    private Adminn admin;



    //@Autowired
    public List<Adminn> getAllAdminns() {
        return this.adminRepository.findAll();
    }

    /**
     * retourne une liste pageable de Adminn prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Adminn> findAllAdminnPage(int p, int s) {
        return adminRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfAdminnByIdAdminn(Long idAdminn) {
//        Adminn c = adminRepository.getOne(idAdminn);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Adminn findAdminnById(long idAdminn) {
        return adminRepository.getOne(idAdminn);
    }

    //@Autowired  
    public void saveOrUpdateAdminn(
            Adminn admin,//from web view fields
            Adminn adm) {//from original in DB
        if (admin == null && adm != null) {//cas d'update "direct" à partir de la base originale adm 
            adminRepository.saveAndFlush(adm);
        } else if (adm == null) {//cas de creation : adm est inexistant car il est à créer par saisies nouvelles
            adminRepository.saveAndFlush(admin);
        } else {//cas d'update
            this.admin = adm;//update Adminn(libelleAdminn, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            adm.setCodePostale(admin.getCodePostale());
            adm.setCssRawColorCode(admin.getCssRawColorCode());
            adm.setComplement(admin.getComplement());
            adm.setIdAdminn(admin.getIdAdminn());
            adm.setDatePersistence(admin.getDatePersistence());
            adm.setNumVoieTypeVoieLibelleVoie(admin.getNumVoieTypeVoieLibelleVoie());            
            adm.setLoginEmail(admin.getLoginEmail());
            adm.setNom(admin.getNom());
            adm.setPassword(admin.getPassword());
            adm.setPays(admin.getPays());
            adm.setPrenom(admin.getPrenom());
            adm.setStatut(admin.getStatut());
            adm.setTelFixe(admin.getTelFixe());
            adm.setTelMobile(admin.getTelMobile());            
            adm.setVille(admin.getVille());
            
        }        
    }

    //@Autowired
    public void deleteAdminn(Adminn adm) {
        this.admin = adm;
        adminRepository.delete(admin);
    }


    
        //@Autowired
    public List<Adminn> findAdminnByLoginEmailAndPassword(String email, String password) {
        return adminRepository.findAdminnByLoginEmailAndPassword(email,password);
    }


}
