package net.ruixinglong.suntv.controller;

import net.ruixinglong.suntv.exception.ForbiddenException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@RequestMapping(value = "files")
public class FileController {

    @RequestMapping("/index")
    public String index() throws Exception {
        if (true) {
            throw new ForbiddenException("test");
        }
        return "2";
    }
}
