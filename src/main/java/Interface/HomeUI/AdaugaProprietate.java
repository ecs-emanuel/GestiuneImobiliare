package Interface.HomeUI;

import Components.*;
import Entities.Persoana.Client;
import Services.LocatieServices.*;
import Utils.CustomColor;
import Entities.Locatie.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AdaugaProprietate
{
    // Panel descriere
    private JPanel panelDescriere;
    private JLabel labelTitlu;
    private JTextField fieldTitlu;
    private JLabel labelPret;
    private JTextField fieldPret;
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
    private JTextField fieldSuprafataParcela;
    private JLabel labelDispozitieTeren;
    private JComboBox<DispozitieTeren> cboxDispozitieTeren;
    private JCheckBox checkbApa;
    private JCheckBox checkbGaz;
    private JCheckBox checkbElectricitate;
    private JCheckBox checkbCanalizare;

    // Panel constructie
    private JPanel panelConstructie;
    private JLabel labelSuprafataUtilizabila;
    private JTextField fieldSuprafataUtilizabila;
    private JLabel labelSuprafataConstructie;
    private JTextField fieldSuprafataConstructie;
    private JLabel labelAnConstructie;
    private JTextField fieldAnConstructie;
    private JLabel labelStructuraConstructie;
    private JComboBox<StructuraConstructie> cboxStructuraConstructie;
    private JLabel labelEtajConstructie;
    private JComboBox<EtajApartament> cboxEtajConstructie;
    private JLabel labelInaltimeConstructie;
    private JComboBox<String> cboxInaltimeConstructie;
    private JLabel labelDispozitieActuala;
    private JComboBox<DispozitieConstructie> cboxDispozitieActuala;
    private JLabel labelDispozitiePredare;
    private JComboBox<DispozitieConstructie> cboxDispozitiePredare;

    // Panel compartimentare
    private JPanel panelCompartimentare;
    private JLabel labelOpenspace;
    private JComboBox<String> cboxOpenspace;
    private JLabel labelLiving;
    private JComboBox<String> cboxLiving;
    private JLabel labelDormitor;
    private JComboBox<String> cboxDormitor;
    private JLabel labelDressing;
    private JComboBox<String> cboxDressing;
    private JLabel labelBucatarie;
    private JComboBox<String> cboxBucatarie;
    private JLabel labelDebara;
    private JComboBox<String> cboxDebara;
    private JLabel labelBaie;
    private JComboBox<String> cboxBaie;
    private JLabel labelHol;
    private JComboBox<String> cboxHol;
    private JLabel labelMansarda;
    private JComboBox<String> cboxMansarda;
    private JLabel labelBalcon;
    private JComboBox<String> cboxBalcon;
    private JLabel labelTerasa;
    private JComboBox<String> cboxTerasa;
    private JLabel labelGradina;
    private JComboBox<String> cboxGradina;
    private JLabel labelParcare;
    private JComboBox<String> cboxParcare;
    private JLabel labelGaraj;
    private JComboBox<String> cboxGaraj;
    private JLabel labelBoxa;
    private JComboBox<String> cboxBoxa;
    private JLabel labelPod;
    private JComboBox<String> cboxPod;

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

    // butoane actiune
    private JSeparator separatorButtons;
    private JButton buttonAccepta;
    private JButton buttonAnuleaza;

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
        homeUI.panelContent.setPreferredSize(new Dimension(1000 - 55 - 190, 1155));

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

        fieldPret = new JTextField(10);
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
                        labelEtajConstructie.setEnabled(false);
                        cboxEtajConstructie.setEnabled(false);
                        break;

                    case Apartament:
                        enablePanelComponents(panelConstructie);
                        enablePanelComponents(panelCompartimentare);
                        labelDispozitieTeren.setEnabled(false);
                        cboxDispozitieTeren.setEnabled(false);
                        cboxDispozitieTeren.setSelectedItem(DispozitieTeren.Intravilan);
                        labelEtajConstructie.setEnabled(true);
                        cboxEtajConstructie.setEnabled(true);
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

        fieldSuprafataParcela = new JTextField(10);
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

        // suprafata utilizabila
        labelSuprafataUtilizabila = new JLabel("Suprafata Utilizabila");
        panelConstructie.add(labelSuprafataUtilizabila);
        labelSuprafataUtilizabila.setBounds(20, 25, 154, 20);

        fieldSuprafataUtilizabila = new JTextField(10);
        panelConstructie.add(fieldSuprafataUtilizabila);
        fieldSuprafataUtilizabila.setBounds(15, 45, 154, 30);

        // suprafata constructie
        labelSuprafataConstructie = new JLabel("Suprafata Constructie");
        panelConstructie.add(labelSuprafataConstructie);
        labelSuprafataConstructie.setBounds(204, 25, 154, 20);

        fieldSuprafataConstructie = new JTextField(10);
        panelConstructie.add(fieldSuprafataConstructie);
        fieldSuprafataConstructie.setBounds(199, 45, 154, 30);

        // an constructie
        labelAnConstructie = new JLabel("An Constructie");
        panelConstructie.add(labelAnConstructie);
        labelAnConstructie.setBounds(388, 25, 154, 20);

        fieldAnConstructie = new JTextField(10);
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
        labelEtajConstructie = new JLabel("Etaj");
        panelConstructie.add(labelEtajConstructie);
        labelEtajConstructie.setBounds(20, 80, 154, 20);

        cboxEtajConstructie = new JComboBox<>();
        panelConstructie.add(cboxEtajConstructie);
        cboxEtajConstructie.setBounds(15, 100, 154, 30);

        for (EtajApartament item : EtajApartament.values())
        {
            cboxEtajConstructie.addItem(item);
        }
        cboxEtajConstructie.setSelectedIndex(-1);

        // inaltime constructie
        labelInaltimeConstructie = new JLabel("Inaltime");
        panelConstructie.add(labelInaltimeConstructie);
        labelInaltimeConstructie.setBounds(204, 80, 154, 20);

        cboxInaltimeConstructie = new JComboBox<>();
        panelConstructie.add(cboxInaltimeConstructie);
        cboxInaltimeConstructie.setBounds(199, 100, 154, 30);

        for (int i = 0; i <= 20; i++)
        {
            cboxInaltimeConstructie.addItem(String.valueOf(i));
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
            String item = String.valueOf(i);

            cboxOpenspace.addItem(item);
            cboxLiving.addItem(item);
            cboxDormitor.addItem(item);
            cboxDressing.addItem(item);
            cboxBucatarie.addItem(item);
            cboxDebara.addItem(item);
            cboxBaie.addItem(item);
            cboxHol.addItem(item);
            cboxMansarda.addItem(item);
            cboxBalcon.addItem(item);
            cboxTerasa.addItem(item);
            cboxGradina.addItem(item);
            cboxParcare.addItem(item);
            cboxGaraj.addItem(item);
            cboxBoxa.addItem(item);
            cboxPod.addItem(item);
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

        // creaza lista judete
        JudetServices judetServices = new JudetServices();
        java.util.List<Judet> listaJudete = judetServices.getListaJudete().getKey();

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

        // Select empty in combobox
        cboxJudet.setSelectedIndex(-1);

        // updateaza lista cu orase/comune cand se alege un judet
        cboxJudet.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                java.util.List<Oras> listaOrase = new ArrayList<>();
                java.util.List<Comuna> listaComune = new ArrayList<>();
                Object selectedItem = cboxJudet.getSelectedItem();

                // Judet selectat
                if (selectedItem instanceof Judet)
                {
                    OrasServices orasServices = new OrasServices();
                    listaOrase = orasServices.getListaOrase((Judet) selectedItem).getKey();

                    ComunaServices comunaServices = new ComunaServices();
                    listaComune = comunaServices.getListaComune((Judet) selectedItem).getKey();

                    // Enable orase/comune
                    rbuttonOras.setEnabled(true);
                    rbuttonComuna.setEnabled(true);
                }
                else
                {
                    // Disable orase/comune
                    rbuttonOras.setEnabled(false);
                    rbuttonComuna.setEnabled(false);
                }

                cboxOras.removeAllItems();
                cboxComuna.removeAllItems();

                for (Oras oras : listaOrase)
                {
                    cboxOras.addItem(oras);
                }

                for (Comuna comuna : listaComune)
                {
                    cboxComuna.addItem(comuna);
                }

                cboxOras.setSelectedIndex(-1);
                cboxComuna.setSelectedIndex(-1);
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
                java.util.List<Cartier> listaCartiere = new ArrayList<>();
                Object selectedItem = cboxOras.getSelectedItem();

                if (selectedItem instanceof Oras)
                {
                    CartierServices cartierServices = new CartierServices();
                    listaCartiere = cartierServices.getListaCartiere((Oras) selectedItem).getKey();
                }

                cboxCartier.removeAllItems();

                for (Cartier cartier : listaCartiere)
                {
                    cboxCartier.addItem(cartier);
                }

                cboxCartier.setSelectedIndex(-1);
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
                List<Sat> listaSate = new ArrayList<>();
                Object selectedItem = cboxComuna.getSelectedItem();

                if (selectedItem instanceof Comuna)
                {
                    SatServices satServices = new SatServices();
                    listaSate = satServices.getListaSate((Comuna) selectedItem).getKey();
                }

                cboxSat.removeAllItems();

                for (Sat sat : listaSate)
                {
                    cboxSat.addItem(sat);
                }

                cboxSat.setSelectedIndex(-1);
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
        separatorButtons = new JSeparator(SwingConstants.HORIZONTAL);
        homeUI.panelContent.add(separatorButtons);
        separatorButtons.setBounds(15, 1080, 730, 3);

        buttonAccepta = new JButton("Accepta");
        homeUI.panelContent.add(buttonAccepta);
        buttonAccepta.setBounds(160, 1100, 200, 35);

        buttonAnuleaza = new JButton("Anuleaza");
        homeUI.panelContent.add(buttonAnuleaza);
        buttonAnuleaza.setBounds(375, 1100, 200, 35);
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
}
