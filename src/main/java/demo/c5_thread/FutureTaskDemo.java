package demo.c5_thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskDemo {

    //拿到子线程的返回值
    @Test
    public void initCallable() throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "nihao";
            }
        });
        futureTask.run();
        log.info("get ing");
        String result = (String) futureTask.get();
        log.info("拿到值了，为 {}", result);
    }

    //Runnable只能写死返回值
    @Test
    public void initRunnable() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Runnable() {
            @Override
            public void run() {
                log.info("{} is run", Thread.currentThread().getName());
            }
        }, "finish");
        futureTask.run();
        String result = futureTask.get();
        log.info("run end,result is {}", result);
    }

    // 使用线程池运行FutureTask
    @Test
    public void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
//        FutureTask 我们叫做任务，入参是 Callable，是对 Callable 的包装，方便线程池的使用；
        FutureTask futureTask = new FutureTask(
//                Callable 定义我们需要做的事情，是可以有返回值的；
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(3000);
                        return "我是子线程" + Thread.currentThread().getName();
                    }
                });
        // 使用线程池运行
        executor.submit(futureTask);
//        最后通过 FutureTask.get() 得到子线程的计算结果。
        String result = (String) futureTask.get();
        log.info("result is {}", result);
    }

    //使用Thread运行FutureTask
    @Test
    public void testThreadByCallable() throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                String result = "我是子线程" + Thread.currentThread().getName();
                log.info("子线程正在运行：{}", Thread.currentThread().getName());
                return result;
            }
        });
        new Thread(futureTask).start();
        log.info("返回的结果是 {}", futureTask.get());
    }

}
