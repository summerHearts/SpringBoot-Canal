package cn.anakin.pushclient.service;

import cn.anakin.pushclient.model.ImMessage;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/8
 * Time: 19:54
 **/
public interface MessageService {
    void transportProces(ImMessage m);
}
