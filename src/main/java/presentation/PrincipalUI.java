package presentation;

import controller.AdminController;
import controller.UserController;
import model.Users;
import service.RegisterLogInValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

public class PrincipalUI {
    private AdminUI adminUI;
    UserController userController;
    AdminController adminController;
    RegisterLogInValidator registerLogInValidator;
    private RegularUserUI regularUserUI;
    private final JButton adminButton=new JButton("Admin");
    JLabel labelImage = new JLabel("Info?");
    private final JButton regularUserButton=new JButton("Regular User");
    private final JButton registerButton=new JButton("Register");
    private final JButton registerButtonAdmin=new JButton("Register admin");
    private final JButton createAccout=new JButton("Create account");
    private final JButton createAccoutAdmin=new JButton("Create account for admin");
    private final JButton logInButton=new JButton("Log in");
    private final JButton logInButtonAdmin=new JButton("Log in");
    private final JTextField usernameText=new JTextField();
    private final JTextField usernameTextAdmin=new JTextField();
    private final JTextField usernameTextReg=new JTextField();
    private final JTextField usernameTextRegAdmin=new JTextField();
    private final JPasswordField passText=new JPasswordField();
    private final JPasswordField passTextReg=new JPasswordField();
    private final JPasswordField passTextRegAdmin=new JPasswordField();
    private final JTextField firstNameText=new JTextField();
    private final JTextField firstNameTextAdmin=new JTextField();
    private final JTextField lastNametext=new JTextField();
    private final JTextField lastNametextAdmin=new JTextField();



    public String getUsernameText() {
        return usernameText.getText();
    }

    public PrincipalUI() {
        this.userController= new UserController();

        this.registerLogInValidator= new RegisterLogInValidator();
        this.adminController= new AdminController();
        JFrame framePrincipal = new JFrame();
        framePrincipal.setSize(550, 250);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel principalPanel = new JPanel();
        principalPanel.setBackground(Color.decode("#ccd9ff"));
        principalPanel.setLayout(null);
        framePrincipal.add(principalPanel);


        JFrame logInFrame=new JFrame("Log in User");
        logInFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        logInFrame.setSize(300,250);
        JPanel logInPanel=new JPanel();
        logInPanel.setBackground(Color.decode("#ccd9ff"));
        logInPanel.setLayout(null);
        logInFrame.add(logInPanel);

        JFrame logInFrameForAdmin=new JFrame("Log In Admin");
        logInFrameForAdmin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        logInFrameForAdmin.setSize(300,250);
        JPanel logInPanelAdmin=new JPanel();
        logInPanelAdmin.setBackground(Color.decode("#ccd9ff"));
        logInPanelAdmin.setLayout(null);
        logInFrameForAdmin.add(logInPanelAdmin);


        JFrame registerFrame=new JFrame("Register User");
        registerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registerFrame.setSize(300,350);
        JPanel registerPanel=new JPanel();
        registerPanel.setBackground(Color.decode("#ccd9ff"));
        registerPanel.setLayout(null);
        registerFrame.add(registerPanel);

        JFrame registerFrameAdmin=new JFrame("Register Admin");
        registerFrameAdmin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registerFrameAdmin.setSize(300,350);
        JPanel registerPanelAdmin=new JPanel();
        registerPanelAdmin.setBackground(Color.decode("#ccd9ff"));
        registerPanelAdmin.setLayout(null);
        registerFrameAdmin.add(registerPanelAdmin);

        adminButton.setBounds(20,100,140,30);
        regularUserButton.setBounds(200,100,140,30);
        registerButton.setBounds(67,127,140,30);
        registerButtonAdmin.setBounds(67,127,140,30);
        logInButton.setBounds(67,167,140,30);
        logInButtonAdmin.setBounds(67,167,140,30);
        createAccout.setBounds(67,207,140,30);
        createAccoutAdmin.setBounds(67,207,200,30);


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(7,47,120,25);
        usernameText.setBounds(100,47,120,25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(7,87,120,25);
        passText.setBounds(100,87,120,25);
        labelImage.setBounds(200,287,120,25);

        JLabel usernameLabelReg = new JLabel("Username:");
        usernameLabelReg.setBounds(7,47,120,25);
        usernameTextReg.setBounds(100,47,120,25);

        JLabel usernameLabelRegAdmin = new JLabel("Username:");
        usernameLabelRegAdmin.setBounds(7,47,120,25);
        usernameTextRegAdmin.setBounds(100,47,120,25);

        JLabel usernameLabelAdmin = new JLabel("Username:");
        usernameLabelAdmin.setBounds(7,47,120,25);
        usernameTextAdmin.setBounds(100,47,120,25);

        JLabel passLabelReg = new JLabel("Password:");
        passLabelReg.setBounds(7,87,120,25);
        passTextReg.setBounds(100,87,120,25);

        JLabel passLabelRegAdmin = new JLabel("Password:");
        passLabelRegAdmin.setBounds(7,87,120,25);
        passTextRegAdmin.setBounds(100,87,120,25);
        labelImage.setBounds(200,87,120,25);


        JLabel passLabelAdmin = new JLabel("Password:");
        passLabelAdmin.setBounds(7,87,120,25);
        JPasswordField passTextAdmin = new JPasswordField();
        passTextAdmin.setBounds(100,87,120,25);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(7,127,120,25);
        firstNameText.setBounds(100,127,120,25);

        JLabel firstNameLabelAdmin = new JLabel("First Name:");
        firstNameLabelAdmin.setBounds(7,127,120,25);
        firstNameTextAdmin.setBounds(100,127,120,25);


        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(7,167,120,25);
        lastNametext.setBounds(100,167,120,25);

        JLabel lastNameLabelAdmin = new JLabel("Last Name:");
        lastNameLabelAdmin.setBounds(7,167,120,25);
        lastNametextAdmin.setBounds(100,167,120,25);

        principalPanel.add(adminButton);
        principalPanel.add(regularUserButton);
        logInPanel.add(usernameLabel);
        logInPanel.add(usernameText);
        logInPanel.add(passLabel);
        logInPanel.add(passText);
        logInPanel.add(registerButton);
        logInPanel.add(logInButton);

        logInPanelAdmin.add(usernameLabelAdmin);
        logInPanelAdmin.add(usernameTextAdmin);
        logInPanelAdmin.add(passLabelAdmin);
        logInPanelAdmin.add(passTextAdmin);
        logInPanelAdmin.add(registerButtonAdmin);
        logInPanelAdmin.add(logInButtonAdmin);


        registerPanel.add(usernameLabelReg);
        registerPanel.add(usernameTextReg);
        registerPanel.add(passLabelReg);
        registerPanel.add(passTextReg);
        registerPanel.add(firstNameLabel);
        registerPanel.add(firstNameText);
        registerPanel.add(lastNameLabel);
        registerPanel.add(lastNametext);
        registerPanel.add(createAccout);

        registerPanelAdmin.add(usernameLabelRegAdmin);
        registerPanelAdmin.add(usernameTextRegAdmin);
        registerPanelAdmin.add(passLabelRegAdmin);
        registerPanelAdmin.add(passTextRegAdmin);
        //registerPanelAdmin.add(labelImage);
        registerPanelAdmin.add(firstNameLabelAdmin);
        registerPanelAdmin.add(firstNameTextAdmin);
        registerPanelAdmin.add(lastNameLabelAdmin);
        registerPanelAdmin.add(lastNametextAdmin);
        registerPanelAdmin.add(createAccoutAdmin);
        adminButtonActionListener(e -> logInFrameForAdmin.setVisible(true));
        regularUserButtonActionListener(e -> logInFrame.setVisible(true));
        registerButtonActionListener(e -> registerFrame.setVisible(true));
        registerAdminButtonActionListener(e -> registerFrameAdmin.setVisible(true));
        createAccountButtonActionListener(e -> {

            Users currentUser= userController.getByUsername(usernameTextReg.getText());
            System.out.println(usernameTextReg.getText());
            if(registerLogInValidator.isValidUsername(usernameTextReg.getText()) && registerLogInValidator.isValidPass(passTextReg.getText())
            && currentUser==null)
            {
                userController.createUser(UUID.randomUUID().toString(),firstNameText.getText(),lastNametext.getText(),usernameTextReg.getText(),passTextReg.getText());
                firstNameText.setText("");
                lastNametext.setText("");
                usernameTextReg.setText("");
                passTextReg.setText("");
                JOptionPane.showMessageDialog(null,"Thank you! Your account has been succesfully created!");

            }
            else if(currentUser!=null){
                JOptionPane.showMessageDialog(null,"An account with this username already exists");
                usernameTextReg.setText("");
                passTextReg.setText("");

            }
            else if(!registerLogInValidator.isValidUsername(usernameTextReg.getText()))
            {
                JOptionPane.showMessageDialog(null,"The username doesn`t meet the requirements!");
                usernameTextReg.setText("");

            }
            else  if(!registerLogInValidator.isValidPass(passTextReg.getText()))
            {
                JOptionPane.showMessageDialog(null,"The password doesn`t meet the requirements!");
                passTextReg.setText("");
            }
        });
        createAccountForAdminButtonActionListener(e -> {
            if(registerLogInValidator.isValidUsername(usernameTextRegAdmin.getText()) && registerLogInValidator.isValidPass(passTextRegAdmin.getText())) {
                adminController.createAdmin(UUID.randomUUID().toString(), firstNameTextAdmin.getText(), lastNametextAdmin.getText(), usernameTextRegAdmin.getText(), passTextRegAdmin.getText());
                firstNameTextAdmin.setText("");
                lastNametext.setText("");
                usernameTextRegAdmin.setText("");
                passTextRegAdmin.setText("");
            }
                else {
                JOptionPane.showMessageDialog(null,"The password must contain minimum eight characters, at least one uppercase letter, one lowercase letter and one number ","Info Box",JOptionPane.INFORMATION_MESSAGE);
                usernameTextRegAdmin.setText("");
                passTextRegAdmin.setText("");
            }
        });
        labelImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JOptionPane.showMessageDialog(null,"The password must contain minimum eight characters, at least one uppercase letter, one lowercase letter and one number ","Info Box",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        logInButtonActionListener(e -> {
            Users regUser=  userController.getByUsername(usernameText.getText());
            if(regUser==null)
            {
                JOptionPane.showMessageDialog(null,"Please make sure that you have an account!");
            }
            else if(!regUser.getPassword().equals(passText.getText()))
            {
                JOptionPane.showMessageDialog(null,"Your password is incorrect!");

            }
            else
                regularUserUI= new RegularUserUI(regUser);

        });
        logInButtonAdminActionListener(e -> {
            Users admin=  userController.getByUsername(usernameTextAdmin.getText());
            if(admin==null)
            {
                JOptionPane.showMessageDialog(null,"Please make sure that you have an account!");
            }
            else
                adminUI= new AdminUI();
        });
        framePrincipal.setVisible(true);

    }
    /**
     * Admin button action listener.
     *
     * @param actionListener the action listener
     */
    public void adminButtonActionListener(final ActionListener actionListener)
    {
        adminButton.addActionListener(actionListener);
    }

    /**
     * Client button action listener.
     *
     * @param actionListener the action listener
     */
    public void regularUserButtonActionListener(final ActionListener actionListener)
    {
        regularUserButton.addActionListener(actionListener);
    }
    /**
     * Register button action listener.
     *
     * @param actionListener the action listener
     */
    public void registerButtonActionListener(final ActionListener actionListener)
    {
        registerButton.addActionListener(actionListener);
    }

    public void registerAdminButtonActionListener(final ActionListener actionListener)
    {
        registerButtonAdmin.addActionListener(actionListener);
    }
    public void createAccountButtonActionListener(final ActionListener actionListener)
    {
        createAccout.addActionListener(actionListener);
    }
    public void createAccountForAdminButtonActionListener(final ActionListener actionListener)
    {
        createAccoutAdmin.addActionListener(actionListener);
    }
    public void logInButtonActionListener(final ActionListener actionListener)
    {
        logInButton.addActionListener(actionListener);
    }
    public void logInButtonAdminActionListener(final ActionListener actionListener)
    {
        logInButtonAdmin.addActionListener(actionListener);
    }
}
