package iyun.anche.auth.controller;

import iyun.anche.auth.common.utils.ResultUtil;
import iyun.anche.auth.common.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leap")
public class LeapController {

    @GetMapping(value = "/bus")
    public Result bus() {
        Result result = new Result();

        return ResultUtil.success();
    }
}
