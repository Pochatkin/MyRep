package com.exigenservices.lectures.api;

import com.exigenservices.lectures.api.exception.UserManagementException;
import com.exigenservices.lectures.model.UserTO;

import java.util.List;

/**
 * Interface for User Manager Bean
 * Created by bolbin on 23.11.2014.
 */
public interface UserManager {

    String BEAN_NAME = "UserManagerSessionBean";

    /**
     * Returns user by id
     * @param id user id
     * @return UserTO item
     * @throws com.exigenservices.lectures.api.exception.UserManagementException when user is not found
     */
    UserTO getUserById(Long id) throws UserManagementException;

    /**
     * Returns list of all users
     * @return List of UserTO items
     */
    List<UserTO> getAllUsers();


    /**
     * Creates or updates user
     * @param user user to save
     * @return id of created or modified user
     * @throws com.exigenservices.lectures.api.exception.UserManagementException when user can not be saved
     */
    Long saveUser(UserTO user) throws UserManagementException;
}
