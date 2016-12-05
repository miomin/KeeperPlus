package com.scu.miomin.keeperplus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by miomin on 16/12/5.
 */

public class DateUtil {

    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}
