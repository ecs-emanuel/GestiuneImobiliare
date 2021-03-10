package Interface.HomeUI;

import Entities.Locatie.Comuna;
import Entities.Locatie.Locatie;
import Entities.Locatie.Oras;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;
import Entities.Programare;
import Entities.Proprietate.Apartament;
import Entities.Proprietate.Casa;
import Entities.Proprietate.Proprietate;
import Entities.Proprietate.Teren;
import Services.ProgramareServices;
import Services.ProprietateServices.ApartamentServices;
import Services.ProprietateServices.CasaServices;
import Services.ProprietateServices.ProprietateServices;
import Services.ProprietateServices.TerenServices;
import Utils.QueryOutcome;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ListaProprietati
{
    private JTable tableContent;

    public void create(HomeUI homeUI)
    {
        addLista(homeUI);
    }

    public void addLista(HomeUI homeUI)
    {
        ApartamentServices apartamentServices = new ApartamentServices();
        CasaServices casaServices = new CasaServices();
        TerenServices terenServices = new TerenServices();

        Pair<List<Apartament>, QueryOutcome> queryOutcomePairApartament;
        QueryOutcome queryOutcomeApartament;
        Pair<List<Casa>, QueryOutcome> queryOutcomePairCasa;
        QueryOutcome queryOutcomeCasa;
        Pair<List<Teren>, QueryOutcome> queryOutcomePairTeren;
        QueryOutcome queryOutcomeTeren;

        // toate proprietatile - else - doar proprietatile agentului
        if (homeUI.checkboxSearch.isSelected())
        {
            queryOutcomePairApartament = apartamentServices.getListaApartamente();
            queryOutcomePairCasa = casaServices.getListaCase();
            queryOutcomePairTeren = terenServices.getListaTerenuri();
        }
        else
        {
            Agent agent = homeUI.mainAgent;
            queryOutcomePairApartament = apartamentServices.getListaApartamente(agent);
            queryOutcomePairCasa = casaServices.getListaCase(agent);
            queryOutcomePairTeren = terenServices.getListaTerenuri(agent);
        }

        //queryOutcomeApartament = queryOutcomePairApartament.getValue();
        //queryOutcomeCasa = queryOutcomePairCasa.getValue();
        //queryOutcomeTeren = queryOutcomePairTeren.getValue();

        List<Proprietate> listaProprietati = new ArrayList<>();
        listaProprietati.addAll(queryOutcomePairApartament.getKey());
        listaProprietati.addAll(queryOutcomePairCasa.getKey());
        listaProprietati.addAll(queryOutcomePairTeren.getKey());

        String[] columnNames = {"Index", "Titlu", "Pret", "Proprietate", "Localitate"};

        int[] columnSizes = {80, 400, 80, 80, 118 };
        int totalColumns = columnNames.length;

        tableContent = new JTable(listaProprietati.size(), totalColumns)
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

        for (int i = 0; i < listaProprietati.size(); i++)
        {
            Proprietate proprietate = listaProprietati.get(i);

            tableContent.setValueAt(proprietate.getIndexProprietate(), i, 0);
            tableContent.setValueAt(proprietate.getTitluProprietate(), i , 1);
            tableContent.setValueAt(proprietate.getPretProprietate(), i, 2);

            String categorieProprietate = proprietate instanceof Apartament ? "Apartament" :
                    proprietate instanceof Casa ? "Casa" : proprietate instanceof Teren ? "Teren" : "";

            tableContent.setValueAt(categorieProprietate, i, 3);

            Locatie locatie = proprietate.getLocatieProprietate();
            Oras oras = locatie.getOrasLocatie();
            Comuna comuna = locatie.getComunaLocatie();

            String localitateProprietate =
                oras != null ? oras.getDenumireOras() :
                comuna != null ? comuna.getDenumireComuna() : "";

            tableContent.setValueAt(localitateProprietate, i, 4);
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
                    Proprietate proprietate = listaProprietati.get(itemIndex);

                    if (proprietate instanceof Apartament)
                    {
                        apartamentServices.delApartament((Apartament) proprietate);
                    }
                    else if (proprietate instanceof Casa)
                    {
                        casaServices.delCasa((Casa) proprietate);
                    }
                    else if (proprietate instanceof Teren)
                    {
                        terenServices.delTeren((Teren) proprietate);
                    }
                    homeUI.buttonCauta.doClick();
                }
            }
        });

        homeUI.scrollContent.setViewportView(tableContent);
    }
}
