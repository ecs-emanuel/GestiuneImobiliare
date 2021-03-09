package Interface.HomeUI;

import Components.*;
import Entities.Persoana.Client;
import Entities.Proprietate.*;
import Services.LocatieServices.*;
import Services.PersoanaServices.ClientServices;
import Services.ProprietateServices.ApartamentServices;
import Services.ProprietateServices.CasaServices;
import Services.ProprietateServices.TerenServices;
import Utils.CustomColor;
import Entities.Locatie.*;
import Utils.QueryMessage;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;


public class AdaugaProprietate
{
    // Panel descriere
    private JPanel panelDescriere;
    private JLabel labelTitlu;
    private JTextField fieldTitlu;
    private JLabel labelPret;
    private JFormattedTextField fieldPret;
    private JLabel labelDescriere;
    private JTextArea areaDescriere;
    private JScrollPane scrollDescriere;

    // Panel proprietate
    private JPanel panelProprietate;
    private JLabel labelCategorieProprietare;
    private JComboBox<CategorieProprietate> cboxCategorieProprietate;
    private JLabel labelProprietar;
    private JComboBox<Client> cboxProprietar;

    // Panel teren
    private JPanel panelTeren;
    private JLabel labelSuprafataParcela;
    private JFormattedTextField fieldSuprafataParcela;
    private JLabel labelDispozitieTeren;
    private JComboBox<DispozitieTeren> cboxDispozitieTeren;
    private JCheckBox checkbApa;
    private JCheckBox checkbGaz;
    private JCheckBox checkbElectricitate;
    private JCheckBox checkbCanalizare;

    // Panel constructie
    private JPanel panelConstructie;
    private JLabel labelSuprafataUtilizabila;
    private JFormattedTextField fieldSuprafataUtilizabila;
    private JLabel labelSuprafataConstructie;
    private JFormattedTextField fieldSuprafataConstructie;
    private JLabel labelAnConstructie;
    private JFormattedTextField fieldAnConstructie;
    private JLabel labelStructuraConstructie;
    private JComboBox<StructuraConstructie> cboxStructuraConstructie;
    private JLabel labelEtajApartament;
    private JComboBox<EtajApartament> cboxEtajApartament;
    private JLabel labelInaltimeConstructie;
    private JComboBox<Integer> cboxInaltimeConstructie;
    private JLabel labelDispozitieActuala;
    private JComboBox<DispozitieConstructie> cboxDispozitieActuala;
    private JLabel labelDispozitiePredare;
    private JComboBox<DispozitieConstructie> cboxDispozitiePredare;

    // Panel compartimentare
    private JPanel panelCompartimentare;
    private JLabel labelOpenspace;
    private JComboBox<Integer> cboxOpenspace;
    private JLabel labelLiving;
    private JComboBox<Integer> cboxLiving;
    private JLabel labelDormitor;
    private JComboBox<Integer> cboxDormitor;
    private JLabel labelDressing;
    private JComboBox<Integer> cboxDressing;
    private JLabel labelBucatarie;
    private JComboBox<Integer> cboxBucatarie;
    private JLabel labelDebara;
    private JComboBox<Integer> cboxDebara;
    private JLabel labelBaie;
    private JComboBox<Integer> cboxBaie;
    private JLabel labelHol;
    private JComboBox<Integer> cboxHol;
    private JLabel labelMansarda;
    private JComboBox<Integer> cboxMansarda;
    private JLabel labelBalcon;
    private JComboBox<Integer> cboxBalcon;
    private JLabel labelTerasa;
    private JComboBox<Integer> cboxTerasa;
    private JLabel labelGradina;
    private JComboBox<Integer> cboxGradina;
    private JLabel labelParcare;
    private JComboBox<Integer> cboxParcare;
    private JLabel labelGaraj;
    private JComboBox<Integer> cboxGaraj;
    private JLabel labelBoxa;
    private JComboBox<Integer> cboxBoxa;
    private JLabel labelPod;
    private JComboBox<Integer> cboxPod;

    // Panel locatie
    private JPanel panelLocatie;
    private JLabel labelLocatie;
    private JTextField fieldLocatie;
    private JLabel labelJudet;
    private JComboBox<Judet> cboxJudet;
    private JRadioButton rbuttonOras;
    private JComboBox<Oras> cboxOras;
    private JLabel labelCartier;
    private JComboBox<Cartier> cboxCartier;
    private JRadioButton rbuttonComuna;
    private JComboBox<Comuna> cboxComuna;
    private JLabel labelSat;
    private JComboBox<Sat> cboxSat;
    private ButtonGroup rbuttonsLocatie;

    // panel buttons
    private JPanel panelButtons;
    private JSeparator separatorButtons;
    private JButton buttonAccepta;
    private JButton buttonAnuleaza;
    private JLabel labelResult;

    public void create(HomeUI homeUI)
    {
        // Add sub panels
        addpanelDescriere(homeUI);
        addPanelProprietate(homeUI);
        addPanelTeren(homeUI);
        addPanelConstructie(homeUI);
        addPanelCompartimentare(homeUI);
        addPanelLocatie(homeUI);
        addButtons(homeUI);
        homeUI.panelContent.setPreferredSize(new Dimension(1000 - 55 - 190, 1185));

        // Disable sub panels
        disablePanelComponents(panelTeren);
        disablePanelComponents(panelConstructie);
        disablePanelComponents(panelCompartimentare);
        disablePanelComponents(panelLocatie);
    }

    private void addpanelDescriere(HomeUI homeUI)
    {
        // panel descriere
        panelDescriere = new JPanel();
        homeUI.panelContent.add(panelDescriere);
        panelDescriere.setLayout(null);
        panelDescriere.setVisible(true);
        panelDescriere.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelDescriere.setBorder(new TitledBorder("Descriere"));
        panelDescriere.setBounds(10, 15, 735, 200);

        // titlu proprietate
        labelTitlu = new JLabel("Titlu");
        panelDescriere.add(labelTitlu);
        labelTitlu.setBounds(20, 25, 507, 20);

        fieldTitlu = new JTextField(10);
        panelDescriere.add(fieldTitlu);
        fieldTitlu.setBounds(15, 45, 507, 30);

        // pret proprietate
        labelPret = new JLabel("Pret");
        panelDescriere.add(labelPret);
        labelPret.setBounds(557, 25, 165, 20);

        NumberFormatter numberFormatter = new NumberFormatter(null);
        numberFormatter.setAllowsInvalid(false);

        fieldPret = new JFormattedTextField(numberFormatter);
        panelDescriere.add(fieldPret);
        fieldPret.setBounds(552, 45, 165, 30);

        // descriere proprietate
        labelDescriere = new JLabel("Descriere");
        panelDescriere.add(labelDescriere);
        labelDescriere.setBounds(20, 80, 705, 20);

        areaDescriere = new JTextArea();
        areaDescriere.setWrapStyleWord(true);
        areaDescriere.setLineWrap(true);

        scrollDescriere = new JScrollPane(areaDescriere);
        panelDescriere.add(scrollDescriere);
        scrollDescriere.setBounds(15, 100, 703, 80);
    }

    private void addPanelProprietate(HomeUI homeUI)
    {
        // panel proprietate
        panelProprietate = new JPanel();
        homeUI.panelContent.add(panelProprietate);
        panelProprietate.setLayout(null);
        panelProprietate.setVisible(true);
        panelProprietate.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelProprietate.setBorder(new TitledBorder("Proprietate"));
        panelProprietate.setBounds(10, 225, 735, 95);

        // categorie proprietate
        labelCategorieProprietare = new JLabel("Categorie");
        panelProprietate.add(labelCategorieProprietare);
        labelCategorieProprietare.setBounds(20, 25, 165, 20);

        cboxCategorieProprietate = new JComboBox<>();
        panelProprietate.add(cboxCategorieProprietate);
        cboxCategorieProprietate.setBounds(15, 45, 165, 30);

        for (CategorieProprietate item : CategorieProprietate.values())
        {
            cboxCategorieProprietate.addItem(item);
        }

        cboxCategorieProprietate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object item = cboxCategorieProprietate.getSelectedItem();

                if (!(item instanceof CategorieProprietate))
                {
                    return;
                }

                enablePanelComponents(panelTeren);

                // We don't want to enable more
                //enablePanelComponents(panelLocatie);
                panelLocatie.setEnabled(true);
                labelLocatie.setEnabled(true);
                fieldLocatie.setEnabled(true);
                labelJudet.setEnabled(true);
                cboxJudet.setEnabled(true);

                switch((CategorieProprietate) item)
                {
                    case Teren:
                        disablePanelComponents(panelConstructie);
                        disablePanelComponents(panelCompartimentare);
                        labelDispozitieTeren.setEnabled(true);
                        cboxDispozitieTeren.setEnabled(true);
                        break;

                    case Casa:
                        enablePanelComponents(panelConstructie);
                        enablePanelComponents(panelCompartimentare);
                        labelDispozitieTeren.setEnabled(false);
                        cboxDispozitieTeren.setEnabled(false);
                        cboxDispozitieTeren.setSelectedItem(DispozitieTeren.Intravilan);
                        labelEtajApartament.setEnabled(false);
                        cboxEtajApartament.setEnabled(false);
                        break;

                    case Apartament:
                        enablePanelComponents(panelConstructie);
                        enablePanelComponents(panelCompartimentare);
                        labelDispozitieTeren.setEnabled(false);
                        cboxDispozitieTeren.setEnabled(false);
                        cboxDispozitieTeren.setSelectedItem(DispozitieTeren.Intravilan);
                        labelEtajApartament.setEnabled(true);
                        cboxEtajApartament.setEnabled(true);
                        break;
                }
            }
        });
        cboxCategorieProprietate.setSelectedIndex(-1);

        // proprietar
        labelProprietar = new JLabel("Proprietar");
        panelProprietate.add(labelProprietar);
        labelProprietar.setBounds(215, 25, 507, 20);

        cboxProprietar = new JComboBox<>();
        panelProprietate.add(cboxProprietar);
        cboxProprietar.setBounds(210, 45, 507, 30);

        cboxProprietar.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Client)
                {
                    Client client = (Client) value;
                    setText(String.format("%s %s - %s", client.getNumePersoana(), client.getPrenumePersoana(), client.getTelefonPersoana()));
                }
                return this;
            }
        });

        ClientServices clientServices = new ClientServices();
        Pair<List<Client>, QueryOutcome> queryOutcome = clientServices.getListaClienti();

        if (queryOutcome.getValue() == QueryOutcome.SUCCESS)
        {
            List<Client> listaClienti = queryOutcome.getKey();

            for (Client client : listaClienti)
            {
                cboxProprietar.addItem(client);
            }
            cboxProprietar.setSelectedIndex(-1);
        }
    }

    private void addPanelTeren(HomeUI homeUI)
    {
        // panel teren
        panelTeren = new JPanel();
        homeUI.panelContent.add(panelTeren);
        panelTeren.setLayout(null);
        panelTeren.setVisible(true);
        panelTeren.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelTeren.setBorder(new TitledBorder("Teren"));
        panelTeren.setBounds(10, 330, 735, 140);

        // suprafata parcela
        labelSuprafataParcela = new JLabel("Suprafata Parcela");
        panelTeren.add(labelSuprafataParcela);
        labelSuprafataParcela.setBounds(20, 25, 154, 20);

        NumberFormatter numberFormatter = new NumberFormatter(null);
        numberFormatter.setAllowsInvalid(false);

        fieldSuprafataParcela = new JFormattedTextField(numberFormatter);
        panelTeren.add(fieldSuprafataParcela);
        fieldSuprafataParcela.setBounds(15, 45, 154, 30);

        // dispozitie teren (intravilan/extravilan)
        labelDispozitieTeren = new JLabel("Dispozitie");
        panelTeren.add(labelDispozitieTeren);
        labelDispozitieTeren.setBounds(204, 25, 154, 20);

        cboxDispozitieTeren = new JComboBox<>();
        panelTeren.add(cboxDispozitieTeren);
        cboxDispozitieTeren.setBounds(199, 45, 154, 30);

        for (DispozitieTeren item : DispozitieTeren.values())
        {
            cboxDispozitieTeren.addItem(item);
        }
        cboxDispozitieTeren.setSelectedIndex(-1);

        // are apa
        checkbApa = new JCheckBox("Apa");
        panelTeren.add(checkbApa);
        checkbApa.setBounds(15, 100, 154, 20);

        // are gaz
        checkbGaz = new JCheckBox("Gaz");
        panelTeren.add(checkbGaz);
        checkbGaz.setBounds(198, 100, 154, 20);

        // are electricitate
        checkbElectricitate = new JCheckBox("Electricitate");
        panelTeren.add(checkbElectricitate);
        checkbElectricitate.setBounds(381, 100, 154, 20);

        // are canalizare
        checkbCanalizare = new JCheckBox("Canalizare");
        panelTeren.add(checkbCanalizare);
        checkbCanalizare.setBounds(563, 100, 154, 20);
    }

    private void addPanelConstructie(HomeUI homeUI)
    {
        // panel constructie
        panelConstructie = new JPanel();
        homeUI.panelContent.add(panelConstructie);
        panelConstructie.setLayout(null);
        panelConstructie.setVisible(true);
        panelConstructie.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelConstructie.setBorder(new TitledBorder("Constructie"));
        panelConstructie.setBounds(10, 480, 735, 160);

        // formatter for number only text fields
        NumberFormatter numberFormatter = new NumberFormatter(null);
        numberFormatter.setAllowsInvalid(false);

        // suprafata utilizabila
        labelSuprafataUtilizabila = new JLabel("Suprafata Utilizabila");
        panelConstructie.add(labelSuprafataUtilizabila);
        labelSuprafataUtilizabila.setBounds(20, 25, 154, 20);

        fieldSuprafataUtilizabila = new JFormattedTextField(numberFormatter);
        panelConstructie.add(fieldSuprafataUtilizabila);
        fieldSuprafataUtilizabila.setBounds(15, 45, 154, 30);

        // suprafata constructie
        labelSuprafataConstructie = new JLabel("Suprafata Constructie");
        panelConstructie.add(labelSuprafataConstructie);
        labelSuprafataConstructie.setBounds(204, 25, 154, 20);

        fieldSuprafataConstructie = new JFormattedTextField(numberFormatter);
        panelConstructie.add(fieldSuprafataConstructie);
        fieldSuprafataConstructie.setBounds(199, 45, 154, 30);

        // an constructie
        labelAnConstructie = new JLabel("An Constructie");
        panelConstructie.add(labelAnConstructie);
        labelAnConstructie.setBounds(388, 25, 154, 20);

        fieldAnConstructie = new JFormattedTextField(numberFormatter);
        panelConstructie.add(fieldAnConstructie);
        fieldAnConstructie.setBounds(383, 45, 154, 30);

        // structura constructie
        labelStructuraConstructie = new JLabel("Structura");
        panelConstructie.add(labelStructuraConstructie);
        labelStructuraConstructie.setBounds(572, 25, 154, 20);

        cboxStructuraConstructie = new JComboBox<>();
        panelConstructie.add(cboxStructuraConstructie);
        cboxStructuraConstructie.setBounds(567, 45, 154, 30);

        for (StructuraConstructie item : StructuraConstructie.values())
        {
            cboxStructuraConstructie.addItem(item);
        }
        cboxStructuraConstructie.setSelectedIndex(-1);

        // etaj apartament
        labelEtajApartament = new JLabel("Etaj");
        panelConstructie.add(labelEtajApartament);
        labelEtajApartament.setBounds(20, 80, 154, 20);

        cboxEtajApartament = new JComboBox<>();
        panelConstructie.add(cboxEtajApartament);
        cboxEtajApartament.setBounds(15, 100, 154, 30);

        for (EtajApartament item : EtajApartament.values())
        {
            cboxEtajApartament.addItem(item);
        }
        cboxEtajApartament.setSelectedIndex(-1);

        // inaltime constructie
        labelInaltimeConstructie = new JLabel("Inaltime");
        panelConstructie.add(labelInaltimeConstructie);
        labelInaltimeConstructie.setBounds(204, 80, 154, 20);

        cboxInaltimeConstructie = new JComboBox<>();
        panelConstructie.add(cboxInaltimeConstructie);
        cboxInaltimeConstructie.setBounds(199, 100, 154, 30);

        for (int i = 0; i <= 20; i++)
        {
            cboxInaltimeConstructie.addItem(i);
        }
        cboxInaltimeConstructie.setSelectedIndex(-1);

        // dispozitie actuala (semifinisat/finisat)
        labelDispozitieActuala = new JLabel("Dispozitie Actuala");
        panelConstructie.add(labelDispozitieActuala);
        labelDispozitieActuala.setBounds(388, 80, 154, 20);

        cboxDispozitieActuala = new JComboBox<>();
        panelConstructie.add(cboxDispozitieActuala);
        cboxDispozitieActuala.setBounds(383, 100, 154, 30);

        for (DispozitieConstructie item : DispozitieConstructie.values())
        {
            cboxDispozitieActuala.addItem(item);
        }
        cboxDispozitieActuala.setSelectedIndex(-1);

        // dispozitie predare (semifinisat/finisat)
        labelDispozitiePredare = new JLabel("Dispozitie Predare");
        panelConstructie.add(labelDispozitiePredare);
        labelDispozitiePredare.setBounds(572, 80, 154, 20);

        cboxDispozitiePredare = new JComboBox<>();
        panelConstructie.add(cboxDispozitiePredare);
        cboxDispozitiePredare.setBounds(567, 100, 154, 30);

        for (DispozitieConstructie item : DispozitieConstructie.values())
        {
            cboxDispozitiePredare.addItem(item);
        }
        cboxDispozitiePredare.setSelectedIndex(-1);
    }

    private void addPanelCompartimentare(HomeUI homeUI)
    {
        // panel compartimentare
        panelCompartimentare = new JPanel();
        homeUI.panelContent.add(panelCompartimentare);
        panelCompartimentare.setLayout(null);
        panelCompartimentare.setVisible(true);
        panelCompartimentare.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelCompartimentare.setBorder(new TitledBorder("Compartimentare"));
        panelCompartimentare.setBounds(10, 650, 735, 260);

        // numar openspaceuri
        labelOpenspace = new JLabel("Openspace");
        panelCompartimentare.add(labelOpenspace);
        labelOpenspace.setBounds(20, 25, 154, 20);

        cboxOpenspace = new JComboBox<>();
        panelCompartimentare.add(cboxOpenspace);
        cboxOpenspace.setBounds(15, 45, 154, 30);

        // numar livinguri
        labelLiving = new JLabel("Living");
        panelCompartimentare.add(labelLiving);
        labelLiving.setBounds(204, 25, 154, 20);

        cboxLiving = new JComboBox<>();
        panelCompartimentare.add(cboxLiving);
        cboxLiving.setBounds(199, 45, 154, 30);

        // numar dormitoare
        labelDormitor = new JLabel("Dormitor");
        panelCompartimentare.add(labelDormitor);
        labelDormitor.setBounds(388, 25, 154, 20);

        cboxDormitor = new JComboBox<>();
        panelCompartimentare.add(cboxDormitor);
        cboxDormitor.setBounds(383, 45, 154, 30);

        // numar dressinguri
        labelDressing = new JLabel("Dressing");
        panelCompartimentare.add(labelDressing);
        labelDressing.setBounds(572, 25, 154, 20);

        cboxDressing = new JComboBox<>();
        panelCompartimentare.add(cboxDressing);
        cboxDressing.setBounds(567, 45, 154, 30);

        // numar bucatarii
        labelBucatarie = new JLabel("Bucatarie");
        panelCompartimentare.add(labelBucatarie);
        labelBucatarie.setBounds(20, 80, 154, 20);

        cboxBucatarie = new JComboBox<>();
        panelCompartimentare.add(cboxBucatarie);
        cboxBucatarie.setBounds(15, 100, 154, 30);

        // numar debarale
        labelDebara = new JLabel("Debara");
        panelCompartimentare.add(labelDebara);
        labelDebara.setBounds(204, 80, 154, 20);

        cboxDebara = new JComboBox<>();
        panelCompartimentare.add(cboxDebara);
        cboxDebara.setBounds(199, 100, 154, 30);

        // numar bai
        labelBaie = new JLabel("Baie");
        panelCompartimentare.add(labelBaie);
        labelBaie.setBounds(388, 80, 154, 20);

        cboxBaie = new JComboBox<>();
        panelCompartimentare.add(cboxBaie);
        cboxBaie.setBounds(383, 100, 154, 30);

        // numar holuri
        labelHol = new JLabel("Hol");
        panelCompartimentare.add(labelHol);
        labelHol.setBounds(572, 80, 154, 20);

        cboxHol = new JComboBox<>();
        panelCompartimentare.add(cboxHol);
        cboxHol.setBounds(567, 100, 154, 30);

        // numar mansarde
        labelMansarda = new JLabel("Mansarda");
        panelCompartimentare.add(labelMansarda);
        labelMansarda.setBounds(20, 135, 154, 20);

        cboxMansarda = new JComboBox<>();
        panelCompartimentare.add(cboxMansarda);
        cboxMansarda.setBounds(15, 155, 154, 30);

        // numar balcoane
        labelBalcon = new JLabel("Balcon");
        panelCompartimentare.add(labelBalcon);
        labelBalcon.setBounds(204, 135, 154, 20);

        cboxBalcon = new JComboBox<>();
        panelCompartimentare.add(cboxBalcon);
        cboxBalcon.setBounds(199, 155, 154, 30);

        // numar terase
        labelTerasa = new JLabel("Terasa");
        panelCompartimentare.add(labelTerasa);
        labelTerasa.setBounds(388, 135, 154, 20);

        cboxTerasa = new JComboBox<>();
        panelCompartimentare.add(cboxTerasa);
        cboxTerasa.setBounds(383, 155, 154, 30);

        // numar gradini
        labelGradina = new JLabel("Gradina");
        panelCompartimentare.add(labelGradina);
        labelGradina.setBounds(572, 135, 154, 20);

        cboxGradina = new JComboBox<>();
        panelCompartimentare.add(cboxGradina);
        cboxGradina.setBounds(567, 155, 154, 30);

        // numar parcari
        labelParcare = new JLabel("Parcare");
        panelCompartimentare.add(labelParcare);
        labelParcare.setBounds(20, 190, 154, 20);

        cboxParcare = new JComboBox<>();
        panelCompartimentare.add(cboxParcare);
        cboxParcare.setBounds(15, 210, 154, 30);

        // numar garaje
        labelGaraj = new JLabel("Garaj");
        panelCompartimentare.add(labelGaraj);
        labelGaraj.setBounds(204, 190, 154, 20);

        cboxGaraj = new JComboBox<>();
        panelCompartimentare.add(cboxGaraj);
        cboxGaraj.setBounds(199, 210, 154, 30);

        // numar boxe
        labelBoxa = new JLabel("Boxa");
        panelCompartimentare.add(labelBoxa);
        labelBoxa.setBounds(388, 190, 154, 20);

        cboxBoxa = new JComboBox<>();
        panelCompartimentare.add(cboxBoxa);
        cboxBoxa.setBounds(383, 210, 154, 30);

        // numar poduri
        labelPod = new JLabel("Pod");
        panelCompartimentare.add(labelPod);
        labelPod.setBounds(572, 190, 154, 20);

        cboxPod = new JComboBox<>();
        panelCompartimentare.add(cboxPod);
        cboxPod.setBounds(567, 210, 154, 30);

        for (int i = 0; i <= 10; i++)
        {
            cboxOpenspace.addItem(i);
            cboxLiving.addItem(i);
            cboxDormitor.addItem(i);
            cboxDressing.addItem(i);
            cboxBucatarie.addItem(i);
            cboxDebara.addItem(i);
            cboxBaie.addItem(i);
            cboxHol.addItem(i);
            cboxMansarda.addItem(i);
            cboxBalcon.addItem(i);
            cboxTerasa.addItem(i);
            cboxGradina.addItem(i);
            cboxParcare.addItem(i);
            cboxGaraj.addItem(i);
            cboxBoxa.addItem(i);
            cboxPod.addItem(i);
        }
    }

    private void addPanelLocatie(HomeUI homeUI)
    {
        // panel locatie
        panelLocatie = new JPanel();
        homeUI.panelContent.add(panelLocatie);
        panelLocatie.setLayout(null);
        panelLocatie.setVisible(true);
        panelLocatie.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelLocatie.setBorder(new TitledBorder("Locatie"));
        panelLocatie.setBounds(10, 920, 735, 150);

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

    // butoane accept/anuleaza
    private void addButtons(HomeUI homeUI)
    {
        panelButtons = new JPanel();
        homeUI.panelContent.add(panelButtons);
        panelButtons.setLayout(null);
        panelButtons.setVisible(true);
        panelButtons.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelButtons.setBounds(10, 1090, 735, 150);

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
                    QueryOutcome queryOutcome = QueryOutcome.ERROR;

                    // create locatie
                    Locatie locatie = new Locatie();
                    locatie.setJudetLocatie((Judet) cboxJudet.getSelectedItem());

                    if (rbuttonOras.isSelected())
                    {
                        locatie.setOrasLocatie((Oras) cboxOras.getSelectedItem());
                        locatie.setCartierLocatie((Cartier) cboxCartier.getSelectedItem());
                    }
                    else if (rbuttonComuna.isSelected())
                    {
                        locatie.setComunaLocatie((Comuna) cboxComuna.getSelectedItem());
                        locatie.setSatLocatie((Sat) cboxSat.getSelectedItem());
                    }
                    locatie.setDenumireLocatie(fieldLocatie.getText());

                    // create parcela
                    Parcela parcela = new Parcela();
                    parcela.setSuprafataParcela(Integer.parseInt(fieldSuprafataParcela.getText()));
                    parcela.setHasApa(checkbApa.isSelected());
                    parcela.setHasGaz(checkbGaz.isSelected());
                    parcela.setHasElectricitate(checkbElectricitate.isSelected());
                    parcela.sethasCanalizare(checkbCanalizare.isSelected());

                    // teren
                    if (cboxCategorieProprietate.getSelectedItem() == CategorieProprietate.Teren)
                    {
                        // creare teren
                        Teren teren = new Teren();
                        teren.setDispozitieTeren((DispozitieTeren) cboxDispozitieTeren.getSelectedItem());
                        teren.setParcelaTeren(parcela);

                        // proprietate
                        teren.setTitluProprietate(fieldTitlu.getText());
                        teren.setDescriereProprietate(areaDescriere.getText());
                        teren.setPretProprietate(Integer.parseInt(fieldPret.getText()));
                        teren.setLocatieProprietate(locatie);
                        teren.setProprietarProprietate((Client) cboxProprietar.getSelectedItem());
                        teren.setAgentProprietate(homeUI.mainAgent);
                        teren.setDispozitieProprietate(DispozitieProprietate.Activ);
                        teren.setDataProprietate(new Date(System.currentTimeMillis()));

                        TerenServices terenServices = new TerenServices();
                        queryOutcome = terenServices.addTeren(teren);
                    }
                    else
                    {
                        // create compartimentare
                        Compartimentare compartimentare = new Compartimentare();
                        compartimentare.setOpenspace((Integer) cboxOpenspace.getSelectedItem());
                        compartimentare.setLiving((Integer) cboxOpenspace.getSelectedItem());
                        compartimentare.setDormitor((Integer) cboxDormitor.getSelectedItem());
                        compartimentare.setDressing((Integer) cboxDressing.getSelectedItem());
                        compartimentare.setBucatarie((Integer) cboxBucatarie.getSelectedItem());
                        compartimentare.setDebara((Integer) cboxDebara.getSelectedItem());
                        compartimentare.setBaie((Integer) cboxBaie.getSelectedItem());
                        compartimentare.setHol((Integer) cboxHol.getSelectedItem());
                        compartimentare.setMansarda((Integer) cboxMansarda.getSelectedItem());
                        compartimentare.setBalcon((Integer) cboxBalcon.getSelectedItem());
                        compartimentare.setTerasa((Integer) cboxTerasa.getSelectedItem());
                        compartimentare.setGradina((Integer) cboxGradina.getSelectedItem());
                        compartimentare.setParcare((Integer) cboxParcare.getSelectedItem());
                        compartimentare.setGaraj((Integer) cboxGaraj.getSelectedItem());
                        compartimentare.setBoxa((Integer) cboxBoxa.getSelectedItem());
                        compartimentare.setPod((Integer) cboxPod.getSelectedItem());

                        // create constructie
                        Constructie constructie = new Constructie();
                        constructie.setSuprafataUtilizabila(Integer.parseInt(fieldSuprafataUtilizabila.getText()));
                        constructie.setSuprafataConstructie(Integer.parseInt(fieldSuprafataConstructie.getText()));
                        constructie.setAnConstructie(Integer.parseInt(fieldAnConstructie.getText()));
                        constructie.setStructuraConstructie((StructuraConstructie) cboxStructuraConstructie.getSelectedItem());
                        constructie.setInaltimeConstructie((Integer) cboxInaltimeConstructie.getSelectedItem());
                        constructie.setDispozitieActuala((DispozitieConstructie) cboxDispozitieActuala.getSelectedItem());
                        constructie.setDispozitiePredare((DispozitieConstructie) cboxDispozitiePredare.getSelectedItem());
                        constructie.setCompartimentareConstructie(compartimentare);
                        constructie.setParcelaConstructie(parcela);

                        if (cboxCategorieProprietate.getSelectedItem() == CategorieProprietate.Casa)
                        {
                            Casa casa = new Casa();
                            casa.setConstructieCasa(constructie);

                            // proprietate
                            casa.setTitluProprietate(fieldTitlu.getText());
                            casa.setDescriereProprietate(areaDescriere.getText());
                            casa.setPretProprietate(Integer.parseInt(fieldPret.getText()));
                            casa.setLocatieProprietate(locatie);
                            casa.setProprietarProprietate((Client) cboxProprietar.getSelectedItem());
                            casa.setAgentProprietate(homeUI.mainAgent);
                            casa.setDispozitieProprietate(DispozitieProprietate.Activ);
                            casa.setDataProprietate(new Date(System.currentTimeMillis()));

                            CasaServices casaServices = new CasaServices();
                            queryOutcome = casaServices.addCasa(casa);
                        }

                        else if (cboxCategorieProprietate.getSelectedItem() == CategorieProprietate.Apartament)
                        {
                            Apartament apartament = new Apartament();
                            apartament.setEtajApartament((EtajApartament) cboxEtajApartament.getSelectedItem());
                            apartament.setConstructieApartament(constructie);

                            // proprietate
                            apartament.setTitluProprietate(fieldTitlu.getText());
                            apartament.setDescriereProprietate(areaDescriere.getText());
                            apartament.setPretProprietate(Integer.parseInt(fieldPret.getText()));
                            apartament.setLocatieProprietate(locatie);
                            apartament.setProprietarProprietate((Client) cboxProprietar.getSelectedItem());
                            apartament.setAgentProprietate(homeUI.mainAgent);
                            apartament.setDispozitieProprietate(DispozitieProprietate.Activ);
                            apartament.setDataProprietate(new Date(System.currentTimeMillis()));

                            ApartamentServices apartamentServices = new ApartamentServices();
                            queryOutcome = apartamentServices.addApartament(apartament);
                        }
                    }

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

    // activeaza un panel
    private void enablePanelComponents(JPanel panel)
    {
        for (Component component : panel.getComponents())
        {
            component.setEnabled(true);
        }

        panel.setEnabled(true);
    }

    // dezactiveaza un panel
    private void disablePanelComponents(JPanel panel)
    {
        for (Component component : panel.getComponents())
        {
            component.setEnabled(false);
        }

        panel.setEnabled(false);
    }

    private void displayMessage(QueryMessage queryMessage)
    {
        labelResult.setText(queryMessage.getMessage());
        labelResult.setForeground(queryMessage.getColor());
        labelResult.setVisible(true);
    }

    private boolean isFormCompleted()
    {
                // title/pret/descriere not empty
        return !fieldTitlu.getText().isEmpty() && !fieldPret.getText().isEmpty() && !areaDescriere.getText().isEmpty() &&
                // and locatie not empty, judet selected
                !fieldLocatie.getText().isEmpty() && cboxJudet.getSelectedItem() instanceof Judet &&
                // and tip oras selected and oras/cartier selected
                ((rbuttonOras.isSelected() &&
                cboxOras.getSelectedItem() instanceof Oras && cboxCartier.getSelectedItem() instanceof Cartier) ^
                // or tip comuna selected and comuna/sat selected
                (rbuttonComuna.isSelected() &&
                cboxComuna.getSelectedItem() instanceof Comuna && cboxSat.getSelectedItem() instanceof Sat)) &&
                // and categorie proprietate selected
                cboxCategorieProprietate.getSelectedItem() instanceof CategorieProprietate &&
                // and proprietar selected
                cboxProprietar.getSelectedItem() instanceof Client &&
                // and categorie teren selected and form teren completed
                ((cboxCategorieProprietate.getSelectedItem() == CategorieProprietate.Teren &&
                isFormTerenCompleted()) ||
                 // or categorie casa selected and form casa completed
                (cboxCategorieProprietate.getSelectedItem() == CategorieProprietate.Casa &&
                isFormCasaCompleted()) ||
                // or categorie apartament selected and form apartament completed
                (cboxCategorieProprietate.getSelectedItem() == CategorieProprietate.Apartament &&
                isFormApartamentCompleted()));
    }

    private boolean isFormTerenCompleted()
    {
        // suprafata parcela not empty and dispozitie teren selected
        return !fieldSuprafataParcela.getText().isEmpty() && cboxDispozitieTeren.getSelectedItem() instanceof DispozitieTeren;
    }

    private boolean isFormCasaCompleted()
    {
             // suprafata utila/construita not empty
        return !fieldSuprafataUtilizabila.getText().isEmpty() && !fieldSuprafataConstructie.getText().isEmpty() &&
                // and an not empty
                !fieldAnConstructie.getText().isEmpty() &&
                // and structura constructie selected
                cboxStructuraConstructie.getSelectedItem() instanceof StructuraConstructie &&
                // and inaltime constructie selected
                cboxInaltimeConstructie.getSelectedItem() instanceof Integer &&
                // and dispozitie actuala selected
                cboxDispozitieActuala.getSelectedItem() instanceof DispozitieConstructie &&
                // and dispozitie predare selected
                cboxDispozitiePredare.getSelectedItem() instanceof DispozitieConstructie &&
                // and all fields from compartimentare selected
                cboxOpenspace.getSelectedItem() instanceof Integer && cboxLiving.getSelectedItem() instanceof Integer &&
                cboxDormitor.getSelectedItem() instanceof Integer && cboxDressing.getSelectedItem() instanceof Integer &&
                cboxBucatarie.getSelectedItem() instanceof Integer && cboxDebara.getSelectedItem() instanceof Integer &&
                cboxBaie.getSelectedItem() instanceof Integer && cboxHol.getSelectedItem() instanceof Integer &&
                cboxMansarda.getSelectedItem() instanceof Integer && cboxBalcon.getSelectedItem() instanceof Integer &&
                cboxTerasa.getSelectedItem() instanceof Integer && cboxGradina.getSelectedItem() instanceof Integer &&
                cboxParcare.getSelectedItem() instanceof Integer && cboxGaraj.getSelectedItem() instanceof Integer &&
                cboxBoxa.getSelectedItem() instanceof Integer && cboxPod.getSelectedItem() instanceof Integer &&
                // and form teren completed
                isFormTerenCompleted();
    }

    private boolean isFormApartamentCompleted()
    {
        // etaj selected and form casa completed;
        return cboxEtajApartament.getSelectedItem() instanceof EtajApartament && isFormCasaCompleted();
    }

}
