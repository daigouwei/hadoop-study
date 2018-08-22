package com.gg.hadoop.log.entity;

/**
 * Created by guowei on 2018/8/22.
 */
public class LogSplitBean {
    private String ip;
    private String unknow1;
    private String unknow2;
    private String time;
    private String agent;
    private String responseStatusCode;
    private String byteNum;
    private String ref;
    private String browserInfo;
    private String unknow3;
    private String pid;
    private String se;
    private String cpid;
    private String ajax;
    private String up;
    private String ab;
    private String uptime;
    private String reqtime;
    private String headPre1;
    private String urlPrefix;

    public static class Builder {
        //必要属性
        private String ip;
        private String time;
        private String agent;
        private String responseStatusCode;
        private String ref;
        private String pid;
        private String cpid;
        //可选属性
        private String unknow1;
        private String unknow2;
        private String byteNum;
        private String browserInfo;
        private String unknow3;
        private String se;
        private String ajax;
        private String up;
        private String ab;
        private String uptime;
        private String reqtime;
        private String headPre1;
        private String urlPrefix;

        public Builder(String ip, String time, String agent, String responseStatusCode, String ref, String pid,
                String cpid) {
            this.ip = ip;
            this.time = time;
            this.agent = agent;
            this.responseStatusCode = responseStatusCode;
            this.ref = ref;
            this.pid = pid;
            this.cpid = cpid;
        }

        public Builder unknow1(String val) {
            this.unknow1 = val;
            return this;
        }

        public Builder unknow2(String val) {
            this.unknow2 = val;
            return this;
        }

        public Builder byteNum(String val) {
            this.byteNum = val;
            return this;
        }

        public Builder browserInfo(String val) {
            this.browserInfo = val;
            return this;
        }

        public Builder unknow3(String val) {
            this.unknow3 = val;
            return this;
        }

        public Builder se(String val) {
            this.se = val;
            return this;
        }

        public Builder ajax(String val) {
            this.ajax = val;
            return this;
        }

        public Builder up(String val) {
            this.up = val;
            return this;
        }

        public Builder ab(String val) {
            this.ab = val;
            return this;
        }

        public Builder uptime(String val) {
            this.uptime = val;
            return this;
        }

        public Builder reqtime(String val) {
            this.reqtime = val;
            return this;
        }

        public Builder headPre1(String val) {
            this.headPre1 = val;
            return this;
        }

        public Builder urlPrefix(String val) {
            this.urlPrefix = val;
            return this;
        }

        public LogSplitBean build() {
            return new LogSplitBean(this);
        }
    }

    public LogSplitBean() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUnknow1() {
        return unknow1;
    }

    public void setUnknow1(String unknow1) {
        this.unknow1 = unknow1;
    }

    public String getUnknow2() {
        return unknow2;
    }

    public void setUnknow2(String unknow2) {
        this.unknow2 = unknow2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(String responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getByteNum() {
        return byteNum;
    }

    public void setByteNum(String byteNum) {
        this.byteNum = byteNum;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }

    public String getUnknow3() {
        return unknow3;
    }

    public void setUnknow3(String unknow3) {
        this.unknow3 = unknow3;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public String getAjax() {
        return ajax;
    }

    public void setAjax(String ajax) {
        this.ajax = ajax;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getReqtime() {
        return reqtime;
    }

    public void setReqtime(String reqtime) {
        this.reqtime = reqtime;
    }

    public String getHeadPre1() {
        return headPre1;
    }

    public void setHeadPre1(String headPre1) {
        this.headPre1 = headPre1;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    private LogSplitBean(Builder builder) {
        this.ip = builder.ip;
        this.time = builder.time;
        this.agent = builder.agent;
        this.responseStatusCode = builder.responseStatusCode;
        this.ref = builder.ref;
        this.pid = builder.pid;
        this.cpid = builder.cpid;
        this.unknow1 = builder.unknow1;
        this.unknow2 = builder.unknow2;
        this.byteNum = builder.byteNum;
        this.browserInfo = builder.browserInfo;
        this.unknow3 = builder.unknow3;
        this.se = builder.se;
        this.ajax = builder.ajax;
        this.up = builder.up;
        this.ab = builder.ab;
        this.uptime = builder.uptime;
        this.reqtime = builder.reqtime;
        this.headPre1 = builder.headPre1;
        this.urlPrefix = builder.urlPrefix;
    }

    @Override
    public String toString() {
        return "LogSplitBean{" +
                "ip='" + ip + '\'' +
                ", unknow1='" + unknow1 + '\'' +
                ", unknow2='" + unknow2 + '\'' +
                ", time='" + time + '\'' +
                ", agent='" + agent + '\'' +
                ", responseStatusCode='" + responseStatusCode + '\'' +
                ", byteNum='" + byteNum + '\'' +
                ", ref='" + ref + '\'' +
                ", browserInfo='" + browserInfo + '\'' +
                ", unknow3='" + unknow3 + '\'' +
                ", pid='" + pid + '\'' +
                ", se='" + se + '\'' +
                ", cpid='" + cpid + '\'' +
                ", ajax='" + ajax + '\'' +
                ", up='" + up + '\'' +
                ", ab='" + ab + '\'' +
                ", uptime='" + uptime + '\'' +
                ", reqtime='" + reqtime + '\'' +
                ", headPre1='" + headPre1 + '\'' +
                ", urlPrefix='" + urlPrefix + '\'' +
                '}';
    }
}
