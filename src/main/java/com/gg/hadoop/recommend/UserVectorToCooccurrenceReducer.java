package com.gg.hadoop.recommend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;

/**
 * @author guowei
 * @date 2018/9/16
 */
public class UserVectorToCooccurrenceReducer extends Reducer<IntWritable, IntWritable, IntWritable, VectorWritable> {
    /**
     * 输入为物品ID对。例如590/22
     * 输出为物品共现关系的行(列)。例如590 / [22:3.0, 95:1.0, ..., 9059:1.0, ...]
     *
     * @param itemIndex1
     * @param itemIndex2s
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void reduce(IntWritable itemIndex1, Iterable<IntWritable> itemIndex2s, Context context)
            throws IOException, InterruptedException {
        Vector cooccurrenceRow = new RandomAccessSparseVector(Integer.MAX_VALUE, 100);
        itemIndex2s.forEach((intWritable) -> {
            int itemIndex2 = intWritable.get();
            cooccurrenceRow.set(itemIndex2, cooccurrenceRow.get(itemIndex2) + 1.0);
        });
        context.write(itemIndex1, new VectorWritable(cooccurrenceRow));
    }
}
