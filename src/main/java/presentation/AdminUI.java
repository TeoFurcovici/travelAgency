package presentation;

import controller.AdminController;
import model.Destination;
import model.StatusVacation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class AdminUI {
    AdminController adminController;
    DefaultTableModel defaultTableModel=new DefaultTableModel();
    JTable jTable;
    private JLabel destinationNameLabel=new JLabel("Destination Name:");
    private JLabel startDateLabel=new JLabel("Start Date:");
    private JLabel endDateLabel=new JLabel("End Date:");
    private JLabel nrPeopleAllowedLabel=new JLabel("People Allowed:");
    private JLabel idVacationLabel=new JLabel("Id Vacation:");
    private JLabel statusVacationLabel=new JLabel("Status Vacation:");
    private JLabel countryNameLabel=new JLabel("Country:");
    private JLabel priceNameLabel=new JLabel("Price:");
    private JTextField destinationNameText=new JTextField();
    private JTextField startDateText=new JTextField();
    private JTextField endDateText=new JTextField();
    private JTextField statusVacationText=new JTextField();
    private JTextField nrPeopleAllowedText=new JTextField();
    private JTextField idVacationText=new JTextField();
    private JTextField countryNameText=new JTextField();
    private JTextField priceNameText=new JTextField();
    private JButton createDestinationButtn=new JButton("Insert Destination");
    private JButton deleteDestinationButton=new JButton("Delete Destination");
    private JButton insertVacationPackage=new JButton("Insert Vacation");
    private JButton deleteVacationPackage=new JButton("Delete Vacation");
    private JButton editVacationPackage=new JButton("Edit Vacation");
    private JButton viewVacationPackage=new JButton("View Vacations");

    public AdminUI() {
        this.adminController=new AdminController();
        JFrame frame = new JFrame();

        frame.setSize(750,700);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setBackground(Color.decode("#ccd9ff"));
        panel.setLayout(null);
        frame.add(panel);

        destinationNameLabel.setBounds(7,7,120,35);
        startDateLabel.setBounds(277,7,120,35);
        endDateLabel.setBounds(277,47,120,35);
        nrPeopleAllowedLabel.setBounds(277,87,120,35);
        idVacationLabel.setBounds(277,127,120,35);
        statusVacationLabel.setBounds(277,167,120,35);
        priceNameLabel.setBounds(277,207,120,35);
        nrPeopleAllowedText.setBounds(370,87,120,25);
        statusVacationText.setBounds(370,167,120,25);
        priceNameText.setBounds(370,207,120,25);
        idVacationText.setBounds(370,127,120,25);
        destinationNameText.setBounds(120,7,120,25);
        startDateText.setBounds(370,7,120,25);
        endDateText.setBounds(370,47,120,25);

        countryNameLabel.setBounds(7,47,120,35);
        countryNameText.setBounds(70,47,120,25);

        createDestinationButtn.setBounds(10,87,140,30);
        deleteDestinationButton.setBounds(10,127,140,30);
        insertVacationPackage.setBounds(10,167,140,30);
        deleteVacationPackage.setBounds(10,207,140,30);
        editVacationPackage.setBounds(10,247,140,30);
        viewVacationPackage.setBounds(10,287,140,30);

        panel.add(destinationNameLabel);
        panel.add(priceNameLabel);
        panel.add(priceNameText);
        panel.add(startDateLabel);
        panel.add(endDateLabel);
        panel.add(nrPeopleAllowedLabel);
        panel.add(destinationNameText);
        panel.add(startDateText);
        panel.add(endDateText);
        panel.add(nrPeopleAllowedText);
        panel.add(countryNameLabel);
        panel.add(statusVacationLabel);
        panel.add(statusVacationText);
        panel.add(countryNameText);
        panel.add(idVacationLabel);
        panel.add(idVacationText);
        panel.add(createDestinationButtn);
        panel.add(deleteDestinationButton);
        panel.add(insertVacationPackage);
        panel.add(deleteVacationPackage);
        panel.add(editVacationPackage);
        panel.add(viewVacationPackage);
        insertDestinationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminController.createDestination(destinationNameText.getText(),countryNameText.getText());
            }
        });
        deleteDestinationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminController.deleteDestination(destinationNameText.getText());
            }
        });
        insertVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate startDate= LocalDate.parse(startDateText.getText());
                LocalDate endDate= LocalDate.parse(endDateText.getText());

                adminController.insertVacationPackage(endDate,Integer.parseInt(nrPeopleAllowedText.getText()),startDate,destinationNameText.getText(), statusVacationText.getText(),Long.parseLong(priceNameText.getText()));
            }
        });
        deleteVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminController.deleteVacationPackage(Long.parseLong(idVacationText.getText()));
            }
        });
        editVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate endDate= LocalDate.parse(endDateText.getText());
                adminController.editVacationPackage(endDate,Integer.parseInt(nrPeopleAllowedText.getText()),statusVacationText.getText(),Long.parseLong(idVacationText.getText()));
            }
        });
        viewVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JScrollPane myScrollPane = new JScrollPane();
                    myScrollPane.setBounds(7, 325, 600, 250);
                    defaultTableModel= new DefaultTableModel(){
                        @Override
                        public boolean isCellEditable(int row, int column)
                        {
                            return  false;
                        }
                    };
                    jTable= new JTable();
                    JTable table=adminController.viewVacationPackage(adminController.getAll(),defaultTableModel,jTable);
                    jTable.setVisible(true);
                    jTable.setEnabled(true);
                    myScrollPane.setViewportView(table);
                    panel.add(myScrollPane);
                } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
    public void insertDestinationActionListener(final ActionListener actionListener)
    {
        createDestinationButtn.addActionListener(actionListener);
    }
    public void deleteDestinationActionListener(final ActionListener actionListener)
    {
        deleteDestinationButton.addActionListener(actionListener);
    }
    public void insertVacationActionListener(final ActionListener actionListener)
    {
        insertVacationPackage.addActionListener(actionListener);
    }
    public void deleteVacationActionListener(final ActionListener actionListener)
    {
        deleteVacationPackage.addActionListener(actionListener);
    }
    public void editVacationActionListener(final ActionListener actionListener)
    {
        editVacationPackage.addActionListener(actionListener);
    }
    public void viewVacationActionListener(final ActionListener actionListener)
    {
        viewVacationPackage.addActionListener(actionListener);
    }
}
