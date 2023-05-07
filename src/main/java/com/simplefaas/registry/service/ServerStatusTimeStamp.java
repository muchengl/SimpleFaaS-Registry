package com.simplefaas.registry.service;

import com.simplefaas.registry.pojo.ServerStatus;

import java.util.*;


public class ServerStatusTimeStamp {

    // 存储所有注册的节点
    public List<ServerStatus> status;

    // <ip,ServerStatus>
    public Map<String,ServerStatus> statusMap;

    private static volatile ServerStatusTimeStamp servers;

    private ServerStatusTimeStamp(){};

    public synchronized static ServerStatusTimeStamp getInstance(){
        if(servers==null){
            synchronized (ServerStatusTimeStamp.class){
                if(servers==null){
                    servers=new ServerStatusTimeStamp();

                    servers.status=new LinkedList<>();
                    servers.statusMap=new HashMap<>();
                }
            }
        }
        return servers;
    }

    public void update(ServerStatus status){
        // 避免更新过程中，线程删除了对象
        synchronized (ServerStatusTimeStamp.class) {
            String ip = status.getHost_ip();
            if (statusMap.containsKey(ip)) {
                statusMap.get(ip).update(status);
            } else {
                this.status.add(status);
                statusMap.put(ip, status);

            }
        }
    }

}
