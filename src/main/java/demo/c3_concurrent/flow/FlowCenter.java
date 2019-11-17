package demo.c3_concurrent.flow;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * FlowCenter
 * author  wenhe
 * date 2019/8/11
 */
@Slf4j
@Component
public class FlowCenter {

    /**
     * flowMap 是共享变量，方便访问
     */
    public static final Map<String, Map<StageEnum, List<DomainAbilityBean>>> flowMap
            = Maps.newConcurrentMap();

    /**
     * PostConstruct 注解的意思就是
     * 在容器启动成功之后，初始化 flowMap
     */
    @PostConstruct
    public void init() {
        // 初始化 flowMap mock
        Map<StageEnum, List<DomainAbilityBean>> stageMap = flowMap.getOrDefault("flow1", Maps.newConcurrentMap());
        for (StageEnum value : StageEnum.values()) {
            List<DomainAbilityBean> domainAbilitys = stageMap.getOrDefault(value, Lists.newCopyOnWriteArrayList());
            if (CollectionUtils.isEmpty(domainAbilitys)) {
                domainAbilitys.addAll(ImmutableList.of(
                        ApplicationContextHelper.getBean(BeanOne.class),
                        ApplicationContextHelper.getBean(BeanTwo.class)
                ));
                stageMap.put(value, domainAbilitys);
            }
        }
        flowMap.put("flow1", stageMap);
        log.info("init success,flowMap is {}", JSON.toJSONString(flowMap));
    }
}
