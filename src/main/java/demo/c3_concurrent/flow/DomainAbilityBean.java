package demo.c3_concurrent.flow;

/**
 * 领域行为
 * author  wenhe
 * date 2019/8/11
 */
public interface DomainAbilityBean {

    /**
     * 领域行为的方法入口
     */
    FlowContent invoke(FlowContent content);
}
