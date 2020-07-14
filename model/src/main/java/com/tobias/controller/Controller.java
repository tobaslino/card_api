package com.tobias.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.tobias.entity.*;

@ApplicationScoped
public class Controller {
    
    public User update(Long id, User user) {
        User userEntity = User.findById(id);

        if (userEntity == null) {
            throw new WebApplicationException("User with id " + id 
                + " does not exist.", Response.Status.NOT_FOUND);
        }
        updateUserFoundWithCurrentData(userEntity, user);
        return userEntity;
    }
    
    private void updateUserFoundWithCurrentData(User founded, User current) {
        // founded.setName(current.getName());
        // founded.setAge(current.getAge());
        // founded.setIdentity(current.getIdentity());
        // founded.setContact(current.getContact());
        // founded.setAddress(current.getAddress());
    }

    public boolean isUserNameNotEmpty(User user) {
        return user.getName().isEmpty();
    }
}