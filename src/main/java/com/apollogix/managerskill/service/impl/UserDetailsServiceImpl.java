package com.apollogix.managerskill.service.impl;


import com.apollogix.managerskill.constants.Role;
import com.apollogix.managerskill.entity.MUser;
import com.apollogix.managerskill.repository.UserRepository;
import com.apollogix.managerskill.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.apollogix.managerskill.constants.Constants.*;
import static com.apollogix.managerskill.constants.MessageLabel.MSG_VALIDATE_USER_DO_NOT_EXIST;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;


    /**
     * Retrieves the user details by the specified email.
     *
     * @param email the email of the user to retrieve
     * @return the User object containing the user details
     * @throws UsernameNotFoundException if the user with the specified email is not found
     */
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        MUser user = userRepository.findByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException(messageService.getMessages(MSG_VALIDATE_USER_DO_NOT_EXIST), null);
        }
        // Setting Role
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (Objects.equals(user.getRole(), Role.ROLE_STUDENT.getI())) {
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_STUDENT.getRole()));
        } else if (Objects.equals(user.getRole(), Role.ROLE_TEACHER.getI())) {
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_TEACHER.getRole()));
        } else {
            authorities.add(new SimpleGrantedAuthority(BLANK));
        }
        return new User(user.getEmail(), user.getPassword(), user.getIsActive(), true, true, true, authorities);
    }
}
