package org.gnuhpc.interview.lang.java8.stream;

import lombok.Data;

/**
 * Created by gnuhpc on 2017/1/6.
 */
@Data
public class SparkJob {
    private String jobName;
    private int jobId;

    public SparkJob(int jobId, String jobName) {
        this.jobName = jobName;
        this.jobId = jobId;
    }

    public SparkJob() {
    }

    public boolean isOdd() {
        if ((jobId / 10) % 2 == 0) {
            return true;
        }
        return false;
    }
}
