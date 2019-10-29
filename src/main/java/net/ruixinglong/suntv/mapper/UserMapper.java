package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> findAll();

    UserEntity findOne(int id);

    int create(UserEntity user);

    int update(int id, UserEntity user);

    int delete(int id);

    UserEntity findOneByCellphone(String cellphone);

    UserEntity findOneByIdAndCellphone(Integer id, String cellphone);

    Integer countByCellphone(String cellphone);

    Integer countByUsername(String username);
}
