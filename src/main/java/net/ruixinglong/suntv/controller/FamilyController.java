package net.ruixinglong.suntv.controller;

import com.github.pagehelper.PageInfo;
import net.ruixinglong.suntv.entity.FamilyEntity;
import net.ruixinglong.suntv.exception.BadRequestException;
import net.ruixinglong.suntv.exception.NotFoundException;
import net.ruixinglong.suntv.service.FamilyService;
import net.ruixinglong.suntv.utils.LocaleMessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@ResponseBody
@RestController
@RequestMapping(value = "families")
public class FamilyController {

    @Resource
    private FamilyService familyService;

    @GetMapping("/index")
    public PageInfo<FamilyEntity> index() throws Exception {
        PageInfo<FamilyEntity> list = familyService.findAll(1, 2);
        return list;
    }

    @GetMapping("/view/{id}")
    public FamilyEntity view(@PathVariable int id) throws Exception {
        FamilyEntity info = familyService.findOne(id);
        if (info == null) {
            throw new NotFoundException(LocaleMessageUtils.getMsg("record.not_found"));
        }
        return info;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public FamilyEntity create(@RequestBody @Valid FamilyEntity request, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        request.setCreate_user_id((Integer) httpServletRequest.getAttribute("user_id"));
        Integer id = familyService.create(request);
        FamilyEntity familyEntity = familyService.findOne(id);
        return familyEntity;
    }

    @PutMapping("/update/{id}")
    public Integer update(@PathVariable int id, @RequestBody @Valid FamilyEntity request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(LocaleMessageUtils.getMsg(bindingResult.getFieldError().getDefaultMessage()));
        }
        Integer rows = familyService.update(id, request);
        return rows;
    }
}
