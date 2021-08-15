package project.service.implementation;


import project.entity.User;
import project.repository.UserRepository;
import project.repository.implementation.UserRepositoryImpl;
import project.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User save(User user) {
        if(user.getId() == null){
            return userRepository.save(user);
        } else {
            return userRepository.update(user);
        }
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.get(id);
    }

    @Override
    public void remove(User user) {
        userRepository.remove(user);
    }

    @Override
    public User load(Long id) {
        return userRepository.load(id);
    }
}
