package net.ruixinglong.suntv.controller;

import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.entity.UserFamilyEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.service.UserFamilyService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@ResponseBody
@RestController
@RequestMapping(value = "user-families")
public class UserFamilyController {

    @Resource
    private UserFamilyService userFamilyService;

    @GetMapping("/index")
    public PageInfo<UserFamilyEntity> index() throws Exception {
        PageInfo<UserFamilyEntity> list = userFamilyService.findAll(1, 2);
        return list;
    }

    @GetMapping("/view/{id}")
    public UserFamilyEntity view(@PathVariable int id) throws Exception {
        UserFamilyEntity info = userFamilyService.findOne(id);
        if (info == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        return info;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public UserFamilyEntity create(@RequestBody @Valid UserFamilyEntity request, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setCreate_user_id((Integer) httpServletRequest.getAttribute("user_id"));
        Integer id = userFamilyService.create(request);
        UserFamilyEntity userFamilyEntity = userFamilyService.findOne(id);
        return userFamilyEntity;
    }

    @PutMapping("/update/{id}")
    public Integer update(@PathVariable int id, @RequestBody @Valid UserFamilyEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer rows = userFamilyService.update(id, request);
        return rows;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable int id) {
        Integer rows = userFamilyService.delete(id);
        return rows;
    }
}
