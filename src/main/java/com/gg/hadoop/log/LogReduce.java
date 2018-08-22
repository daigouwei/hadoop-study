package com.gg.hadoop.log;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guowei on 2018/8/22.
 */
public class LogReduce extends Reducer<Text, Text, Text, ArrayWritable> {
    private static final Logger logger = LoggerFactory.getLogger(LogMapper.class);

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> valueList = new ArrayList<String>();
        for (Text value : values) {
            valueList.add(value.toString());
        }
        context.write(key, new ArrayWritable((String[]) valueList.toArray()));
    }
}
