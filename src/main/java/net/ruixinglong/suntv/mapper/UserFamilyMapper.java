package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.entity.UserFamilyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFamilyMapper {

    List<UserFamilyEntity> findAll();

    UserFamilyEntity findOne(int id);

    int create(UserFamilyEntity userFamily);

    int update(int id, UserFamilyEntity userFamily);

    int delete(int id);

    UserFamilyEntity findDefaultOne(int user_id);
}
