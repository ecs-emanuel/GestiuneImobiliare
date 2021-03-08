package Interface.HomeUI;

import Entities.Programare;
import Services.PersoanaServices.ClientServices;
import Utils.CustomColor;
import Entities.Persoana.Client;
import Utils.QueryOutcome;
import javafx.util.Pair;

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
import java.util.List;

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

        cboxClient.setRenderer(new DefaultListCellRenderer()
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
        Pair<java.util.List<Client>, QueryOutcome> queryOutcome = clientServices.getListaClienti();

        if (queryOutcome.getValue() == QueryOutcome.SUCCESS)
        {
            List<Client> listaClienti = queryOutcome.getKey();

            for (Client client : listaClienti)
            {
                cboxClient.addItem(client);
            }
        }

        cboxClient.setSelectedIndex(-1);

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
                    Date data = (Date) spinnerData.getValue();
                    Date ora = (Date) spinnerOra.getValue();

                    LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalTime localTime = ora.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                    Timestamp timeStamp = Timestamp.valueOf(localDateTime);

                    Client client = (Client) cboxClient.getSelectedItem();

                    Programare programare = new Programare();
                    programare.setAgent(homeUI.mainAgent);
                    programare.setClient(client);
                    programare.setData(timeStamp);

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
