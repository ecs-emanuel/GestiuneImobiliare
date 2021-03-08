package Repository.PersoanaRepository;

import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.User;
import javafx.util.Pair;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class UserRepository
{
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
        Connection connection = databaseRepository.craeteConnection();

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

        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        // if we reached this point, something went wrong
        return new Pair<>(user, QueryOutcome.ERROR);
    }
}
