package cn.anakin.pushclient;

import cn.anakin.pushclient.model.ImMessage;
import cn.anakin.pushclient.model.RegisterUser;
import cn.anakin.pushclient.service.UserService;
import com.gexin.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushclientApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {

    }
    @Test
    public void test() {
        ImMessage msg = new ImMessage();
        Object j = JSON.toJSON(msg);
        String s = j.toString();
        RegisterUser u = userService.findByUid(80000197);
    }

}
