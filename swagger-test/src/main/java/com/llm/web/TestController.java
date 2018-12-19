package com.llm.web;

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
public class TestController {

    @ResponseStatus
    @RequestMapping(path = "getOne/{id}", method = RequestMethod.GET)
    public String getOne(@PathVariable("id") Integer id) {
        return "welcome" + id;
    }

    @ResponseBody
    @RequestMapping(path = "getTwo",consumes = "application/json")
    public String getTwo(@RequestBody String jsonParams,@RequestBody String key) {
        return "hello " + jsonParams;
    }
}
