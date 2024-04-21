package project.service;

import project.Model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
