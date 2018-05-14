package cn.anakin.pushclient.service.impl;

import cn.anakin.pushclient.service.UserService;
import cn.anakin.pushclient.dao.UserDao;
import cn.anakin.pushclient.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 13:31
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public RegisterUser findByUid(long uid) {
        return userDao.findByUid(uid);
    }
}
