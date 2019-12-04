package net.ruixinglong.suntv.service;

import net.ruixinglong.suntv.bean.UserRegisterBean;
import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.mapper.UserMapper;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<UserEntity> findAll() {
        List<UserEntity> userList = null;
        userList = userMapper.findAll();
        return userList;
    }

    public UserEntity findOne(int id) {
        UserEntity userEntity = null;
        userEntity = userMapper.findOne(id);
        return userEntity;
    }

    @Transactional
    public int create(UserEntity user) throws Exception {
        Integer countCellphone = userMapper.countByCellphone(user.getCellphone());
        if (countCellphone > 0) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.cellphone_used"));
        }
        Integer countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            throw new BadRequestException(LocaleMessageUtils.getMsg("user.username_used"));
        }
        int rows = userMapper.create(user);
        return user.getId();
    }

    public int register(UserRegisterBean userRegisterBean) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRegisterBean.getName());
        userEntity.setCellphone(userRegisterBean.getCellphone());
        userEntity.setUsername("");
        String password = DigestUtils.md5DigestAsHex(userRegisterBean.getPassword().getBytes());
        userEntity.setPassword(password);
        return this.create(userEntity);
    }

    @Transactional
    public int update(Integer id, UserEntity user) throws Exception {
        UserEntity userEntity = userMapper.findOne(id);
        if (userEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        if (!userEntity.getCellphone().equals(user.getCellphone())) {
            Integer countByCellphone = userMapper.countByCellphone(user.getCellphone());
            if (countByCellphone > 0) {
                throw new BadRequestException(LocaleMessageUtils.getMsg("user.cellphone_used"));
            }
        }
        if (!userEntity.getUsername().equals(user.getUsername())) {
            Integer countByUsername = userMapper.countByUsername(user.getUsername());
            if (countByUsername > 0) {
                throw new BadRequestException(LocaleMessageUtils.getMsg("user.username_used"));
            }
        }
        int rows = userMapper.update(id, user);
        return rows;
    }

    public UserEntity findOneByCellphone(String cellphone) {
        UserEntity userEntity = null;
        userEntity = userMapper.findOneByCellphone(cellphone);
        return userEntity;
    }

    public UserEntity findOneByIdAndCellphone(Integer id, String cellphone) {
        UserEntity userEntity = null;
        userEntity = userMapper.findOneByIdAndCellphone(id, cellphone);
        return userEntity;
    }
}