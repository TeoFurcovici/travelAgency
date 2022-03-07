package repository;

import model.Admin;
import model.Destination;
import model.VacationPackage;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

public class AdminRepo {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public void insertAdmin(Admin admin)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
        em.close();
    }
    public void insertDestination(Destination destination)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteDestination(String destination)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE from Destination d where d.destinationName= : destination");
        query.setParameter("destination",destination);
        int deleted= query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    public Destination findDestinationByName(String name)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("select d from Destination  d where d.destinationName= :name", Destination.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            System.out.println("No destination found by provided username.");
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
    public List<VacationPackage> getAll()
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            return em.createQuery("select v from VacationPackage v ", VacationPackage.class).getResultList();
        }
        catch (NoResultException e)
        {
            System.out.println("No vacation package was found by provided id.");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }
    public void insertVacationPackage(LocalDate endDate, int nrPeopleAllowed, LocalDate startDate, String destination, String statusVacation,long price)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Destination selectedDest= this.findDestinationByName(destination);
        VacationPackage vacationPackage= new VacationPackage(endDate,nrPeopleAllowed,startDate,selectedDest,statusVacation,price);
        em.persist(vacationPackage);
        List<VacationPackage> vacationPackageList= getAll();
        for (VacationPackage vacationPackage1:vacationPackageList) {
            selectedDest.addVacationPackage(vacationPackage1);
        }
        em.getTransaction().commit();
        selectedDest.printVacations();
        em.close();
    }
    public void deleteVacationPackage(long idVacation)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vacationPackage= findVacationById(idVacation);
        Destination destination=this.findDestinationByName(vacationPackage.getDestination().getDestinationName());
        Query query = em.createQuery("DELETE from VacationPackage d where d.idVacation= : idVacation");
        query.setParameter("idVacation",idVacation);
        int deleted= query.executeUpdate();
        List<VacationPackage> vacationPackageList= getAll();
        destination.setVacationPackages(vacationPackageList);
        em.getTransaction().commit();
        em.close();
        destination.printVacations();
    }
    public void editVacationPackage(LocalDate endDate, int nrPeopleAllowed, String statusVacation,long idVacation)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        VacationPackage selectedVacationPackage= findVacationById(idVacation);
        selectedVacationPackage.setEndPeriod(endDate);
        selectedVacationPackage.setNrPeopleAllowed(nrPeopleAllowed);
        selectedVacationPackage.setStatusVacation(statusVacation);
        em.merge(selectedVacationPackage);
        em.getTransaction().commit();
        em.close();
    }
    public  <T> JTable create(List<T> genericList, DefaultTableModel defaultTableModel,JTable jTable) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        String[] columnsTable=new String[genericList.get(0).getClass().getDeclaredFields().length];
        int i=0;
        for(Field field:genericList.get(0).getClass().getDeclaredFields())
        {
            columnsTable[i]= field.getName();
            if(columnsTable[i].equals("destination"))
            {
                Field destinationField= Destination.class.getDeclaredField("destinationName");
                columnsTable[i]=destinationField.getName();
            }
            i++;

        }
        defaultTableModel.setColumnIdentifiers(columnsTable);
        for(Object currObj:genericList)
        {
            VacationPackage vacationPackage = (VacationPackage) currObj;
            Object[] rowObj=new Object[i];
            rowObj[0]= vacationPackage.getIdVacation();
            rowObj[1]= vacationPackage.getDestination().getDestinationName();
            rowObj[2]=vacationPackage.getStartPeriod();
            rowObj[3]=vacationPackage.getEndPeriod();
            rowObj[4]=vacationPackage.getNrPeopleAllowed();
            rowObj[5]=vacationPackage.getPrice();
            rowObj[6]= vacationPackage.getStatusVacation();
            rowObj[7]=vacationPackage.getUsers();
            defaultTableModel.addRow(rowObj);

        }

//        for (Object currObject:genericList) {
//            Vector<Object> genericListObject=new Vector<>();
//            genericListObject.setSize(genericList.get(0).getClass().getDeclaredFields().length);
//            int currColumnToAddTo=0;
//            for (Field field:currObject.getClass().getDeclaredFields()) {
//                field.setAccessible(true);
//                if(field.getName().equals("destination")) {
//                    for (Field field1 : Destination.class.getDeclaredFields()) {
//                        field1.setAccessible(true);
//                        if (field1.getName().equals("destinationName")) {
//                            System.out.println(currObject.getClass().getSimpleName());
//                           // genericListObject.set(currColumnToAddTo,field1.get(currObject.getClass().getSuperclass()));
//                        }
//                    }
//                }
//                    genericListObject.set(currColumnToAddTo,field.get(currObject));
//                currColumnToAddTo++;
//            }
        //}
        jTable=new JTable(defaultTableModel);
        return  jTable;
    }
    public JTable showTable(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel,JTable jTable) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        JTable vacationPackagesTable;
        vacationPackagesTable=create(vacationPackagesList,defaultTableModel,jTable);
        return  vacationPackagesTable;
    }


}
