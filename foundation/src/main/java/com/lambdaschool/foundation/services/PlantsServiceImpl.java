package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.Plants;
import com.lambdaschool.foundation.repository.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the plantService Interface
 */
@Transactional
@Service(value = "plantsService")
public class PlantsServiceImpl implements PlantsService
{
    /**
     * Connects this service to the plants model
     */
    @Autowired
    private PlantsRepository plantsRepository;

    /**
     * Connects this service to the User Service
     */
    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<Plants> findAll() {
        List<Plants> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        plantsRepository.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Plants findPlantsById(long id) {
        return plantsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant with id " + id + " Not Found!"));

    }

    @Transactional
    @Override
    public void delete(long id) {
        if (plantsRepository.findById(id).isPresent())
      {
          if (helperFunctions.isAuthorizedToMakeChange(plantsRepository.findById(id)
                  .get().getUser().getUsername())){
              plantsRepository.deleteById(id);
          }
          else
          {
              throw new ResourceNotFoundException("Plant with id " + id + " Not Found!");
          }
      }
    }

    @Transactional
    @Override
    public Plants update(Plants plants, long id) {

        Plants currentPlant = plantsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant id " + id + " not found!"));

        if (plants.getNickname() != null)
        {
            currentPlant.setNickname(plants.getNickname());
        }

        if (plants.getSpecies() != null)
        {
            currentPlant.setSpecies(plants.getSpecies());
        }

        if (plants.getFrequency() != null)
        {
            currentPlant.setFrequency(plants.getFrequency());
        }

        if (plants.getDays() != null)
        {
            currentPlant.setDays(plants.getDays());
        }

        if (plants.getImage() != null)
        {
            currentPlant.setImage(plants.getImage());
        }

        if (plants.getDescription() != null)
        {
            currentPlant.setDescription(plants.getDescription());
        }

        if (plants.getDatePlanted() != null)
        {
            currentPlant.setDatePlanted(plants.getDatePlanted());
        }

        if (plants.getCareInstructions() != null)
        {
            currentPlant.setCareInstructions(plants.getCareInstructions());
        }

        return plantsRepository.save(currentPlant);
    }

    @Transactional
    @Override
    public Plants save(Plants plants) {
        Plants newPlant = new Plants();

        if (plants.getPlantid() != 0)
        {
            newPlant = plantsRepository.findById(plants.getPlantid())
                    .orElseThrow(() -> new ResourceNotFoundException("Plant id " + plants.getPlantid() + " not found!"));
        }

        newPlant.setNickname(plants.getNickname());
        newPlant.setSpecies(plants.getSpecies());
        newPlant.setFrequency(plants.getFrequency());
        newPlant.setDays(plants.getDays());
        newPlant.setImage(plants.getImage());
        newPlant.setDescription(plants.getDescription());
        newPlant.setDatePlanted(plants.getDatePlanted());
        newPlant.setCareInstructions(plants.getCareInstructions());
        newPlant.setUser(plants.getUser());

        return plantsRepository.save(newPlant);
    }




//
//    @Transactional
//    @Override
//    public Useremail update(
//        long useremailid,
//        String emailaddress)
//    {
//        if (useremailrepos.findById(useremailid)
//            .isPresent())
//        {
//            if (helperFunctions.isAuthorizedToMakeChange(useremailrepos.findById(useremailid)
//                .get()
//                .getUser()
//                .getUsername()))
//            {
//                Useremail useremail = findUseremailById(useremailid);
//                useremail.setUseremail(emailaddress.toLowerCase());
//                return useremailrepos.save(useremail);
//            } else
//            {
//                // note we should never get to this line but is needed for the compiler
//                // to recognize that this exception can be thrown
//                throw new ResourceNotFoundException("This user is not authorized to make change");
//            }
//        } else
//        {
//            throw new ResourceNotFoundException("Useremail with id " + useremailid + " Not Found!");
//        }
//    }
//
//    @Transactional
//    @Override
//    public Useremail save(Useremail useremail, userid)
//    {
//        User currentUser = userService.findUserById(userid);
//
//        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
//        {
//            Useremail newUserEmail = new Useremail(currentUser,
//                emailaddress);
//            return useremailrepos.save(newUserEmail);
//        } else
//        {
//            // note we should never get to this line but is needed for the compiler
//            // to recognize that this exception can be thrown
//            throw new ResourceNotFoundException("This user is not authorized to make change");
//        }
//    }
}
