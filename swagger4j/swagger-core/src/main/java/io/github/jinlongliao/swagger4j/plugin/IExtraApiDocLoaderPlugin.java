package io.github.jinlongliao.swagger4j.plugin;

import io.github.jinlongliao.swagger4j.APIDoc;

import java.util.Properties;

/**
 * 额外插件
 *
 * @author liaojinlong
 * @since 2021/1/3 00:27
 */
public interface IExtraApiDocLoaderPlugin {
    /**
     * 额外插件，用于自定义
     *
     * @param apiDoc
     * @param properties
     * @return /
     */
    APIDoc extraPlugin(APIDoc apiDoc, Properties properties);
}
