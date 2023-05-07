package com.simplefaas.registry;

import com.simplefaas.registry.pojo.ServerStatus;
import com.simplefaas.registry.service.ServerStatusTimeStamp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SimpleFaaSRegistryApplication {

    public static void main(String[] args) {

        SpringApplication.run(SimpleFaaSRegistryApplication.class, args);


        // 启动定期删除过期server线程
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    ServerStatusTimeStamp servers = ServerStatusTimeStamp.getInstance();
                    List<ServerStatus> serverList = servers.status;


                    for (int i = 0; i < serverList.size(); i++) {
                        Date now = new Date();
                        ServerStatus status = serverList.get(i);

                        long subtime = subTime(now, status.getDate());

                        if (subtime > liveTime) {
                            System.out.println("Delete: "+status.getHost_ip());
                            serverList.remove(i);
                            servers.statusMap.remove(status.getHost_ip());

                            i--;
                        }
                    }

                    try {
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }).start();

    }

    public static int liveTime=10;

    public static long subTime(Date now,Date pre){
        long nowtime=now.getTime();
        long pretime=pre.getTime();

        return (nowtime-pretime)/1000;
    }

}
