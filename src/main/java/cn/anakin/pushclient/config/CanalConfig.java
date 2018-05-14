package cn.anakin.pushclient.config;

import cn.anakin.pushclient.cons.OrderEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/8
 * Time: 19:44
 **/
@Component
@ConfigurationProperties(prefix = "canal")
@Data
@Order(OrderEnum.HIGH)
public class CanalConfig {
    String host;
    int port;
    String destination;
    String username;
    String password;
}
