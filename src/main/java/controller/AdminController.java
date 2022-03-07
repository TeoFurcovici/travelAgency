package controller;

import model.Admin;
import model.Destination;
import model.StatusVacation;
import model.VacationPackage;
import service.AdminService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;


public class AdminController {
    private AdminService adminService;

    public AdminController( ) {
        this.adminService = new AdminService();
    }

    public void createAdmin(String id, String firstName, String lastName, String username, String password)
    {
        Admin admin = new Admin(id,firstName,lastName,username,password);
        adminService.createAdmin(admin);
    }
    public void createDestination(String name, String country)
    {
        Destination destination = new Destination(name,country);
        adminService.createDestination(destination);
    }
    public void deleteDestination(String destination)
    {
        adminService.deleteDestination(destination);
    }

    public void insertVacationPackage(LocalDate endDate,int nrPeopleAllowed, LocalDate start,  String destination, String statusVacation, long price)
    {
        adminService.insertVacationPackage(endDate,nrPeopleAllowed,start,destination,statusVacation,price);
    }
    public void deleteVacationPackage(long idVacation)
    {
        adminService.deleteVacationPackage(idVacation);
    }
    public void editVacationPackage(LocalDate endDate,int nrPeopleAllowed,String statusVacation,long idVacation)
    {
        adminService.editVacationPackage(endDate,nrPeopleAllowed,statusVacation,idVacation);
    }
    public JTable viewVacationPackage(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel, JTable jTable) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        return adminService.viewVacationPackage(vacationPackagesList,defaultTableModel,jTable);
    }
    public List<VacationPackage> getAll()  {
        return adminService.getAll();
    }
}
