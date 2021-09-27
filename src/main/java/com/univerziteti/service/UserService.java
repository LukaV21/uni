package com.univerziteti.service;

import com.univerziteti.model.User;
import com.univerziteti.web.dto.UserPasswordChangeDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(Long id);

    List<User> findAll();

    Page<User> findAll(int pageNo);

    User save(User user);

    void delete(Long id);

    Optional<User> findByUsername(String username);

    boolean changePassword(Long id, UserPasswordChangeDto userPasswordChangeDto);
}
