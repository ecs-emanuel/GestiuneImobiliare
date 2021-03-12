package Interface.HomeUI;

import Entities.Locatie.Locatie;
import Entities.Persoana.Client;
import Entities.Programare;
import Services.PersoanaServices.ClientServices;
import Utils.CustomColor;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaClienti
{
    private JTable tableContent;

    public void create(HomeUI homeUI)
    {
        homeUI.clearPanel(homeUI.scrollContent);
        homeUI.buttonSterge.setEnabled(false);
        homeUI.buttonModifica.setEnabled(false);
        addLista(homeUI);
    }

    public void addLista(HomeUI homeUI)
    {
        ClientServices clientServices = new ClientServices();
        Pair<List<Client>, QueryOutcome> queryOutcomePair;

        if (homeUI.fieldSearch.getText().isEmpty())
        {
            queryOutcomePair = clientServices.getListaClienti();
        }
        else
        {
            queryOutcomePair = clientServices.getListaClient(homeUI.fieldSearch.getText());
        }


        List<Client> listaClienti = queryOutcomePair.getKey();

        String[] columnNames = {"Nume", "Prenume", "Telefon", "Email", "Judet", "Oras/Comuna"};

        int[] columnSizes = {100, 150, 120, 200, 80, 108 };
        int totalColumns = columnNames.length;

        tableContent = new JTable(listaClienti.size(), totalColumns)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        tableContent.setRowHeight(30);
        tableContent.setShowGrid(false);
        tableContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableContent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel columnModel = tableContent.getColumnModel();

        for (int i = 0; i < totalColumns; i++)
        {
            TableColumn column = columnModel.getColumn(i);
            column.setHeaderValue(columnNames[i]);
            column.setMinWidth(columnSizes[i]);
            column.setMaxWidth(columnSizes[i]);
        }

        for (int i = 0; i < listaClienti.size(); i++)
        {
            Client client = listaClienti.get(i);
            Locatie domiciliu = client.getDomiciliuPersoana();

            tableContent.setValueAt(client.getNumePersoana(), i , 0);
            tableContent.setValueAt(client.getPrenumePersoana(), i, 1);
            tableContent.setValueAt(client.getTelefonPersoana(), i, 2);
            tableContent.setValueAt(client.getEmailPersoana(), i, 3);
            tableContent.setValueAt(domiciliu.getJudetLocatie().getDenumireJudet(), i, 4);

            if (domiciliu.getOrasLocatie() != null)
            {
                tableContent.setValueAt(domiciliu.getOrasLocatie().getDenumireOras(), i, 5);
            }
            else if (domiciliu.getComunaLocatie() != null)
            {
                tableContent.setValueAt(domiciliu.getComunaLocatie().getDenumireComuna(), i, 5);
            }
        }

        tableContent.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if (tableContent.getSelectedRow() >= 0)
                {
                    homeUI.buttonModifica.setEnabled(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });

        homeUI.buttonModifica.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int itemIndex = tableContent.getSelectedRow();

                if (itemIndex >= 0)
                {
                    homeUI.buttonModifica.setEnabled(false);
                    Client client = listaClienti.get(itemIndex);
                    AdaugaClient adaugaClient = new AdaugaClient();
                    adaugaClient.create(homeUI, client);
                    homeUI.buttonModifica.removeActionListener(this);
                }
            }
        });

        homeUI.scrollContent.setViewportView(tableContent);
    }
}
