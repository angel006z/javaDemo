package com.meida.backend.basic.service.inter;

import java.util.List;

import com.meida.backend.basic.po.User;

public interface IUserService {
    Integer queryTotal(User user);
    List<User> queryPage(User user, Integer page, Integer pageSize);
    List<User> query(User user);
    User getUserById(Long id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
}
