package com.muatik;

import com.muatik.entity.Stat;
import com.muatik.service.StatsService;
import com.muatik.service.StatsServiceBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by muatik on 9/12/17.
 */
@RunWith(SpringRunner.class)
public class StatsServiceTest {

    @Test
    public void testGetSummary() throws StatsServiceBean.StatAlreadyExpired {
        StatsService statsService = new StatsServiceBean(60);

        Assert.assertEquals(statsService.getSummary().getCount(), 0);
        Assert.assertEquals(statsService.getSummary().getAvg(), 0, 0);

        double amount = 123456.789;
        long timestamp = System.currentTimeMillis() / 1000;
        Stat stat = new Stat(amount, timestamp);

        statsService.add(stat);
        statsService.add(stat);

        Assert.assertEquals(statsService.getSummary().getCount(), 2);
        Assert.assertEquals(statsService.getSummary().getSum(), amount * 2, 0);
        Assert.assertEquals(statsService.getSummary().getAvg(), amount, 0);
    }

    @Test
    public void testAddRejection() throws StatsServiceBean.StatAlreadyExpired {
        long rejectOlderThan = 60;
        StatsService statsService = new StatsServiceBean(rejectOlderThan);

        double amount = 123456.789;
        long timestamp = System.currentTimeMillis() / 1000;

        Stat stat = new Stat(amount, timestamp);
        statsService.add(stat);

        Stat alreadyExpiredStat = new Stat(amount, timestamp - 61);
        try {
            statsService.add(alreadyExpiredStat);
            fail("stat which is already expired was not rejected by the service");
        } catch (StatsServiceBean.StatAlreadyExpired e) {}
    }

    @Test
    public void testClearingExpiredStats() throws StatsServiceBean.StatAlreadyExpired {
        long rejectOlderThan = 60;
        StatsService statsService = new StatsServiceBean(rejectOlderThan);

        double amount = 123456.789;
        long timestamp = System.currentTimeMillis() / 1000;
        for (int i = 0; i < 10; i++) {
            statsService.add(new Stat(amount, timestamp - 50 - i));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        statsService.clear();
        assertEquals(statsService.getSummary().getCount(), 6);
    }

}
