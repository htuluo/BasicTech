package com.llm.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/21
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@RestController
@RequestMapping(path = "swagger")
@Api(value = "SwaggerAPI",description = "Swagger Description")
public class SwaggerController {
    @ResponseBody
    @RequestMapping(path = "testOne/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "testOne接口",notes = "注释testOne")
    public String testOne(@PathVariable("id") Integer id) {
        return "welcome" + id;
    }

}
