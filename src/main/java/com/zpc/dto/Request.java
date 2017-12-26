package com.zpc.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 周鹏程 on 2017/4/27.
 * request类
 */
public class Request implements Serializable{
    private String url;

    private Map<String , String> headers = new HashMap<String, String>();

    private Map<String , String> parameters = new HashMap<String, String>();

    private Map<String , String> extra = new HashMap<String, String>();

    private String method;
    // 是否为extra去重
    private boolean isExtraDuplicateRemoval;

    private boolean isMobile;

    public void addHeader(String key , String value) {
        this.headers.put(key , value);
    }

    public Request(String url, Map<String, String> headers, Map<String, String> parameters, Map<String, String> extra, String method, boolean isExtraDuplicateRemoval) {
        this.url = url;
        this.headers = headers;
        this.parameters = parameters;
        this.extra = extra;
        this.method = method;
        this.isExtraDuplicateRemoval = isExtraDuplicateRemoval;
    }

    public Request(String url, Map<String, String> parameters, String method) {
        this.url = url;
        this.parameters = parameters;
        this.method = method;
    }

    public Request(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public Request() {
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", headers=" + headers +
                ", parameters=" + parameters +
                ", extra=" + extra +
                ", method='" + method + '\'' +
                ", isExtraDuplicateRemoval=" + isExtraDuplicateRemoval +
                '}';
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public boolean isExtraDuplicateRemoval() {
        return isExtraDuplicateRemoval;
    }

    public void setExtraDuplicateRemoval(boolean extraDuplicateRemoval) {
        isExtraDuplicateRemoval = extraDuplicateRemoval;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
