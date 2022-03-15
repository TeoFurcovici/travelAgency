package presentation;

import controller.UserController;
import model.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;


public class RegularUserUI {
    String[] optionsSearch={"Price" , "Destination","End Date"};
    private final JTextField idVacationText=new JTextField();
    JLabel labelImage = new JLabel("Info?");
    private final JComboBox<String> jComboBox=new JComboBox<>(optionsSearch);


    private  final JTextField usernameText=new JTextField();
    private  final JButton viewAvailableVacation=new JButton("View Vacations");
    private  final JButton bookVacation=new JButton("Book Vacation");
    private  final JButton searchButton=new JButton("Search");
    private  final JButton viewMyBooking=new JButton("My bookings");
    UserController userController;
    DefaultTableModel defaultTableModel=new DefaultTableModel();
    JTable jTable;

    public RegularUserUI(Users user) {
        this.usernameText.setText(user.getUsername());
        JFrame frame1 = new JFrame();
        userController= new UserController();
        frame1.setSize(750,700);
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel1=new JPanel();
        panel1.setBackground(Color.decode("#ccd9ff"));
        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel usernameLabel = new JLabel("Your username:");
        usernameLabel.setBounds(7,7,120,35);
        usernameText.setBounds(120,7,120,25);


        JLabel idVacationLabel = new JLabel("Id Vacation:");
        idVacationLabel.setBounds(407,7,120,35);
        idVacationText.setBounds(500,7,120,25);
        JLabel searchByLabel = new JLabel("Search by:");
        searchByLabel.setBounds(407,47,200,35);
        labelImage.setBounds(640,47,200,35);
        jComboBox.setBounds(500,47,120,25);

        bookVacation.setBounds(10,47,140,30);
        viewAvailableVacation.setBounds(10,87,140,30);
        searchButton.setBounds(10,127,200,30);
        viewMyBooking.setBounds(10,167,200,30);

        viewAvailableVacationListener(e -> {
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
        });
        labelImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JOptionPane.showMessageDialog(null,"Please enter the desired filter.","Info Box",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        panel1.add(usernameLabel);
        panel1.add(usernameText);
        panel1.add(idVacationLabel);
        panel1.add(labelImage);
        panel1.add(idVacationText);
        panel1.add(searchByLabel);
        panel1.add(jComboBox);
        panel1.add(viewAvailableVacation);
        panel1.add(bookVacation);
        panel1.add(searchButton);
        panel1.add(viewMyBooking);
        bookVacationListener(e -> {
            Users user1= userController.getByUsername(usernameText.getText());
            usernameText.setText(usernameText.getText());
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
                table = userController.insertVacationForUser(user1.getId(),Long.parseLong(idVacationText.getText()),defaultTableModel,jTable);
                JOptionPane.showMessageDialog(null,"Your vacation has been successfully booked!");
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
            jTable.setVisible(true);
            jTable.setEnabled(true);
            myScrollPane.setViewportView(table);
            panel1.add(myScrollPane);

        });
        searchButtonListener(e -> {
            switch (jComboBox.getItemAt(jComboBox.getSelectedIndex())) {
                case "Price": {
                    JLabel l = new JLabel("Enter the price");

                    JPanel p = new JPanel(new GridLayout(1, 2, 10, 10));
                    p.setPreferredSize(new Dimension(400, 50));
                    JTextField t = new JTextField();
                    t.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            try {
                                long price = Long.parseLong(t.getText());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    p.add(t);
                    p.add(l);
                    int option = JOptionPane.showConfirmDialog(null, p, " Price : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == 0) {

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
                    } else {
                        System.out.println("cancel clicked");
                    }


                    break;
                }
                case "Destination": {
                    JLabel l = new JLabel("Enter the destination");

                    JPanel p = new JPanel(new GridLayout(1, 2, 10, 10));
                    p.setPreferredSize(new Dimension(400, 50));
                    JTextField t = new JTextField();
                    p.add(t);
                    p.add(l);
                    int option = JOptionPane.showConfirmDialog(null, p, "Destination : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == 0) {

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
                            if (table == null) {
                                JOptionPane.showMessageDialog(null, "Sorry! This vacation is fully booked! ", "Info Box", JOptionPane.INFORMATION_MESSAGE);

                            }
                        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                            illegalAccessException.printStackTrace();

                        }
                        jTable.setVisible(true);
                        jTable.setEnabled(true);
                        myScrollPane.setViewportView(table);
                        panel1.add(myScrollPane);
                    } else {
                        System.out.println("cancel clicked");
                    }
                    break;
                }
                case "End Date": {
                    JLabel l = new JLabel("Enter the date ");

                    JPanel p = new JPanel(new GridLayout(1, 2, 10, 10));
                    p.setPreferredSize(new Dimension(400, 50));
                    JTextField t = new JTextField();

                    p.add(t);
                    p.add(l);
                    int option = JOptionPane.showConfirmDialog(null, p, " End Date  : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == 0) {

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
                            table = userController.viewVacationPackage(userController.searchByEndDate(LocalDate.parse(t.getText())), defaultTableModel, jTable);
                        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                        jTable.setVisible(true);
                        jTable.setEnabled(true);
                        myScrollPane.setViewportView(table);
                        panel1.add(myScrollPane);
                    } else {
                        System.out.println("cancel clicked");
                    }


                    break;
                }
            }

        });
        viewBookingsActionListener(e -> {
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
                Users currUser= userController.getByUsername(usernameText.getText());
                JTable table = userController.viewVacationPackage(currUser.getVacations(), defaultTableModel, jTable);
                jTable.setVisible(true);
                jTable.setEnabled(true);
                myScrollPane.setViewportView(table);
                panel1.add(myScrollPane);
            }
            catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }        });


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

    public void viewBookingsActionListener(final ActionListener actionListener)
    {
        viewMyBooking.addActionListener(actionListener);
    }
}
