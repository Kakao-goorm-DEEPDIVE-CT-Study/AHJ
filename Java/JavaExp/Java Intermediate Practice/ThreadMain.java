package java_intermediate;

public class ThreadMain {

    public static void main(String[] args) {
        int amount = 1000;
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                iter(Thread.currentThread().getName());
            }
        },"ThreadA");
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                iter(Thread.currentThread().getName());
            }
        },"ThreadB");
        threadA.start();
        threadB.start();
    }
    public static void iter(String ThreadName) {
        for(int i = 0; i < 10; i++){
            System.out.println(ThreadName + " : " + i);
        }
    }

}
