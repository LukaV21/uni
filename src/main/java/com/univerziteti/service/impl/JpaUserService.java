package com.univerziteti.service.impl;

import com.univerziteti.model.User;
import com.univerziteti.repository.UserRepository;
import com.univerziteti.service.UserService;
import com.univerziteti.utils.UserRole;
import com.univerziteti.web.dto.user.UserPasswordChangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(int pageNo) {
        return userRepository.findAll(PageRequest.of(pageNo,10));
    }

    @Override
    public User save(User user) {
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public boolean changePassword(Long id, UserPasswordChangeDto userPasswordChangeDto) {
        Optional<User> res = userRepository.findById(id);

        if(!res.isPresent()) {
            throw new EntityNotFoundException();
        }

        User user = res.get();

        if(!user.getUsername().equals(userPasswordChangeDto.getUsername())
                || !user.getPassword().equals(userPasswordChangeDto.getPassword())){
            return false;
        }

        String password = userPasswordChangeDto.getPassword();
        if (!userPasswordChangeDto.getPassword().equals("")) {
            password = passwordEncoder.encode(userPasswordChangeDto.getPassword());
        }

        user.setPassword(password);

        userRepository.save(user);

        return true;
    }
}
