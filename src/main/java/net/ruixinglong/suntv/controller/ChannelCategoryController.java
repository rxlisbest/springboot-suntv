package net.ruixinglong.suntv.controller;

import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.bean.ChannelCategoryUpdateStatusBean;
import net.ruixinglong.suntv.bean.PaginationBean;
import net.ruixinglong.suntv.entity.ChannelCategoryEntity;
import net.ruixinglong.suntv.entity.ChannelEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.service.ChannelCategoryService;
import net.ruixinglong.suntv.service.ChannelService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping(value = "channel-categories")
public class ChannelCategoryController {

    @Resource
    private ChannelCategoryService channelCategoryService;

    @Resource
    private ChannelService channelService;

    @GetMapping("/index")
    public PageInfo<ChannelCategoryEntity> index() throws Exception {
        PageInfo<ChannelCategoryEntity> list = channelCategoryService.findAll(1, 2);
        return list;
    }

    @GetMapping("/view/{id}")
    public ChannelCategoryEntity view(@PathVariable int id) throws Exception {
        ChannelCategoryEntity info = channelCategoryService.findOne(id);
        if (info == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        return info;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ChannelCategoryEntity create(@RequestBody @Valid ChannelCategoryEntity request, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setCreate_user_id((Integer) httpServletRequest.getAttribute("user_id"));
        request.setFamily_id((Integer) httpServletRequest.getAttribute("family_id"));
        Integer id = channelCategoryService.create(request);
        ChannelCategoryEntity channelCategoryEntity = channelCategoryService.findOne(id);
        return channelCategoryEntity;
    }

    @PutMapping("/update/{id}")
    public Integer update(@PathVariable int id, @RequestBody @Valid ChannelCategoryEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer rows = channelCategoryService.update(id, request);
        return rows;
    }

    @PatchMapping("/update-status/{id}")
    public Integer updateStatus(@PathVariable int id, @RequestBody @Valid ChannelCategoryUpdateStatusBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer rows = channelCategoryService.updateStatus(id, request);
        return rows;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable int id) throws NotFoundException {
        List<ChannelEntity> channelList = channelService.findAllByChannelCategoryId(id);
        if (channelList.size() != 0) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("channel_category.delete.has_channel"));
        }
        Integer rows = channelCategoryService.delete(id);
        return rows;
    }

    @GetMapping("/all")
    public List<ChannelCategoryEntity> all() throws Exception {
        List<ChannelCategoryEntity> list = channelCategoryService.findAll();
        return list;
    }

    @GetMapping("/family-all")
    public List<ChannelCategoryEntity> familyAll(HttpServletRequest httpServletRequest) throws Exception {
        int familyId = (Integer) httpServletRequest.getAttribute("family_id");
        List<ChannelCategoryEntity> list = channelCategoryService.findAllByFamilyId(familyId);
        return list;
    }

    @GetMapping("/family-index")
    public PageInfo<ChannelCategoryEntity> familyIndex(Integer pageNum, Integer pageSize,  HttpServletRequest httpServletRequest) throws Exception {
        int familyId = (Integer) httpServletRequest.getAttribute("family_id");
        PageInfo<ChannelCategoryEntity> list = channelCategoryService.findAllByFamilyId(familyId, pageNum, pageSize);
        return list;
    }
}
