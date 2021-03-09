package Repository.ProprietateRepository;

import Entities.Locatie.Locatie;
import Entities.Proprietate.Parcela;
import Entities.Proprietate.Teren;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TerenRepository
{
    public QueryOutcome addTeren(Teren teren)
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
            Locatie locatie = teren.getLocatieProprietate();

            // add locatie
            if (locatie.getOrasLocatie() != null)
            {
                sqlScript1 = String.format
                (
                    "INSERT INTO locatii (judetLocatie, orasLocatie, cartierLocatie, denumireLocatie) VALUES\n" +
                    "(%d, %d, %d, '%s');\n",
                    locatie.getJudetLocatie().getIndexJudet(),
                    locatie.getOrasLocatie().getIndexOras(), locatie.getCartierLocatie().getIndexCartier(), locatie.getDenumireLocatie()
                );
            }
            else
            {
                sqlScript1 = String.format
                (
                    "INSERT INTO locatii (judetLocatie, comunaLocatie, satLocatie, denumireLocatie) VALUES\n" +
                    "(%d, %d, %d, '%s');\n",
                    locatie.getJudetLocatie().getIndexJudet(),
                    locatie.getComunaLocatie().getIndexComuna(), locatie.getSatLocatie().getIndexSat(), locatie.getDenumireLocatie()
                );
            }

            statement.executeUpdate(sqlScript1);

            // add proprietate
            String sqlScript2 = String.format
            (
                "INSERT INTO proprietati (titluProprietate, descriereProprietate, pretProprietate,\n" +
                "locatieProprietate, proprietarProprietate, agentProprietate,\n" +
                "dispozitieProprietate, dataProprietate) VALUES\n" +
                "('%s', '%s', %d, LAST_INSERT_ID(), %d, %d, '%s', '%s');",
                teren.getTitluProprietate(), teren.getDescriereProprietate(), teren.getPretProprietate(),
                teren.getProprietarProprietate().getIndexClient(), teren.getAgentProprietate().getIndexAgent(),
                teren.getDispozitieProprietate().name(), teren.getDataProprietate()
            );

            // retrieve indexProprietate for later use
            int indexProprietate = statement.executeUpdate(sqlScript2, Statement.RETURN_GENERATED_KEYS);

            Parcela parcela = teren.getParcelaTeren();

            // add parcela
            String sqlScript4 = String.format
            (
                "INSERT INTO parcele (suprafataParcela, hasApa, hasGaz, hasElectricitate, hasCanalizare) VALUES\n" +
                "(%d, %b, %b, %b, %b);",
                parcela.getSuprafataParcela(), parcela.hasApa(), parcela.hasGaz(), parcela.hasElectricitate(), parcela.hasCanalizare()
            );

            statement.addBatch(sqlScript4);

            // add teren
            String sqlScript5 = String.format
            (
                "INSERT INTO terenuri(dispozitieTeren, parcelaTeren, proprietateTeren) VALUES\n" +
                "('%s', LAST_INSERT_ID(), %d)",
                teren.getDispozitieTeren().name(), indexProprietate
            );

            statement.addBatch(sqlScript5);
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
}
