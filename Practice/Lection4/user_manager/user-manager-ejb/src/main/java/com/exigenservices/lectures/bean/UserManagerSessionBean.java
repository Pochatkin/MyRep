package com.exigenservices.lectures.bean;

import com.exigenservices.lectures.api.UserManager;
import com.exigenservices.lectures.api.exception.UserManagementException;
import com.exigenservices.lectures.converter.UserTOConverter;
import com.exigenservices.lectures.domain.User;
import com.exigenservices.lectures.model.UserTO;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * UserManager bean implementation
 *
 * Created by bolbin on 23.11.2014.
 */
@Remote(UserManager.class)
@Stateless(mappedName = UserManager.BEAN_NAME)
public class UserManagerSessionBean implements UserManager {

    @PersistenceContext(unitName = "userManager")
    private EntityManager entityManager;


    @Override
    public UserTO getUserById(Long id) throws UserManagementException {
        User user = entityManager.find(User.class, id);
        if(user == null){
            throw new UserManagementException("User with id=" + id + " is not exist");
        }

        return new UserTOConverter().convert(user);
    }

    @Override
    public List<UserTO> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from USER u", User.class);
        return new UserTOConverter().convertAll(query.getResultList());
    }

    @Override
    public Long saveUser(UserTO userTO) throws UserManagementException {
        if(userTO == null){
            throw  new UserManagementException("User can not be saved");
        }

        User user = null;
        if(userTO.getId() == null) {
            //add new user
            user = new User();
            user.setName(userTO.getName());
            user.setDateOfBirth(userTO.getDateOfBirth());
            entityManager.persist(user);
        } else {
            //edit current user
            user = entityManager.find(User.class, userTO.getId());
            if(user == null) {
                throw  new UserManagementException("User with id = " + userTO.getId() + " is not exist");
            }
            user.setName(userTO.getName());
            user.setDateOfBirth(userTO.getDateOfBirth());
            entityManager.merge(user);
        }

        return user.getId();
    }
}
