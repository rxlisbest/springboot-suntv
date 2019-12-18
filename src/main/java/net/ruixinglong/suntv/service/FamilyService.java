package net.ruixinglong.suntv.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.bean.FamilyUpdateStatusBean;
import net.ruixinglong.suntv.entity.FamilyEntity;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.mapper.FamilyMapper;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    FamilyMapper familyMapper;

    public PageInfo<FamilyEntity> findAll(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<FamilyEntity> familyList = familyMapper.findAll();
        PageInfo<FamilyEntity> pageInfo = new PageInfo<>(familyList);
        return pageInfo;
    }

    public FamilyEntity findOne(int id) {
        FamilyEntity familyEntity = familyMapper.findOne(id);
        return familyEntity;
    }

    public int create(FamilyEntity family) {
        familyMapper.create(family);
        return family.getId();
    }

    public int update(Integer id, FamilyEntity family) throws Exception {
        FamilyEntity familyEntity = familyMapper.findOne(id);
        if (familyEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = familyMapper.update(id, family);
        return rows;
    }

    public int updateStatus(Integer id, FamilyUpdateStatusBean familyUpdateStatusBean) throws Exception {
        FamilyEntity familyEntity = familyMapper.findOne(id);
        if (familyEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = familyMapper.updateStatus(id, familyUpdateStatusBean);
        return rows;
    }

    public int delete(int id) {
        int rows = familyMapper.delete(id);
        return rows;
    }

    public FamilyEntity findOfficialOne() {
        FamilyEntity familyEntity = familyMapper.findOfficialOne();
        return familyEntity;
    }
}