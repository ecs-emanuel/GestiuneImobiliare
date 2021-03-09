package Interface.HomeUI;

import Entities.Locatie.Locatie;
import Entities.Persoana.Client;
import Services.PersoanaServices.ClientServices;
import Utils.CustomColor;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaClienti
{
    private JTable tableClienti;
    private JScrollPane scrollTableClienti;

    private final int LIST_ITEM_HEIGHT = 30;

    public void create(HomeUI homeUI)
    {
        addLista(homeUI);
    }

    public void addLista(HomeUI homeUI)
    {
        ClientServices clientServices = new ClientServices();
        Pair<List<Client>, QueryOutcome> queryOutcomePair = clientServices.getListaClienti();

        List<Client> listaClienti = queryOutcomePair.getKey();

        String[] columnNames = {"Nume", "Prenume", "Telefon", "Email", "Judet", "Oras/Comuna", "Cartier/Sat", "Strada" };

        int[] columnSizes = {50, 150, 70, 120, 100, 100, 100, 150 };
        int totalColumns = columnNames.length;

        JTable table = new JTable(listaClienti.size(), totalColumns);

        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = table.getColumnModel();

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

            table.setValueAt(client.getNumePersoana(), i , 0);
            table.setValueAt(client.getPrenumePersoana(), i, 1);
            table.setValueAt(client.getTelefonPersoana(), i, 2);
            table.setValueAt(client.getEmailPersoana(), i, 3);
            table.setValueAt(domiciliu.getJudetLocatie().getDenumireJudet(), i, 4);

            if (domiciliu.getOrasLocatie() != null)
            {
                table.setValueAt(domiciliu.getOrasLocatie().getDenumireOras(), i, 5);
                table.setValueAt(domiciliu.getCartierLocatie().getDenumireCartier(), i, 6);
            }
            else if (domiciliu.getComunaLocatie() != null)
            {
                table.setValueAt(domiciliu.getComunaLocatie().getDenumireComuna(), i, 5);
                table.setValueAt(domiciliu.getSatLocatie().getDenumireSat(), i, 6);
            }

            table.setValueAt(domiciliu.getDenumireLocatie(), i, 7);
        }

        scrollTableClienti = new JScrollPane(table);
        //scrollTableClienti.getViewport().add(table);
        homeUI.panelContent.add(scrollTableClienti);
        scrollTableClienti.setBounds(10, 12, 735, 430);

    }
}
