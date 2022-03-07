package model;

import javax.persistence.Entity;

@Entity
public class Admin extends Users{
    public Admin(String id, String firstName, String lastName, String username, String password) {
        super( firstName, lastName, username, password);
    }

    public Admin() {

    }
}
