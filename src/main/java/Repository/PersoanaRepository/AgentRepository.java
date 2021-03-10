package Repository.PersoanaRepository;

import Entities.Locatie.*;
import Entities.Persoana.Persoana;
import Repository.DatabaseRepository;
import Utils.QueryOutcome;
import Entities.Persoana.Agent;
import Entities.Persoana.User;
import javafx.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgentRepository
{
    public Pair<Agent, QueryOutcome> getAgent(int indexAgent)
    {
        Agent agent = new Agent();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        if (connection == null)
        {
            return new Pair<>(agent, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT *\n" +
            "FROM agenti\n" +
            "WHERE indexAgent = %d",
            indexAgent
        );

        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(sqlScript))
            {
                if (resultSet.first())
                {
                    agent.setIndexAgent(indexAgent);

                    int indexUser = resultSet.getInt(2);

                    UserRepository userRepository = new UserRepository();
                    Pair<User, QueryOutcome> queryOutcomePairUser = userRepository.getUser(indexUser);
                    QueryOutcome queryOutcome = queryOutcomePairUser.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(agent, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    agent.setUserAgent(queryOutcomePairUser.getKey());

                    int indexPersoana = resultSet.getInt(3);

                    PersoanaRepository persoanaRepository = new PersoanaRepository();
                    Pair<Persoana, QueryOutcome> queryOutcomePairPersoana = persoanaRepository.getPersoana(indexPersoana);
                    queryOutcome = queryOutcomePairPersoana.getValue();

                    if (queryOutcome != QueryOutcome.SUCCESS)
                    {
                        return new Pair<>(agent, queryOutcome == QueryOutcome.EMPTY ? QueryOutcome.CORRUPT : queryOutcome);
                    }

                    Persoana persoana = queryOutcomePairPersoana.getKey();

                    agent.setNumePersoana(persoana.getNumePersoana());
                    agent.setPrenumePersoana(persoana.getPrenumePersoana());
                    agent.setTelefonPersoana(persoana.getTelefonPersoana());
                    agent.setEmailPersoana(persoana.getEmailPersoana());
                    agent.setDomiciliuPersoana(persoana.getDomiciliuPersoana());

                    return new Pair<>(agent, QueryOutcome.SUCCESS);
                }
                return new Pair<>(agent, QueryOutcome.EMPTY);
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
        return new Pair<>(agent, QueryOutcome.ERROR);
    }

    public Pair<Agent, QueryOutcome> getAgent(User user)
    {
        Agent agent = new Agent();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        // no connection / offline
        if (connection == null)
        {
            return new Pair<>(agent, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT a.indexAgent,\n" +
            "p.indexPersoana, p.numePersoana, p.prenumePersoana, p.telefonPersoana, p.emailPersoana,\n" +
            "l.indexLocatie, l.denumireLocatie,\n" +
            "j.indexJudet, j.denumireJudet,\n" +
            "o.indexOras, o.denumireOras,\n" +
            "ca.indexCartier, ca.denumireCartier,\n" +
            "co.indexComuna, co.denumireComuna,\n" +
            "s.indexSat, s.denumireSat\n" +
            "FROM agenti a\n" +
            "JOIN persoane p\n" +
            "ON a.persoanaAgent = p.indexPersoana\n" +
            "JOIN locatii l\n" +
            "ON p.domiciliuPersoana = l.indexLocatie\n" +
            "JOIN judete j\n" +
            "ON l.judetLocatie = j.indexJudet\n" +
            "LEFT JOIN orase o\n" +
            "ON l.orasLocatie = o.indexOras\n" +
            "LEFT JOIN cartiere ca\n" +
            "ON l.cartierLocatie = ca.indexCartier\n" +
            "LEFT JOIN comune co\n" +
            "ON l.comunaLocatie = co.indexComuna\n" +
            "LEFT JOIN sate s\n" +
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
        finally
        {
            databaseRepository.closeConnection(connection);
        }

        // if we reached this point, something went wrong
        return new Pair<>(agent, QueryOutcome.ERROR);
    }

    public Pair<List<Agent>, QueryOutcome> getListaAgenti()
    {
        List<Agent> listaAgenti = new ArrayList<>();

        DatabaseRepository databaseRepository = new DatabaseRepository();
        Connection connection = databaseRepository.createConnection();

        // no connection / offline
        if (connection == null)
        {
            return new Pair<>(listaAgenti, QueryOutcome.OFFLINE);
        }

        String sqlScript = String.format
        (
            "SELECT a.indexAgent,\n" +
            "p.indexPersoana, p.numePersoana, p.prenumePersoana, p.telefonPersoana, p.emailPersoana,\n" +
            "l.indexLocatie, l.denumireLocatie,\n" +
            "j.indexJudet, j.denumireJudet,\n" +
            "o.indexOras, o.denumireOras,\n" +
            "ca.indexCartier, ca.denumireCartier,\n" +
            "co.indexComuna, co.denumireComuna,\n" +
            "s.indexSat, s.denumireSat\n" +
            "FROM agenti a\n" +
            "JOIN persoane p\n" +
            "ON a.persoanaAgent = p.indexPersoana\n" +
            "JOIN locatii l\n" +
            "ON p.domiciliuPersoana = l.indexLocatie\n" +
            "JOIN judete j\n" +
            "ON l.judetLocatie = j.indexJudet\n" +
            "LEFT JOIN orase o\n" +
            "ON l.orasLocatie = o.indexOras\n" +
            "LEFT JOIN cartiere ca\n" +
            "ON l.cartierLocatie = ca.indexCartier\n" +
            "LEFT JOIN comune c\n" +
            "ON l.comunaLocatie = co.indexComuna\n" +
            "LEFT JOIN sate s\n" +
            "ON l.satLocatie = s.indexSat\n" +
            "ORDER BY p.numePersoana, p.prenumePersoana, p.telefonPersoana, p.emailPersoana;"
        );

        try (Statement statament = connection.createStatement())
        {
            try (ResultSet resultset = statament.executeQuery(sqlScript))
            {
                // agent found in database
                while (resultset.next())
                {
                    Agent agent = new Agent();
                    agent.setIndexAgent(resultset.getInt(1));
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
                    listaAgenti.add(agent);
                }

                // nothing found
                if (listaAgenti.size() <= 0)
                {
                    return new Pair<>(listaAgenti, QueryOutcome.EMPTY);
                }

                return new Pair<>(listaAgenti, QueryOutcome.SUCCESS);
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
        return new Pair<>(listaAgenti, QueryOutcome.ERROR);
    }
}
