package Repository.PersoanaRepository;

import Entities.Locatie.*;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import javafx.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AgentRepository
{
    public Pair<Agent, QueryOutcome> getAgent(User user)
    {
        Agent agent = new Agent();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.craeteConnection();

        // no connection / offline
        if (connection == null)
        {
            return new Pair<>(agent, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT a.indexAgent,\n" +
            "p.indexPersoana, p.numePersoana, p.prenumePersoana, p.telefonPersoana, p.emailPersoana, \n" +
            "l.indexLocatie, l.denumireLocatie,\n" +
            "j.indexJudet, j.denumireJudet, \n" +
            "o.indexOras, o.denumireOras, \n" +
            "ca.indexCartier, ca.denumireCartier,\n" +
            "co.indexComuna, co.denumireComuna, \n" +
            "s.indexSat, s.denumireSat\n" +
            "FROM agenti a \n" +
            "JOIN persoane p \n" +
            "ON a.persoanaAgent = p.indexPersoana\n" +
            "JOIN locatii l \n" +
            "ON p.domiciliuPersoana = l.indexLocatie\n" +
            "JOIN judete j \n" +
            "ON l.judetLocatie = j.indexJudet\n" +
            "LEFT JOIN orase o \n" +
            "ON l.orasLocatie = o.indexOras\n" +
            "LEFT JOIN cartiere ca\n" +
            "ON l.cartierLocatie = ca.indexCartier\n" +
            "LEFT JOIN comune co \n" +
            "ON l.comunaLocatie = co.indexComuna\n" +
            "LEFT JOIN sate s \n" +
            "ON l.satLocatie = s.indexSat\n" +
            "WHERE userAgent = %d",
            user.getIndexUser()
        );

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                // agent found in database
                if (resultset.first())
                {
                    agent.setIndexAgent(resultset.getInt(1));
                    agent.setUserAgent(user);
                    agent.setIndexPersoana(resultset.getInt(2));
                    agent.setNumePersoana(resultset.getString(3));
                    agent.setPrenumePersoana(resultset.getString(4));
                    agent.setTelefonPersoana(resultset.getString(5));
                    agent.setEmailPersoana(resultset.getString(6));

                    Locatie domiciliu = new Locatie();
                    domiciliu.setIndexLocatie(resultset.getInt(7));
                    domiciliu.setDenumireLocatie(resultset.getString(8));

                    Judet judet = new Judet();
                    judet.setIndexJudet(resultset.getInt(9));
                    judet.setDenumireJudet(resultset.getString(10));
                    domiciliu.setJudetLocatie(judet);

                    if (resultset.getInt(11) > 0)
                    {
                        Oras oras = new Oras();
                        oras.setIndexOras(resultset.getInt(11));
                        oras.setDenumireOras(resultset.getString(12));
                        domiciliu.setOrasLocatie(oras);

                        Cartier cartier = new Cartier();
                        cartier.setIndexCartier(resultset.getInt(13));
                        cartier.setDenumireCartier(resultset.getString(14));
                        domiciliu.setCartierLocatie(cartier);
                    }
                    else if (resultset.getInt(15) > 0)
                    {
                        Comuna comuna = new Comuna();
                        comuna.setIndexComuna(resultset.getInt(15));
                        comuna.setDenumireComuna(resultset.getString(16));
                        domiciliu.setComunaLocatie(comuna);

                        Sat sat = new Sat();
                        sat.setIndexSat(resultset.getInt(17));
                        sat.setDenumireSat(resultset.getString(18));
                        domiciliu.setSatLocatie(sat);
                    }

                    agent.setDomiciliuPersoana(domiciliu);

                    return new Pair<>(agent, QueryOutcome.SUCCESS);
                }

                // nothing found
                return new Pair<>(agent, QueryOutcome.EMPTY);
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
        return new Pair<>(agent, QueryOutcome.ERROR);
    }
}
