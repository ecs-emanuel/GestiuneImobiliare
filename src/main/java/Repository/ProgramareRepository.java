package Repository;

import Entities.Locatie.*;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;
import Entities.Persoana.User;
import Entities.Programare;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProgramareRepository
{
    public QueryOutcome addProgramare(Programare programare)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        String sqlScript = String.format
        (
            "INSERT INTO programari(dataProgramare, agentProgramare, clientProgramare) VALUES\n" +
            "('%s', %d, %d)",
            programare.getData(), programare.getAgent().getIndexAgent(), programare.getClient().getIndexClient()
        );

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sqlScript);
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

    public QueryOutcome modProgramare(Programare oldProgramare, Programare newProgramare)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        String sqlScript = String.format
        (
            "UPDATE programari SET\n" +
            "dataProgramare = '%s',\n" +
            "agentProgramare = %d,\n" +
            "clientProgramare = %d\n" +
            "WHERE indexProgramare = %d",
            newProgramare.getData(), newProgramare.getAgent().getIndexAgent(), newProgramare.getClient().getIndexClient(),
            oldProgramare.getIndexProgramare()
        );

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sqlScript);
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

    public QueryOutcome delProgramare(Programare programare)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return QueryOutcome.OFFLINE;
        }

        String sqlScript = String.format
        (
            "DELETE FROM programari\n" +
            "WHERE indexProgramare = %d",
            programare.getIndexProgramare()
        );

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sqlScript);
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

    public Pair<List<Programare>, QueryOutcome> getListaProgramari(Agent agent, boolean istoricComplet)
    {
        List<Programare> listaProgramari = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(listaProgramari, QueryOutcome.OFFLINE);
        }

        String sqlExtraCondition = "";

        if (!istoricComplet)
        {
            sqlExtraCondition = "AND pr.dataProgramare >= CURRENT_DATE\n";
        }

        String sqlScript = String.format
        (
            "SELECT pr.indexProgramare, pr.dataProgramare,\n" +
            "c.indexClient,\n" +
            "pe.indexPersoana, pe.numePersoana, pe.prenumePersoana, pe.telefonPersoana, pe.emailPersoana,\n" +
            "d.indexLocatie, d.denumireLocatie,\n" +
            "j.indexJudet, j.denumireJudet,\n" +
            "o.indexOras, o.denumireOras,\n" +
            "ca.indexCartier, ca.denumireCartier,\n" +
            "co.indexComuna, co.denumireComuna,\n" +
            "s.indexSat, s.denumireSat\n" +
            "FROM programari pr\n" +
            "JOIN clienti c \n" +
            "ON c.indexClient = pr.clientProgramare\n" +
            "JOIN persoane pe \n" +
            "ON pe.indexPersoana = c.persoanaClient\n" +
            "JOIN locatii d\n" +
            "ON d.indexLocatie = pe.domiciliuPersoana\n" +
            "JOIN judete j \n" +
            "ON j.indexJudet = d.judetLocatie\n" +
            "LEFT JOIN orase o\n" +
            "ON o.indexOras = d.orasLocatie\n" +
            "LEFT JOIN cartiere ca\n" +
            "ON ca.indexCartier = d.cartierLocatie\n" +
            "LEFT JOIN comune co\n" +
            "ON co.indexComuna = d.comunaLocatie\n" +
            "LEFT JOIN sate s\n" +
            "ON s.indexSat = d.satLocatie\n" +
            "WHERE pr.agentProgramare = %d\n" +
            "%s" +
            "ORDER BY pr.dataProgramare DESC", agent.getIndexAgent(), sqlExtraCondition
        );


        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                while (resultSet.next())
                {
                    Programare programare = new Programare();
                    programare.setIndexProgramare(resultSet.getInt(1));
                    programare.setData(resultSet.getTimestamp(2));
                    programare.setAgent(agent);

                    Client client = new Client();
                    client.setIndexClient(resultSet.getInt(3));
                    client.setIndexPersoana(resultSet.getInt(4));
                    client.setNumePersoana(resultSet.getString(5));
                    client.setPrenumePersoana(resultSet.getString(6));
                    client.setTelefonPersoana(resultSet.getString(7));
                    client.setEmailPersoana(resultSet.getString(8));

                    Locatie domiciliu = new Locatie();
                    domiciliu.setIndexLocatie(resultSet.getInt(9));
                    domiciliu.setDenumireLocatie(resultSet.getString(10));

                    Judet judet = new Judet();
                    judet.setIndexJudet(resultSet.getInt(11));
                    judet.setDenumireJudet(resultSet.getString(12));
                    domiciliu.setJudetLocatie(judet);

                    if (resultSet.getInt(13) > 0)
                    {
                        Oras oras = new Oras();
                        oras.setIndexOras(resultSet.getInt(13));
                        oras.setDenumireOras(resultSet.getString(14));
                        domiciliu.setOrasLocatie(oras);

                        Cartier cartier = new Cartier();
                        cartier.setIndexCartier(resultSet.getInt(15));
                        cartier.setDenumireCartier(resultSet.getString(16));
                        domiciliu.setCartierLocatie(cartier);
                    }
                    else if (resultSet.getInt(17) > 0)
                    {
                        Comuna comuna = new Comuna();
                        comuna.setIndexComuna(resultSet.getInt(17));
                        comuna.setDenumireComuna(resultSet.getString(18));
                        domiciliu.setComunaLocatie(comuna);

                        Sat sat = new Sat();
                        sat.setIndexSat(resultSet.getInt(19));
                        sat.setDenumireSat(resultSet.getString(20));
                        domiciliu.setSatLocatie(sat);
                    }

                    client.setDomiciliuPersoana(domiciliu);
                    programare.setClient(client);

                    listaProgramari.add(programare);
                }

                // empty
                if (listaProgramari.size() <= 0)
                {
                    return new Pair<>(listaProgramari, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaProgramari, QueryOutcome.SUCCESS);
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

        return new Pair<>(listaProgramari, QueryOutcome.ERROR);
    }
}
