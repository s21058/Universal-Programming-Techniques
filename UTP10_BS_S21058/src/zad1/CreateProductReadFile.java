package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static zad1.Towar.towarFilePath;

public class CreateProductReadFile implements Runnable{
    static List<Towar> productObjectList= new ArrayList<>();
    Towar towar =null;
    CreateProductReadFile(){
    }

    @Override
    public synchronized   void run() {
        File file = new File(towarFilePath());
        try {
            Scanner sc = new Scanner(file);
            int counter=0;
            while (sc.hasNext()){
                String[] splited=sc.nextLine().split("\\s");
                towar=new Towar(splited[0],splited[1]);
                productObjectList.add(towar);
                counter++;
                Thread.sleep(0,1);
                if(counter==200){
                    System.out.println("utworzono "+ productObjectList.size()+" obiektow");
                    counter=0;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public  static List<Towar> getProductObjectList() {
        return productObjectList;
    }

}
