package com.muatik.service;

import com.muatik.entity.Stat;
import com.muatik.entity.Summary;
import org.springframework.stereotype.Service;

/**
 * Created by muatik on 9/12/17.
 */
@Service
public class StatsServiceBean implements StatsService{

    @Override
    public void add(Stat stat) throws StatAlreadyExpired {

    }

    @Override
    public Summary getSummary() {
        return null;
    }

    public class StatAlreadyExpired extends Exception {
    }
}
