/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomersPurchaseSortFind {
    List<Purchase> purchaseList = null;
    File file = null;
    Scanner sc = null;

    public void readFile(String fname) {
        purchaseList = new ArrayList<Purchase>();
        try {
            file = new File(fname);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] splited = sc.nextLine().split(";| ");
                Purchase purchase = new Purchase(splited[0],
                        splited[1],
                        splited[2],
                        splited[3],
                        Double.parseDouble(splited[4]),
                        Double.parseDouble(splited[5]),
                        Double.parseDouble(splited[4]) * Double.parseDouble(splited[5])
                );

                purchaseList.add(purchase);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Blad wczytania pliku in readFile constructor !");
        }

    }

    public void showSortedBy(String sortByWhat) {
        if (sortByWhat.equals("Nazwiska")) {
            System.out.println(sortByWhat);
            purchaseList.stream()
                    .sorted(Comparator.comparing(Purchase::getNazwisko)
                            .thenComparing(Purchase::getIdKlient)).forEach(System.out::println);
            System.out.println();
        } else if (sortByWhat.equals("Koszty")) {
            System.out.println(sortByWhat);
            purchaseList.stream()
                    .sorted(Comparator.comparing(Purchase::getKoszt).reversed()
                            .thenComparing(Purchase::getIdKlient)).forEach(purchase -> {
                System.out.println(purchase + "(koszt: " + purchase.getKoszt() + ")");
            });
            System.out.println();
        }
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        purchaseList.stream().filter(filter_id -> id.equals(filter_id.getIdKlient())).forEach(System.out::println); {
            System.out.println();
        }
    }
}
