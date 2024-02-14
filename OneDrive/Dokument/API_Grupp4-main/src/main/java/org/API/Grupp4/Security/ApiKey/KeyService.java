package org.API.Grupp4.Security.ApiKey;

import java.util.List;
import java.util.UUID;

import org.API.Grupp4.User;
import org.API.Grupp4.UserService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class KeyService {

    @Inject
    EntityManager em;
    UserService userService;

    public List<Key> getAllKeys() {
        return em.createQuery("SELECT k FROM Key k", Key.class).getResultList();
    }

    public List<Key> getYouKeys(UUID owner) {

        return em.createQuery("SELECT k FROM Key k WHERE k.owner = :owner", Key.class)
                .setParameter("owner", owner)
                .getResultList();

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Key createkey(User owner) {
        Key key = new Key(owner);
        em.persist(key);
        return key;

    }

    public String findKey(String apiKey) {
        Key key = em.find(Key.class, apiKey);
        return key.getKey();

    }

}
