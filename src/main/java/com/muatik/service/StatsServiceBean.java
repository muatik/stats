package com.muatik.service;

import com.muatik.entity.Stat;
import com.muatik.entity.Summary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by muatik on 9/12/17.
 */
@Service
public class StatsServiceBean implements StatsService{

    /**
     * PriorityQueue will keep stat entries ordered by timestamp.
     * To do so, Stat class will implement Comparable interface accordingly.
     * Head entry of this collection will always be the oldest element.
     */
    private final PriorityQueue<Stat> stats;

    /**
     * stores the up-to-date stat summary. As soon as stats collection updated,
     * summaries will be updated too.
     */
    private volatile Summary summary;

    /**
     * after how many seconds stats will be expired
     */
    private long expireMilis = 60;

    /**
     * used to update stats collection and summary in thread safe manner
     */
    private Lock lock = new ReentrantLock();

    public StatsServiceBean(@Value("${rejectOlderThan}") long rejectOlderThan) {
        expireMilis = rejectOlderThan;
        summary = new Summary(0, 0, 0);
        stats = new PriorityQueue<>();
    }

    @Override
    public void add(Stat stat) throws StatAlreadyExpired {
        if (isExpired(stat))
            throw new StatAlreadyExpired();

        lock.lock();
        try {
            stats.add(stat);
            updateSummary(stat, StatOperation.INSERT);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Summary getSummary() {
        return summary;
    }

    /**
     * updates summary at once. To update all the stat summary variables in
     * <b>atomic</b> manner, they are wrapped in a Summary instance.
     *
     * @param stat Stat instance
     * @param operation StatOperation value
     */
    private void updateSummary(Stat stat, StatOperation operation){
//        System.out.println(System.currentTimeMillis() + " thread: " + Thread.currentThread().getId() + " updating the summary: " + operation + ", " + stat);
        if (operation.equals(StatOperation.INSERT)) {
            summary = new Summary(
                    summary.getCount() + 1,
                    summary.getSum() + stat.getAmount(),
                    (summary.getSum() + stat.getAmount()) / (summary.getCount() + 1));
        } else if (operation.equals(StatOperation.REMOVE)) {
            summary = new Summary(
                    summary.getCount() - 1,
                    summary.getSum() - stat.getAmount(),
                    (summary.getSum() - stat.getAmount()) / (summary.getCount() -1));
        }
    }

    /**
     * removes expired stat entries from the stat collection
     */
    @Override
    public void clear() {
        // head will always be the oldest stat entry.
        Stat head = stats.peek();
        while (head != null && isExpired(head)) {
            lock.lock();
            // we acquire lock to update both collection and the summary in thread safe way
            // so only a single thread will be able to make this two update operation.
            try {
                // we need to re-peek in case of being already removed just before acquiring the lock.
                head = stats.peek();
                if (isExpired(head))
                    updateSummary(stats.poll(), StatOperation.REMOVE);
            } finally {
                // there may be other stat entries expired but unlock it not to cause lock starvation,
                lock.unlock();
            }
            head = stats.peek();
        }
    }

    /**
     * checks whether given stat is already expired or not.
     *
     * @param stat
     * @return true if expired
     */
    private boolean isExpired(Stat stat) {
        return stat.getTimestamp() < (System.currentTimeMillis() / 1000 - expireMilis);
    }


    private enum StatOperation {
        INSERT, REMOVE;
    }

    public class StatAlreadyExpired extends Exception {
    }

}
