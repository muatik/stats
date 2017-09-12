package com.muatik.entity;

/**
 * Created by muatik on 9/12/17.
 */
public class Summary {
    private long count = 0;
    private Double sum = 0.0;
    private Double avg = 0.0;
    private Double min;
    private Double max;

    public Summary(long count, double sum, double avg, double min, double max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public Summary(long count, double sum, double avg) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
    }

    public long getCount() {
        return count;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "count=" + count +
                ", sum=" + sum +
                ", avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
