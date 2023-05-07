package com.simplefaas.registry.controller;


import com.alibaba.fastjson.JSON;
import com.simplefaas.registry.pojo.ServerStatus;
import com.simplefaas.registry.service.ServerStatusTimeStamp;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;




@RestController
@RequestMapping("/servers")
public class RegistryController {


    @RequestMapping("/reg")
    public String postServers(String host_ip,long task_num,String image_cache){


        ServerStatus status=new ServerStatus(host_ip,task_num,image_cache,new Date());
        System.out.println("Get server msg: "+JSON.toJSONString(status));

        ServerStatusTimeStamp servers=ServerStatusTimeStamp.getInstance();
        servers.add_update(status);

        return "ok";
    }

    @RequestMapping("/list")
    public String getServers(){
        ServerStatusTimeStamp servers=ServerStatusTimeStamp.getInstance();

        List<ServerStatus> serverList=servers.status;

        return JSON.toJSONString(serverList);

    }




}
