package project.service;


import project.entity.User;

public interface UserService extends GenericService<User, Long> {

    User load(Long id);
}
