package com.apollogix.managerskill.service.impl;

import com.apollogix.managerskill.constants.Role;
import com.apollogix.managerskill.entity.MUser;
import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.repository.UserRepository;
import com.apollogix.managerskill.request.LoginRequest;
import com.apollogix.managerskill.request.UserRegisterRequest;
import com.apollogix.managerskill.request.UserUpdateRequest;
import com.apollogix.managerskill.response.LoginResponse;
import com.apollogix.managerskill.security.JwtUtil;
import com.apollogix.managerskill.service.MessageService;
import com.apollogix.managerskill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

import static com.apollogix.managerskill.constants.MessageLabel.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(messageService.getMessages(MSG_AUTHENTICATION_IN_CORRECT_EMAIL_OR_PASSWORD));
        } catch (DisabledException disabledException) {
            throw new BadCredentialsException(messageService.getMessages(MSG_AUTHENTICATION_ACCOUNT_IN_ACTIVE));
        }
        MUser user = userRepository.findByEmail(request.getEmail());
        return LoginResponse.builder()
                .accessToken(jwtUtil.generateToken(user.getEmail()))
                .name(user.getName())
                .build();
    }

    @Override
    public Integer register(UserRegisterRequest request) throws BusinessException {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new BusinessException(messageService.getMessages(MSG_VALIDATE_EMAIL_ALREADY_EXIST));
        }
        MUser mUser = MUser.builder().email(request.getEmail())
                .name(request.getName())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(Role.ROLE_STUDENT.getI())
                .isActive(true).build();
        userRepository.save(mUser);
        return mUser.getId();
    }

    @Override
    public ResponseEntity<Object> delete(Integer id) throws BusinessException {
        MUser user = checkExistUserById(id);
        user.setIsActive(false);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> update(Integer id, UserUpdateRequest request) throws BusinessException {
        MUser user = checkExistUserById(id);
        if (!ObjectUtils.isEmpty(request.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        }
        if (!ObjectUtils.isEmpty(request.getName())) {
            user.setName(request.getName());
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> upRole(Integer id) throws BusinessException {
        MUser user = checkExistUserById(id);
        Optional<MUser> mUser = userRepository.findById(id);
        if(mUser.isPresent()){
            if (!ObjectUtils.isEmpty(request.getPassword())){
                mUser.get().setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            }
            if (!ObjectUtils.isEmpty(request.getName())){
                mUser.get().setName(request.getName());
            }
            userRepository.save(mUser.get());
            return ResponseEntity.ok().build();
        } else {
            throw new BusinessException(messageService.getMessages(MSG_VALIDATE_USER_DO_NOT_EXIST));
        }
    }

    /**
     * Checks if a user with the given ID exists in the database.
     *
     * @param id The ID of the user to check.
     * @return The user with the given ID.
     * @throws BusinessException If the user with the given ID does not exist.
     */
    private MUser checkExistUserById(Integer id) throws BusinessException {
        Optional<MUser> mUser = userRepository.findById(id);
        if(!mUser.isPresent()){
            throw new BusinessException(messageService.getMessages(MSG_VALIDATE_USER_DO_NOT_EXIST));
        } else {
            return mUser.get();
        }
    }
}
