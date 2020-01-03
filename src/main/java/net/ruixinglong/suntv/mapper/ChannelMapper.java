package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.bean.ChannelUpdateStatusBean;
import net.ruixinglong.suntv.entity.ChannelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelMapper {

    List<ChannelEntity> findAll();

    ChannelEntity findOne(int id);

    int create(ChannelEntity channel);

    int update(int id, ChannelEntity channel);

    int delete(int id);

    int updateStatus(Integer id, ChannelUpdateStatusBean channelUpdateStatusBean);

    List<ChannelEntity> findAllByFamilyId(int family_id);

    List<ChannelEntity> findAllByChannelCategoryId(int channel_category_id);
}
