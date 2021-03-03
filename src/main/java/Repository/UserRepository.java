package Repository;

import Utils.QueryOutcome;
import Entities.Persoana.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class UserRepository
{
    private QueryOutcome queryOutcome;

    public User authenticate(User user)
    {
        String sqlScript = String.format(
            "SELECT indexUser, isAdminUser " +
            "FROM useri " +
            "WHERE nameUser = '%s' AND passUser = '%s'",
            user.getNameUser(), user.getPassUser());

        user.setNameUser(null);
        user.setPassUser(null);

        DatabaseRepository databaseRepository = new DatabaseRepository();
        CachedRowSet rowset = databaseRepository.retrieveData(sqlScript);
        QueryOutcome result = databaseRepository.getQueryOutcome();

        if (result != result.SUCCESS)
        {
            this.queryOutcome = result;
            return user;
        }

        try
        {
            if (rowset.first())
            {
                user.setIndexUser(rowset.getInt(1));
                user.setAdminUser(rowset.getBoolean(2));
                this.queryOutcome = QueryOutcome.SUCCESS;
            }
            else
            {
                this.queryOutcome = QueryOutcome.EMPTY;
            }
        }
        catch (SQLException throwables)
        {
            this.queryOutcome = QueryOutcome.ERROR;
            throwables.printStackTrace();
        }

        return user;
    }

    public QueryOutcome getQueryOutcome()
    {
        return this.queryOutcome;
    }
}
