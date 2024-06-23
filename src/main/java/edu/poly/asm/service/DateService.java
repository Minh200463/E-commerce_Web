package edu.poly.asm.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateService {
	 private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	 private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    /**
	     * Lấy thời gian hiện tại dưới dạng LocalDateTime
	     *
	     * @return LocalDateTime kết quả
	     */
	    public static LocalDateTime now() {
	        return LocalDateTime.now();
	    }

	    /**
	     * Lấy thời gian hiện tại dưới dạng chuỗi định dạng
	     *
	     * @return String kết quả
	     */
	    public static String stringsnow() {
	        return now().format(DATE_TIME_FORMATTER);
	    }

	    /**
	     * Chuyển đổi String sang LocalDateTime
	     *
	     * @param date là String cần chuyển
	     * @param pattern là định dạng thời gian
	     * @return LocalDateTime kết quả
	     */
	    public static LocalDateTime toLocalDateTime(String date, String... pattern) {
	        try {
	            DateTimeFormatter formatter = pattern.length > 0 ? DateTimeFormatter.ofPattern(pattern[0]) : DATE_TIME_FORMATTER;
	            return LocalDateTime.parse(date, formatter);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * Chuyển đổi từ LocalDateTime sang String
	     *
	     * @param date là LocalDateTime cần chuyển đổi
	     * @param pattern là định dạng thời gian
	     * @return String kết quả
	     */
	    public static String toString(LocalDateTime date, String... pattern) {
	        DateTimeFormatter formatter = pattern.length > 0 ? DateTimeFormatter.ofPattern(pattern[0]) : DATE_TIME_FORMATTER;
	        return date.format(formatter);
	    }

	    /**
	     * Bổ sung số ngày vào thời gian
	     *
	     * @param date thời gian hiện có
	     * @param days số ngày cần bổ sung vào date
	     * @return LocalDateTime kết quả
	     */
	    public static LocalDateTime addDays(LocalDateTime date, int days) {
	        return date.plusDays(days);
	    }

	    /**
	     * Bổ sung số ngày vào thời gian hiện hành
	     *
	     * @param days số ngày cần bổ sung vào thời gian hiện tại
	     * @return LocalDateTime kết quả
	     */
	    public static LocalDateTime add(int days) {
	        return now().plusDays(days);
	    }

	    /**
	     * Chuyển đổi String sang Date
	     *
	     * @param date là String cần chuyển
	     * @param pattern là định dạng thời gian
	     * @return Date kết quả
	     */
	    public static Date toDate(String date, String... pattern) {
	        try {
	            if (pattern.length > 0) {
	                SIMPLE_DATE_FORMAT.applyPattern(pattern[0]);
	            }
	            return SIMPLE_DATE_FORMAT.parse(date);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * Chuyển đổi từ Date sang String
	     *
	     * @param date là Date cần chuyển đổi
	     * @param pattern là định dạng thời gian
	     * @return String kết quả
	     */
	    public static String toString(Date date, String... pattern) {
	        if (pattern.length > 0) {
	            SIMPLE_DATE_FORMAT.applyPattern(pattern[0]);
	        }
	        return SIMPLE_DATE_FORMAT.format(date);
	    }

	    /**
	     * Bổ sung số ngày vào thời gian
	     *
	     * @param date thời gian hiện có
	     * @param days số ngày cần bổ sung vào date
	     * @return Date kết quả
	     */
	    public static Date addDays(Date date, int days) {
	        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
	        return date;
	    }

	    /**
	     * Bổ sung số ngày vào thời gian hiện hành
	     *
	     * @param days số ngày cần bổ sung vào thời gian hiện tại
	     * @return Date kết quả
	     */
	    public static Date add1(int days) {
	        Date now = new Date();
	        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
	        return now;
	    }

}
