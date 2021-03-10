package Repository.ProprietateRepository;

import Components.DispozitieProprietate;
import Entities.Locatie.Locatie;
import Entities.Persoana.Agent;
import Entities.Persoana.Client;
import Entities.Proprietate.*;
import Repository.DatabaseRepository;
import Repository.LocatieRepository.LocatieRepository;
import Repository.PersoanaRepository.AgentRepository;
import Repository.PersoanaRepository.ClientRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProprietateRepository
{
    public Pair<Proprietate, QueryOutcome> getProprietate(int indexProprietate)
    {
        Proprietate proprietate = new Proprietate();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(proprietate, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM proprietati\n" +
            "WHERE indexProprietate = %d",
            indexProprietate
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    proprietate.setIndexProprietate(indexProprietate);
                    proprietate.setTitluProprietate(resultSet.getString(2));
                    proprietate.setDescriereProprietate(resultSet.getString(3));
                    proprietate.setPretProprietate(resultSet.getInt(4));

                    int indexLocatie = resultSet.getInt(5);
                    LocatieRepository locatieRepository = new LocatieRepository();
                    Pair<Locatie, QueryOutcome> queryOutcomePairLocatie = locatieRepository.getLocatie(indexLocatie);
                    QueryOutcome queryOutcome = queryOutcomePairLocatie.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(proprietate, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    proprietate.setLocatieProprietate(queryOutcomePairLocatie.getKey());

                    int indexProprietar = resultSet.getInt(6);
                    ClientRepository clientRepository = new ClientRepository();
                    Pair<Client, QueryOutcome> queryOutcomePairClient = clientRepository.getClient(indexProprietar);
                    queryOutcome = queryOutcomePairClient.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(proprietate, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    proprietate.setProprietarProprietate(queryOutcomePairClient.getKey());

                    int indexAgent = resultSet.getInt(7);
                    AgentRepository agentRepository = new AgentRepository();
                    Pair<Agent, QueryOutcome> queryOutcomePairAgent = agentRepository.getAgent(indexAgent);
                    queryOutcome = queryOutcomePairAgent.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(proprietate, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    proprietate.setAgentProprietate(queryOutcomePairAgent.getKey());

                    proprietate.setDispozitieProprietate(DispozitieProprietate.valueOf(resultSet.getString(8)));
                    proprietate.setDataProprietate(resultSet.getDate(9));

                    return new Pair<>(proprietate, QueryOutcome.SUCCESS);
                }
                return new Pair<>(proprietate, QueryOutcome.EMPTY);
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
        return new Pair<>(proprietate, QueryOutcome.ERROR);
    }
}

