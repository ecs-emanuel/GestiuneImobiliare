package Interface.HomeUI;

import Entities.Programare;
import Services.PersoanaServices.ClientServices;
import Services.ProgramareServices;
import Utils.CustomColor;
import Entities.Persoana.Client;
import Utils.QueryMessage;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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


public class AdaugaProgramare
{
    // main panel
    private JPanel panelContent;

    // panel programare
    protected JPanel panelProgramare;
    protected JLabel labelData;
    protected JSpinner spinnerData;
    protected JLabel labelOra;
    protected JSpinner spinnerOra;
    protected JLabel labelClient;
    protected JComboBox<Client> cboxClient;

    // panel buttons
    private JPanel panelButtons;
    private JSeparator separatorButtons;
    private JButton buttonAccepta;
    private JButton buttonAnuleaza;
    private JLabel labelResult;

    private boolean updateForm = false;
    private Programare oldProgramare;

    public void create(HomeUI homeUI)
    {
        handleForm(homeUI);
    }

    public void create(HomeUI homeUI, Programare programare)
    {
        handleForm(homeUI);
        updateForm(programare);
    }

    private void handleForm(HomeUI homeUI)
    {
        homeUI.clearPanel(homeUI.scrollContent);
        panelContent = new JPanel();
        homeUI.clearPanel(homeUI.scrollContent);
        homeUI.scrollContent.setViewportView(panelContent);
        panelContent.setLayout(null);
        panelContent.setVisible(true);
        panelContent.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelContent.setPreferredSize(new Dimension(1000 - 55 - 190, 590));

        addPanelProgramare();
        addButtons(homeUI);
    }

    private void updateForm(Programare programare)
    {
        updateForm = true;
        oldProgramare = programare;

        Client client = programare.getClient();

        for (int i = 0; i < cboxClient.getItemCount(); i++)
        {
            if (cboxClient.getItemAt(i).getIndexClient() == client.getIndexClient())
            {
                cboxClient.setSelectedIndex(i);
            }
        }

        spinnerData.setValue(new Date(programare.getData().getTime()));
        spinnerOra.setValue(new Date(programare.getData().getTime()));

        buttonAccepta.setText("Modifica");
    }

    private void addPanelProgramare()
    {
        panelProgramare = new JPanel();
        panelContent.add(panelProgramare);
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
        Pair<List<Client>, QueryOutcome> queryOutcomePair = clientServices.getListaClienti();

        if (queryOutcomePair.getValue() == QueryOutcome.SUCCESS)
        {
            List<Client> listaClienti = queryOutcomePair.getKey();

            for (Client client : listaClienti)
            {
                cboxClient.addItem(client);
            }

            cboxClient.setSelectedIndex(-1);
        }

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
        panelButtons = new JPanel();
        panelContent.add(panelButtons);
        panelButtons.setLayout(null);
        panelButtons.setVisible(true);
        panelButtons.setBackground(CustomColor.GRAY_VERYLIGHT.getColor());
        panelButtons.setBounds(10, 495, 735, 150);

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
                    Date data = (Date) spinnerData.getValue();
                    Date ora = (Date) spinnerOra.getValue();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                    LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalTime localTime = ora.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().truncatedTo(ChronoUnit.MINUTES);
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                    Timestamp timeStamp = Timestamp.valueOf(localDateTime);

                    Client client = (Client) cboxClient.getSelectedItem();

                    Programare programare = new Programare();
                    programare.setAgent(homeUI.mainAgent);
                    programare.setClient(client);
                    programare.setData(timeStamp);

                    ProgramareServices programareServices = new ProgramareServices();
                    QueryOutcome queryOutcome;

                    if (updateForm && oldProgramare != null)
                    {
                        queryOutcome = programareServices.modProgramare(oldProgramare, programare);
                    }
                    else
                    {
                        queryOutcome = programareServices.addProgramare(programare);
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

        buttonAnuleaza.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                homeUI.clearPanel(homeUI.scrollContent);
            }
        });

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
        return cboxClient.getSelectedItem() instanceof Client;
    }
}
