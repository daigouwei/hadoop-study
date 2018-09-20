package com.gg.hadoop.recommend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.cf.taste.hadoop.item.VectorAndPrefsWritable;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;
import java.util.List;

/**
 * @author guowei
 * @date 2018/9/16
 */
public class PartialMultiplyMapper
        extends Mapper<IntWritable, VectorAndPrefsWritable, VarLongWritable, VectorWritable> {
    /**
     * 输入：key为物品ID，对应的是VectorAndPrefsWritable里面封装好了某物品对应的共现矩阵列和全部用户ID及对应的偏好
     * 输出：key为用户ID，得到针对某物品的共现矩阵乘物品偏好的局部推荐向量
     *
     * @param key
     * @param vectorAndPrefsWritable
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void map(IntWritable key, VectorAndPrefsWritable vectorAndPrefsWritable, Context context)
            throws IOException, InterruptedException {
        Vector cooccurrenceColumn = vectorAndPrefsWritable.getVector();
        List<Long> userIDs = vectorAndPrefsWritable.getUserIDs();
        List<Float> prefValues = vectorAndPrefsWritable.getValues();
        for (int i = 0; i < userIDs.size(); i++) {
            long userID = userIDs.get(i);
            float prefValue = prefValues.get(i);
            Vector partialProduct = cooccurrenceColumn.times(prefValue);
            context.write(new VarLongWritable(userID), new VectorWritable(partialProduct));
        }
    }
}
