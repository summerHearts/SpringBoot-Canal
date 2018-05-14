package cn.anakin.pushclient.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 13:20
 **/
@Data
public class RegisterUser {
    private long uid;
    private String dev_token;
    private int client_type;
    private int accessid;
    private Timestamp update_time;
}
