package com.framework.common.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] rawData;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            if(request.getMethod().equalsIgnoreCase("post")
                    && (request.getContentType().startsWith("application/json") || request.getContentType().startsWith("multipart/form-data"))) {
                InputStream is = request.getInputStream();
                this.rawData = replaceXSS(IOUtils.toByteArray(is));
            }
        } catch (IOException e) {
            log.error("XSS IOException", e);
        }
    }

    private byte[] replaceXSS(byte[] data) {
        String strData = new String(data);
        strData = strData.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\\(", "&#40;")
                .replaceAll("\\)", "&#41;");

        return strData.getBytes();
    }

    private String replaceXSS(String value) {
        if(value != null) {
            value = value.replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\\(", "&#40;")
                    .replaceAll("\\)", "&#41;");
        }
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if(this.rawData == null) {
            return super.getInputStream();
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public String getQueryString() {
        return replaceXSS(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return replaceXSS(super.getParameter(name));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> params = super.getParameterMap();
        if(params != null) {
            params.forEach((key, value) -> {
                for(int i=0; i<value.length; i++) {
                    value[i] = replaceXSS(value[i]);
                }
            });
        }
        return params;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] params = super.getParameterValues(name);
        if(params != null) {
            for(int i=0; i<params.length; i++) {
                params[i] = replaceXSS(params[i]);
            }
        }
        return params;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), "UTF-8"));
    }
}
