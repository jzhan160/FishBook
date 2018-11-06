package com.se.fishbook.test;

import com.se.fishbook.util.DateUtil;
import org.junit.Test;

import java.util.Date;

public class TestDateUtil {
    @Test
    public void test(){
        System.out.println(DateUtil.getYear());
        System.out.println(DateUtil.getDays()); //20181106
        System.out.println(DateUtil.getTime()); //2018-11-06 10:02:17
        System.out.println(DateUtil.getTimestamp()); //2018-11-06 10:06:50.81
        System.out.println(DateUtil.getAfterDayDate(DateUtil.getDays()));
        System.out.println(DateUtil.getAfterDayWeek(DateUtil.getDays())); //星期三
        System.out.println(DateUtil.date2Str(new Date())); //2018-11-06
       // System.out.println(DateUtil.fomatDate());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


    }

}
