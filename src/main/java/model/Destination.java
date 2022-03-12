package model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Destination {
    @Id
    @GeneratedValue
    @Column(name = "idDestination", unique = true, nullable = false)
    private long idDestination;
    @Column(name = "destinationName")
    private String destinationName;
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "destination",  cascade = CascadeType.ALL)
    private List<VacationPackage> vacationPackages=new ArrayList<>();

    public List<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    public void setVacationPackages(List<VacationPackage> vacationPackages) {
        this.vacationPackages = vacationPackages;
    }

    public void printVacations( )
    {
        for (VacationPackage vacation:vacationPackages) {
            System.out.println(vacation.toString());
        }
    }
    public void addVacationPackage(VacationPackage vacationPackage)
    {
        vacationPackages.add(vacationPackage);
    }
    public void removeVacationPackage(VacationPackage vacationPackage)
    {
        vacationPackages.remove(vacationPackage);
    }
    public String getDestinationName() {
        return destinationName;
    }

    public String getCountry() {
        return country;
    }

    public Destination() {

    }

    public Destination(String destinationName, String country) {
        this.destinationName = destinationName;
        this.country = country;

    }

    public long getIdDestination() {
        return idDestination;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destinationName='" + destinationName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
