package Repository.PersoanaRepository;

import Entities.Locatie.*;
import Entities.Persoana.Agent;
import Entities.Persoana.Persoana;
import Entities.Persoana.User;
import Entities.Programare;
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
        Connection connection = databaseRepository.createConnection();

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
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return QueryOutcome.ERROR;
    }

    public QueryOutcome modClient(Client oldClient, Client newClient)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        try (Statement statement = connection.createStatement())
        {
            connection.setAutoCommit(false);

            String sqlScript1 = null;
            Locatie oldDomiciliu = oldClient.getDomiciliuPersoana();
            Locatie newDomiciliu = newClient.getDomiciliuPersoana();

            // update locatie
            if (newDomiciliu.getOrasLocatie() != null)
            {
                sqlScript1 = String.format
                (
                    "UPDATE locatii SET\n" +
                    "judetLocatie = %d,\n" +
                    "orasLocatie = %d,\n" +
                    "cartierLocatie = %d,\n" +
                    "comunaLocatie = NULL,\n" +
                    "satLocatie = NULL,\n" +
                    "denumireLocatie = '%s'\n" +
                    "WHERE indexLocatie = %d",
                    newDomiciliu.getJudetLocatie().getIndexJudet(),
                    newDomiciliu.getOrasLocatie().getIndexOras(), newDomiciliu.getCartierLocatie().getIndexCartier(),
                    newDomiciliu.getDenumireLocatie(),
                    oldDomiciliu.getIndexLocatie()
                );
            }
            else
            {
                sqlScript1 = String.format
                (
                    "UPDATE locatii SET\n" +
                    "judetLocatie = %d,\n" +
                    "orasLocatie = NULL,\n" +
                    "cartierLocatie = NULL,\n" +
                    "comunaLocatie = %d,\n" +
                    "satLocatie = %d,\n" +
                    "denumireLocatie = '%s'\n" +
                    "WHERE indexLocatie = %d",
                    newDomiciliu.getJudetLocatie().getIndexJudet(),
                    newDomiciliu.getComunaLocatie().getIndexComuna(), newDomiciliu.getSatLocatie().getIndexSat(),
                    newDomiciliu.getDenumireLocatie(),
                    oldDomiciliu.getIndexLocatie()
                );
            }

            statement.addBatch(sqlScript1);

            // update persoana
            String sqlScript2 = String.format
            (
                "UPDATE persoane SET\n" +
                "numePersoana = '%s',\n" +
                "prenumePersoana = '%s',\n" +
                "telefonPersoana = '%s',\n" +
                "emailPersoana = '%s'\n" +
                "WHERE indexPersoana = %d",
                newClient.getNumePersoana(), newClient.getPrenumePersoana(),
                newClient.getTelefonPersoana(), newClient.getEmailPersoana(),
                oldClient.getIndexPersoana()
            );

            statement.addBatch(sqlScript2);

            statement.executeBatch();
            connection.commit();

            return QueryOutcome.SUCCESS;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return QueryOutcome.ERROR;
    }


    public Pair<Client, QueryOutcome> getClient(int indexClient)
    {
        Client client = new Client();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(client, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM clienti\n" +
            "WHERE indexClient = %d",
            indexClient
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    client.setIndexClient(indexClient);

                    int indexPersoana = resultSet.getInt(2);

                    PersoanaRepository persoanaRepository = new PersoanaRepository();
                    Pair<Persoana, QueryOutcome> queryOutcomePairPersoana = persoanaRepository.getPersoana(indexPersoana);
                    QueryOutcome queryOutcome = queryOutcomePairPersoana.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(client, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    Persoana persoana = queryOutcomePairPersoana.getKey();

                    client.setNumePersoana(persoana.getNumePersoana());
                    client.setPrenumePersoana(persoana.getPrenumePersoana());
                    client.setTelefonPersoana(persoana.getTelefonPersoana());
                    client.setEmailPersoana(persoana.getEmailPersoana());
                    client.setDomiciliuPersoana(persoana.getDomiciliuPersoana());

                    return new Pair<>(client, QueryOutcome.SUCCESS);
                }
                return new Pair<>(client, QueryOutcome.EMPTY);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            databaseRepository.closeConnection(connection);
        }
        return new Pair<>(client, QueryOutcome.ERROR);
    }

    public Pair<List<Client>, QueryOutcome> getListaClienti()
    {
        List<Client> listaClienti = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

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
                    else if (resultSet.getInt(15) > 0)
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
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        return new Pair<>(listaClienti, QueryOutcome.ERROR);
    }
}
