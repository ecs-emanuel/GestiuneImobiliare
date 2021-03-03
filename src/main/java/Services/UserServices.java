package Services;

import Utils.QueryOutcome;
import Entities.Persoana.User;
import Repository.UserRepository;
import javafx.util.Pair;

public class UserServices
{
    public Pair<User, QueryOutcome> authenticate(User user)
    {
        UserRepository userRepository = new UserRepository();
        return userRepository.authenticate(user);
    }
}
