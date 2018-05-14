package cn.anakin.pushclient.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/9
 * Time: 10:42
 **/
@Data
public class ImMessage {
    private long            id;
    private String          text;
    private long            msgtime;
    private long            fromid;
    private long            toid;
    private int             msgtype;
    private Timestamp       sendtime;
    private int             fromtype;
}
