package Services;

import Utils.QueryOutcome;
import Entities.Persoana.User;
import Repository.UserRepository;

public class UserServices
{
    private QueryOutcome queryOutcome;

    public User authenticate(User user)
    {
        UserRepository userRepository = new UserRepository();
        user = userRepository.authenticate(user);
        this.queryOutcome = userRepository.getQueryOutcome();
        return user;
    }

    public QueryOutcome getQueryOutcome()
    {
        return this.queryOutcome;
    }
}
