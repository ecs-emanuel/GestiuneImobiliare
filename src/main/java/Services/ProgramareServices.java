package Services;

import Entities.Persoana.Agent;
import Entities.Persoana.Client;
import Entities.Programare;
import Entities.Proprietate.Proprietate;
import Repository.ProgramareRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ProgramareServices
{
    public QueryOutcome addProgramare(Programare programare)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.addProgramare(programare);
    }

    public QueryOutcome modProgramare(Programare oldProgramare, Programare newProgramare)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.modProgramare(oldProgramare, newProgramare);
    }

    public QueryOutcome delProgramare(Programare programare)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.delProgramare(programare);
    }

    public Pair<List<Programare>, QueryOutcome> getListaProgramari(Agent agent, boolean istoricComplet)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        return programareRepository.getListaProgramari(agent, istoricComplet);
    }

    public Pair<List<Programare>, QueryOutcome> getListaProgramari(Agent agent, boolean istoricComplet, String text)
    {
        ProgramareRepository programareRepository = new ProgramareRepository();
        Pair<List<Programare>, QueryOutcome> queryOutcomePairProgramare = programareRepository.getListaProgramari(agent, istoricComplet);
        QueryOutcome queryOutcome = queryOutcomePairProgramare.getValue();

        if (queryOutcome != QueryOutcome.SUCCESS)
        {
            return queryOutcomePairProgramare;
        }

        List<Programare> listaProgramari = queryOutcomePairProgramare.getKey();
        List<Programare> listaProgramariFiltrate = new ArrayList<>();

        for (Programare programare : listaProgramari)
        {
            Client client = programare.getClient();
            String nume = client.getNumePersoana().toLowerCase();
            String prenume = client.getPrenumePersoana().toLowerCase();

            String[] textArray = text.split(" ");

            for (String piece : textArray)
            {
                piece = piece.toLowerCase();
                if (nume.contains(piece) || prenume.contains(piece))
                {
                    listaProgramariFiltrate.add(programare);
                    break;
                }
            }
        }

        if (listaProgramariFiltrate.size() <= 0)
        {
            return new Pair<>(listaProgramariFiltrate, QueryOutcome.EMPTY);
        }

        return new Pair<>(listaProgramariFiltrate, QueryOutcome.SUCCESS);
    }
}
