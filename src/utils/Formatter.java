package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {
    private NumberFormat numberFormat;

    public Formatter() {
        Locale vnLocale = new Locale("vi", "VN");
        this.numberFormat = NumberFormat.getInstance(vnLocale);
    }

    public String formatMoney(double amount) {
        return numberFormat.format(amount) + "đ";
    }
}
