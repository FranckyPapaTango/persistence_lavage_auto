/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mgw.persistence_lavage_auto.repository;

import java.util.List;
import mgw.domaine_lavage_auto.entities.Message;
import mgw.domaine_lavage_auto.entities.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    
    @Query(nativeQuery = true)
    public List<Message> findAllMessagesOfThisUser(@Param("utilisateurId") Long utilisateurId);

    @Query(nativeQuery = true)
    public List<Message> findMessageByDestinataire(@Param("idDestinataire") Long idDestinataire, @Param("utilisateurId") Long utilisateurId);

    
    List<Message> findByUtilisateurId(Utilisateur utilisateurId);
    
    @Query(nativeQuery=true)
    public List<Message> findMessageOfUtilisateurByKeyWord(@Param("utilisateurId") Long utilisateurId, @Param("search") String search);

}

//public interface MessageRepository {
//	List<String> getMessages(int messageIndex);
//	void addMessage(String message);
//
//}
