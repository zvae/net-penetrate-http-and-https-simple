package com.kele.penetrate.utils.http;

import com.kele.penetrate.enumeration.RequestType;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 解析http&https请求
 */
@SuppressWarnings("unused")
@Slf4j
public class AnalysisRequest {
    //<editor-fold desc="获取请求方法类型">
    public RequestType getRequestType(FullHttpRequest fullHttpRequest) {
        return RequestType.getRequestTypeByCodeStr(fullHttpRequest.method().name().trim());
    }
    //</editor-fold>

    //<editor-fold desc="获取请求url携带的参数">
    public Map<String, Object> getParamsFromChannel(FullHttpRequest fullHttpRequest) {
        Map<String, Object> params = new HashMap<>();
        QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());
        Map<String, List<String>> paramList = decoder.parameters();
        for (Map.Entry<String, List<String>> entry : paramList.entrySet()) {
            params.put(entry.getKey(), entry.getValue().get(0));
        }
        return params;
    }
    //</editor-fold>

    //<editor-fold desc="获取请求头信息">
    public Map<String, String> getRequestHeaders(FullHttpRequest fullHttpRequest) {
        Map<String, String> headers = new HashMap<>();
        Iterator<Map.Entry<String, String>> entryIterator = fullHttpRequest.headers().iteratorAsString();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> next = entryIterator.next();
            headers.put(next.getKey(), next.getValue());
        }
        return headers;
    }
    //</editor-fold>

    //<editor-fold desc="获取Host">
    public String getHost(FullHttpRequest fullHttpRequest) {
        try {
            return fullHttpRequest.headers().get("Host");
        } catch (Exception ex) {
            log.error("没有获取到Host");
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="获取请求路径">
    public String getRequestUrl(FullHttpRequest fullHttpRequest) {
        return new StringBuilder(fullHttpRequest.uri()).toString();
    }
    //</editor-fold>
}
