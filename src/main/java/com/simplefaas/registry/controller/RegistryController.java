package com.simplefaas.registry.controller;


import com.alibaba.fastjson.JSON;
import com.simplefaas.registry.pojo.ServerStatus;
import com.simplefaas.registry.service.ServerStatusTimeStamp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/servers")
public class RegistryController {


    @RequestMapping("/reg")
    public String postServers(String ip,long tasknum,String cache){

        ServerStatus status=new ServerStatus(ip,tasknum,cache,new Date());

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
