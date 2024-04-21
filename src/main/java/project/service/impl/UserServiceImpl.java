package project.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Model.User;
import project.Repository.UserRepository;
import project.service.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByAccountLogin(userToCreate.getAccount().getLogin())){
            throw new IllegalArgumentException("Essa conta já existe.");
        }
        else if(userRepository.existsByAccountId(userToCreate.getAccount().getId())){
            throw new IllegalArgumentException("Esse ID de conta já existe.");
        }
        else if(userRepository.existsById(userToCreate.getId())){
            throw new IllegalArgumentException("Esse ID de usuário já existe.");
        }
        return userRepository.save(userToCreate);
    }
}
