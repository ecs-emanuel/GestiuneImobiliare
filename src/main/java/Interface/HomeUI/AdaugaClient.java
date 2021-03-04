package Interface.HomeUI;
import Services.JudetServices;
import Utils.CustomColor;
import Entities.Locatie.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // butoane actiune
    private JSeparator separatorButtons;
    private JButton buttonAccepta;
    private JButton buttonAnuleaza;

    public void create(HomeUI homeUI)
    {
        addPanelPersoana(homeUI);
        addPanelLocatie(homeUI);
        addButtons(homeUI);
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

        // creaza lista judete
        JudetServices judetServices = new JudetServices();
        List<Judet> listaJudete = judetServices.getListaJudete();

        for (Judet judet : listaJudete)
        {
            cboxJudet.addItem(judet);
        }

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

        rbuttonOras = new JRadioButton("Oras");
        panelLocatie.add(rbuttonOras);
        rbuttonsLocatie.add(rbuttonOras);
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

        rbuttonOras.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cboxOras.setEnabled(true);
                cboxCartier.setEnabled(true);
                cboxComuna.setEnabled(false);
                cboxSat.setEnabled(false);
            }
        });

        labelCartier = new JLabel("Cartier");
        panelLocatie.add(labelCartier);
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
        rbuttonComuna.setBounds(510, 25, 215, 20);
        rbuttonComuna.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cboxOras.setEnabled(false);
                cboxCartier.setEnabled(false);
                cboxComuna.setEnabled(true);
                cboxSat.setEnabled(true);
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

        labelSat = new JLabel("Sat");
        panelLocatie.add(labelSat);
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

    private void addButtons(HomeUI homeUI)
    {
        separatorButtons = new JSeparator(SwingConstants.HORIZONTAL);
        homeUI.panelContent.add(separatorButtons);
        separatorButtons.setBounds(15, 535, 730, 3);

        buttonAccepta = new JButton("Accepta");
        homeUI.panelContent.add(buttonAccepta);
        buttonAccepta.setBounds(160, 555, 200, 35);

        buttonAnuleaza = new JButton("Anuleaza");
        homeUI.panelContent.add(buttonAnuleaza);
        buttonAnuleaza.setBounds(375, 555, 200, 35);
    }
}
