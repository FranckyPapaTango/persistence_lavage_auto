package mgw.persistence_lavage_auto.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import mgw.domaine_lavage_auto.entities.Message;
import mgw.domaine_lavage_auto.entities.Utilisateur;
import mgw.domaine_lavage_auto.tools.ModuleTools;
import mgw.persistence_lavage_auto.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class MessageService {

//    @Autowired
//    private InMemoryChatRepository inMemoryChatRepository;
    @Autowired
    private MessageRepository messageRepository;

    private Message message;

//    //@Autowired
//    public List<String> getAllMessages(int index) {
////        return this.inMemoryChatRepository.findAll();
//        return this.inMemoryChatRepository.getMessages(index);
//    }
//
//    public void addMessage(String message){
//		this.inMemoryChatRepository.addMessage(message);
//	}
    /**
     * retourne une liste pageable de Message prête à la pagination
     *
     * @param p
     * @param s
     * @return
     */
//    //@Autowired
//    public Page<Message> findAllMessagePage(int p, int s) {
//        return inMemoryChatRepository.findAll(new PageRequest(p, s));//<=(int page, int size)				
//    }
//    //@Autowired
//    public List<Module> findAllModulesOfMessageByIdMessage(Long idMessage) {
//        Message c = inMemoryChatRepository.findOne(idMessage);
//        return (List<Module>) c.getModuleCollection();
//    }
    //@Autowired
    public Message findMessageById(long idMessage) {
        return messageRepository.findOne(idMessage);
    }

//    @Autowired
//    @Transactional
    public Message saveOrUpdateMessage(
            Message message,//from web view fields
            Message mess) {//from original in DB
        if (mess == null) {//cas de creation : mess est inexistant car il est à créer par saisies nouvelles
            this.message = messageRepository.saveAndFlush(message);
        } else {
            if (message == null && mess != null) {//cas d'update "direct" à partir de la base originale mess 

//            message.setDateSent(message.getDateSent());//<=champ rendu invisible et date system auto injecté en valeur :
//            Date maDate = ModuleTools.asDateHMS(LocalDateTime.now());
//            System.out.println("DateSent="+maDate);
//            mess.setDateSent(maDate);
//            messageRepository.saveAndFlush(mess);
//            this.message = messageRepository.saveAndFlush(mess);
                //cas d'update
                //update Message(libelleMessage, annee, dateDeb, dateFin, detail, paypalId, prix1, prix2, moduleCollection, tjSInscrireCollection);
//            message.setDateSent(message.getDateSent());//<=champ rendu invisible et date system auto injecté en valeur :
                Date maDate = ModuleTools.asDateHMS(LocalDateTime.now());
//            System.out.println("DateSent="+maDate);
                mess.setDateSent(maDate);
                mess.setDateSent(maDate);
                mess.setMessageContent(message.getMessageContent());
                mess.setSeenBy(message.getSeenBy());// OPTIONNEL
                mess.setPathPj(message.getPathPj());//optionnel
//                mess.setIdDestinataire(0)//fait en front

                /**
                 * *****************************************************
                 *
                 */
// LES ATTRIBUTS QUI SUIVENT SONT REMPLIS ET GERES PAR LA SUPER CLASSE "AbstractEntity.java":            
//            mess.setDatePersistence(message.getDatePersistence());            
//            mess.setCssRawColorCode(message.getCssRawColorCode());
//            mess.setUpdatedVersionDate(message.getUpdatedVersionDate());
//            this.saveAndFlushOperationnel(message);
                this.message = messageRepository.saveAndFlush(mess);
            }

        }
        messageRepository.flush();
        System.out.println("idMessage flushd = " + this.message.getMessageId());
        return this.message;
    }

    // SAVE AND FLUSH OPERATIONNEL:
//    public void saveAndFlushOperationnel(Message m) {
//        Session session = getSessionFactory().getCurrentSession();
//        session.setFlushMode(FlushMode.COMMIT);
//        Transaction tx = session.beginTransaction();
//        messageRepository.saveAndFlush(m);
//        messageRepository.flush();
//        session.flush();       
//
//        tx.commit();
////        this.message=messageRepository.flush()
//    }
//    //@Autowired
//    public void deleteMessage(Message mess) {
//        this.message = mess;
//        inMemoryChatRepository.delete(message);
//    }
    public List<Message> findAll() {
        return this.messageRepository.findAll();
    }
    
    public List<Message> findAllMessagesOfThisUser(Long utilisateurId){
    return this.messageRepository.findAllMessagesOfThisUser(utilisateurId);
    }

    public List<Message> findMessageByDestinataire(Long expediteurId, Long utilisateurId) {
        List<Message> list = this.messageRepository.findMessageByDestinataire(utilisateurId, expediteurId);
        return list;
    }
    
//        public List<Message> findByUtilisateurId(Utilisateurr utilisateurId) {
//        return this.messageRepository.findOne(utilisateurId);
//    }

    public List<Message> findByUtilisateurId(Utilisateur utilisateurId) {
        return this.messageRepository.findByUtilisateurId(utilisateurId);
    }
    

    public List<Message> findMessageOfUtilisateurByKeyWord(Long utilisateurId, String search) {
//        Pageable pageable = new PageRequest(p, s);
        List<Message> liste = (List<Message>) messageRepository.findMessageOfUtilisateurByKeyWord(utilisateurId,search);
//        Page<Message> pagem = new PageImpl<>(liste, pageable, liste.size());
        return liste;
    }
}
