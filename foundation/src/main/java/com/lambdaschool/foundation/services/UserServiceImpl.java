package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Plants;
import com.lambdaschool.foundation.models.Role;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.UserRoles;
import com.lambdaschool.foundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements UserService Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl
    implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;

    /**
     * Connects this service to the Role table
     */
    @Autowired
    private RoleService roleService;

    @Autowired
    private HelperFunctions helperFunctions;



    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) throws
                                      ResourceNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User save(User user) {
        //when looping through plants set up all the properties, set user as the user you have created
        User newUser = new User();

        if (user.getUserid() != 0) {
            userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPrimaryemail(user.getPrimaryemail());
        newUser.setPhone(user.getPhone());
        newUser.setPassword(user.getPassword());

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles()) {
            Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());
            newUser.getRoles()
                    .add(new UserRoles(newUser,
                            addRole));
        }

        newUser.getPlants().clear();
        for (Plants p : user.getPlants()) {

            Plants newPlant = new Plants();

            newPlant.setNickname(newPlant.getNickname());
            newPlant.setSpecies(newPlant.getSpecies());
            newPlant.setFrequency(newPlant.getFrequency());
            newPlant.setDays(newPlant.getDays());
            newPlant.setImage(newPlant.getImage());
            newPlant.setDescription(newPlant.getDescription());
            newPlant.setDatePlanted(newPlant.getDatePlanted());
            newPlant.setCareInstructions(newPlant.getCareInstructions());
            newPlant.setUser(newUser);

            newUser.getPlants().add(newPlant);
        }
        return userrepos.save(newUser);
    }

    @Override
    public User update(User user, long id) {
        User currentPlant = userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant id " + id + " not found!"));

        if (user.getUsername() != null)
        {
            currentPlant.setUsername(user.getUsername());
        }

        if (user.getFirstName() != null)
        {
            currentPlant.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null)
        {
            currentPlant.setLastName(user.getLastName());
        }

        if (user.getPassword() != null)
        {
            currentPlant.setPassword(user.getPassword());
        }

        if (user.getPrimaryemail() != null)
        {
            currentPlant.setPrimaryemail(user.getPrimaryemail());
        }

        if (user.getPhone() != null)
        {
            currentPlant.setPhone(user.getPhone());
        }

        if (user.getRoles().size() > 0)
        {
            currentPlant.setRoles(user.getRoles());
        }

        if (user.getPlants().size() > 0)
        {
            currentPlant.setPlants(user.getPlants());
        }
        return userrepos.save(currentPlant);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
