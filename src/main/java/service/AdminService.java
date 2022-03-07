package service;

import model.Admin;
import model.Destination;
import model.StatusVacation;
import model.VacationPackage;
import repository.AdminRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

public class AdminService {
    private AdminRepo adminRepo;

    public AdminService() {
        this.adminRepo=new AdminRepo();
    }

    public void createAdmin(Admin admin)
    {
        if(admin.getUsername()!=null && !admin.getUsername().isEmpty())
        {
            adminRepo.insertAdmin(admin);
        }
        else
        {
            System.out.println("Cannot insert user in db");
        }
    }
    public void createDestination(Destination destination)
    {
        if(destination.getDestinationName()!=null && destination.getCountry()!=null)
        {
            adminRepo.insertDestination(destination);
        }
        else
        {
            System.out.println("Cannot insert destination in db");
        }
    }
    public void deleteDestination(String destination)
    {
        if(destination!=null)
        {
            adminRepo.deleteDestination(destination);

        }
        else
        {
            System.out.println("Cannot delete destination in db");
        }
    }
    public void insertVacationPackage(LocalDate endDate, int nrPeopleAllowed, LocalDate start,  String  destination, String statusVacation,long price)
    {
        if(destination!=null && start!=null && endDate!=null )
        {
            adminRepo.insertVacationPackage(endDate,nrPeopleAllowed,start,destination,statusVacation,price);

        }
        else
        {
            System.out.println("Cannot delete destination in db");
        }
    }
    public void deleteVacationPackage(long idVacation) {
        adminRepo.deleteVacationPackage(idVacation);
    }
    public void editVacationPackage(LocalDate endDate, int nrPeopleAllowed, String statusVacation,long idVacation)
    {
        if(endDate!=null &&  statusVacation!=null )
        {
            adminRepo.editVacationPackage(endDate,nrPeopleAllowed,statusVacation,idVacation);

        }
        else
        {
            System.out.println("Cannot be edited  destination in db");
        }
    }
    public JTable viewVacationPackage(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel, JTable jTable) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        if(!vacationPackagesList.isEmpty()  )
        {
           return
                   adminRepo.showTable(vacationPackagesList,defaultTableModel,jTable);

        }
        else
        {
            System.out.println("The vacation packages cannot be viewed");
            return null;
        }
    }
    public List<VacationPackage> getAll()  {
        return adminRepo.getAll();
    }
}
