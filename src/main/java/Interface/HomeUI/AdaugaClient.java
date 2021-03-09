package Interface.HomeUI;
import Entities.Persoana.Client;
import Services.LocatieServices.*;
import Services.PersoanaServices.ClientServices;
import Utils.CustomColor;
import Entities.Locatie.*;
import Utils.QueryMessage;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdaugaClient
{
    // panel persoana
    protected JPanel panelPersoana;
    protected JLabel labelNume;
    protected JTextField fieldNume;
    protected JLabel labelPrenume;
    protected JTextField fieldPrenume;
    protected JLabel labelTelefon;
    protected JTextField fieldTelefon;
    protected JLabel labelEmail;
    protected JTextField fieldEmail;

    // panel locatie
    protected JPanel panelLocatie;
    protected JLabel labelLocatie;
    protected JTextField fieldLocatie;
    protected JLabel labelJudet;
    protected JComboBox<Judet> cboxJudet;
    protected JRadioButton rbuttonOras;
    protected JComboBox<Oras> cboxOras;
    protected JLabel labelCartier;
    protected JComboBox<Cartier> cboxCartier;
    protected JRadioButton rbuttonComuna;
    protected JComboBox<Comuna> cboxComuna;
    protected JLabel labelSat;
    protected JComboBox<Sat> cboxSat;
    private ButtonGroup rbuttonsLocatie;

    // panel buttons
    private JPanel panelButtons;
    private JSeparator separatorButtons;
    private JButton buttonAccepta;
    private JButton buttonAnuleaza;
    private JLabel labelResult;

    public void create(HomeUI homeUI)
    {
        addPanelPersoana(homeUI);
        addPanelLocatie(homeUI);
        addPanelButtons(homeUI);
        homeUI.panelContent.setPreferredSize(new Dimension(1000 - 55 - 190, 600));
    }

    private void addPanelPersoana(HomeUI homeUI)
    {
        panelPersoana = new JPanel();
        homeUI.panelContent.add(panelPersoana);
        panelPersoana.setLayout(null);
        panelPersoana.setVisible(true);
        panelPersoana.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelPersoana.setBorder(new TitledBorder("Persoana"));
        panelPersoana.setBounds(10, 15, 735, 95);

        labelNume = new JLabel("Nume");
        panelPersoana.add(labelNume);
        labelNume.setBounds(20, 25, 154, 20);

        fieldNume = new JTextField(10);
        panelPersoana.add(fieldNume);
        fieldNume.setBounds(15, 45, 154, 30);

        labelPrenume = new JLabel("Prenume");
        panelPersoana.add(labelPrenume);
        labelPrenume.setBounds(203, 25, 154, 20);

        fieldPrenume = new JTextField(10);
        panelPersoana.add(fieldPrenume);
        fieldPrenume.setBounds(198, 45, 154, 30);

        labelTelefon = new JLabel("Telefon");
        panelPersoana.add(labelTelefon);
        labelTelefon.setBounds(386, 25, 154, 20);

        fieldTelefon = new JTextField(10);
        panelPersoana.add(fieldTelefon);
        fieldTelefon.setBounds(381, 45, 154, 30);

        labelEmail = new JLabel("Email");
        panelPersoana.add(labelEmail);
        labelEmail.setBounds(568, 25, 154, 20);

        fieldEmail = new JTextField(10);
        panelPersoana.add(fieldEmail);
        fieldEmail.setBounds(563, 45, 154, 30);
    }

    private void addPanelLocatie(HomeUI homeUI)
    {
        panelLocatie = new JPanel();
        homeUI.panelContent.add(panelLocatie);
        panelLocatie.setLayout(null);
        panelLocatie.setVisible(true);
        panelLocatie.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelLocatie.setBorder(new TitledBorder("Domiciliu"));
        panelLocatie.setBounds(10, 120, 735, 150);

        rbuttonsLocatie = new ButtonGroup();

        labelLocatie = new JLabel("Strada");
        panelLocatie.add(labelLocatie);
        labelLocatie.setBounds(20, 25, 215, 20);

        fieldLocatie = new JTextField(10);
        panelLocatie.add(fieldLocatie);
        fieldLocatie.setBounds(15, 45, 215, 30);

        labelJudet = new JLabel("Judet");
        panelLocatie.add(labelJudet);
        labelJudet.setBounds(20, 80, 215, 20);

        // lista judete
        cboxJudet = new JComboBox<>();
        panelLocatie.add(cboxJudet);
        cboxJudet.setBounds(15, 100, 215, 30);

        // afiseaza numele judetelor in lista
        cboxJudet.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Judet)
                {
                    Judet judet = (Judet) value;
                    setText(judet.getDenumireJudet());
                }
                return this;
            }
        });

        // creaza lista judete
        JudetServices judetServices = new JudetServices();
        Pair<List<Judet>, QueryOutcome> queryOutcomePairJudet = judetServices.getListaJudete();

        if (queryOutcomePairJudet.getValue() == QueryOutcome.SUCCESS)
        {
            List<Judet> listaJudete = queryOutcomePairJudet.getKey();

            for (Judet judet : listaJudete)
            {
                cboxJudet.addItem(judet);
            }

            cboxJudet.setSelectedIndex(-1);
        }

        // updateaza lista cu orase/comune cand se alege un judet
        cboxJudet.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // clear lists
                cboxOras.removeAllItems();
                cboxComuna.removeAllItems();

                // disable orase/comune
                rbuttonOras.setEnabled(false);
                rbuttonComuna.setEnabled(false);

                Object selectedItem = cboxJudet.getSelectedItem();

                // Judet selectat
                if (selectedItem instanceof Judet)
                {
                    OrasServices orasServices = new OrasServices();
                    Pair<List<Oras>, QueryOutcome> queryOutcomePairOras = orasServices.getListaOrase((Judet) selectedItem);

                    if (queryOutcomePairOras.getValue() == QueryOutcome.SUCCESS)
                    {
                        List<Oras> listaOrase = queryOutcomePairOras.getKey();

                        for (Oras oras : listaOrase)
                        {
                            cboxOras.addItem(oras);
                        }

                        cboxOras.setSelectedIndex(-1);

                        // enable orase
                        rbuttonOras.setEnabled(true);
                    }

                    ComunaServices comunaServices = new ComunaServices();
                    Pair<List<Comuna>, QueryOutcome> queryOutcomePairComuna = comunaServices.getListaComune((Judet) selectedItem);

                    if (queryOutcomePairComuna.getValue() == QueryOutcome.SUCCESS)
                    {
                        List<Comuna> listaComune = queryOutcomePairComuna.getKey();

                        for (Comuna comuna : listaComune)
                        {
                            cboxComuna.addItem(comuna);
                        }

                        cboxComuna.setSelectedIndex(-1);

                        // enable comune
                        rbuttonComuna.setEnabled(true);
                    }
                }
            }
        });

        rbuttonOras = new JRadioButton("Oras");
        panelLocatie.add(rbuttonOras);
        rbuttonsLocatie.add(rbuttonOras);
        rbuttonOras.setEnabled(false);
        rbuttonOras.setBounds(265, 25, 215, 20);

        // lista orase
        cboxOras = new JComboBox<>();
        panelLocatie.add(cboxOras);
        cboxOras.setEnabled(false);
        cboxOras.setBounds(260, 45, 215, 30);

        // afiseaza numele oraselor in lista
        cboxOras.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Oras)
                {
                    Oras oras = (Oras) value;
                    setText(oras.getDenumireOras());
                }
                return this;
            }
        });

        // updateaza lista cu cartiere cand un oras este selectat
        cboxOras.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // clear lists
                cboxCartier.removeAllItems();

                Object selectedItem = cboxOras.getSelectedItem();

                if (selectedItem instanceof Oras)
                {
                    CartierServices cartierServices = new CartierServices();
                    Pair<List<Cartier>, QueryOutcome> queryOutcomePairCartier = cartierServices.getListaCartiere((Oras) selectedItem);

                    if (queryOutcomePairCartier.getValue() == QueryOutcome.SUCCESS)
                    {
                        List<Cartier> listaCartiere = queryOutcomePairCartier.getKey();

                        for (Cartier cartier : listaCartiere)
                        {
                            cboxCartier.addItem(cartier);
                        }

                        cboxCartier.setSelectedIndex(-1);
                    }
                }
            }
        });

        rbuttonOras.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cboxOras.setEnabled(true);
                labelCartier.setEnabled(true);
                cboxCartier.setEnabled(true);
                cboxComuna.setEnabled(false);
                labelSat.setEnabled(false);
                cboxSat.setEnabled(false);
                cboxComuna.setSelectedIndex(-1);
                cboxSat.setSelectedIndex(-1);
            }
        });

        labelCartier = new JLabel("Cartier");
        panelLocatie.add(labelCartier);
        labelCartier.setEnabled(false);
        labelCartier.setBounds(265, 80, 215, 20);

        // lista cartiere
        cboxCartier = new JComboBox<>();
        panelLocatie.add(cboxCartier);
        cboxCartier.setEnabled(false);
        cboxCartier.setBounds(260, 100, 215, 30);

        // afiseaza numele cartierelor in lista
        cboxCartier.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Cartier)
                {
                    Cartier cartier = (Cartier) value;
                    setText(cartier.getDenumireCartier());
                }
                return this;
            }
        });

        rbuttonComuna = new JRadioButton("Comuna");
        panelLocatie.add(rbuttonComuna);
        rbuttonsLocatie.add(rbuttonComuna);
        rbuttonComuna.setEnabled(false);
        rbuttonComuna.setBounds(510, 25, 215, 20);
        rbuttonComuna.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cboxComuna.setEnabled(true);
                labelSat.setEnabled(true);
                cboxSat.setEnabled(true);
                cboxOras.setEnabled(false);
                labelCartier.setEnabled(false);
                cboxCartier.setEnabled(false);
                cboxOras.setSelectedIndex(-1);
                cboxCartier.setSelectedIndex(-1);

            }
        });

        // lista comune
        cboxComuna = new JComboBox<>();
        panelLocatie.add(cboxComuna);
        cboxComuna.setEnabled(false);
        cboxComuna.setBounds(505, 45, 215, 30);

        // afiseaza numele comunelor in lista
        cboxComuna.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Comuna)
                {
                    Comuna comuna = (Comuna) value;
                    setText(comuna.getDenumireComuna());
                }
                return this;
            }
        });

        // updateaza lista cu sate cand o comuna este selectata
        cboxComuna.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // clear lists
                cboxSat.removeAllItems();

                Object selectedItem = cboxComuna.getSelectedItem();

                if (selectedItem instanceof Comuna)
                {
                    SatServices satServices = new SatServices();
                    Pair<List<Sat>, QueryOutcome> queryOutcomePairSat = satServices.getListaSate((Comuna) selectedItem);

                    if (queryOutcomePairSat.getValue() == QueryOutcome.SUCCESS)
                    {
                        List<Sat> listaSate = queryOutcomePairSat.getKey();

                        for (Sat sat : listaSate)
                        {
                            cboxSat.addItem(sat);
                        }

                        cboxSat.setSelectedIndex(-1);
                    }
                }
            }
        });

        labelSat = new JLabel("Sat");
        panelLocatie.add(labelSat);
        labelSat.setEnabled(false);
        labelSat.setBounds(510, 80, 215, 20);

        // lista sate
        cboxSat = new JComboBox<>();
        panelLocatie.add(cboxSat);
        cboxSat.setEnabled(false);
        cboxSat.setBounds(505, 100, 215, 30);

        // afiseaza numele satelor in lista
        cboxSat.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Sat)
                {
                    Sat sat = (Sat) value;
                    setText(sat.getDenumireSat());
                }
                return this;
            }
        });
    }

    private void addPanelButtons(HomeUI homeUI)
    {
        panelButtons = new JPanel();
        homeUI.panelContent.add(panelButtons);
        panelButtons.setLayout(null);
        panelButtons.setVisible(true);
        panelButtons.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelButtons.setBounds(10, 515, 735, 150);

        separatorButtons = new JSeparator(SwingConstants.HORIZONTAL);
        panelButtons.add(separatorButtons);
        separatorButtons.setBounds(15, 0, 710, 3);

        buttonAccepta = new JButton("Accepta");
        panelButtons.add(buttonAccepta);
        buttonAccepta.setBounds(160, 12, 200, 35);

        buttonAccepta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isFormCompleted())
                {
                    Locatie locatie = new Locatie();
                    locatie.setJudetLocatie((Judet) cboxJudet.getSelectedItem());

                    if (cboxOras.getSelectedItem() instanceof Oras)
                    {
                        locatie.setOrasLocatie((Oras) cboxOras.getSelectedItem());
                        locatie.setCartierLocatie((Cartier) cboxCartier.getSelectedItem());
                    }

                    if (cboxComuna.getSelectedItem() instanceof Comuna)
                    {
                        locatie.setComunaLocatie((Comuna) cboxComuna.getSelectedItem());
                        locatie.setSatLocatie((Sat) cboxSat.getSelectedItem());
                    }

                    locatie.setDenumireLocatie(fieldLocatie.getText());

                    Client client = new Client();
                    client.setNumePersoana(fieldNume.getText());
                    client.setPrenumePersoana(fieldPrenume.getText());
                    client.setTelefonPersoana(fieldTelefon.getText());
                    client.setEmailPersoana(fieldEmail.getText());
                    client.setDomiciliuPersoana(locatie);

                    ClientServices clientServices = new ClientServices();
                    QueryOutcome queryOutcome = clientServices.addClient(client);

                    switch (queryOutcome)
                    {
                        case SUCCESS:
                            displayMessage(QueryMessage.INSERT_SUCCESS);
                            break;

                        case OFFLINE:
                            displayMessage(QueryMessage.DATABASE_OFFLINE);
                            break;

                        case ERROR:
                            displayMessage(QueryMessage.DATABASE_ERROR);
                            break;
                    }
                }
                else
                {
                    displayMessage(QueryMessage.FORM_INCOMPLETE);
                }
            }
        });

        buttonAnuleaza = new JButton("Anuleaza");
        panelButtons.add(buttonAnuleaza);
        buttonAnuleaza.setBounds(375, 12, 200, 35);

        labelResult = new JLabel("", JLabel.CENTER);
        panelButtons.add(labelResult);
        labelResult.setBorder(BorderFactory.createLineBorder(CustomColor.GRAY_LIGHTSTEEL.getColor()));
        labelResult.setBounds(170, 57, 395, 25);
    }

    private void displayMessage(QueryMessage queryMessage)
    {
        labelResult.setText(queryMessage.getMessage());
        labelResult.setForeground(queryMessage.getColor());
        labelResult.setVisible(true);
    }

    private boolean isFormCompleted()
    {
        return !fieldNume.getText().isEmpty() && !fieldPrenume.getText().isEmpty() &&
                !fieldTelefon.getText().isEmpty() && !fieldEmail.getText().isEmpty() &&
                !fieldLocatie.getText().isEmpty() && cboxJudet.getSelectedItem() instanceof Judet &&
                ((cboxOras.getSelectedItem() instanceof Oras && cboxCartier.getSelectedItem() instanceof Cartier) ^
                (cboxComuna.getSelectedItem() instanceof Comuna && cboxSat.getSelectedItem() instanceof Sat));
    }
}
