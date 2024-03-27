package com.apollogix.managerskill.service.impl;


import com.apollogix.managerskill.entity.MUser;
import com.apollogix.managerskill.repository.UserRepository;
import com.apollogix_manager_skill.service.MessageService;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        MUser user = userRepository.findByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException(messageService.getMessages("MSG_VALIDATE_PHONE_NUMBER_NOT_EXISTS"), null);
        }
        // Setting Role
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_USER));
        return new User(user.getEmail(), user.getPassword(),
                user.getIsActive(), true, true, true,
                authorities);
    }
}
