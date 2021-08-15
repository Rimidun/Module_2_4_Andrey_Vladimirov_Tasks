package project.repository.implementation;

import project.entity.User;
import project.repository.UserRepository;

public class UserRepositoryImpl extends GenericRepositoryImpl<User, Long> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }
}
