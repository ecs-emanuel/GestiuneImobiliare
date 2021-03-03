package Repository;

import Utils.QueryOutcome;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DatabaseRepository
{
    public Connection craeteConnection()
    {
        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost/gestiuneimobiliara", "root", null);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return connection;
    }
}
