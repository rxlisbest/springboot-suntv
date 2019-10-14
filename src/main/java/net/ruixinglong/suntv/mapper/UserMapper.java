package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> index();

    UserEntity view(int id);

    int create(UserEntity user);

    int update(UserEntity user);

    int delete(UserEntity user);
}
