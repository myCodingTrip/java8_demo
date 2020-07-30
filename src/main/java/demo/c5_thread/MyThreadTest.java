package demo.c5_thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class MyThreadTest {

    //主线程结束子线程运行程序会不会退出？
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

    //主线程想一个数，子线程来猜
    @Test
    public void guessTest() throws ExecutionException, InterruptedException {
        final int bound = 10;
        Random random = new Random();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        int i = random.nextInt(bound);

        while (true) {
            //必须放在循环中否则结果总一样
            FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() {
//                    log.info("guess");
                    return random.nextInt(bound);
                }
            });
            executor.submit(task);
            Integer j = task.get();
            System.out.println("main: " + i + " task: " + j);
            if (i == j) break;
            Thread.sleep(1000);
        }

    }
}
