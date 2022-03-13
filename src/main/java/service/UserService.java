package service;

import model.RegularUser;
import model.Users;
import model.VacationPackage;
import repository.UserRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
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
    public JTable viewVacationPackage(List<VacationPackage> vacationPackagesList, DefaultTableModel defaultTableModel, JTable jTable) throws  NoSuchFieldException {
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
    public List<VacationPackage> searchByEndDate(LocalDate date)  {
        if(date!=null )
        {
            return userRepo.searchByEndDate(date);

        }
        else
        {
            System.out.println("The end date must be valid");
            return null;
        }
    }
    public JTable insertVacationForUser(long idUser,long idVacation,DefaultTableModel defaultTableModel, JTable jTable) throws  NoSuchFieldException {
       return userRepo.insertVacationForUser(idUser,idVacation,defaultTableModel,jTable);
    }

}
