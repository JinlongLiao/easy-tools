package com.poke.swagger4j.servlet.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * poke代理转发层
 *
 * @author liaojinlong
 * @since 2021/1/3 16:30
 */
public class BokeMiddleFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(BokeMiddleFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        request.setCharacterEncoding("utf-8");
        // response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        Pack result = forward(servletRequest);
        String msg;
        int status = 200;
        if ((result == null)) {
            msg = "{\"success\":true}";
        } else {
            status = result.status;
            msg = result.message;
        }
        servletResponse.setStatus(status);
        servletResponse.getWriter().write(msg);
    }

    /**
     * 转发请求
     *
     * @param request
     * @return /
     */
    private Pack forward(HttpServletRequest request) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        int key = Integer.parseInt(request.getParameter("key"));
        HttpGet httpGet = new HttpGet(getUrl(key, request));

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            StatusLine status = response.getStatusLine();
            log.info("响应状态为:" + status);
            String msg = "";
            int statusCode = status.getStatusCode();
            if (responseEntity != null) {
                log.debug("响应内容长度为:" + responseEntity.getContentLength());
                msg = EntityUtils.toString(responseEntity);
                if (statusCode == 200) {
                    msg = parseResponse(key, msg);
                }

                log.debug("响应内容为:" + msg);
            }
            return new Pack(statusCode, msg);
        } catch (ClientProtocolException e) {
            log.error(e.getLocalizedMessage(), e);
        } catch (ParseException e) {
            log.error(e.getLocalizedMessage(), e);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }

        return null;
    }

    private String parseResponse(int key, String msg) {
        return msg;
    }

    private String getUrl(int key, HttpServletRequest request) {
        return "http://127.0.0.1:8080/demo/login?password=12345";
    }

    @Override
    public void destroy() {

    }

    class Pack {
        private int status;
        private String message;

        public Pack(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
