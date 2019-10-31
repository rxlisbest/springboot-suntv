package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.bean.FamilyUpdateStatusBean;
import net.ruixinglong.suntv.entity.FamilyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMapper {

    List<FamilyEntity> findAll();

    FamilyEntity findOne(int id);

    int create(FamilyEntity family);

    int update(int id, FamilyEntity family);

    int delete(int id);

    int updateStatus(Integer id, FamilyUpdateStatusBean familyUpdateStatusBean);
}
