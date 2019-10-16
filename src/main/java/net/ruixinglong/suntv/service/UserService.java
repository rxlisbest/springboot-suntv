package net.ruixinglong.suntv.service;

import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<UserEntity> index() {
        List<UserEntity> userList = null;
        userList = userMapper.index();
        return userList;
    }

    public int create(UserEntity user) {
        int rows = userMapper.create(user);
        return user.getId();
    }

    public int update(UserEntity user) {
        int rows = userMapper.update(user);
        return rows;
    }
}