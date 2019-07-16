package org.study.java.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @Test
    public void mapTest() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        map.put("key4", "val4");
        map.put("key5", "val5");
        // key로 value 조회
        assertEquals("val1", map.get("key1"));
        // key 존재 여부
        assertTrue(map.containsKey("key2"));
        // value 존재 여부
        assertFalse(map.containsValue("value2"));
        // 비어있는지 여부
        assertFalse(map.isEmpty());
        // key 리스트 조회
        assertThat(map.keySet(), contains("key1", "key2", "key5", "key3", "key4"));
        // value 리스트 조회
        assertThat(map.values(), contains("val1", "val2", "val5", "val3", "val4"));
        // Map 사이즈 조회
        assertEquals(5, map.size());
        // key로 데이터 삭제
        assertEquals("val1", map.remove("key1"));
        // key, value 두 데이터를 연관지어 삭제.
        assertTrue(map.remove("key4", "val4"));
        // 키가 존재하지 않으면 디폴트 값 지정
        assertEquals("defaultValue", map.getOrDefault("Key3", "defaultValue"));
        // key가 없을 경우 값 쌍이 Map에 추가된다.
        map.putIfAbsent("key6", "val6");
        // key가 존재하면 교체. key가 없으면 아무것도 안함.
        map.replace("key7", "value7");
        // 순회하면서 지정된 동작 수행
        map.forEach((key, value) -> System.out.printf("key : %s, value : %s\n", key, value));
        System.out.println("------------------------");
        // 모든 키들의 값 변경
        map.replaceAll((key, value) -> key + "_" + value);
        map.forEach((key, value) -> System.out.printf("key : %s, value : %s\n", key, value));
        System.out.println("------------------------");

        Map<String, Integer> map2 = new HashMap<>();
        // key가 없을 경우 값 쌍이 Map에 추가된다.
        map2.putIfAbsent("key1", 10);
        map2.forEach((key, value) -> System.out.printf("key : %s, value : %s\n", key, value));
        // key가 있으면 value 값을 1 증가. value가 없으면 아무것도 안함.
        map2.computeIfPresent("key1", (String key, Integer value) -> ++value);
        assertSame(11, map2.get("key1"));
        Map<String, User> map3 = new HashMap<>();
        // key가 없으면 value를 계산하는 함수를 호출한다.
        User user = map3.computeIfAbsent("happydaddy", key -> new User("happydaddy", 20, "programmer"));
        assertEquals("happydaddy", user.name);
        /*
        key가 존재하지 않는지, key의 값이 null의 경우, 맵에 key와 value의 페어를 추가한다.
        key가 존재하는 경우, value를 새롭게 계산 된 값으로 옮겨 놓는다.
        계산 된 새로운 값이 null인 경우, 맵으로부터 키가 삭제된다.
        */
        Map<String, Integer> mapMerge = new HashMap<>();
        mapMerge.merge("happydaddy", 0, (k, v) -> mapMerge.get("happydaddy") + 1);
        assertSame(0, mapMerge.get("happydaddy"));
        mapMerge.merge("happydaddy", 0, (k, v) -> mapMerge.get("happydaddy") + 1);
        assertSame(1, mapMerge.get("happydaddy"));
    }

    /* User Class */
    public static class User {
        public String name;
        public int age;
        public String job;

        public User(String name, int age, String job) {
            this.name = name;
            this.age = age;
            this.job = job;
        }
    }
}
