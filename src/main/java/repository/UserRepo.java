package repository;

import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public void insertUser(RegularUser regularUser)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(regularUser);
        em.getTransaction().commit();
        em.close();
    }

    public Users findByName(String username)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("select u from Users  u where u.username= :username", Users.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            System.out.println("No user found by provided username.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public VacationPackage findVacationById(long id)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("select d from VacationPackage  d where d.idVacation= :id", VacationPackage.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            System.out.println("No vacation package was found by provided id.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
    public List<VacationPackage> getAllAvailable()
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List <VacationPackage> vacationPackageList;
        try {
             vacationPackageList = em.createQuery("select v from VacationPackage v ", VacationPackage.class).getResultList();
            vacationPackageList.removeIf(vacation -> vacation.getStatusVacation().equals(StatusVacation.BOOKED.toString()));
            return  vacationPackageList;
        }
        catch (NoResultException e)
        {
            System.out.println("No vacation package was found by provided id.");
        }

        em.getTransaction().commit();
        em.close();
        return null;
    }

    public  <T> JTable create(List<T> genericList, DefaultTableModel defaultTableModel,JTable jTable) throws  NoSuchFieldException {
        String[] columnsTable = new String[genericList.get(0).getClass().getDeclaredFields().length];
        int i = 0;
        for (Field field : genericList.get(0).getClass().getDeclaredFields()) {
            columnsTable[i] = field.getName();
            if (columnsTable[i].equals("destination")) {
                Field destinationField = Destination.class.getDeclaredField("destinationName");
                columnsTable[i] = destinationField.getName();
            }
            if (columnsTable[i].equals("users")) {
                Field destinationField = Users.class.getDeclaredField("username");
                columnsTable[i] = destinationField.getName();
            }
            i++;

        }
        defaultTableModel.setColumnIdentifiers(columnsTable);
        for (Object currObj : genericList) {
                VacationPackage vacationPackage = (VacationPackage) currObj;
                Object[] rowObj = new Object[i];
                rowObj[0]= vacationPackage.getIdVacation();
                rowObj[1]= vacationPackage.getDestination().getDestinationName();
                rowObj[2]=vacationPackage.getStartPeriod();
                rowObj[3]=vacationPackage.getEndPeriod();
                rowObj[4]=vacationPackage.getNrPeopleAllowed();
                rowObj[5]=vacationPackage.getPrice();
                rowObj[6]= vacationPackage.getStatusVacation();
                List<String> userNames= new ArrayList<>();
            for (Users user: vacationPackage.getUsers()) {
                    userNames.add(user.getUsername());
            }
                rowObj[7]=userNames;
                defaultTableModel.addRow(rowObj);

        }
        jTable=new JTable(defaultTableModel);
        return  jTable;
    }
    public JTable showTable(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel, JTable jTable) throws  NoSuchFieldException {
        JTable vacationPackagesTable;
        vacationPackagesTable=create(vacationPackagesList,defaultTableModel,jTable);
        return  vacationPackagesTable;
    }

    public Users fidnUserById(long id)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("select d from Users  d where d.id= :id", Users.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            System.out.println("No vacation package was found by provided id.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public JTable insertVacationForUser(long idUser,long idVacation,DefaultTableModel defaultTableModel, JTable jTable) throws  NoSuchFieldException {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Users user= this.fidnUserById(idUser);
        List<Users> usersList= new ArrayList<>();
        usersList.add(user);
        VacationPackage vacationPackage=this.findVacationById(idVacation);
        VacationPackage newVacation= new VacationPackage(vacationPackage.getEndPeriod(),vacationPackage.getNrPeopleAllowed(),vacationPackage.getStartPeriod(), vacationPackage.getDestination(), vacationPackage.getStatusVacation(),usersList);
        user.addVacationtoUser(vacationPackage);
        JTable vacationPackagesTable;
        vacationPackagesTable=create(user.getVacations(),defaultTableModel,jTable);
        vacationPackage.setNrPeopleAllowed(vacationPackage.getNrPeopleAllowed()-1 );
        em.merge(newVacation);
        em.getTransaction().commit();
        em.close();
        return  vacationPackagesTable;
    }
    public List<VacationPackage> searchByprice(long price)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List <VacationPackage> vacationPackageList;
        try {
            vacationPackageList = em.createQuery("select v from VacationPackage v where v.price > : price", VacationPackage.class)
                    .setParameter("price",price)
                    .getResultList();
            return  vacationPackageList;
        }
        catch (NoResultException e)
        {
            System.out.println("No vacation package was found by provided id.");
        }

        em.getTransaction().commit();
        em.close();
        return null;
    }
    public List<VacationPackage> searchByDestination(String name)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List <VacationPackage> vacationPackageList;
        try {
            vacationPackageList = em.createQuery("select v from VacationPackage v where v.destination.destinationName = : name", VacationPackage.class)
                    .setParameter("name",name)
                    .getResultList();
            return  vacationPackageList;
        }
        catch (NoResultException e)
        {
            System.out.println("No vacation package was found by provided id.");
        }

        em.getTransaction().commit();
        em.close();
        return null;
    }


}
