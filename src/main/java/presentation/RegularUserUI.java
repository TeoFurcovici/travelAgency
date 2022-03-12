package presentation;

import controller.UserController;
import model.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;


public class RegularUserUI {
    private JLabel usernameLabel=new JLabel("Your username:");
    private JLabel idVacationLabel=new JLabel("Id Vacation:");
    private JLabel searchByLabel=new JLabel("Search by:");
    private JTextField idVacationText=new JTextField();
    JLabel labelImage = new JLabel("Info?");

    private JTextField usernameText=new JTextField();
    private JTextField searchBytText=new JTextField();
    private JButton viewAvailableVacation=new JButton("View Vacations");
    private JButton bookVacation=new JButton("Book Vacation");
    private JButton searchButton=new JButton("Search");
    UserController userController;
    DefaultTableModel defaultTableModel=new DefaultTableModel();
    JTable jTable;

    public RegularUserUI() {
        JFrame frame1 = new JFrame();
        userController= new UserController();
        frame1.setSize(750,700);
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel1=new JPanel();
        panel1.setBackground(Color.decode("#ccd9ff"));
        panel1.setLayout(null);
        frame1.add(panel1);

        usernameLabel.setBounds(7,7,120,35);
        usernameText.setBounds(120,7,120,25);


        idVacationLabel.setBounds(407,7,120,35);
        idVacationText.setBounds(500,7,120,25);
        searchByLabel.setBounds(407,47,200,35);
        labelImage.setBounds(640,47,200,35);
        searchBytText.setBounds(500,47,120,25);

        bookVacation.setBounds(10,47,140,30);
        viewAvailableVacation.setBounds(10,87,140,30);
        searchButton.setBounds(10,127,200,30);

        viewAvailableVacationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JScrollPane myScrollPane = new JScrollPane();
                    myScrollPane.setBounds(7, 325, 600, 250);
                    defaultTableModel = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    jTable = new JTable();
                    JTable table = userController.viewVacationPackage(userController.getAll(), defaultTableModel, jTable);
                    jTable.setVisible(true);
                    jTable.setEnabled(true);
                    myScrollPane.setViewportView(table);
                    panel1.add(myScrollPane);
                }
                catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
        labelImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JOptionPane.showMessageDialog(null,"In this box you should introduce the type of filter you want.","Info Box",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        panel1.add(usernameLabel);
        panel1.add(usernameText);
        panel1.add(idVacationLabel);
        panel1.add(labelImage);
        panel1.add(idVacationText);
        panel1.add(searchByLabel);
        panel1.add(searchBytText);
        panel1.add(viewAvailableVacation);
        panel1.add(bookVacation);
        panel1.add(searchButton);
        bookVacationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users user= userController.getByUsername(usernameText.getText());
                JScrollPane myScrollPane = new JScrollPane();
                myScrollPane.setBounds(7, 325, 600, 250);
                defaultTableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable = new JTable();
                JTable table = null;
                try {
                    table = userController.insertVacationForUser(user.getId(),Long.parseLong(idVacationText.getText()),defaultTableModel,jTable);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }
                jTable.setVisible(true);
                jTable.setEnabled(true);
                myScrollPane.setViewportView(table);
                panel1.add(myScrollPane);

            }
        });
        searchButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchBytText.getText().equals("price") || searchBytText.getText().equals("Price") )
                {
                    JLabel l=new JLabel("Enter the price");

                    JPanel p=new JPanel(new GridLayout(1, 2, 10, 10));
                    p.setPreferredSize(new Dimension(400, 50));
                    JTextField t=new JTextField();
                    t.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            try{
                                long price=Long.parseLong(t.getText());
                            }catch(Exception ex){
                                 ex.printStackTrace();
                            }
                        }
                    });
                    p.add(t);
                    p.add(l);
                    int option = JOptionPane.showConfirmDialog(null,p,"JOptionPane Example : ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(option==0){

                        JScrollPane myScrollPane = new JScrollPane();
                        myScrollPane.setBounds(7, 325, 600, 250);
                        defaultTableModel = new DefaultTableModel() {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        jTable = new JTable();
                        JTable table = null;
                        try {
                            table = userController.viewVacationPackage(userController.searchByprice(Long.parseLong(t.getText())), defaultTableModel, jTable);
                        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                        jTable.setVisible(true);
                        jTable.setEnabled(true);
                        myScrollPane.setViewportView(table);
                        panel1.add(myScrollPane);
                    }else{
                        System.out.println("cancel clicked");
                    }


                }
                else if(searchBytText.getText().equals("destination") || searchBytText.getText().equals("Destination"))
                {
                    JLabel l=new JLabel("Enter the destination");

                    JPanel p=new JPanel(new GridLayout(1, 2, 10, 10));
                    p.setPreferredSize(new Dimension(400, 50));
                    JTextField t=new JTextField();
                    t.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            try{
                                long price=Long.parseLong(t.getText());
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    });
                    p.add(t);
                    p.add(l);
                    int option = JOptionPane.showConfirmDialog(null,p,"JOptionPane Example : ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(option==0){

                        JScrollPane myScrollPane = new JScrollPane();
                        myScrollPane.setBounds(7, 325, 600, 250);
                        defaultTableModel = new DefaultTableModel() {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        jTable = new JTable();
                        JTable table = null;
                        try {
                            table = userController.viewVacationPackage((userController.searchByDestination(t.getText())), defaultTableModel, jTable);
                            if(table==null)
                            {
                                JOptionPane.showMessageDialog(null,"Sorry! This vacation is fully booked! ","Info Box",JOptionPane.INFORMATION_MESSAGE);

                            }
                        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                            illegalAccessException.printStackTrace();

                        }
                        jTable.setVisible(true);
                        jTable.setEnabled(true);
                        myScrollPane.setViewportView(table);
                        panel1.add(myScrollPane);
                    }else{
                        System.out.println("cancel clicked");
                    }
                }

            }
        });

        frame1.setVisible(true);
    }


    public void searchButtonListener(final ActionListener actionListener)
    {
        searchButton.addActionListener(actionListener);
    }

    public void viewAvailableVacationListener(final ActionListener actionListener)
    {
        viewAvailableVacation.addActionListener(actionListener);
    }
    public void bookVacationListener(final ActionListener actionListener)
    {
        bookVacation.addActionListener(actionListener);
    }

}
