package Repository.PersoanaRepository;

import Entities.Locatie.Cartier;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.User;
import javafx.util.Pair;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class UserRepository
{
    public Pair<User, QueryOutcome> getUser(int indexUser)
    {
        User user = new User();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(user, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT isAdminUser\n" +
            "FROM useri\n" +
            "WHERE indexUser = %d",
            indexUser
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    user.setIndexUser(indexUser);
                    user.setAdminUser(resultSet.getBoolean(1));

                    return new Pair<>(user, QueryOutcome.SUCCESS);
                }
                return new Pair<>(user, QueryOutcome.EMPTY);
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
        return new Pair<>(user, QueryOutcome.ERROR);
    }

    public Pair<User, QueryOutcome> authenticate(User user)
    {
        String sqlScript = String.format
        (
            "SELECT indexUser, isAdminUser\n" +
            "FROM useri\n" +
            "WHERE nameUser = '%s' AND passUser = '%s'",
            user.getNameUser(), user.getPassUser()
        );

        user.setNameUser(null);
        user.setPassUser(null);

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        // no connection / offline
        if (connection == null)
        {
            return new Pair<>(user, QueryOutcome.OFFLINE);
        }

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                // user found in database
                if (resultset.first())
                {
                    user.setIndexUser(resultset.getInt(1));
                    user.setAdminUser(resultset.getBoolean(2));
                    return new Pair<>(user, QueryOutcome.SUCCESS);
                }

                // nothing found
                return new Pair<>(user, QueryOutcome.EMPTY);
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

        // if we reached this point, something went wrong
        return new Pair<>(user, QueryOutcome.ERROR);
    }
}
