package cn.anakin.pushclient.service;

import cn.anakin.pushclient.model.RegisterUser;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 13:30
 **/
public interface UserService {
    RegisterUser findByUid(long uid);
}
