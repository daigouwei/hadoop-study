package com.gg.hadoop.recommend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author guowei
 * @date 2018/9/16
 */
public class UserVectorToCooccurrenceMapper extends Mapper<VarLongWritable, VectorWritable, IntWritable, IntWritable> {
    /**
     * 输入为用户ID和该用户的偏好向量。例如98955 / [590:1.0, 22:1.0, 9059:1.0]
     * 输出为物品ID对。例如590/22
     *
     * @param userID
     * @param userVector
     * @param context
     */
    @Override
    public void map(VarLongWritable userID, VectorWritable userVector, Context context)
            throws IOException, InterruptedException {
        Iterator<Vector.Element> it = userVector.get().nonZeroes().iterator();
        while (it.hasNext()) {
            int index1 = it.next().index();
            Iterator<Vector.Element> it2 = userVector.get().nonZeroes().iterator();
            while (it2.hasNext()) {
                int index2 = it2.next().index();
                context.write(new IntWritable(index1), new IntWritable(index2));
            }
        }
    }
}
