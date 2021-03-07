package Interface.HomeUI;

import Entities.Programare;
import Utils.CustomColor;
import Entities.Persoana.Client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class AdaugaProgramare
{
    // panel programare
    protected JPanel panelProgramare;
    protected JLabel labelData;
    protected JSpinner spinnerData;
    protected JLabel labelOra;
    protected JSpinner spinnerOra;
    protected JLabel labelClient;
    protected JComboBox<Client> cboxClient;

    // butoane actiune
    private JSeparator separatorButtons;
    private JButton buttonAccepta;
    private JButton buttonAnuleaza;

    public void create(HomeUI homeUI)
    {
        addPanelProgramare(homeUI);
        addButtons(homeUI);
        homeUI.panelContent.setPreferredSize(new Dimension(1000 - 55 - 190, 600));
    }

    private void addPanelProgramare(HomeUI homeUI)
    {
        panelProgramare = new JPanel();
        homeUI.panelContent.add(panelProgramare);
        panelProgramare.setLayout(null);
        panelProgramare.setVisible(true);
        panelProgramare.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelProgramare.setBorder(new TitledBorder("Programare"));
        panelProgramare.setBounds(10, 15, 735, 95);

        labelClient = new JLabel("Client");
        panelProgramare.add(labelClient);
        labelClient.setBounds(20, 25, 337, 20);

        cboxClient = new JComboBox<>();
        panelProgramare.add(cboxClient);
        cboxClient.setBounds(15, 45, 337, 30);

        labelData = new JLabel("Data");
        panelProgramare.add(labelData);
        labelData.setBounds(387, 25, 154, 20);

        spinnerData = new JSpinner(new SpinnerDateModel());
        panelProgramare.add(spinnerData);
        spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd-MMMMM-yyyy"));
        spinnerData.setBounds(382, 45, 154, 30);

        labelOra = new JLabel("Ora");
        panelProgramare.add(labelOra);
        labelOra.setBounds(571, 25, 154, 20);

        spinnerOra = new JSpinner(new SpinnerDateModel());
        panelProgramare.add(spinnerOra);
        spinnerOra.setEditor(new JSpinner.DateEditor(spinnerOra, "HH:mm"));
        spinnerOra.setBounds(566, 45, 154, 30);
    }

    private void addButtons(HomeUI homeUI)
    {
        separatorButtons = new JSeparator(SwingConstants.HORIZONTAL);
        homeUI.panelContent.add(separatorButtons);
        separatorButtons.setBounds(15, 535, 730, 3);

        buttonAccepta = new JButton("Accepta");
        homeUI.panelContent.add(buttonAccepta);
        buttonAccepta.setBounds(160, 555, 200, 35);

        buttonAccepta.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isFormCompleted())
                {


                    // add
                }
            }
        });

        buttonAnuleaza = new JButton("Anuleaza");
        homeUI.panelContent.add(buttonAnuleaza);
        buttonAnuleaza.setBounds(375, 555, 200, 35);
    }

    private boolean isFormCompleted()
    {
        return cboxClient.getSelectedItem() instanceof Client;
    }
}
