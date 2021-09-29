package com.univerziteti.support.user;

import com.univerziteti.model.User;
import com.univerziteti.web.dto.user.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setEMail(user.getEMail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setUsername(user.getUsername());

        return dto;
    }

    public List<UserDto> convert(List<User> users){
        List<UserDto> dtoS = new ArrayList<>();

        for(User u : users) {
            UserDto dto = convert(u);
            dtoS.add(dto);
        }

        return dtoS;
    }
}
