package net.ruixinglong.suntv.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.entity.UserFamilyEntity;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.mapper.UserFamilyMapper;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFamilyService {

    @Autowired
    UserFamilyMapper userFamilyMapper;

    public PageInfo<UserFamilyEntity> findAll(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<UserFamilyEntity> userFamilyList = userFamilyMapper.findAll();
        PageInfo<UserFamilyEntity> pageInfo = new PageInfo<>(userFamilyList);
        return pageInfo;
    }

    public UserFamilyEntity findOne(int id) {
        UserFamilyEntity userFamilyEntity = userFamilyMapper.findOne(id);
        return userFamilyEntity;
    }

    public int create(UserFamilyEntity userFamily) {
        userFamilyMapper.create(userFamily);
        return userFamily.getId();
    }

    public int update(Integer id, UserFamilyEntity userFamily) throws Exception {
        UserFamilyEntity userFamilyEntity = userFamilyMapper.findOne(id);
        if (userFamilyEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = userFamilyMapper.update(id, userFamily);
        return rows;
    }

    public int delete(int id) {
        int rows = userFamilyMapper.delete(id);
        return rows;
    }
}