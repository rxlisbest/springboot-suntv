package net.ruixinglong.suntv.controller;

import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.bean.ChannelUpdateStatusBean;
import net.ruixinglong.suntv.bean.PaginationBean;
import net.ruixinglong.suntv.entity.ChannelEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.service.ChannelService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@ResponseBody
@RestController
@RequestMapping(value = "channels")
public class ChannelController {

    @Resource
    private ChannelService channelService;

    @Autowired
    PaginationBean paginationBean;

    @GetMapping("/index")
    public PageInfo<ChannelEntity> index(Integer pageNum, Integer pageSize) throws Exception {
        PageInfo<ChannelEntity> list = channelService.findAll(pageNum, pageSize);
        return list;
    }

    @GetMapping("/view/{id}")
    public ChannelEntity view(@PathVariable int id) throws Exception {
        ChannelEntity info = channelService.findOne(id);
        if (info == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        return info;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ChannelEntity create(@RequestBody @Valid ChannelEntity request, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setCreate_user_id((Integer) httpServletRequest.getAttribute("user_id"));
        request.setUrl(request.getUrl() != null ? request.getUrl() : "");
        request.setFamily_id((Integer) httpServletRequest.getAttribute("family_id"));
        Integer id = channelService.create(request);
        ChannelEntity channelEntity = channelService.findOne(id);
        return channelEntity;
    }

    @PutMapping("/update/{id}")
    public Integer update(@PathVariable int id, @RequestBody @Valid ChannelEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setUrl(request.getUrl() != null ? request.getUrl() : "");
        Integer rows = channelService.update(id, request);
        return rows;
    }

    @PatchMapping("/update-status/{id}")
    public Integer updateStatus(@PathVariable int id, @RequestBody @Valid ChannelUpdateStatusBean request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer rows = channelService.updateStatus(id, request);
        return rows;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable int id) {
        Integer rows = channelService.delete(id);
        return rows;
    }

    @GetMapping("/family-index")
    public PageInfo<ChannelEntity> index(Integer pageNum, Integer pageSize, HttpServletRequest httpServletRequest) throws Exception {
        int familyId = (Integer) httpServletRequest.getAttribute("family_id");
        PageInfo<ChannelEntity> list = channelService.findAllByFamilyId(familyId, pageNum, pageSize);
        return list;
    }
}
