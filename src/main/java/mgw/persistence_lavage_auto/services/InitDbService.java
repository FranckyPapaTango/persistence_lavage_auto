package mgw.persistence_lavage_auto.services;



import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgw.domaine_lavage_auto.entities.Adminn;
//import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.tools.Validateur;
import mgw.persistence_lavage_auto.repository.AdminnRepository;
import mgw.persistence_lavage_auto.repository.LaveurRepository;
import java.util.List;
import mgw.domaine_lavage_auto.entities.Laveur;
import mgw.domaine_lavage_auto.entities.Prestation;
import mgw.persistence_lavage_auto.repository.PrestationRepository;

@Transactional
@Service
public class InitDbService { 

//    @Autowired
//    private AdminnRepository adminRepository;
    @Autowired
    private PrestationRepository prestationRepository;

    //@PostConstruct
    public void init() throws ParseException {

        List<Prestation> lp = prestationRepository.findAll();
        if (lp.isEmpty()) {    
        
        Prestation p1=new Prestation(1L, "PAYP-D56YH", "Lavage Basique", "lavage extérieur : carosserie, jante, pare-choc, rétroviseurs (produit pro sans eau)", new BigDecimal(18.5) );
        Prestation p2=new Prestation(2L, "PAYP-TY78GTDS", "Lavage Complet", "lavage intégrale Extérieur et intérieur (par aspiration pro, éponge-chiffon de désinfection)", new BigDecimal(29.5));
        
//        lp.add(p1);
//        lp.add(p2);
//        
//        prestationRepository.save(lp);


prestationRepository.deleteAll();
//prestationRepository.truncateTablePrestation();
  
prestationRepository.save(p1);
prestationRepository.save(p2);


        
//        List<Adminn> lc = adminRepository.findAll();
//        if (lc.isEmpty()) {

//           Collection<Laveur> laveurCollection = new ArrayList<>();
//            Laveur m1 = new Laveur(1,"TANGUY", "Pierre","Micro Entrepreneur","pierretanguy@gmail.com","jl4t1","0614665424","76 Boulevard","Magenta","75014","Paris");
//            Laveur m2 = new Laveur(2,"CHARLES", "Ludovic","Micro Entrepreneur","ludoviccharles@gmail.com","jl4t2","0614665424","6 Rue","Paul Eluard","75011","Paris");
//            Laveur m3 = new Laveur(3,"MALLET", "Jean","Micro Entrepreneur","jeanmallet@gmail.com","jl4t3","0614665424","7 Avenue","Jean Jaurès","75008","Paris");
//            Laveur m4 = new Laveur(4,"BENALA", "Gerard","Micro Entrepreneur","gererdbenala@gmail.com","jl4t4","0614665424","25 Impasse","Feuillants","75012","Paris");
//            Laveur m5 = new Laveur(5,"POLOZZY", "Marcel","Micro Entrepreneur","marcelpolozzi@gmail.com","jl4t5","0614665424","19 Rond Point","Fernandel","77050","Marne-La-Vallée");
//            Laveur m6 = new Laveur(6,"GIMENEZ", "Jacob","Micro Entrepreneur","jacobgimenez@gmail.com","jl4t6","0614665424"," 26 Square","Cerruti","92220","Bagneux");
//            
//            laveurCollection.add(m1);
//            laveurCollection.add(m2);
//            laveurCollection.add(m3);
//            laveurCollection.add(m4);
//            laveurCollection.add(m5);
//            laveurCollection.add(m6);            
//            laveurRepository.save(laveurCollection);// <= évite de faire un save pour chaque élément lorsqu'une collection contient les éléments
            
//Adminn c1 = new Adminn(1, "RAFARALAHY","François","sous admin","rafaralahyf@gmail.com","root1A@","0647378978","46 Avenue","MARNE","92120","MONTROUGE");
//Adminn c2 = new Adminn(2, "RAFARALAHY","François","sous admin","rafaralahyf@gmail.com","root1A@","0647378978","46 Avenue","MARNE","92120","MONTROUGE");

          
            /*List<Adminn> admins = new ArrayList<Adminn>();
    admins.add(c1);
    admins.add(c2);
    admins.add(c3);
    admins.add(c4);
    this.adminRepository.save(admins);*/

/*
            //=========EQUIVALENT SANS CREATION D'UNE LISTE : ===========================
            adminRepository.save(c1);
//            adminRepository.save(c2);
//            adminRepository.save(c3);
//            adminRepository.save(c4);
 */       }
    }

}

