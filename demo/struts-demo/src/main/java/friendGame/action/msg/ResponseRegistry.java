package friendGame.action.msg;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import friendGame.action.annotation.RequestMsgType;


public class ResponseRegistry {
	private Map<Integer, Class<?>> responseInfos = new HashMap<Integer, Class<?>>();
	private static transient Logger logger = LoggerFactory.getLogger(MsgRegistry.class);
	
	/**
	 * 初始化消息体定义
	 */
	private ResponseRegistry() {
		logger.info("[cjddz msg registry init....]");
		String path = ResponseRegistry.class.getResource("").getPath();
		path = path + "response/";
		File requestMsgFiles = new File(path);
		String[] allMsgInfos = requestMsgFiles.list();
		for (String msgInfo : allMsgInfos) {
			try {
				String msgPathInfo = path + msgInfo;
				msgPathInfo = msgPathInfo.substring(msgPathInfo.indexOf("classes") + 8, msgPathInfo.lastIndexOf("."));
				msgPathInfo = msgPathInfo.replace("/", ".");
				Class<?> msgClazz = Class.forName(msgPathInfo);
				RequestMsgType requestMsgType = msgClazz.getAnnotation(RequestMsgType.class);				
				responseInfos.put(requestMsgType.msgType(), msgClazz);
			} catch (ClassNotFoundException e) {
				logger.error("[cjddz msg registry class not found..]", e);
			}
		}	
	}
	
	private static final ResponseRegistry registry = new ResponseRegistry();	
	public static ResponseRegistry getRegistry() {
		return registry;
	}
	
	/**
	 * 获取返回值消息类�?
	 * @param msgType
	 * @return
	 */
	public Class<?> getMsgClass(int msgType) {
		return responseInfos.get(msgType);
	}
}
