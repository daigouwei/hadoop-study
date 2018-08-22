package com.gg.hadoop.log.parser;

import com.gg.hadoop.log.entity.LogSplitBean;
import org.apache.hadoop.io.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guowei on 2018/8/22.
 */
public class LogParser {
    public static LogSplitBean parseLog(String data) {
        Matcher matcher = Pattern.compile(
                "(.*?)\\s(.*?)\\s(.*?)\\s\\[(.*?)\\]\\s\"(.*?)\"\\s([0-9]{3})\\s([0-9]+?)\\s\"(.*?)\"\\s\"(.*?)\"\\s\"(.*?)\"\\s\"pid=(.*?)\\sse=(.*?)\\scpid=(.*?)\\sajax=(.*?)\\sup=(.*?)\\sab=(.*?)\"\\s\"head_pre1=(.*?)\"\\s\"(.*?)\"")
                .matcher(data);
        while (matcher.find()) {
            String ip = matcher.group(1);
            String unknow1 = matcher.group(2);
            String unknow2 = matcher.group(3);
            String time = matcher.group(4);
            String agent = matcher.group(5);
            String responseStatusCode = matcher.group(6);
            String byteNum = matcher.group(7);
            String ref = matcher.group(8);
            String browserInfo = matcher.group(9);
            String unknow3 = matcher.group(10);
            String pid = matcher.group(11);
            String se = matcher.group(12);
            String cpid = matcher.group(13);
            String ajax = matcher.group(14);
            String up = matcher.group(15);
            String ab = matcher.group(16);
            String headPre1 = matcher.group(17);
            String urlPrefix = matcher.group(18);
            //暂时返回必要的属性，可选属性可以后续添加
            return new LogSplitBean.Builder(ip, time, agent, responseStatusCode, ref, pid, cpid).build();
        }
        return null;
    }
}
