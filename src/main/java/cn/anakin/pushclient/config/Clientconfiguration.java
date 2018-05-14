package cn.anakin.pushclient.config;

import cn.anakin.pushclient.cons.OrderEnum;
import cn.ishow.pushserver.subscriber.CanalClientConfig;
import cn.ishow.pushserver.subscriber.IRowDataChange;
import cn.ishow.pushserver.subscriber.Subscriber;
import com.gexin.rp.sdk.http.IGtPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/8
 * Time: 19:51
 **/
@Configuration
@Order(OrderEnum.MIDDLE)
public class Clientconfiguration {
    @Autowired
    private PushConfig pushConfig;
    @Autowired
    private CanalConfig canalConfig;
    @Autowired
    private IRowDataChange iRowDataChange;

    private final static Logger logger = LoggerFactory.getLogger(Clientconfiguration.class);

    @Bean
    public Subscriber subscriber() {
        Subscriber sub = null;
        try {
            sub = Subscriber.buildAsync(canalClientConfig(), iRowDataChange, 6);
            if( sub != null){
                sub.mon(15, TimeUnit.MINUTES, null);
            }
        }catch(Exception e){
            logger.error("{}",e);
        }
        return sub;
    }

    @Bean
    public CanalClientConfig canalClientConfig() {
        CanalClientConfig config = new CanalClientConfig();
        config.host = canalConfig.host;
        config.standby = "";
        config.port = canalConfig.port;
        config.destination = canalConfig.destination;
        config.username = canalConfig.username;
        config.password = canalConfig.password;
        return config;
    }

    @Bean
    public IGtPush iGtPush() {
        return new IGtPush(pushConfig.appkey, pushConfig.mastersecret);
    }
}
