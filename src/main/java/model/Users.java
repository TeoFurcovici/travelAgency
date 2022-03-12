package model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique =true, nullable = false)
    private String username;
    @Column(unique =true, nullable = false)
    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<VacationPackage> vacations= new ArrayList<VacationPackage>();

    public List<VacationPackage> getVacations() {
        return vacations;
    }

    public String getPassword() {
        return password;
    }

    public Users() {}

    public Users(String firstName, String lastName, String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password=password;

    }
    public void addVacationtoUser(VacationPackage vacationPackage)
    {
        vacations.add(vacationPackage);
    }
    public void removeVacationtoUser(VacationPackage vacationPackage)
    {
        vacations.remove(vacationPackage);
    }

    public String getFirstName() {
        return firstName;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vacations=" + vacations +
                '}';
    }
}