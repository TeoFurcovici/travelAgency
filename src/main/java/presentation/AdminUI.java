package presentation;

import controller.AdminController;
import service.RegisterLogInValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AdminUI {
    AdminController adminController;
    DefaultTableModel defaultTableModel=new DefaultTableModel();
    JTable jTable;
    RegisterLogInValidator registerLogInValidator;
    private final JTextField destinationNameText=new JTextField();
    private final JTextField startDateText=new JTextField();
    private final JTextField endDateText=new JTextField();
    private final JTextField statusVacationText=new JTextField();
    private final JTextField nrPeopleAllowedText=new JTextField();
    private final JTextField idVacationText=new JTextField();
    private final JTextField countryNameText=new JTextField();
    private final JTextField priceNameText=new JTextField();
    private final JButton createDestinationButtn=new JButton("Insert Destination");
    private final JButton deleteDestinationButton=new JButton("Delete Destination");
    private final JButton insertVacationPackage=new JButton("Insert Vacation");
    private final JButton deleteVacationPackage=new JButton("Delete Vacation");
    private final JButton editVacationPackage=new JButton("Edit Vacation");
    private final JButton viewVacationPackage=new JButton("View Vacations");

    public AdminUI() {
        this.adminController=new AdminController();
        this.registerLogInValidator=new RegisterLogInValidator();
        JFrame frame = new JFrame();

        frame.setSize(670,620);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setBackground(Color.decode("#ccd9ff"));
        panel.setLayout(null);
        frame.add(panel);

        JLabel destinationNameLabel = new JLabel("Destination Name:");
        destinationNameLabel.setBounds(7,7,120,35);
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(277,7,120,35);
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(277,47,120,35);
        JLabel nrPeopleAllowedLabel = new JLabel("People Allowed:");
        nrPeopleAllowedLabel.setBounds(277,87,120,35);
        JLabel idVacationLabel = new JLabel("Id Vacation:");
        idVacationLabel.setBounds(277,127,120,35);
        JLabel statusVacationLabel = new JLabel("Status Vacation:");
        statusVacationLabel.setBounds(277,167,120,35);
        JLabel priceNameLabel = new JLabel("Price:");
        priceNameLabel.setBounds(277,207,120,35);
        nrPeopleAllowedText.setBounds(370,87,120,25);
        statusVacationText.setBounds(370,167,120,25);
        priceNameText.setBounds(370,207,120,25);
        idVacationText.setBounds(370,127,120,25);
        destinationNameText.setBounds(120,7,120,25);
        startDateText.setBounds(370,7,120,25);
        endDateText.setBounds(370,47,120,25);

        JLabel countryNameLabel = new JLabel("Country:");
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
        insertDestinationActionListener(e -> adminController.createDestination(destinationNameText.getText(),countryNameText.getText()));
        deleteDestinationActionListener(e -> adminController.deleteDestination(destinationNameText.getText()));
        insertVacationActionListener(e -> {
            LocalDate startDate= LocalDate.parse(startDateText.getText());
            LocalDate endDate= LocalDate.parse(endDateText.getText());
            if(registerLogInValidator.isValidNr(priceNameText.getText()) && registerLogInValidator.isValidDate(startDateText.getText())
            && registerLogInValidator.isValidDate(endDateText.getText()) && registerLogInValidator.endDateAfterStartDate(endDate,startDate)
            && registerLogInValidator.isValidNr(nrPeopleAllowedText.getText())) {
                adminController.insertVacationPackage(endDate, Integer.parseInt(nrPeopleAllowedText.getText()), startDate, destinationNameText.getText(), statusVacationText.getText(), Long.parseLong(priceNameText.getText()));
            }
            else if(!registerLogInValidator.isValidNr(priceNameText.getText()))
            {
                JOptionPane.showMessageDialog(null,"Plese enter a valid number for price","Info Box",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(!registerLogInValidator.endDateAfterStartDate(endDate,startDate) ||
            !registerLogInValidator.isValidDate(endDateText.getText()) || !registerLogInValidator.isValidDate(startDateText.getText()))
            {
                JOptionPane.showMessageDialog(null,"The date isn`t valid!","Info Box",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(!registerLogInValidator.isValidNr(nrPeopleAllowedText.getText()))
            {
                JOptionPane.showMessageDialog(null,"Plese enter a valid number for the number of people ","Info Box",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        deleteVacationActionListener(e -> adminController.deleteVacationPackage(Long.parseLong(idVacationText.getText())));
        editVacationActionListener(e -> {
            LocalDate endDate;
            int peopleAllowed;
            long price;
            try {
                endDate= LocalDate.parse(endDateText.getText());
            } catch (DateTimeParseException ex) {
                endDate = null;
            }
            try {
                peopleAllowed = Integer.parseInt(nrPeopleAllowedText.getText());
            } catch (NumberFormatException ex) {
                peopleAllowed = -1;
            }
            try {
                price = Long.parseLong(priceNameText.getText());
            } catch (NumberFormatException ex) {
                price = -1;
            }

            adminController.editVacationPackage(endDate,peopleAllowed,statusVacationText.getText(),Long.parseLong(idVacationText.getText()),price);
        });
        viewVacationActionListener(e -> {
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
