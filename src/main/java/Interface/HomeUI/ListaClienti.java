package Interface.HomeUI;

import Entities.Locatie.Locatie;
import Entities.Persoana.Client;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaClienti
{
    private JTable tableContent;

    public void create(HomeUI homeUI)
    {
        addLista(homeUI);
    }

    public void addLista(HomeUI homeUI)
    {
        ClientServices clientServices = new ClientServices();
        Pair<List<Client>, QueryOutcome> queryOutcomePair = clientServices.getListaClienti();

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
        homeUI.scrollContent.setViewportView(tableContent);
    }
}
