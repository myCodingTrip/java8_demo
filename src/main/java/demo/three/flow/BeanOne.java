package demo.three.flow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanOne implements DomainAbilityBean {

    @Override
    public FlowContent invoke(FlowContent content) {
        log.info("BeanOne is run,thread name is {}", Thread.currentThread().getName());
        return null;
    }
}
