package cn.anakin.pushclient.plugin.impl;

import cn.anakin.pushclient.plugin.GeTuiAPI;
import cn.anakin.pushclient.config.PushConfig;
import cn.anakin.pushclient.model.ImMessage;
import cn.anakin.pushclient.model.RegisterUser;
import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 17:26
 **/
@Service
public class GeTuiAPIImpl implements GeTuiAPI {

    private ExecutorService executorService;
    @Autowired
    private IGtPush iGtPush;
    @Autowired
    private PushConfig pushConfig;
    private static final Logger logger = LoggerFactory.getLogger(GeTuiAPIImpl.class);

    public GeTuiAPIImpl() {
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        executorService = new ThreadPoolExecutor(0, 10, 6, TimeUnit.SECONDS,
                linkedBlockingQueue, Executors.defaultThreadFactory());
    }
    @Override
    public void addPushTaskToLine(final ImMessage msg, final RegisterUser user) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                push(getSingleMessage(msg, pushConfig.getAppid(), pushConfig.getAppkey()),
                        pushConfig.getAppid(), user.getDev_token());
            }
        });
    }

    private TransmissionTemplate getTemplate(ImMessage msg, String appid, String appkey) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appid);
        template.setAppkey(appkey);
        template.setTransmissionType(2);
        template.setTransmissionContent(JSON.toJSONString(msg));
        APNPayload payload = new APNPayload();
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(msg.getText()));
        template.setAPNInfo(payload);
        return template;
    }

    private SingleMessage getSingleMessage(ImMessage msg, String appid, String appkey) {
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        message.setOfflineExpireTime(8000);
        message.setData(getTemplate(msg, appid, appkey));
        message.setPushNetWorkType(0);
        return message;
    }

    private void push(SingleMessage singleMessage, String appid, String token) {
        Target target = new Target();
        target.setAppId(appid);
        target.setClientId(token);
        IPushResult ret = iGtPush.pushMessageToSingle(singleMessage, target);
        if (ret != null) {
            if (ret.getResponse().containsKey("result") && ret.getResponse().get("result").equals("ok")) {
                return;
            }
            logger.error("push fail, errmsg = " + ret.getResponse().toString());
        } else {
            logger.error("push fail, server error");
        }
    }
}
