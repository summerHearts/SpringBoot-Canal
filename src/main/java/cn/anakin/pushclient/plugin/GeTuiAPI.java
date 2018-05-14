package cn.anakin.pushclient.plugin;

import cn.anakin.pushclient.model.ImMessage;
import cn.anakin.pushclient.model.RegisterUser;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 17:23
 **/
public interface GeTuiAPI {
    void addPushTaskToLine(ImMessage msg, RegisterUser user);
}
