package net.ruixinglong.suntv.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.bean.ChannelUpdateStatusBean;
import net.ruixinglong.suntv.entity.ChannelEntity;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.mapper.ChannelMapper;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    public PageInfo<ChannelEntity> findAll(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ChannelEntity> channelList = channelMapper.findAll();
        PageInfo<ChannelEntity> pageInfo = new PageInfo<>(channelList);
        return pageInfo;
    }

    public ChannelEntity findOne(int id) {
        ChannelEntity channelEntity = channelMapper.findOne(id);
        return channelEntity;
    }

    public int create(ChannelEntity channel) {
        channelMapper.create(channel);
        return channel.getId();
    }

    public int update(Integer id, ChannelEntity channel) throws Exception {
        ChannelEntity channelEntity = channelMapper.findOne(id);
        if (channelEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = channelMapper.update(id, channel);
        return rows;
    }

    public int updateStatus(Integer id, ChannelUpdateStatusBean channelUpdateStatusBean) throws Exception {
        ChannelEntity channelEntity = channelMapper.findOne(id);
        if (channelEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = channelMapper.updateStatus(id, channelUpdateStatusBean);
        return rows;
    }

    public int delete(int id) {
        int rows = channelMapper.delete(id);
        return rows;
    }
}