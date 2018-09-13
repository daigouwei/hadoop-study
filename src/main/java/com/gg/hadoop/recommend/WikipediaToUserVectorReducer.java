package com.gg.hadoop.recommend;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;

/**
 *
 * @author guowei
 * @date 2018/9/13
 */
public class WikipediaToUserVectorReducer
        extends Reducer<VarLongWritable, VarLongWritable, VarLongWritable, VectorWritable> {
    /**
     * 输入为WikipediaToItemPrefsMapper的输出, (Long,Long)用户ID对应的物品ID。例如98955 / 590
     * 输出为用户ID和该用户的偏好向量。例如98955 / [590:1.0, 22:1.0, 9059:1.0]
     *
     * @param userID
     * @param itemPrefs
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void reduce(VarLongWritable userID, Iterable<VarLongWritable> itemPrefs, Context context)
            throws IOException, InterruptedException {
        Vector userVector = new RandomAccessSparseVector(Integer.MAX_VALUE, 100);
        itemPrefs.forEach((itemPref) -> {
            userVector.set((int) itemPref.get(), 1.0f);
        });
        context.write(userID, new VectorWritable(userVector));
    }
}
