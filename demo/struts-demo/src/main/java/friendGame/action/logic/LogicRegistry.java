package friendGame.action.logic;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import friendGame.action.annotation.RequestMsgType;
import friendGame.action.msg.MsgRegistry;

/**
 * @author liaojinlong
 * @since 2021/1/3 14:04
 */
public class LogicRegistry {
    private Map<Integer, Class<?>> logicInfos = new HashMap<Integer, Class<?>>();
    private static transient Logger logger = LoggerFactory.getLogger(MsgRegistry.class);

    /**
     * 初始化消息体定义
     */
    private LogicRegistry() {
        logger.info("[cjddz logic registry init....]");
        String path = LogicRegistry.class.getResource("").getPath();
        path = path + "impl/";
        File requestMsgFiles = new File(path);
        String[] allMsgInfos = requestMsgFiles.list();
        for (String msgInfo : allMsgInfos) {
            try {
                String msgPathInfo = path + msgInfo;
                msgPathInfo = msgPathInfo.substring(msgPathInfo.indexOf("classes") + 8, msgPathInfo.lastIndexOf("."));
                msgPathInfo = msgPathInfo.replace("/", ".");
                Class<?> msgClazz = Class.forName(msgPathInfo);
                RequestMsgType requestMsgType = msgClazz.getAnnotation(RequestMsgType.class);
                logicInfos.put(requestMsgType.msgType(), msgClazz);
            } catch (ClassNotFoundException e) {
                logger.error("[cjddz logic registry class not found..]", e);
            }
        }
    }

    private static final LogicRegistry registry = new LogicRegistry();

    public static LogicRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取消息类型
     *
     * @param msgType
     * @return
     */
    public Class<?> getLogicClass(int msgType) {
        return logicInfos.get(msgType);
    }
}
