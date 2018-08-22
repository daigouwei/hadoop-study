package com.gg.hadoop.log;

import com.alibaba.fastjson.JSON;
import com.gg.hadoop.log.entity.LogSplitBean;
import com.gg.hadoop.log.parser.LogParser;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by guowei on 2018/8/22.
 */
public class LogMapper extends Mapper<Object, Text, Text, Text> {
    private static final Logger logger = LoggerFactory.getLogger(LogMapper.class);

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        LogSplitBean logSplitBean = LogParser.parseLog(value);
        if (null != logSplitBean) {
            logger.info("logSplitBean=null, value=" + value.toString() + "没能解析出来");
            return;
        }
        context.write(new Text(logSplitBean.getPid()), new Text(JSON.toJSONString(logSplitBean)));
    }
}
