package cn.anakin.pushclient.service.impl;

import cn.anakin.pushclient.service.MessageService;
import cn.anakin.pushclient.cons.CanalRowEnum;
import cn.anakin.pushclient.model.ImMessage;
import cn.ishow.pushserver.subscriber.IRowDataChange;
import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: AnakinSky
 * Date: 2018/5/8
 * Time: 19:55
 **/
@Service
public class IRowDataImpl implements IRowDataChange {

    @Autowired
    private MessageService messageService;

    @Override
    public void onDelete(CanalEntry.Header header, List<CanalEntry.Column> list) {
        onProcessData(header.getTableName(),CanalRowEnum.ACTION_DELETE,list, null);
    }

    @Override
    public void onInsert(CanalEntry.Header header, List<CanalEntry.Column> list) {
        onProcessData(header.getTableName(),CanalRowEnum.ACTION_INSERT,list, null);
    }

    @Override
    public void onUpdate(CanalEntry.Header header, List<CanalEntry.Column> list, List<CanalEntry.Column> list1) {
        onProcessData(header.getTableName(),CanalRowEnum.ACTION_UPDATE,list, list1);
    }
    private void onProcessData(String table, int action, List<CanalEntry.Column> columns,
                               List<CanalEntry.Column> columnsbefor) {
        if (!table.startsWith("im_message_") || action != CanalRowEnum.ACTION_INSERT) {
            return;
        }
        ImMessage nmsg = new ImMessage();
        for (CanalEntry.Column column : columns) {
            final String colName = column.getName();
            final String colValue = column.getValue();

            switch (colName) {
                case "id":
                    nmsg.setId(Integer.valueOf(colValue));
                    break;
                case "text":
                    nmsg.setText(colValue != null? String.valueOf(colValue): null);
                    break;
                case "msgtime":
                    nmsg.setMsgtime(colValue != null? Integer.valueOf(colValue): 0);
                    break;
                case "fromid":
                    nmsg.setFromid(Integer.valueOf(colValue));
                    break;
                case "toid":
                    nmsg.setToid(Integer.valueOf(colValue));
                    break;
                case "msgtype":
                    nmsg.setMsgtype(Integer.valueOf(colValue));
                    break;
                case "sendtime":
                    nmsg.setSendtime(colValue != null? Timestamp.valueOf(colValue):null);
                    break;
                case "fromtype":
                    nmsg.setFromtype(Integer.valueOf(colValue));
                    break;
                default:
                    break;
            }
        }
        messageService.transportProces(nmsg);
    }
}
