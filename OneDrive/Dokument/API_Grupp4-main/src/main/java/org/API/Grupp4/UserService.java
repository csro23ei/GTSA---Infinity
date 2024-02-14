package org.API.Grupp4;


import java.util.List;
import java.util.Objects;

import org.API.Grupp4.Security.PasswordEncrypt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class UserService {

    @Inject
    EntityManager em;

    public List<User> findAllUsers() {
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();

        return users;

    }

    public User findByUsername(String userName) {
        String jpql = "SELECT u FROM User u WHERE u.userName = :userName";
        return em.createQuery(jpql, User.class)
                .setParameter("userName", userName)
                .getSingleResult();

    }

    public boolean authenticateUser(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
    
        
        User user = findByUsername(username);
        if (user == null) {
            return false; 
        }
    
        
        return user.getPassword().equals(password);
    }


    @Transactional(Transactional.TxType.REQUIRED)
    public User createUser(User user) {
        User oldUser = null;

        for (User u : findAllUsers()) {
            if (u.getUserName().equals(user.getUserName())) {
                oldUser = u;
            }
        }

        if (oldUser == null) {
            String PSW = PasswordEncrypt.encryptPassword(user.getPassword());
            System.out.println(PSW);
            user.setPassword(PSW);
            em.persist(user);

            return user;
        } else {
            return null;

        }

    }
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteUser(User user) {
    User managedUser = em.find(User.class, user.getUuid()); 
    if (managedUser != null) {
        em.remove(managedUser);
    }
}
}