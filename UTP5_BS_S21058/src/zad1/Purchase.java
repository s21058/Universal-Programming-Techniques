/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;


public class Purchase {
    public String idKlient;
    public String imie;
    public String nazwisko;
    public String nazwaTowaru;
    public double cena;
    public double zakupionaIlosc;
    public double koszt;

    Purchase(String idKlient,
             String nazwisko,
             String imie,
             String nazwaTowaru,
             double cena,
             double zakupionaIlosc,
             double koszt) {
        this.idKlient = idKlient;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwaTowaru = nazwaTowaru;
        this.cena = cena;
        this.zakupionaIlosc = zakupionaIlosc;
        this.koszt = koszt;
    }

    @Override
    public String toString() {
        return "" + idKlient + ";" + nazwisko +
                " " + imie +
                ";" + nazwaTowaru + ";" + cena + ";" + zakupionaIlosc;
    }

    public String getIdKlient() {
        return idKlient;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public double getZakupionaIlosc() {
        return zakupionaIlosc;
    }

    public double getKoszt() {
        return koszt;
    }
}