package cn.anakin.pushclient.dao;

import cn.anakin.pushclient.model.RegisterUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 13:19
 **/
@Mapper
public interface UserDao {
    /**
     * 根据内部id查询用户信息
     * @param uid
     * @return
     */
    RegisterUser findByUid(@Param("uid") long uid);
}
