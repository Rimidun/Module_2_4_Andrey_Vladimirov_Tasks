package project.repository.implementation;

import project.entity.User;
import project.repository.UserRepository;

public class UserRepositoryImplementation extends GenericRepositoryImplementation<User, Long> implements UserRepository {
    public UserRepositoryImplementation() {
        super(User.class);
    }
}
