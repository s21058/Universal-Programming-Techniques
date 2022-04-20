package zad1;

import static zad1.Towar.linesInFileCount;

public class CountWeightProduct  implements Runnable {

    CountWeightProduct() {
    }

    @Override
    public synchronized void run() {
        int counter = -1;
        float sumOfAllWaga = 0;
        for (int i = 0; i <linesInFileCount(); i++) {
                counter++;
                sumOfAllWaga += Float.parseFloat(CreateProductReadFile.getProductObjectList().get(i).getWaga());
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter == 100) {
                    System.out.println("policzono wage " + i + " towarow");
                    counter = 0;

                }

        }
        System.out.println(sumOfAllWaga);

    }


}
