package org.study.java.collections.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;

public class ListTest {

    @Test
    public void arrayListTest() {
        List<String> list = new ArrayList<>();
        // 맨뒤에 객체 추가
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        assertThat(list, contains("a", "b", "c", "d", "e"));
        // 특정인덱스에 객체 추가
        list.add(1, "b-0");
        assertEquals("b-0", list.get(1));

        // 리스트 사이즈
        assertEquals(6, list.size());

        // 객체 존재여부
        assertTrue(list.contains("c"));

        // 특정 인덱스 객체 조회
        assertEquals("a", list.get(0));

        // 비어있는지 확인
        assertFalse(list.isEmpty());

        // 객체 삭제
        list.remove("b"); // by 객체
        list.remove(4); // by 인덱스
        assertEquals(4, list.size());
        assertEquals("b-0", list.get(1));

        // 리스트 초기화
        list.clear();
        assertEquals(0, list.size());

        List<String> alist = new ArrayList<>();
        alist.add("b");
        alist.add("c");
        alist.add("d");
        List<String> blist = new ArrayList<>();
        blist.add("a");
        blist.add("b");
        blist.add("c");

        // a, b 리스트의 교집합을 a 리스트에 주입.
        alist.retainAll(blist); // [b, c]
        assertEquals(2, alist.size());
        assertThat(alist, contains("b", "c"));

        // list에서 array로의 변환
        String[] array = alist.toArray(new String[alist.size()]);
        assertEquals("b", array[0]);
        assertEquals("c", array[1]);

        // array에서 list로의 변환
        List<String> convertList = new ArrayList<>(Arrays.asList(array));
        assertThat(convertList, contains("b", "c"));
    }
}
