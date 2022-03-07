package controller;

import model.RegularUser;
import model.Users;
import model.VacationPackage;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class UserController {
    private UserService userService;

    public UserController( ) {
        this.userService = new UserService();
    }
    public void createUser(String id, String firstName, String lastName, String username, String password)
    {
        RegularUser regularUser = new RegularUser(firstName,lastName,username,password);
        userService.createUser(regularUser);
    }

    public Users getByUsername(String username)
    {
        return userService.getUserByUsername(username);
    }
    public Users getbyId(long id)
    {
        return userService.getUserById(id);
    }
    public JTable viewVacationPackage(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel, JTable jTable) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        return userService.viewVacationPackage(vacationPackagesList,defaultTableModel,jTable);
    }

    public List<VacationPackage> getAll()  {
        return userService.getAll();
    }
    public JTable insertVacationForUser(long idUser,long idVacation,DefaultTableModel defaultTableModel, JTable jTable) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
       return userService.insertVacationForUser(idUser,idVacation,defaultTableModel,jTable);
    }
    public List<VacationPackage> searchByprice(long price)  {
        return userService.searchByprice(price);
    }
    public List<VacationPackage> searchByDestination(String name)  {
        return userService.searchByDestination(name);
    }

}
