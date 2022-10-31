package com.csse.backend.UserAndOrderManagement.repositories.impl;

import com.csse.backend.UserAndOrderManagement.domains.User;
import com.csse.backend.UserAndOrderManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * Get a user by its identification
     *
     * @param id - User identification
     * @return User
     */
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

}
