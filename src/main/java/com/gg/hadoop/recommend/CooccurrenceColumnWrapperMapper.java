package com.gg.hadoop.recommend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.cf.taste.hadoop.item.VectorOrPrefWritable;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;

/**
 * 封装共现关系列
 *
 * @author guowei
 * @date 2018/9/16
 */
public class CooccurrenceColumnWrapperMapper
        extends Mapper<IntWritable, VectorWritable, IntWritable, VectorOrPrefWritable> {
    /**
     * 输入为共现矩阵列。例如590 / [22:3.0, 95:1.0, ..., 9059:1.0, ...]
     * 输出为VectorOrPrefWritable形式的共现矩阵
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void map(IntWritable key, VectorWritable value, Context context) throws IOException, InterruptedException {
        context.write(key, new VectorOrPrefWritable(value.get()));
    }
}
