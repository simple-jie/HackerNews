package com.simple_jie.hackernews.utility;

import android.content.Context;

import com.simple_jie.hackernews.R;

/**
 * Created by Xingbo.Jie on 28/2/17.
 */

public class TimeUtil {
    public static long SECOND_ONE_DAY = 24 * 60 * 60;
    public static long SECOND_ONE_HOUR = 60 * 60;
    public static long SECOND_ONE_MINUTE = 60;
    public static String getNewsListTime(Context context, long timeInSecond) {
        String result = "";

        long diff = System.currentTimeMillis() / 1000 - timeInSecond;
        if (diff > 0) {
            if (diff >= SECOND_ONE_DAY) {
                int days = (int) (diff / SECOND_ONE_DAY);
                result = String.format(context.getString(R.string.time_format_days), days);
            } else if (diff >= SECOND_ONE_HOUR) {
                int hours = (int) (diff / SECOND_ONE_HOUR);
                result = String.format(context.getString(R.string.time_format_hours), hours);
            } else if (diff >= SECOND_ONE_MINUTE) {
                int mins = (int) (diff / SECOND_ONE_MINUTE);
                result = String.format(context.getString(R.string.time_format_minutes), mins);
            } else {
                result = context.getString(R.string.just_now);
            }
        }

        return result;
    }
}
