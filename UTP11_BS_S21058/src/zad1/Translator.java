package zad1;

import java.util.Locale;

public class Translator {
    public static String translatingCountry(String country, Locale translateFrom, Locale translateInto) {
        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(translateFrom).equals(country)) {
                return l.getDisplayCountry(translateInto);
            }
        }
        return "";
    }
}
