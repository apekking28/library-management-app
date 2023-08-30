package com.king.service;

import com.king.entity.Address;
import com.king.entity.User;
import com.king.exception.NotFoundException;
import com.king.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    public User createUser(User user) {
        Address address = addressService.getAddressById(user.getAddress().getId());

        User saveUser = User.builder()
                .username(user.getUsername())
                .address(address)
                .build();

        return userRepository.save(saveUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id : " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        User findUser = getUserById(id);

        User saveUser = User.builder()
                .id(findUser.getId())
                .username(user.getUsername())
                .address(findUser.getAddress())
                .build();

        return userRepository.save(saveUser);
    }

    public String deleteUser(Long id) {
        User findUser = getUserById(id);
        userRepository.deleteById(id);

        return "Successfully delete user with id : " + id;
    }
}
