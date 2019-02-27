package com.zhou.test;

import com.google.common.base.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GuavaTest {

    /**
     * 使用com.google.common.base.Strings类的isNullOrEmpty(input)方法判断字符串是否为空
     */
    @Test
    public void isNotNull() {
        //Strings.isNullOrEmpty(input) demo
        String input = "123";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty ? "is" : "is not") + " null or empty.");
    }

    /**
     * 获得两个字符串相同的前缀或者后缀
     */
    @Test
    public void samePrefixOrSuffixPart() {
//Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a, b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c, d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }

    @Test
    public void padStartOrEnd() {

        int minLength = 8;
        String padEndResult = Strings.padEnd("123", minLength, '1');
        System.out.println("padEndResult is " + padEndResult);
        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);

    }

    @Test
    public void splitAndJoiner() {
        Iterable<String> splitResults = Splitter.onPattern("[，,]")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平,hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }

        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=')
                .split(toSplitString);
        for (Map.Entry entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }

        String joinResult = Joiner.on(" ").join(new String[]{"hello", "world"});
        System.out.println(joinResult);

        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);

    }


    @Test
    public void ObjectsTest() {
        Object a = null;
        Object b = new Object();
        boolean aEqualsB = Objects.equal(a, b);
        System.out.println("aEqualsB:" + aEqualsB);

        a = b;
        aEqualsB = Objects.equal(a, b);
        System.out.println("aEqualsB:" + aEqualsB);

    }

    @Test
    public void preconditionsTest() {
        preconditionsTest("Jim", 19, "hello world, hello java");
    }

    private void preconditionsTest(String name, int age, String desc) {
        Preconditions.checkNotNull(name, "name may not be null");
        Preconditions.checkArgument(age >= 18 && age < 99, "age must in range (18,99)");
        Preconditions.checkArgument(desc !=null && desc.length() < 10, "desc too long, max length is ", 10);
    }

}
