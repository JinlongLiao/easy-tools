package io.github.jinlonghliao.commons.servlet.session.datasource.redis.encode;

import com.alibaba.fastjson.JSON;

/**
 * @author liaojinlong
 * @since 2020/10/13 00:11
 */

public interface Serialize {
    /**
     * 序列化
     *
     * @param data
     * @return 结果
     */
    String toSerialize(Object data);

    /**
     * 反序列化
     *
     * @param data
     * @return 结果
     */
    <T> T fromSerialize(String data, Class<T> t);

    class Default implements Serialize {

        @Override
        public String toSerialize(Object data) {
            if (data instanceof String) {
                return (String) data;
            }
            return JSON.toJSONString(data);
        }

        @Override
        public <T> T fromSerialize(String data, Class<T> t) {
            if (t.getName().equals(String.class.getName())) {
                return (T) data;
            }
            return JSON.parseObject(data, t);
        }
    }


}