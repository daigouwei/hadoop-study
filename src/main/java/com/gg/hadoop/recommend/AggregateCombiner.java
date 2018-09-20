package com.gg.hadoop.recommend;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;

/**
 * 实现部分乘积的combiner，也就是在实际写HDFS之后的producer之前先在内存中合并能合并的
 *
 * @author guowei
 * @date 2018/9/20
 */
public class AggregateCombiner extends Reducer<VarLongWritable, VectorWritable, VarLongWritable, VectorWritable> {
    @Override
    public void reduce(VarLongWritable key, Iterable<VectorWritable> values, Context context)
            throws IOException, InterruptedException {
        Vector partial = null;
        for (VectorWritable vectorWritable : values) {
            partial = null == partial ? vectorWritable.get() : partial.plus(vectorWritable.get());
        }
        context.write(key, new VectorWritable(partial));
    }
}
