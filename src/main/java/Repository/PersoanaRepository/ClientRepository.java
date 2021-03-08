package Repository.PersoanaRepository;

import Entities.Locatie.Locatie;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.Client;
import javafx.util.Pair;

import java.sql.*;

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
}
