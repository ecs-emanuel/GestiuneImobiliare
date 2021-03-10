package Interface.HomeUI;

import Entities.Locatie.Locatie;
import Entities.Persoana.Client;
import Entities.Programare;
import Services.PersoanaServices.ClientServices;
import Services.ProgramareServices;
import Utils.QueryOutcome;
import javafx.util.Pair;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.ZoneId;
import java.util.List;

public class ListaProgramari
{
    private JTable tableContent;

    public void create(HomeUI homeUI)
    {
        addLista(homeUI);
    }

    public void addLista(HomeUI homeUI)
    {
        ProgramareServices programareServices = new ProgramareServices();
        Pair<List<Programare>, QueryOutcome> queryOutcomePair;

        // toate programarile - else - doar programarile valabile
        if (homeUI.checkboxSearch.isSelected())
        {
            queryOutcomePair = programareServices.getListaProgramari(homeUI.mainAgent, true);
        }
        else
        {
            queryOutcomePair = programareServices.getListaProgramari(homeUI.mainAgent, false);
        }

        List<Programare> listaProgramari = queryOutcomePair.getKey();

        String[] columnNames = {"Data", "Ora", "Nume", "Prenume", "Telefon", "Email"};

        int[] columnSizes = {100, 80, 120, 150, 120, 188 };
        int totalColumns = columnNames.length;

        tableContent = new JTable(listaProgramari.size(), totalColumns)
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

        for (int i = 0; i < listaProgramari.size(); i++)
        {
            Programare programare = listaProgramari.get(i);
            Client client = programare.getClient();

            tableContent.setValueAt(programare.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), i , 0);
            tableContent.setValueAt(programare.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalTime(), i, 1);
            tableContent.setValueAt(client.getNumePersoana(), i, 2);
            tableContent.setValueAt(client.getPrenumePersoana(), i, 3);
            tableContent.setValueAt(client.getTelefonPersoana(), i, 4);
            tableContent.setValueAt(client.getEmailPersoana(), i, 5);
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
                    homeUI.buttonSterge.setEnabled(true);
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

        homeUI.buttonSterge.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int itemIndex = tableContent.getSelectedRow();

                if (itemIndex >= 0)
                {
                    programareServices.delProgramare(listaProgramari.get(itemIndex));
                    homeUI.buttonCauta.doClick();
                }
            }
        });

        homeUI.scrollContent.setViewportView(tableContent);
    }
}
