package demo.c3_concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMapDemo
 * author  wenhe
 * date 2019/8/7
 */
@Slf4j
public class ConcurrentHashMapDemo {

    @Test
    public void testTransfer() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(2);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.put(57, 58);
        map.put(51, 51);
        map.put(52, 52);
        map.put(53, 53);
        map.put(54, 54);
        map.put(55, 55);
        map.put(56, 56);
        map.put(57, 57);
        map.put(58, 58);
        map.put(59, 59);
    }

    private Map<Integer, Integer> map1 = new ConcurrentHashMap<>(2);

    @Test
    public void testInit() {
        map1 = new HashMap<>();//ok
        new Thread(() -> map1.put(1, 1)).start();
        new Thread(() -> map1.put(1, 2)).start();
        System.out.println(map1.get(1));
        System.out.println("nihao");
    }

    //遍历remove
    @Test
    public void testForeach() {
        // HashMap : java.util.ConcurrentModificationException
        Map<Integer, Integer> map = new ConcurrentHashMap<>(2);
        //remove是报错java.util.ConcurrentModificationException，元素存在时才报错
//        map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Remove Key = " + entry.getKey() + ", Value = " + entry.getValue());
            map.remove(entry.getKey());
        }
        System.out.println(map);
    }

}
