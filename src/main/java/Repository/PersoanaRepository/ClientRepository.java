package Repository.PersoanaRepository;

import Entities.Locatie.Locatie;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.Client;
import javafx.util.Pair;

import java.sql.*;

public class ClientRepository
{

    public Pair<Client, QueryOutcome> addClient(Client client)
    {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        if (connection == null)
        {
            return new Pair<>(client, QueryOutcome.OFFLINE);
        }

        Locatie domiciliu = client.getDomiciliuPersoana();

        String sqlScript = String.format
        (
            "START TRANSACTION;\n" +
            "INSERT INTO locatii (judetLocatie, orasLocatie, cartierLocatie, comunaLocatie, satLocatie, denumireLocatie) VALUES\n" +
            "(%d, %d, %d, %d, %d, '%s');\n" +
            "INSERT INTO persoane (numePersoana, prenumePersoana, telefonPersoana, emailPersoana, domiciliuPersoana) VALUES\n" +
            "('%s', '%s', '%s', '%s', LAST_INSERT_ID());\n" +
            "INSERT INTO clienti (persoanaClient) VALUES\n" +
            "(LAST_INSERT_ID());\n" +
            "COMMIT;",
            domiciliu.getJudetLocatie().getIndexJudet(),
            domiciliu.getOrasLocatie().getIndexOras(), domiciliu.getCartierLocatie().getIndexCartier(),
            domiciliu.getComunaLocatie().getIndexComuna(), domiciliu.getSatLocatie().getIndexSat(),
            client.getNumePersoana(), client.getPrenumePersoana(), client.getTelefonPersoana(), client.getEmailPersoana()
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlScript, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                if (resultSet.first())
                {
                    client.setIndexClient(resultSet.getInt(1));
                    return new Pair<>(client, QueryOutcome.SUCCESS);
                }
                return new Pair<>(client, QueryOutcome.EMPTY);
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
        return new Pair<>(client, QueryOutcome.ERROR);
    }
}
