package com.univerziteti.support.user;

import com.univerziteti.model.User;
import com.univerziteti.service.UserService;
import com.univerziteti.web.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {

    @Autowired
    private UserService userService;


    @Override
    public User convert(UserDto dto) {
        User user = null;
        if(dto.getId() != null) {
            user = userService.findOne(dto.getId()).get();
        }

        if(user == null) {
            user = new User();
        }

        user.setUsername(dto.getUsername());
        user.setEMail(dto.getEMail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        return user;
    }

}
