package com.llm.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/19
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@RestController
@RequestMapping(path = "app", method = RequestMethod.POST)
@Api(value = "TestSwagger|测试swaggerAPI")
public class TestController {

    @ResponseStatus
    @RequestMapping(path = "getOne/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "getOne接口",notes = "注释getOne")
    public String getOne(@PathVariable("id") Integer id) {
        return "welcome" + id;
    }

    @ResponseBody
    @RequestMapping(path = "getTwo")
    @ApiOperation(value="getTwo", notes="根据用户id修改密码")
    @ApiImplicitParams({@ApiImplicitParam(paramType="query", name = "jsonParams", value = "Json字符", required = true, dataType = "String")})
    public String getTwo(@RequestBody String jsonParams) {
        return "hello " + jsonParams;
    }
}
