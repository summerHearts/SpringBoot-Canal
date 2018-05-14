package cn.anakin.pushclient;

import cn.anakin.pushclient.config.Clientconfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PushclientApplication {

    @Autowired
    Clientconfiguration clientconfiguration;

    public static void main(String[] args) {
        SpringApplication.run(PushclientApplication.class, args);
    }
}
