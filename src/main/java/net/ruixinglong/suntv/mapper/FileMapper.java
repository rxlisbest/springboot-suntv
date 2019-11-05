package net.ruixinglong.suntv.mapper;

import net.ruixinglong.suntv.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    List<FileEntity> findAll();

    FileEntity findOne(int id);

    int create(FileEntity file);

    int update(int id, FileEntity file);

    int delete(int id);
}
