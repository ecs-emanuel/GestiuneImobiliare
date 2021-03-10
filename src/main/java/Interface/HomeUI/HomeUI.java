package Interface.HomeUI;

import Interface.LoginUI;
import Utils.CustomColor;
import Entities.Persoana.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUI
{
    protected Agent mainAgent;

    protected final int FRAME_WIDTH = 1000;
    protected final int FRAME_HEIGHT = 800;

    private JFrame mainFrame;

    // panel header
    private JPanel panelHead;
    private JLabel labelHead;
    private JButton buttonLogout;

    // panel user
    private JPanel panelUser;
    private JLabel labelUser;

    // panel menu
    private JPanel panelMenu;
    private ButtonGroup buttonsMenu;
    protected JToggleButton buttonProprietati;
    protected JToggleButton buttonClienti;
    protected JToggleButton buttonProgramari;

    // panel search
    private JPanel panelSearch;
    private JLabel labelSearch;
    private JTextField fieldSearch;
    protected JCheckBox checkboxSearch;
    protected JButton buttonCauta;
    private JButton buttonFiltre;
    private JButton buttonAdauga;
    private JButton buttonModifica;
    protected JButton buttonSterge;

    // panel content
    protected JScrollPane scrollContent;

    public void displayInterface(Agent agent)
    {
        mainAgent = agent;
        changeLookAndFeel();

        mainFrame = new JFrame();
        mainFrame.setTitle("Home");
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.getContentPane().setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        mainFrame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addPanelHead();
        addPanelUser();
        addPanelSearch();
        addPanelMenu();
        addPanelContent();
    }

    private void addPanelHead()
    {
        panelHead = new JPanel();
        mainFrame.add(panelHead);
        panelHead.setLayout(null);
        panelHead.setVisible(true);
        panelHead.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelHead.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelHead.setBounds(10, 10, FRAME_WIDTH - 35, 50);

        labelHead = new JLabel("Blitz Imobiliare");
        panelHead.add(labelHead);
        labelHead.setLayout(null);
        labelHead.setVisible(true);
        labelHead.setFont(new Font("Tahoma", Font.BOLD, 24));
        labelHead.setBounds(15, 0, 250, 50);

        buttonLogout = new JButton("Logout");
        panelHead.add(buttonLogout);
        buttonLogout.setLayout(null);
        buttonLogout.setVisible(true);
        buttonLogout.setFocusPainted(false);
        buttonLogout.setBounds(855, 10, 100, 30);

        buttonLogout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LoginUI loginUI = new LoginUI();
                loginUI.displayInterface();
                mainFrame.dispose();
            }
        });
    }

    private void addPanelUser()
    {
        panelUser = new JPanel();
        mainFrame.add(panelUser);
        panelUser.setLayout(null);
        panelUser.setVisible(true);
        panelUser.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelUser.setBounds(10, 70, 180, 60);

        labelUser = new JLabel(mainAgent.getNumePersoana() + " " + mainAgent.getPrenumePersoana());
        panelUser.add(labelUser);
        labelUser.setLayout(null);
        labelUser.setVisible(true);
        labelUser.setHorizontalAlignment(SwingConstants.CENTER);
        labelUser.setVerticalAlignment(SwingConstants.CENTER);
        labelUser.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelUser.setBounds(5, 5, 170, 50);
    }

    private void addPanelSearch()
    {
        panelSearch = new JPanel();
        mainFrame.add(panelSearch);
        panelSearch.setLayout(null);
        panelSearch.setVisible(true);
        panelSearch.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelSearch.setBounds(200, 70, FRAME_WIDTH - 35 - 190, 60);


        labelSearch = new JLabel("");
        panelSearch.add(labelSearch);
        labelSearch.setLayout(null);
        labelSearch.setVisible(false);
        labelSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
        labelSearch.setBounds(15, 5, 160, 15);


        fieldSearch = new JTextField(10);
        panelSearch.add(fieldSearch);
        fieldSearch.setLayout(null);
        fieldSearch.setVisible(true);
        fieldSearch.setBounds(10, 20, 160, 30);

        checkboxSearch = new JCheckBox("Toate");
        panelSearch.add(checkboxSearch);
        checkboxSearch.setLayout(null);
        checkboxSearch.setVisible(true);
        checkboxSearch.setEnabled(false);
        //checkboxSearch.setBounds(190, 20, 60, 30);
        checkboxSearch.setBounds(285, 20, 60, 30);

        buttonCauta = new JButton("Cauta");
        panelSearch.add(buttonCauta);
        buttonCauta.setLayout(null);
        buttonCauta.setVisible(true);
        buttonCauta.setEnabled(false);
        //buttonCauta.setBounds(10, 20, 120, 30);
        //buttonCauta.setBounds(260, 20, 100, 30);
        buttonCauta.setBounds(180, 20, 100, 30);

        buttonCauta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                buttonSterge.setEnabled(false);

                ButtonModel selectedButtonModel = buttonsMenu.getSelection();

                if (selectedButtonModel == buttonProprietati.getModel())
                {
                    ListaProprietati listaProprietati = new ListaProprietati();
                    listaProprietati.create(HomeUI.this);
                }

                else if (selectedButtonModel == buttonClienti.getModel())
                {
                    ListaClienti listaClienti = new ListaClienti();
                    listaClienti.create(HomeUI.this);
                }

                else if (selectedButtonModel == buttonProgramari.getModel())
                {
                    ListaProgramari listaProgramari = new ListaProgramari();
                    listaProgramari.create(HomeUI.this);
                }
            }
        });

        /*
        buttonFiltre = new JButton("Filtre");
        panelSearch.add(buttonFiltre);
        buttonFiltre.setLayout(null);
        buttonFiltre.setVisible(true);
        buttonFiltre.setEnabled(false);
        buttonFiltre.setBounds(360, 20, 100, 30);*/

        buttonAdauga = new JButton("Adauga");
        panelSearch.add(buttonAdauga);
        buttonAdauga.setLayout(null);
        buttonAdauga.setVisible(true);
        buttonAdauga.setEnabled(false);
        buttonAdauga.setBounds(525, 20, 80, 30);

        buttonAdauga.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                buttonSterge.setEnabled(false);

                ButtonModel selectedButtonModel = buttonsMenu.getSelection();

                if (selectedButtonModel == buttonProprietati.getModel())
                {
                    AdaugaProprietate adaugaProprietate = new AdaugaProprietate();
                    adaugaProprietate.create(HomeUI.this);
                }

                else if (selectedButtonModel == buttonClienti.getModel())
                {
                    AdaugaClient adaugaClient = new AdaugaClient();
                    adaugaClient.create(HomeUI.this);
                }

                else if (selectedButtonModel == buttonProgramari.getModel())
                {
                    AdaugaProgramare adaugaProgramare = new AdaugaProgramare();
                    adaugaProgramare.create(HomeUI.this);
                }
            }
        });

        buttonModifica = new JButton("Modifica");
        panelSearch.add(buttonModifica);
        buttonModifica.setLayout(null);
        buttonModifica.setVisible(true);
        buttonModifica.setEnabled(false);
        buttonModifica.setBounds(605, 20, 80, 30);

        buttonSterge = new JButton("Sterge");
        panelSearch.add(buttonSterge);
        buttonSterge.setLayout(null);
        buttonSterge.setVisible(true);
        buttonSterge.setEnabled(false);
        buttonSterge.setBounds(685, 20, 80, 30);
    }

    private void addPanelMenu()
    {
        panelMenu = new JPanel();
        mainFrame.add(panelMenu);
        panelMenu.setLayout(null);
        panelMenu.setVisible(true);
        panelMenu.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelMenu.setBounds(10, 140, 180, 610);

        buttonProprietati = new JToggleButton("Proprietati");
        panelMenu.add(buttonProprietati);
        buttonProprietati.setLayout(null);
        buttonProprietati.setVisible(true);
        buttonProprietati.setFocusPainted(false);
        buttonProprietati.setRolloverEnabled(false);
        buttonProprietati.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        buttonProprietati.setBounds(5, 5, 170, 40);

        buttonProprietati.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                labelSearch.setText("Index");
                labelSearch.setVisible(true);
                clearPanel(scrollContent);
                resetSearchPanel();
            }
        });

        buttonClienti = new JToggleButton("Clienti");
        panelMenu.add(buttonClienti);
        buttonClienti.setLayout(null);
        buttonClienti.setVisible(true);
        buttonClienti.setFocusPainted(false);
        buttonClienti.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        buttonClienti.setBounds(5, 50, 170, 40);

        buttonClienti.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                labelSearch.setText("Tel");
                labelSearch.setVisible(true);
                clearPanel(scrollContent);
                resetSearchPanel();
                checkboxSearch.setEnabled(false);
            }
        });

        buttonProgramari = new JToggleButton("Programari");
        panelMenu.add(buttonProgramari);
        buttonProgramari.setLayout(null);
        buttonProgramari.setVisible(true);
        buttonProgramari.setFocusPainted(false);
        buttonProgramari.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        buttonProgramari.setBounds(5, 95, 170, 40);

        buttonProgramari.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                labelSearch.setText("Nume");
                labelSearch.setVisible(true);
                clearPanel(scrollContent);
                resetSearchPanel();
            }
        });

        buttonsMenu = new ButtonGroup();
        buttonsMenu.add(buttonProprietati);
        buttonsMenu.add(buttonClienti);
        buttonsMenu.add(buttonProgramari);
    }

    private void addPanelContent()
    {
        scrollContent = new JScrollPane();
        mainFrame.add(scrollContent);
        scrollContent.setVisible(true);
        scrollContent.setOpaque(true);
        scrollContent.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        scrollContent.getViewport().setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        scrollContent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollContent.setBounds(200, 140, FRAME_WIDTH - 35 - 190, 610);
        scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    protected void clearPanel(JScrollPane panel)
    {
        panel.setViewportView(null);
    }

    private void resetSearchPanel()
    {
        fieldSearch.setText("");
        fieldSearch.setEnabled(true);
        checkboxSearch.setSelected(false);
        checkboxSearch.setEnabled(true);
        buttonCauta.setEnabled(true);
        //buttonFiltre.setEnabled(true);
        buttonAdauga.setEnabled(true);
        buttonModifica.setEnabled(false);
        buttonSterge.setEnabled(false);
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
