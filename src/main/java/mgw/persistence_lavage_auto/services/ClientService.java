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
import mgw.domaine_lavage_auto.entities.Client;
import mgw.persistence_lavage_auto.repository.ClientRepository;
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
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    //@Autowired 
    private Client client;

    //@Autowired
    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    /**
     * retourne une liste pageable de Client prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
    //@Autowired
    public Page<Client> findAllClientPage(int p, int s) {
        return clientRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
    }

//    //@Autowired
//    public List<Module> findAllModulesOfClientByIdClient(Long idClient) {
//        Client c = clientRepository.getOne(idClient);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Client findClientById(long idClient) {
        return clientRepository.getOne(idClient);
    }

    //@Autowired
    public void saveOrUpdateClient(
            Client client,//from web view fields
            Client cli) {//from original in DB
        if (client == null && cli != null) {//cas d'update "direct" à partir de la base originale cli 
            clientRepository.saveAndFlush(cli);
        } else if (cli == null) {//cas de creation : cli est inexistant car il est à créer par saisies nouvelles
            clientRepository.saveAndFlush(client);
        } else {//cas d'update
            this.client = cli;//update Client(libelleClient, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
            cli.setCodePostale(client.getCodePostale());
            cli.setCssRawColorCode(client.getCssRawColorCode());
            cli.setComplement(client.getComplement());
            cli.setIdClient(client.getIdClient());
            cli.setDatePersistence(client.getDatePersistence());
            cli.setNumVoieTypeVoieLibelleVoie(client.getNumVoieTypeVoieLibelleVoie());
            cli.setLoginEmail(client.getLoginEmail());
            cli.setNom(client.getNom());
            cli.setPassword(client.getPassword());
            cli.setPays(client.getPays());
            cli.setPrenom(client.getPrenom());
            cli.setStatut(client.getStatut());
            cli.setTelFixe(client.getTelFixe());
            cli.setTelMobile(client.getTelMobile());
            cli.setVille(client.getVille());

        }
    }

    //@Autowired
    public void deleteClient(Client cli) {
        this.client = cli;
        clientRepository.delete(client);
    }

//    public Page<Client> findClientByIdUtilisateurPage(int p, int s, long idUtilisateur) {
//        Pageable pageable = new PageRequest(p, s);
//        //List<Client> liste = (List<Client>) clientRepository.findByIdUtilisateur(utilisateurService.findUtilisateurById(idUtilisateur));
//        List<Client> liste = (List<Client>) clientRepository.findByUtilis(utilisateurService.findUtilisateurById(idUtilisateur));
////Page<Module> pagem = cursusRepository.findModuleCollectionByIdCursus(idCursus, pageable);       
//        Page<Client> pagem = new PageImpl<>(liste, pageable, liste.size());
//        return pagem;
//    }


    public List<Client> findClientByLoginEmailAndPassword(String email, String password) {
        return clientRepository.findClientByLoginEmailAndPassword(email,password);
    }

}
