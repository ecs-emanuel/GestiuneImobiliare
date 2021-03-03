package Repository;

import Utils.QueryOutcome;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DatabaseRepository
{
    private QueryOutcome queryOutcome;

    public CachedRowSet retrieveData(String sqlScript)
    {
        CachedRowSet rowset = null;

        try
        {
            rowset = RowSetProvider.newFactory().createCachedRowSet();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            this.queryOutcome = QueryOutcome.ERROR;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/gestiuneimobiliara", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery(sqlScript))
                {
                    assert rowset != null;
                    rowset.populate(rs);
                    this.queryOutcome = QueryOutcome.SUCCESS;
                }
            }
            catch (SQLException throwables)
            {
                this.queryOutcome = QueryOutcome.ERROR;
                throwables.printStackTrace();
            }
        }
        catch (SQLException throwables)
        {
            this.queryOutcome = QueryOutcome.OFFLINE;
            throwables.printStackTrace();
        }
        return rowset;
    }

    public void updateData(String sqlScript)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/gestiuneimobiliara", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                stmt.executeUpdate(sqlScript);
                this.queryOutcome = QueryOutcome.SUCCESS;
            }
            catch (SQLException throwables)
            {
                this.queryOutcome = QueryOutcome.ERROR;
                throwables.printStackTrace();
            }
        }
        catch (SQLException throwables)
        {
            this.queryOutcome = QueryOutcome.OFFLINE;
            throwables.printStackTrace();
        }
    }

    public QueryOutcome getQueryOutcome()
    {
        return this.queryOutcome;
    }
}
