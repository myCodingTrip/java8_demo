package demo.c5_thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class MyThreadTest {

    @Test
    public void mainStopTest() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log.info(new Date().toString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        //默认主线程不会等待子线程
        t.join();
    }

    @Test
    public void guessTest() {

    }
}
