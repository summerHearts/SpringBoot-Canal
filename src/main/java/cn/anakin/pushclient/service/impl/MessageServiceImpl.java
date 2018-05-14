package cn.anakin.pushclient.service.impl;

import cn.anakin.pushclient.plugin.GeTuiAPI;
import cn.anakin.pushclient.service.MessageService;
import cn.anakin.pushclient.service.UserService;
import cn.anakin.pushclient.model.ImMessage;
import cn.anakin.pushclient.model.RegisterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 11:31
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserService userService;
    @Autowired
    private GeTuiAPI geTuiAPI;
    private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    @Override
    public void transportProces(ImMessage m) {
        RegisterUser user = userService.findByUid(m.getToid());
        if (user == null) {
            logger.error("cann't find user:{}", m.getToid());
            return;
        }
        geTuiAPI.addPushTaskToLine(m, user);
    }
}
