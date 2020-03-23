package com.shunyi.autoparts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @description 生成条形码控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class BarcodeController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BarcodeController.class);
    private Random random = new Random();

    @GetMapping("/barcode")
    public String generate() {
        return createRandomInteger(100000000, 999999999L, random)+"";
    }

    private long createRandomInteger(int aStart, long aEnd, Random aRandom){
        if ( aStart > aEnd ) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        long randomNumber =  fraction + (long)aStart;
        return randomNumber;
    }
}
