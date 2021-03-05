/*
 *
 * Copyright (c) 2011, 2016 CPJ and/or its affiliates. All rights reserved.
 *
 */
/**
 * Copyright 2020-2021 JinlongLiao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jinlongliao.swagger4j.demo.struts2.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import io.github.jinlongliao.swagger4j.annotation.API;
import io.github.jinlongliao.swagger4j.annotation.APIs;
import io.github.jinlongliao.swagger4j.annotation.DataType;
import io.github.jinlongliao.swagger4j.annotation.Param;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.alibaba.fastjson.JSONWriter;

/**
 * @author yonghuan
 */
@APIs("/demo")
@Namespace("/demo")
public class DemoAction {
    @API(value = "login", summary = "示例1", parameters = {
            @Param(name = "username", description = "用户名", dataType = DataType.STRING),
            @Param(name = "password", description = "密码", dataType = DataType.PASSWORD),
            @Param(name = "image", description = "图片", dataType = DataType.FILE)
    })
    @Action(value = "login")
    public void login() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        JSONWriter writer = new JSONWriter(response.getWriter());
        Map<String, String> user = new HashMap<String, String>();
        user.put("username", username);
        user.put("password", password);
        writer.writeObject(user);
        writer.flush();
        writer.close();
    }

    @API(value = "login2", summary = "示例1", parameters = {
            @Param(name = "username", description = "用户名", dataType = DataType.LONG),
            @Param(name = "password", description = "密码", dataType = DataType.PASSWORD),
            @Param(name = "image", description = "图片", dataType = DataType.FILE)
    })
    @Action(value = "login2")
    public void login2() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        JSONWriter writer = new JSONWriter(response.getWriter());
        Map<String, String> user = new HashMap<String, String>();
        user.put("username", username);
        user.put("password", password);
        writer.writeObject(user);
        writer.flush();
        writer.close();
    }

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
