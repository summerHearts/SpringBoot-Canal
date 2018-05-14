package cn.anakin.pushclient.config;

import cn.anakin.pushclient.cons.OrderEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 10:53
 **/
@Component
@ConfigurationProperties(prefix = "getui")
@Data
@Order(OrderEnum.HIGH)
public class PushConfig {
    String appid;
    String appkey;
    String appsecret;
    String mastersecret;
    int accessid;
}
