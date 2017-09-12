package com.muatik.entity;

/**
 * Created by muatik on 9/12/17.
 */
public class Stat implements Comparable<Stat> {
    private double amount;
    private long timestamp;

    public Stat() {}

    public Stat(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public int compareTo(Stat o) {
        if (o.getTimestamp() == getTimestamp())
            return 0;
        else if(o.getTimestamp() > getTimestamp())
            return -1;
        else
            return 1;
    }
}
