package Interface;

import Entities.Persoana.Agent;
import Interface.HomeUI.HomeUI;
import Services.LoginServices;
import Utils.QueryMessage;
import Entities.Persoana.User;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI
{
    private final int FRAME_WIDTH = 330;
    private final int FRAME_HEIGHT = 210;

    private JFrame mainFrame;
    private JPanel mainPanel;

    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelResult;

    private JTextField fieldUsername;
    private JPasswordField fieldPassword;

    private JButton buttonSignIn;
    private JButton buttonSignUp;

    public static void main(String[] args)
    {
        LoginUI test = new LoginUI();
        test.displayInterface();
    }

    public void displayInterface()
    {
        changeLookAndFeel();

        mainFrame = new JFrame();
        mainFrame.setTitle("Login");
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainFrame.add(mainPanel);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        mainPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        labelUsername = new JLabel("Username");
        mainPanel.add(labelUsername);
        labelUsername.setLayout(null);
        labelUsername.setVisible(true);
        labelUsername.setBounds(15, 20, 70, 25);

        fieldUsername = new JTextField(10);
        mainPanel.add(fieldUsername);
        fieldUsername.setLayout(null);
        fieldUsername.setVisible(true);
        fieldUsername.setBounds(90, 20, 200, 25);

        labelPassword = new JLabel("Password");
        mainPanel.add(labelPassword);
        labelPassword.setLayout(null);
        labelPassword.setVisible(true);
        labelPassword.setBounds(15, 50, 70, 25);

        fieldPassword = new JPasswordField(10);
        mainPanel.add(fieldPassword);
        fieldPassword.setLayout(null);
        fieldPassword.setVisible(true);
        fieldPassword.setBounds(90, 50, 200, 25);

        buttonSignIn = new JButton("Sign in");
        mainPanel.add(buttonSignIn);
        buttonSignIn.setLayout(null);
        buttonSignIn.setVisible(true);
        buttonSignIn.setBounds(90, 90, 100, 30);

        buttonSignIn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                User user = new User();
                user.setNameUser(fieldUsername.getText());
                user.setPassUser(String.valueOf(fieldPassword.getPassword()));

                trySignIn(user);
            }
        });

        buttonSignUp = new JButton("Sign up");
        mainPanel.add(buttonSignUp);
        buttonSignUp.setLayout(null);
        buttonSignUp.setVisible(true);
        buttonSignUp.setBounds(190, 90, 100, 30);
        buttonSignUp.setEnabled(false);

        labelResult = new JLabel("", JLabel.CENTER);
        mainPanel.add(labelResult);
        labelResult.setLayout(null);
        labelResult.setVisible(false);
        labelResult.setBounds(90, 130, 200, 25);
    }

    private void trySignIn(User user)
    {
        LoginServices loginServices = new LoginServices();
        Pair<Agent, QueryMessage> agentQueryMessagePair = loginServices.signIn(user);

        Agent agent = agentQueryMessagePair.getKey();
        QueryMessage queryMessage = agentQueryMessagePair.getValue();

        if (agent != null)
        {
            HomeUI homeUI = new HomeUI();
            homeUI.displayInterface(agent);
            mainFrame.dispose();
        }
        else
        {
            displayMessage(queryMessage);
        }
    }

    private void displayMessage(QueryMessage queryMessage)
    {
        labelResult.setText(queryMessage.getMessage());
        labelResult.setForeground(queryMessage.getColor());
        labelResult.setVisible(true);
    }

    private void changeLookAndFeel()
    {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
