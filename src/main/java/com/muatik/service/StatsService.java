package com.muatik.service;

import com.muatik.entity.Stat;
import com.muatik.entity.Summary;

/**
 * Created by muatik on 9/12/17.
 */
public interface StatsService {
    void add(Stat stat) throws StatsServiceBean.StatAlreadyExpired;
    Summary getSummary();

    void clear();
}
