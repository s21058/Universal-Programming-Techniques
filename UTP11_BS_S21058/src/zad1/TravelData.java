package zad1;

import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {
    private ArrayList<String> resultForMainList = null;

    private File file;
    private ArrayList<ArrayList<String>> dataList = new ArrayList<>();
    private ArrayList<ArrayList<String>> anotherList = new ArrayList<>();

    private Scanner sc;
    private Locale currentLocale;

    public TravelData(File dataDir) {
        try {
            file = new File(dataDir.getAbsolutePath() + "\\dane.txt");
            sc = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] elemOfEachRow = line.split("&");
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < elemOfEachRow.length; i++) {
                arrayList.add(elemOfEachRow[i]);
            }
            dataList.add(arrayList);
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        resultForMainList = new ArrayList<>();
        String[] eachElement = locale.split("_");
        String helpString = new String();
        if (eachElement.length > 1) {
            currentLocale = new Locale(eachElement[0], eachElement[1]);
        } else {
            currentLocale = new Locale(eachElement[0]);
        }
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateInstance();
        simpleDateFormat.applyPattern(dateFormat);
        Date date;
        for (ArrayList<String> arr : dataList) {
            ArrayList helpList = new ArrayList<>();

            String[] tmp = arr.get(0).split("_");
            Locale locale1 = null;
            if (tmp.length > 1) {
                locale1 = new Locale(tmp[0], tmp[1]);
            } else {
                locale1 = new Locale(tmp[0]);
            }
            Locale.setDefault(locale1);
            helpString += "   " + Translator.translatingCountry(arr.get(1), locale1, currentLocale);
            helpList.add(Translator.translatingCountry(arr.get(1), locale1, currentLocale));
            try {
                date = simpleDateFormat.parse(arr.get(2));
                helpString += "   " + simpleDateFormat.format(date);
                helpList.add(simpleDateFormat.format(date));

                date = simpleDateFormat.parse(arr.get(3));
                helpString += "   " + simpleDateFormat.format(date);
                helpList.add(simpleDateFormat.format(date));
                ResourceBundle place = ResourceBundle.getBundle("zad1.bundle", currentLocale);
                helpString += "   " + place.getString(arr.get(4));
                helpList.add(place.getString(arr.get(4)));
                NumberFormat numberFormat = NumberFormat.getNumberInstance(locale1);
                Number number = numberFormat.parse(arr.get(5));
                helpString += "   " + numberFormat.format(number);
                helpList.add(numberFormat.format(number));
                Currency currency = Currency.getInstance(arr.get(6));
                helpString += "   " + currency.getCurrencyCode();
                helpList.add(currency.getCurrencyCode());
                resultForMainList.add(helpString);
                helpString = new String();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            anotherList.add(helpList);
        }

        return resultForMainList;
    }

    public ArrayList<ArrayList<String>> getAnotherList() {
        return anotherList;
    }
}

