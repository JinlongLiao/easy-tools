package com.poke.swagger4j.plugin;

import com.alibaba.fastjson.JSONObject;
import friendGame.action.annotation.RequestMsgType;
import friendGame.action.logic.ILogic;
import friendGame.action.logic.LogicRegistry;
import friendGame.action.msg.IRequest;
import friendGame.action.msg.MsgRegistry;
import io.github.jinlongliao.swagger4j.APIDoc;
import io.github.jinlongliao.swagger4j.Operation;
import io.github.jinlongliao.swagger4j.plugin.IExtraApiDocLoaderPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 波克Swagger4j扩展
 *
 * @author liaojinlong
 * @since 2021/1/3 11:24
 */

public class PokeExtraApiDocLoaderPlugin implements IExtraApiDocLoaderPlugin {
    private static final Logger log = LoggerFactory.getLogger(PokeExtraApiDocLoaderPlugin.class);

    @Override
    public APIDoc extraPlugin(APIDoc apiDoc, Properties properties) {
        String apiUrl = properties.getProperty("apiUrl");
        final Map<String, Map<String, Operation>> paths = apiDoc.getPaths();
        Map<Integer, Class<ILogic>> logics = parseLogic();
        Map<Integer, Class<IRequest>> requests = parseRequest();
        buildExtraApiDoc(paths, logics, requests, apiUrl);

        return apiDoc;
    }

    private void buildExtraApiDoc(Map<String, Map<String, Operation>> paths,
                                  Map<Integer, Class<ILogic>> logics,
                                  Map<Integer, Class<IRequest>> requests, String apiUrl) {
        for (Map.Entry<Integer, Class<ILogic>> entry : logics.entrySet()) {
            final Integer key = entry.getKey();
            if (requests.containsKey(key)) {
                final HashMap<String, Operation> operationHashMap = new HashMap<>(2);
                paths.put(apiUrl + "?key=" + key, operationHashMap);
                Operation operation = getOperation(key, requests.get(key));
                operationHashMap.put("post", operation);
            }
        }
    }

    private Operation getOperation(Integer key, Class<IRequest> iRequestClass) {
        Operation operation = new Operation();
        final String operationId = "0x" + Integer.toHexString(key) + "-" + iRequestClass.getName();
        operation.setOperationId(operationId);
        operation.setSummary(operationId);
        List<Map<String, Object>> parameters = getParameters(key, iRequestClass);
        operation.setParameters(parameters);
        operation.setTags(Arrays.asList("哈灵麻将"));
        operation.setConsumes(Arrays.asList("application/json; charset=utf-8"));
        operation.setProduces(Arrays.asList("application/json; charset=utf-8"));
        return operation;
    }

    private List<Map<String, Object>> getParameters(Integer key, Class<IRequest> iRequestClass) {
        List<Map<String, Object>> parameters = new ArrayList<>(16);
        final Field[] parentFields = IRequest.class.getDeclaredFields();
        final Field[] fields = iRequestClass.getDeclaredFields();

        for (Field field : parentFields) {
            buildParameter(key, parameters, field, false);
        }
        for (Field field : fields) {
            buildParameter(key, parameters, field, true);
        }
        return parameters;
    }

    private void buildParameter(Integer key, List<Map<String, Object>> parameters, Field field, boolean required) {
        final int modifiers = field.getModifiers();
        if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)) {
            return;
        }
        if (field.getName().equalsIgnoreCase("signature")) {
            return;
        }
        Map<String, Object> param = new HashMap<>(8);
        if (field.getName().equalsIgnoreCase("msgType")) {
            param.put("value", key);
        }
        parameters.add(param);
        param.put("in", "formData");
        param.put("required", required);
        param.put("name", field.getName());
        param.put("description", field.getName());

        Class<?> fieldType = field.getType();
        if (fieldType == Integer.TYPE) {
            param.put("format", "int32");
            param.put("type", "integer");
        } else if (fieldType == String.class) {
            param.put("format", "string");
            param.put("type", "string");
        } else if (fieldType == Float.TYPE) {
            param.put("format", "float");
            param.put("type", "number");
        } else if (fieldType == Double.TYPE) {
            param.put("format", "double");
            param.put("type", "number");
        } else if (fieldType == Long.TYPE) {
            param.put("format", "int64");
            param.put("type", "integer");
        } else if (fieldType == Short.TYPE) {
            param.put("format", "int32");
            param.put("type", "integer");
        } else if (fieldType == Boolean.TYPE) {
            param.put("format", null);
            param.put("type", "boolean");
        } else if (fieldType == Character.TYPE) {
            param.put("format", "string");
            param.put("type", "string");
        } else if (fieldType == Byte.TYPE) {
            param.put("format", "byte");
            param.put("type", "string");
        } else if (fieldType == Date.class) {
            param.put("format", "date-time");
            param.put("type", "string");
        }
    }

    private Map<Integer, Class<IRequest>> parseRequest() {
        String path = MsgRegistry.class.getResource("").getPath();
        path = path + "request/";
        File requestMsgFiles = new File(path);
        String[] allMsgInfoCache = requestMsgFiles.list();
        Map<Integer, Class<IRequest>> cache = new HashMap<>(allMsgInfoCache.length);

        for (String msgInfo : allMsgInfoCache) {
            try {
                String msgPathInfo = path + msgInfo;
                msgPathInfo = msgPathInfo.substring(msgPathInfo.indexOf("classes") + 8, msgPathInfo.lastIndexOf("."));
                msgPathInfo = msgPathInfo.replace("/", ".");
                Class<IRequest> msgClazz = (Class<IRequest>) Class.forName(msgPathInfo);
                RequestMsgType requestMsgType = msgClazz.getAnnotation(RequestMsgType.class);
                cache.put(requestMsgType.msgType(), msgClazz);
            } catch (ClassNotFoundException e) {
                log.error("[cjddz msg registry class not found..]", e);
            }
        }
        return cache;
    }

    private Map<Integer, Class<ILogic>> parseLogic() {
        String path = LogicRegistry.class.getResource("").getPath();
        path = path + "impl/";
        File requestMsgFiles = new File(path);
        String[] allMsgInfoCache = requestMsgFiles.list();
        Map<Integer, Class<ILogic>> cache = new HashMap<>(allMsgInfoCache.length);
        for (String msgInfo : allMsgInfoCache) {
            try {
                String msgPathInfo = path + msgInfo;
                msgPathInfo = msgPathInfo.substring(msgPathInfo.indexOf("classes") + 8, msgPathInfo.lastIndexOf("."));
                msgPathInfo = msgPathInfo.replace("/", ".");
                Class<ILogic> msgClazz = (Class<ILogic>) Class.forName(msgPathInfo);
                RequestMsgType requestMsgType = msgClazz.getAnnotation(RequestMsgType.class);
                cache.put(requestMsgType.msgType(), msgClazz);
            } catch (ClassNotFoundException e) {
                log.error("[cjddz logic registry class not found..]", e);
            }
        }
        return cache;
    }
}
