package com.muatik;

import com.muatik.entity.Stat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by muatik on 9/12/17.
 */
@RunWith(SpringRunner.class)
public class StatTest {

    @Test
    public void testStatConstructor() {
        double amount = 123456.789;
        long timestamp = 1505236870;
        Stat stat = new Stat(amount, timestamp);
        Assert.assertEquals(amount, stat.getAmount(), 0);
        Assert.assertEquals(timestamp, stat.getTimestamp());
    }

    @Test
    public void testStatToString() {
        double amount = 123456.789;
        long timestamp = 1505236870;
        Stat stat = new Stat(amount, timestamp);
        Assert.assertEquals(
                "Stat{amount=123456.789, timestamp=1505236870}",
                stat.toString());
    }


    @Test
    public void testStatComparision() {

        double amount = 123456.789;
        long timestamp = 1505236870;
        Stat stat1 = new Stat(amount, timestamp);
        Stat stat2 = new Stat(amount, timestamp - 1);
        Stat stat3 = new Stat(amount, timestamp + 1);

        Assert.assertTrue(stat1.compareTo(stat1) == 0);
        Assert.assertTrue(stat1.compareTo(stat2) == 1);
        Assert.assertTrue(stat1.compareTo(stat3) == -1);
    }
}
