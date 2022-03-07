package model;

import javax.persistence.Entity;

@Entity
public class RegularUser extends Users {



    public RegularUser(String firstName, String lastName, String username, String password) {
        super( firstName, lastName, username, password);
    }

    public RegularUser() {

    }


}
