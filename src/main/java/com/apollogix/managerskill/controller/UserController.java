package com.apollogix.managerskill.controller;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.request.LoginRequest;
import com.apollogix.managerskill.request.UserRegisterRequest;
import com.apollogix.managerskill.request.UserUpdateRequest;
import com.apollogix.managerskill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Performs a login operation for a user Student/Teacher.
     *
     * @param request the LoginRequest object containing the user's email and password
     * @return a ResponseEntity object with the result of the login operation
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    /**
     * API for Teacher create Student
     *
     * @param request the UserRegisterRequest object containing the user's email, password, and name
     * @return a ResponseEntity object with the result of the register operation
     * @throws BusinessException if email already exists in Database
     */
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody UserRegisterRequest request) throws BusinessException {
        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
    }

    /**
     * Update info user: Email, Password
     *
     * @param id      user need update
     * @param request data update for user
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserUpdateRequest request) throws BusinessException {
        return userService.update(id, request);
    }

    /**
     * API Delete User -> change isActive true -> false
     *
     * @param id user delete
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException {
        return userService.delete(id);
    }

    /**
     * API Update Role for user form Student -> Teacher
     *
     * @param id user need update
     * @return ResponseEntity
     */
    @DeleteMapping("/up-role/{id}")
    public ResponseEntity<?> UpRole(@PathVariable Integer id) throws BusinessException {
        return userService.upRole(id);
    }

}
