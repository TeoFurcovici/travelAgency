package service;

import model.RegularUser;
import model.Users;
import model.VacationPackage;
import repository.UserRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UserService {
    private UserRepo userRepo;

    public UserService() {

        this.userRepo = new UserRepo();
    }
    public void createUser(RegularUser regularUser)
    {
        if(regularUser.getUsername()!=null && !regularUser.getUsername().isEmpty())
        {
            userRepo.insertUser(regularUser);
        }
        else
        {
            System.out.println("Cannot insert user in db");
        }
    }
    public Users getUserById(long id){
        if(id > 0)
        {
            Users users =userRepo.fidnUserById(id);
            if(users !=null)
            {
                System.out.println(users.toString());
                return users;
            }
            else return  null;


        }
        else
        {
            System.out.println("Cannot insert user in db");
            return null;
        }
    }
    public Users getUserByUsername(String username){
        if(username!=null && !username.isEmpty())
        {
             Users users =userRepo.findByName(username);
             if(users !=null)
             {
                 System.out.println(users.toString());
                 return users;
             }
             else return  null;


        }
        else
        {
            System.out.println("Cannot insert user in db");
            return null;
        }
    }
    public JTable viewVacationPackage(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel, JTable jTable) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        if(!vacationPackagesList.isEmpty()  )
        {
            return
                    userRepo.showTable(vacationPackagesList,defaultTableModel,jTable);

        }
        else
        {
            System.out.println("The vacation packages cannot be viewed");
            return null;
        }
    }

    public List<VacationPackage> getAll()  {
        return userRepo.getAllAvailable();
    }
    public List<VacationPackage> searchByprice(long price)  {
        if(price> 0 )
        {
            return userRepo.searchByprice(price);

        }
        else
        {
            System.out.println("The price must be a positive value");
            return null;
        }
    }
    public List<VacationPackage> searchByDestination(String name)  {
        if(name!=null )
        {
            return userRepo.searchByDestination(name);

        }
        else
        {
            System.out.println("The price must be a positive value");
            return null;
        }
    }
    public JTable insertVacationForUser(long idUser,long idVacation,DefaultTableModel defaultTableModel, JTable jTable) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
       return userRepo.insertVacationForUser(idUser,idVacation,defaultTableModel,jTable);
    }

}
