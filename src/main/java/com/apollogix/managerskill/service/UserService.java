package com.apollogix.managerskill.service;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.request.LoginRequest;
import com.apollogix.managerskill.request.UserRegisterRequest;
import com.apollogix.managerskill.request.UserUpdateRequest;
import com.apollogix.managerskill.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserService {

    /**
     * Authenticates a user login request.
     *
     * @param request the login request containing the user's email and password
     * @return the login response containing the user's name and access token
     * @throws BadCredentialsException if the email or password is incorrect
     * @throws BadCredentialsException if the user's account is inactive
     */
    LoginResponse login(LoginRequest request) throws BadCredentialsException;

    /**
     * Function for Teacher register account for Student
     *
     * @param request is Data register: Email, Password, Name
     * @return id new user if create successful
     * @throws BusinessException if email already exists in database
     */
    Integer register(UserRegisterRequest request) throws BusinessException;

    /**
     * Delete User in database
     *
     * @param id user delete
     * @return ResponseEntity Ok/NotFound
     */
    ResponseEntity<Object> delete(Integer id) throws BusinessException;

    /**
     * Update user by id
     *
     * @param id      user need update
     * @param request data update
     * @return ResponseEntity
     */
    ResponseEntity<?> update(Integer id, UserUpdateRequest request) throws BusinessException;

    /**
     * Update role for User form Student -> Teacher
     *
     * @param id user teacher need up role
     * @return ResponseEntity
     */
    ResponseEntity<?> upRole(Integer id) throws BusinessException;
}
