package Interface.HomeUI;

import Utils.CustomColor;
import Entities.Persoana.Agent;
import Entities.Persoana.Persoana;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FiltruProprietate
{
    JPanel panelLocatie;
    JLabel labelTara;
    JComboBox<String> comboboxTara;
    JLabel labelJudet;
    JComboBox<String> comboboxJudet;
    JLabel labelOras;
    JComboBox<String> comboboxOras;
    JLabel labelCartier;
    JComboBox<String> comboboxCartier;

    JPanel panelProprietate;

    public void create(HomeUI homeUI)
    {
        //homeUI.clearPanel(homeUI.panelContent);
        addFiltruLocatie(homeUI);
        addFiltruProprietati(homeUI);
    }

    private void addFiltruLocatie(HomeUI homeUI)
    {
        JPanel panelLocatie = new JPanel();
        homeUI.panelContent.add(panelLocatie);
        panelLocatie.setLayout(null);
        panelLocatie.setVisible(true);
        panelLocatie.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelLocatie.setBorder(BorderFactory.createTitledBorder("Locatie"));
        panelLocatie.setBounds(10, 10, homeUI.FRAME_WIDTH - 245, 100);

        labelTara = new JLabel("Tara");
        panelLocatie.add(labelTara);
        labelTara.setLayout(null);
        labelTara.setVisible(true);
        labelTara.setBounds(18, 30, 80, 20);

        comboboxTara = new JComboBox<>();
        panelLocatie.add(comboboxTara);
        comboboxTara.setLayout(null);
        comboboxTara.setVisible(true);
        comboboxTara.setBounds(15, 50, 133, 25);

        labelJudet = new JLabel("Judet");
        panelLocatie.add(labelJudet);
        labelJudet.setLayout(null);
        labelJudet.setVisible(true);
        labelJudet.setBounds(166, 30, 80, 20);

        comboboxJudet = new JComboBox<>();
        panelLocatie.add(comboboxJudet);
        comboboxJudet.setLayout(null);
        comboboxJudet.setVisible(true);
        comboboxJudet.setBounds(163, 50, 133, 25);

        labelOras = new JLabel("Oras/Comuna");
        panelLocatie.add(labelOras);
        labelOras.setLayout(null);
        labelOras.setVisible(true);
        labelOras.setBounds(314, 30, 80, 20);

        comboboxOras = new JComboBox<>();
        panelLocatie.add(comboboxOras);
        comboboxOras.setLayout(null);
        comboboxOras.setVisible(true);
        comboboxOras.setBounds(311, 50, 133, 25);

        labelCartier = new JLabel("Cartier/Sat");
        panelLocatie.add(labelCartier);
        labelCartier.setLayout(null);
        labelCartier.setVisible(true);
        labelCartier.setBounds(462, 30, 80, 20);

        comboboxCartier = new JComboBox<>();
        panelLocatie.add(comboboxCartier);
        comboboxCartier.setLayout(null);
        comboboxCartier.setVisible(true);
        comboboxCartier.setBounds(459, 50, 133, 25);
    }

    public void addFiltruProprietati(HomeUI homeUI)
    {
        JPanel panelProprietate = new JPanel();
        homeUI.panelContent.add(panelProprietate);
        panelProprietate.setLayout(null);
        panelProprietate.setVisible(true);
        panelProprietate.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelProprietate.setBorder(BorderFactory.createTitledBorder("Proprietate"));
        panelProprietate.setBounds(10, 120, homeUI.FRAME_WIDTH - 245, 100);

        List<Persoana> box = new ArrayList<>();

        Agent agent = new Agent();

        box.add(agent);

        if (box.get(0) instanceof Agent)
        {
            Agent agent2 = (Agent)box.get(0);
        }

    }
}
