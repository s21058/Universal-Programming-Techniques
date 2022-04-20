package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Towar {
    public  String idTowar;
    public  String waga;

    Towar(String idTowar, String waga){
        this.idTowar =idTowar;
        this.waga =waga;
    }
    Towar(){}

    public  String getIdTowar() {
        return idTowar;
    }

    public  String getWaga() {
        return waga;
    }

    public static int linesInFileCount(){
        int rowCount=0;
        try {
            rowCount= (int) Files.lines(Paths.get(towarFilePath())).count();
          } catch (IOException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public String toString() {
        return idTowar +" "+ waga;
    }

    public static String towarFilePath(){
        return "..//Towary.txt";
    }
}
