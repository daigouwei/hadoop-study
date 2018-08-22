package com.gg.hadoop.log;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

/**
 * Created by guowei on 2018/8/22.
 */
public class LogWorker {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //增加log4j的日志
        PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
        Configuration conf = new Configuration();
        Job job = new Job(conf, "word count");
        job.setJarByClass(LogWorker.class);
        job.setMapperClass(LogMapper.class);
        //        job.setCombinerClass(LogReduce.class);
        job.setReducerClass(LogReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ArrayWritable.class);
        FileInputFormat.addInputPath(job, new Path("src/main/resources/input/log"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/output/log"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
