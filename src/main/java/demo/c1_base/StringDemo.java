package demo.c1_base;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * StringDemo
 * author  wenhe
 * date 2019/7/28
 */
@Slf4j
public class StringDemo {

    @Test
    public void testFinal() {
        String s = "hello";
        s = "world";
        log.info(s);
    }

    @Test
    public void testGibberish() throws UnsupportedEncodingException {
        String str = "nihao 你好 喬亂";
        byte[] bytes = str.getBytes("ISO-8859-1");
        String s2 = new String(bytes, "ISO-8859-1");
        log.info(s2);
    }

    @Test
    public void testReplace() {
        String str = "hello word !!";
        log.info("替换之前 :{}", str);
        str = str.replace('l', 'd');
        log.info("替换所有字符 :{}", str);
        str = str.replaceAll("d", "l");
        log.info("替换全部 :{}", str);
        str = str.replaceFirst("l", "");
        log.info("替换第一个 :{}", str);
    }

    @Test
    public void testSplit() {
        String s = "boo:and:foo";
        log.info("s.split(\":\") 结果:{}", JSON.toJSONString(s.split(":")));
        log.info("s.split(\":\",2) 结果:{}", JSON.toJSONString(s.split(":", 2)));
        log.info("s.split(\":\",5) 结果:{}", JSON.toJSONString(s.split(":", 5)));
        log.info("s.split(\":\",-2) 结果:{}", JSON.toJSONString(s.split(":", -2)));
        log.info("s.split(\"o\") 结果:{}", JSON.toJSONString(s.split("o")));
        log.info("s.split(\"o\",2) 结果:{}", JSON.toJSONString(s.split("o", 2)));


        String a1 = ",a, ,  b  c ,null";
        log.info("a.split(\",\") 结果:{}", JSON.toJSONString(a1.split(",")));

        String a = ",a, ,  b  c ,";
        List<String> list = Splitter.on(',')
                .trimResults()// 去掉空格
                .omitEmptyStrings()// 去掉空值
                .splitToList(a);
        log.info("Guava 去掉空格的分割方法：{}", JSON.toJSONString(list));
    }

    @Test
    public void testJoin() {
        String s = "hello";
        String s1 = "china";

//    log.info(s.join(",",s1).join(",","null"));
        log.info(String.join(",", s1).join(",", "null"));


        // 依次  join 多个字符串
        Joiner joiner = Joiner.on(",").skipNulls();
        String result = joiner.join("hello", null, "china");
        log.info("依次  join 多个字符串:{}", result);

        List<String> list = Lists.newArrayList(new String[]{"hello", "china", null});
        log.info("自动删除 list 中空值:{}", joiner.join(list));

        String r1 = String.join(",", list);
//    log.info("结果为:{}",r1);
    }

    @Test
    public void testLong128() {
        Long.valueOf(128);
        Long.valueOf(129);
    }

    @Test
    public void lt() {
        String s = "100";
        long beginTime1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Long l = Long.valueOf(s);
        }
        log.info("valueOf 耗时 {}", System.currentTimeMillis() - beginTime1);
    }

    @Test
    public void lt2() {
        String s = "100";
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Long l = Long.parseLong(s);
        }
        log.info("parseLong 耗时 {}", System.currentTimeMillis() - beginTime);
    }

    //通过反射修改字符串
    @Test
    public void reflect() throws Exception {
        String str = "hello world";
        Class clazz = Class.forName("java.lang.String");
        Field field = clazz.getDeclaredField("value");
        field.setAccessible(true);
        // 拿到 string 里面的数组
        char[] value = (char[]) field.get(str);
        log.info("修改之前{}", str);
        log.info(str.length() + "");
        field.set(str, "hello java".toCharArray());
        log.info("修改之后{}", str);
        log.info(str.length() + "");
    }

}
