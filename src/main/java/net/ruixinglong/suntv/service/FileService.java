package net.ruixinglong.suntv.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.entity.FileEntity;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.mapper.FileMapper;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    public PageInfo<FileEntity> findAll(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<FileEntity> fileList = fileMapper.findAll();
        PageInfo<FileEntity> pageInfo = new PageInfo<>(fileList);
        return pageInfo;
    }

    public FileEntity findOne(int id) {
        FileEntity fileEntity = fileMapper.findOne(id);
        return fileEntity;
    }

    public int create(FileEntity file) {
        fileMapper.create(file);
        return file.getId();
    }

    public int update(Integer id, FileEntity file) throws Exception {
        FileEntity fileEntity = fileMapper.findOne(id);
        if (fileEntity == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        int rows = fileMapper.update(id, file);
        return rows;
    }

    public int delete(int id) {
        int rows = fileMapper.delete(id);
        return rows;
    }
}