package Services.ProprietateServices;

import Entities.Proprietate.Parcela;
import Repository.ProprietateRepository.ParcelaRepository;
import Utils.QueryOutcome;
import javafx.util.Pair;

public class ParcelaServices
{
    public Pair<Parcela, QueryOutcome> getParcela(int indexParcela)
    {
        ParcelaRepository parcelaRepository = new ParcelaRepository();
        return parcelaRepository.getParcela(indexParcela);
    }
}
