package com.muatik.controller;

import com.muatik.entity.Stat;
import com.muatik.entity.Summary;
import com.muatik.service.StatsService;
import com.muatik.service.StatsServiceBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by muatik on 9/12/17.
 */
@RestController
public class StatsController {

    private StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/statistics")
    public Summary get() {
        return statsService.getSummary();
    }

    @PostMapping("/transactions")
    public ResponseEntity add(@RequestBody Stat stat) {
        try {
            statsService.add(stat);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (StatsServiceBean.StatAlreadyExpired e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
