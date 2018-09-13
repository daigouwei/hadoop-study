package com.gg.hadoop.recommend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.math.VarLongWritable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author guowei
 * @date 2018/9/13
 */
public class WikipediaToItemPrefsMapper extends Mapper<LongWritable, Text, VarLongWritable, VarLongWritable> {
    private static final Pattern NUMBERS = Pattern.compile("(\\d+)");

    /**
     * 输入为(Long,String)对，Long型是文件中的位置，String型是文本行。例如239 / 98955: 590 22 9059
     * 输出为(Long,Long)用户ID对应的物品ID。例如98955 / 590
     *
     * @param key
     * @param value
     * @param context
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        Matcher m = NUMBERS.matcher(line);
        m.find();
        VarLongWritable userID = new VarLongWritable(Long.parseLong(m.group()));
        VarLongWritable itemID = new VarLongWritable();
        while (m.find()) {
            itemID.set(Long.parseLong(m.group()));
            context.write(userID, itemID);
        }
    }
}
