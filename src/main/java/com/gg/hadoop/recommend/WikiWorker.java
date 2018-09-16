package com.gg.hadoop.recommend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.log4j.PropertyConfigurator;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.VectorWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author guowei
 * @date 2018/9/16
 */
public class WikiWorker {
    private static final Logger log = LoggerFactory.getLogger(WikiWorker.class);

    public static void main(String[] args) throws IOException {
        //增加log4j的日志
        PropertyConfigurator.configure("src/main/resources/config/log4j.properties");

        //job1计算出用户向量
        Configuration job1Conf = new Configuration();
        Job job1 = new Job(job1Conf, "job1");
        job1.setJarByClass(WikiWorker.class);
        job1.setMapperClass(WikipediaToItemPrefsMapper.class);
        job1.setReducerClass(WikipediaToUserVectorReducer.class);
        job1.setMapOutputKeyClass(VarLongWritable.class);
        job1.setMapOutputValueClass(VarLongWritable.class);
        //将job1输出的文件格式设置为SequenceFileOutputFormat
        job1.setOutputFormatClass(SequenceFileOutputFormat.class);
        job1.setOutputKeyClass(VarLongWritable.class);
        job1.setOutputValueClass(VectorWritable.class);
        FileInputFormat.addInputPath(job1, new Path("src/main/resources/input/recommend/wiki"));
        FileOutputFormat.setOutputPath(job1, new Path("src/main/resources/output/recommend/wiki/job1"));

        //job2计算出共现矩阵
        Configuration job2Conf = new Configuration();
        Job job2 = new Job(job2Conf, "job2");
        job2.setJarByClass(WikiWorker.class);
        job2.setMapperClass(UserVectorToCooccurrenceMapper.class);
        job2.setReducerClass(UserVectorToCooccurrenceReducer.class);
        job2.setMapOutputKeyClass(IntWritable.class);
        job2.setMapOutputValueClass(IntWritable.class);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(VectorWritable.class);
        //将job2输入的文件格式设置为SequenceFileInputFormat
        job2.setInputFormatClass(SequenceFileInputFormat.class);
        FileInputFormat.addInputPath(job2, new Path("src/main/resources/output/recommend/wiki/job1/part-r-00000"));
        FileOutputFormat.setOutputPath(job2, new Path("src/main/resources/output/recommend/wiki/job2"));

        //启动job
        ControlledJob ctrlJob1 = new ControlledJob(job1.getConfiguration());
        ctrlJob1.setJob(job1);
        ControlledJob ctrlJob2 = new ControlledJob(job2.getConfiguration());
        ctrlJob2.setJob(job2);
        ctrlJob2.addDependingJob(ctrlJob1);
        JobControl jc = new JobControl("wiki");
        jc.addJob(ctrlJob1);
        jc.addJob(ctrlJob2);
        new Thread(jc).start();
        while (true) {
            if (jc.allFinished()) {
                log.info("成功job列表======" + jc.getSuccessfulJobList().toString());
                jc.stop();
                System.exit(0);
            }
            if (jc.getFailedJobList().size() > 0) {
                log.info("失败job列表======" + jc.getFailedJobList());
                jc.stop();
                System.exit(1);
            }
        }
    }
}
