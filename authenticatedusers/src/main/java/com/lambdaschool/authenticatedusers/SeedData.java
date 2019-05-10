package com.lambdaschool.authenticatedusers;

import com.lambdaschool.authenticatedusers.model.ToDos;
import com.lambdaschool.authenticatedusers.model.Role;
import com.lambdaschool.authenticatedusers.model.User;
import com.lambdaschool.authenticatedusers.model.UserRoles;
import com.lambdaschool.authenticatedusers.repository.RoleRepository;
import com.lambdaschool.authenticatedusers.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        rolerepos.save(r1);
        rolerepos.save(r2);
        User u1 = new User("gw", "password", users);
        u1.getToDos().add(new ToDos("Finish java-orders-swagger", u1));
        u1.getToDos().add(new ToDos("Feed the turtles", u1));
        u1.getToDos().add(new ToDos("Complete the sprint challenge", u1));

        User u2 = new User("admin", "password", admins);
        u2.getToDos().add(new ToDos("Walk the dogs", u2));
        u2.getToDos().add(new ToDos("The question isn't who is going to let me; it's who is going to stop me.", u2));

        userrepos.save(u1);
        userrepos.save(u2);
    }
}
