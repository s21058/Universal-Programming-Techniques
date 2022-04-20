package zad1;

public class StringTask implements Runnable {

    Thread thread = new Thread();
    final String str;
    static String another = "";
    int countOfConcat;
    volatile static TaskState status;

    boolean flagIsDoing;

    enum TaskState {
        RUNNING, ABORTED, READY, CREATED
    }

    StringTask(String str, int countOfConcat) {
        status = TaskState.CREATED;
        this.str = str;
        this.countOfConcat = countOfConcat;
    }


    public  void run() {
        while (!this.thread.isInterrupted() || flagIsDoing != true) {
            for (int i = 0; i < countOfConcat; i++) {
                status = TaskState.RUNNING;
                try {
                    Thread.sleep(1);
                    another += str;

                } catch (InterruptedException e) {
                    status=TaskState.ABORTED;
                    return;
                }

                if (i == countOfConcat - 1) {
                    status = TaskState.READY;
                    return;
                }

            }
        }
    }

    public synchronized boolean isDone() {
        switch (status) {
            case RUNNING:
                flagIsDoing = false;
                break;
            case ABORTED:
                flagIsDoing = true;
                break;
            case READY:
                flagIsDoing = true;
                break;
        }
        return flagIsDoing;
    }

    public void start() {
        this.thread = new Thread(new StringTask(str, countOfConcat));
        this.thread.start();
    }

    public synchronized TaskState getState() {
        return status;
    }

    public synchronized static String getResult() {
        return another;
    }

    public  void abort() {
        this.thread.interrupt();


    }
}
