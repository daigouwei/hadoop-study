package com.gg.hadoop.recommend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.cf.taste.hadoop.item.VectorOrPrefWritable;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;
import java.util.Iterator;

/**
 * 分割用户向量
 *
 * @author guowei
 * @date 2018/9/16
 */
public class UserVectorSplitterMapper
        extends Mapper<VarLongWritable, VectorWritable, IntWritable, VectorOrPrefWritable> {
    /**
     * 输入为用户向量，以用户ID为key，对应vector形式的偏好值。例如98955 / [590:1.0, 22:1.0, 9050:1.0]
     * 输出为物品ID作为key，对应用户ID加偏好值。例如590 / [98955:1.0]
     *
     * @param key
     * @param value
     * @param context
     */
    @Override
    public void map(VarLongWritable key, VectorWritable value, Context context)
            throws IOException, InterruptedException {
        long userID = key.get();
        Iterator<Vector.Element> it = value.get().nonZeroes().iterator();
        while (it.hasNext()) {
            Vector.Element e = it.next();
            int itemIndex = e.index();
            float preferenceValue = (float) e.get();
            context.write(new IntWritable(itemIndex), new VectorOrPrefWritable(userID, preferenceValue));
        }
    }
}
