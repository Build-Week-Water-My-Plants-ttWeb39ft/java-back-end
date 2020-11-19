package com.lambdaschool.foundation;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.foundation.models.Plants;
import com.lambdaschool.foundation.models.Role;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.UserRoles;
import com.lambdaschool.foundation.services.PlantsService;
import com.lambdaschool.foundation.services.RoleService;
import com.lambdaschool.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    PlantsService plantsService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        //admin

        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local", "The", "Boss", "123-456-789");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1 = userService.save(u1);
        Plants p1 = new Plants("Test plant", "Unknown", "Monthly", "M W F", "image goes here", "this plant isn't real", "11/17/2020", "I am the boss", u1);
        Plants p2 = new Plants("Another Test plant", "Unknown", "Monthly", "M W F", "image goes here", "this plant isn't real", "11/17/2020", "I am the boss", u1);
        //u1.getPlants().add(p1);


        plantsService.save(p1);
        plantsService.save(p2);

        // user

        User u2 = new User("testuser",
            "test123",
            "TestUser@lambdaschool.local", "John", "Johnson", "987-654-321");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));

        u2 = userService.save(u2);

        Plants p3 = new Plants("My First Plant", "Ficus", "biweekly", "Thursdays", "image goes here", "My first plant", "11/17/2020", "put me in the sun", u2);
        Plants p4 = new Plants("Worst Christmas Present", "No Idea What it is", "Daily", "Everyday", "image goes here", "Christmas present 2019", "11/17/2020", "Over water me till I die", u2 );
        Plants p5 = new Plants("My Favorite Plant", "Pine Tree", "Monthly", "Friday", "image goes here", "I have had this plant since I was a child", "01/24/02", "Handle with care", u2 );

        plantsService.save(p3);
        plantsService.save(p4);
        plantsService.save(p5);



//        if (false)
//        {
//            // using JavaFaker create a bunch of regular users
//            // https://www.baeldung.com/java-faker
//            // https://www.baeldung.com/regular-expressions-java
//
//            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                new RandomService());
//            Faker nameFaker = new Faker(new Locale("en-US"));
//
//            for (int i = 0; i < 25; i++)
//            {
//                new User();
//                User fakeUser;
//
//                fakeUser = new User(nameFaker.name()
//                    .username(),
//                    "password",
//                    nameFaker.internet()
//                        .emailAddress());
//                fakeUser.getRoles()
//                    .add(new UserRoles(fakeUser,
//                        r2));
//                fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                        fakeValuesService.bothify("????##@gmail.com")));
//                userService.save(fakeUser);
//            }
//        }
    }
}