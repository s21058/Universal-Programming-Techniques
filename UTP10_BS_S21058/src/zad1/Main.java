/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;
public class Main {
  static  long start;
  public static void main(String[] args){
    CreateProductReadFile createProductReadFile = new CreateProductReadFile();
    CountWeightProduct countWeightProduct=new CountWeightProduct();

    Thread countTowarObjectCreatedThred = new Thread(createProductReadFile);
    Thread countWeightProductThread = new Thread(countWeightProduct);

    countTowarObjectCreatedThred.start();


    countWeightProductThread.start();

  }


}
