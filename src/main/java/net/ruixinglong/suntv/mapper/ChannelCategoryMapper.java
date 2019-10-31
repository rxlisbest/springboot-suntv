package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.bean.ChannelCategoryUpdateStatusBean;
import net.ruixinglong.suntv.entity.ChannelCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelCategoryMapper {

    List<ChannelCategoryEntity> findAll();

    ChannelCategoryEntity findOne(int id);

    int create(ChannelCategoryEntity channelCategory);

    int update(int id, ChannelCategoryEntity channelCategory);

    int delete(int id);

    int updateStatus(Integer id, ChannelCategoryUpdateStatusBean channelCategoryUpdateStatusBean);
}
