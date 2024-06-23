package edu.poly.asm.utils;

import java.text.DecimalFormat;

public class CurrencyConverter {
	private static final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

    public static String convertToVND(double amount) {
        double roundedAmount = Math.round(amount * 100.0) / 100.0; // Làm tròn số đến hai chữ số lẻ
        return decimalFormat.format(roundedAmount) + " VND";
    }

    public static void main(String[] args) {
        double amount = 4024.800079345703;
        System.out.println("Amount in VND: " + convertToVND(amount));
    }
}
