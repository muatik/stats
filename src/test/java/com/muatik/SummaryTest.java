package com.muatik;

import com.muatik.entity.Summary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by muatik on 9/12/17.
 */
@RunWith(SpringRunner.class)
public class SummaryTest {

    @Test
    public void testSummaryConstructor() {
        long count = 20;
        double sum = 1002;
        double avg = 50.1;

        Summary stat = new Summary(count, sum, avg, 50.1, 50.1);
        Assert.assertEquals(avg, stat.getAvg(), 0);
        Assert.assertEquals(count, stat.getCount(), 0);
        Assert.assertEquals(sum, stat.getSum(), 0);
    }

    @Test
    public void testSummaryToString() {
        long count = 20;
        double sum = 1002;
        double avg = 50.1;

        Summary stat = new Summary(count, sum, avg, 50.1, 50.1);
        Assert.assertEquals(
                "Summary{count=20, sum=1002.0, avg=50.1, min=50.1, max=50.1}",
                stat.toString());
    }


}
