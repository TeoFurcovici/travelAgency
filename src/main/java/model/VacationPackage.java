package model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VacationPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idVacation", unique = true, nullable = false)
    private long idVacation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idDestination")
    private Destination destination;
    @Column
    private LocalDate startPeriod;
    @Column
    private LocalDate endPeriod;
    @Column
    private int nrPeopleAllowed;
    @Column
    private long price;
    @Column
    private String statusVacation;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="idVacation")
    private List<Users> users=new ArrayList<Users>();

    public void setPrice(long price) {
        this.price = price;
    }

    public long getIdVacation() {
        return idVacation;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    public LocalDate getEndPeriod() {
        return endPeriod;
    }

    public int getNrPeopleAllowed() {
        return nrPeopleAllowed;
    }

    public String getStatusVacation() {
        return statusVacation;
    }

    public void setEndPeriod(LocalDate endPeriod) {
        this.endPeriod = endPeriod;
    }

    public void setNrPeopleAllowed(int nrPeopleAllowed) {
        this.nrPeopleAllowed = nrPeopleAllowed;
    }

    public void setStatusVacation(String statusVacation) {
        this.statusVacation = statusVacation;
    }




    public long getPrice() {
        return price;
    }



    public VacationPackage(LocalDate endDate, int nrPeopleAllowed, LocalDate startDate, Destination destination, String statusVacation,long price) {
        this.destination = destination;
        this.startPeriod = startDate;
        this.endPeriod = endDate;
        this.nrPeopleAllowed = nrPeopleAllowed;
        this.statusVacation = statusVacation;
        this.price=price;
    }

    public Destination getDestination() {
        return destination;
    }

    public VacationPackage() {
    }

    @Override
    public String toString() {
        return "VacationPackage{" +
                "idVacation=" + idVacation +
                //", destination=" + destination +
                ", startPeriod=" + startPeriod +
                ", endPeriod=" + endPeriod +
                ", nrPeopleAllowed=" + nrPeopleAllowed +
                ", statusVacation='" + statusVacation + '\'' +
                '}';
    }
}
