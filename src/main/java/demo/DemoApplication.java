package demo;

import demo.c3_concurrent.flow.ApplicationContextHelper;
import demo.c3_concurrent.flow.FlowContent;
import demo.c3_concurrent.flow.FlowStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * DemoApplication
 * author  wenhe
 * date 2019/9/24
 */
@SpringBootApplication(scanBasePackages = {"demo"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
        ApplicationContextHelper.getBean(FlowStart.class)
                .start("flow1", new FlowContent());
    }


}
