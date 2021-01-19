package friendGame.action.msg;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import friendGame.action.annotation.RequestMsgType;


/**
 * 消息注册
 *
 * @author caohuihui
 */
public class MsgRegistry {
    private Map<Integer, Class<?>> msgInfos = new HashMap<>();
    private static transient Logger logger = LoggerFactory.getLogger(MsgRegistry.class);

    /**
     * 初始化消息体定义
     */
    private MsgRegistry() {
        logger.info("[cjddz msg registry init....]");
        String path = MsgRegistry.class.getResource("").getPath();
        path = path + "request/";
        File requestMsgFiles = new File(path);
        String[] allMsgInfos = requestMsgFiles.list();
        for (String msgInfo : allMsgInfos) {
            try {
                String msgPathInfo = path + msgInfo;
                msgPathInfo = msgPathInfo.substring(msgPathInfo.indexOf("classes") + 8, msgPathInfo.lastIndexOf("."));
                msgPathInfo = msgPathInfo.replace("/", ".");
                Class<?> msgClazz = Class.forName(msgPathInfo);
                RequestMsgType requestMsgType = msgClazz.getAnnotation(RequestMsgType.class);
                msgInfos.put(requestMsgType.msgType(), msgClazz);
            } catch (ClassNotFoundException e) {
                logger.error("[cjddz msg registry class not found..]", e);
            }
        }
    }

    private static final MsgRegistry registry = new MsgRegistry();

    public static MsgRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取消息类型
     *
     * @param msgType
     * @return
     */
    public Class<?> getMsgClass(int msgType) {
        return msgInfos.get(msgType);
    }
}
