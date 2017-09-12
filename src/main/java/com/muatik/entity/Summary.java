package com.muatik.entity;

/**
 * Created by muatik on 9/12/17.
 */
public class Summary {
    private long count = 0;
    private double sum = 0;
    private double avg = 0;

    public Summary(long count, double sum, double avg) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "count=" + count +
                ", sum=" + sum +
                ", avg=" + avg +
                '}';
    }
}
