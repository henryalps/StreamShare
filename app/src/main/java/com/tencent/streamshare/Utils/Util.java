package com.tencent.streamshare.Utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/21.
 */
public class Util {
	public static boolean isEmpty(final String str) {
		return str == null || str.length() <= 0;
	}

	public static boolean isEmpty(final Collection<? extends Object> collection){
		return collection == null || collection.size() <= 0;
	}

	public static boolean isEmpty(final Map<? extends Object,? extends Object> list){
		return list == null || list.size() <= 0;
	}

	public static boolean isEmpty(final byte[] bytes) {
		return bytes == null || bytes.length <= 0;
	}

	public static boolean isEmpty(final String[] strArr){
		return strArr == null || strArr.length <= 0;
	}


	/**
	 * 将现在的时间（单位：秒）转换成时分秒格式
	 * @param time
	 * @return
	 */
	public static String convertTimeInfo(int time)
	{
		int iTime;
		int hour, minute, second;
		String sTime;
		second = time%60;
		minute = time/60;
		hour = minute/60;
		minute = minute%60;
		sTime = String.format("%02d:%02d:%02d", hour, minute, second);
		return sTime;
	}

	public static String unix2dateFormat(long unix) {

		String dateStr;
		dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(unix*1000));
		return dateStr;
	}
}
