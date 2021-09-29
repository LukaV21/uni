package com.univerziteti.web.controller;

import com.univerziteti.model.User;
import com.univerziteti.security.TokenUtils;
import com.univerziteti.service.UserService;
import com.univerziteti.support.user.UserDtoToUser;
import com.univerziteti.support.user.UserToUserDto;
import com.univerziteti.web.dto.user.AuthUserDto;
import com.univerziteti.web.dto.user.UserDto;
import com.univerziteti.web.dto.user.UserPasswordChangeDto;
import com.univerziteti.web.dto.user.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoToUser toUser;
    @Autowired
    private UserToUserDto toUserDto;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@RequestBody @Validated UserRegistrationDto dto){
        if(dto.getId() != null || !dto.getPassword().equals(dto.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = toUser.convert(dto);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);

        return new ResponseEntity<>(toUserDto.convert(userService.save(user)), HttpStatus.CREATED);
    }
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthUserDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto dto){

        if(!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = toUser.convert(dto);

        return new ResponseEntity<>(toUserDto.convert(userService.save(user)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id){
        Optional<User> user = userService.findOne(id);

        if(user.isPresent()) {
            return new ResponseEntity<>(toUserDto.convert(user.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> get(@RequestParam(defaultValue="0") int page){
        Page<User> korisnici = userService.findAll(page);
        return new ResponseEntity<>(toUserDto.convert(korisnici.getContent()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "passwordChanged")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody UserPasswordChangeDto dto){


        if(!dto.getPassword().equals(dto.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean result;
        try {
            result = userService.changePassword(id, dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
