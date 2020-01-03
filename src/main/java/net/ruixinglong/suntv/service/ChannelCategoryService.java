package net.ruixinglong.suntv.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.bean.ChannelCategoryUpdateStatusBean;
import net.ruixinglong.suntv.bean.PaginationBean;
import net.ruixinglong.suntv.entity.ChannelCategoryEntity;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.mapper.ChannelCategoryMapper;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelCategoryService {

    @Autowired
    ChannelCategoryMapper channelCategoryMapper;

    @Autowired
    PaginationBean paginationBean;

    public PageInfo<ChannelCategoryEntity> findAll(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ChannelCategoryEntity> channelCategoryList = channelCategoryMapper.findAll();
        PageInfo<ChannelCategoryEntity> pageInfo = new PageInfo<>(channelCategoryList);
        return pageInfo;
    }

    public ChannelCategoryEntity findOne(int id) {
        ChannelCategoryEntity channelCategoryEntity = channelCategoryMapper.findOne(id);
        return channelCategoryEntity;
    }

    public int create(ChannelCategoryEntity channelCategory) {
        channelCategoryMapper.create(channelCategory);
        return channelCategory.getId();
    }

    public int update(Integer id, ChannelCategoryEntity channelCategory) throws Exception {
        ChannelCategoryEntity channelCategoryEntity = channelCategoryMapper.findOne(id);
        if (channelCategoryEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = channelCategoryMapper.update(id, channelCategory);
        return rows;
    }

    public int updateStatus(Integer id, ChannelCategoryUpdateStatusBean channelCategoryUpdateStatusBean) throws Exception {
        ChannelCategoryEntity channelCategoryEntity = channelCategoryMapper.findOne(id);
        if (channelCategoryEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = channelCategoryMapper.updateStatus(id, channelCategoryUpdateStatusBean);
        return rows;
    }

    public int delete(int id) {
        int rows = channelCategoryMapper.delete(id);
        return rows;
    }

    public List<ChannelCategoryEntity> findAll() {
        List<ChannelCategoryEntity> channelCategoryList = channelCategoryMapper.findAll();
        return channelCategoryList;
    }

    public List<ChannelCategoryEntity> findAllByFamilyId(int familyId) {
        List<ChannelCategoryEntity> channelCategoryList = channelCategoryMapper.findAllByFamilyId(familyId);
        return channelCategoryList;
    }

    public PageInfo<ChannelCategoryEntity> findAllByFamilyId(int familyId, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = paginationBean.getPageSize();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ChannelCategoryEntity> channelCategoryList = channelCategoryMapper.findAllByFamilyId(familyId);
        PageInfo<ChannelCategoryEntity> pageInfo = new PageInfo<>(channelCategoryList);
        return pageInfo;
    }
}