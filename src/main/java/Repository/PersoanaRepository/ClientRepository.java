package Repository.PersoanaRepository;

import Entities.Locatie.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.Client;
import javafx.util.Pair;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository
{

    public QueryOutcome addClient(Client client)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        try (Statement statement = connection.createStatement())
        {
            connection.setAutoCommit(false);

            String sqlScript1 = null;
            Locatie domiciliu = client.getDomiciliuPersoana();

            // add locatie
            if (domiciliu.getOrasLocatie() != null)
            {
                sqlScript1 = String.format
                (
                    "INSERT INTO locatii (judetLocatie, orasLocatie, cartierLocatie, denumireLocatie) VALUES\n" +
                    "(%d, %d, %d, '%s');\n",
                    domiciliu.getJudetLocatie().getIndexJudet(),
                    domiciliu.getOrasLocatie().getIndexOras(), domiciliu.getCartierLocatie().getIndexCartier(), domiciliu.getDenumireLocatie()
                );
            }
            else
            {
                sqlScript1 = String.format
                (
                    "INSERT INTO locatii (judetLocatie, comunaLocatie, satLocatie, denumireLocatie) VALUES\n" +
                    "(%d, %d, %d, '%s');\n",
                    domiciliu.getJudetLocatie().getIndexJudet(),
                    domiciliu.getComunaLocatie().getIndexComuna(), domiciliu.getSatLocatie().getIndexSat(), domiciliu.getDenumireLocatie()
                );
            }

            statement.addBatch(sqlScript1);

            // add persoana
            String sqlScript2 = String.format
            (
                "INSERT INTO persoane (numePersoana, prenumePersoana, telefonPersoana, emailPersoana, domiciliuPersoana) VALUES\n" +
                "('%s', '%s', '%s', '%s', LAST_INSERT_ID());\n",
                client.getNumePersoana(), client.getPrenumePersoana(), client.getTelefonPersoana(), client.getEmailPersoana()
            );

            statement.addBatch(sqlScript2);

            // add client
            String sqlScript3 = String.format
            (
                "INSERT INTO clienti (persoanaClient) VALUES\n" +
                "(LAST_INSERT_ID());"
            );

            statement.addBatch(sqlScript3);

            statement.executeBatch();
            connection.commit();

            return QueryOutcome.SUCCESS;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return QueryOutcome.ERROR;
    }

    public Pair<List<Client>, QueryOutcome> getListaClienti()
    {
        List<Client> listaClienti = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return new Pair<>(listaClienti, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT c.indexClient,\n" +
            "p.indexPersoana, numePersoana, prenumePersoana, telefonPersoana, emailPersoana,\n" +
            "l.indexLocatie, l.denumireLocatie,\n" +
            "j.indexJudet, j.denumireJudet,\n" +
            "o.indexOras, o.denumireOras,\n" +
            "ca.indexCartier, ca.denumireCartier,\n" +
            "co.indexComuna, co.denumireComuna,\n" +
            "s.indexSat, s.denumireSat\n" +
            "FROM clienti c \n" +
            "JOIN persoane p\n" +
            "ON c.persoanaClient = p.indexPersoana\n" +
            "JOIN locatii l \n" +
            "ON p.domiciliuPersoana = l.indexLocatie\n" +
            "JOIN judete j\n" +
            "ON l.judetLocatie = j.indexJudet\n" +
            "LEFT JOIN orase o\n" +
            "ON l.orasLocatie = o.indexOras\n" +
            "LEFT JOIN cartiere ca\n" +
            "ON l.cartierLocatie = ca.indexCartier\n" +
            "LEFT JOIN comune co \n" +
            "ON l.comunaLocatie = co.indexComuna\n" +
            "LEFT JOIN sate s \n" +
            "ON l.satLocatie = s.indexSat\n" +
            "ORDER BY p.numePersoana, p.prenumePersoana, p.telefonPersoana, p.emailPersoana;"
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    Client client = new Client();
                    client.setIndexClient(resultSet.getInt(1));
                    client.setIndexPersoana(resultSet.getInt(2));
                    client.setNumePersoana(resultSet.getString(3));
                    client.setPrenumePersoana(resultSet.getString(4));
                    client.setTelefonPersoana(resultSet.getString(5));
                    client.setEmailPersoana(resultSet.getString(6));

                    Locatie domiciliu = new Locatie();
                    domiciliu.setIndexLocatie(resultSet.getInt(7));
                    domiciliu.setDenumireLocatie(resultSet.getString(8));

                    Judet judet = new Judet();
                    judet.setIndexJudet(resultSet.getInt(9));
                    judet.setDenumireJudet(resultSet.getString(10));
                    domiciliu.setJudetLocatie(judet);

                    if (resultSet.getInt(11) > 0)
                    {
                        Oras oras = new Oras();
                        oras.setIndexOras(resultSet.getInt(11));
                        oras.setDenumireOras(resultSet.getString(12));
                        domiciliu.setOrasLocatie(oras);

                        Cartier cartier = new Cartier();
                        cartier.setIndexCartier(resultSet.getInt(13));
                        cartier.setDenumireCartier(resultSet.getString(14));
                        domiciliu.setCartierLocatie(cartier);
                    }
                    else
                    {
                        Comuna comuna = new Comuna();
                        comuna.setIndexComuna(resultSet.getInt(15));
                        comuna.setDenumireComuna(resultSet.getString(16));
                        domiciliu.setComunaLocatie(comuna);

                        Sat sat = new Sat();
                        sat.setIndexSat(resultSet.getInt(17));
                        sat.setDenumireSat(resultSet.getString(18));
                        domiciliu.setSatLocatie(sat);
                    }

                    client.setDomiciliuPersoana(domiciliu);
                    listaClienti.add(client);
                }

                // empty
                if (listaClienti.size() <= 0)
                {
                    return new Pair<>(listaClienti, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaClienti, QueryOutcome.SUCCESS);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return new Pair<>(listaClienti, QueryOutcome.ERROR);
    }
}
