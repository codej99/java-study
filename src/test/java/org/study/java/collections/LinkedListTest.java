package org.study.java.collections;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    public void linkedListTest() {
        LinkedList<String> list = new LinkedList<>();
        // 객체 추가
        list.add("a");
        list.add("b");
        list.add("c");
        list.addFirst("d");
        list.addLast("e");
        list.addLast("f");
        list.addLast("g");
        list.addLast("h");
        list.addLast("i");
        assertThat(list, contains("d", "a", "b", "c", "e", "f", "g", "h", "i"));
        // 특정인덱스에 객체 추가
        list.add(1, "b-0");
        assertEquals("b-0", list.get(1));
        // 객체 존재여부
        assertTrue(list.contains("c"));
        // 객체 조회
        assertEquals("d", list.get(0));
        assertEquals("d", list.getFirst());
        assertEquals("i", list.getLast());
        // 인덱스 확인
        assertEquals(2, list.indexOf("a"));
        assertEquals(4, list.lastIndexOf("c"));
        // 비어있는지 확인
        assertFalse(list.isEmpty());
        // 사이즈 확인
        assertEquals(10, list.size());
        // 객체 삭제
        list.remove("b"); // by 객체
        list.remove(4); // by 인덱스
        assertThat(list, contains("d", "b-0", "a", "c", "f", "g", "h", "i"));
        // 객체를 하나 조회한다. 조회한 객체는 리스트에서 삭제된다.
        assertEquals("d", list.poll());
        assertEquals("b-0", list.pollFirst());
        assertEquals("i", list.pollLast());
        assertThat(list, contains("a", "c", "f", "g", "h"));
        // 맨앞의 데이터를 뺀다.
        assertEquals("a", list.pop());
        // 맨앞에 데이터를 추가한다.
        list.push("first");
        assertThat(list, contains("first", "c", "f", "g", "h"));
        // 객체를 삭제한다.
        assertTrue(list.remove("f"));
        // 최초에 발견된 데이터를 삭제한다.
        assertFalse(list.removeFirstOccurrence("i"));
        // 최종으로 발견된 데이터를 삭제한다.
        assertTrue(list.removeLastOccurrence("g"));
        assertThat(list, contains("first", "c", "h"));
        // 리스트를 초기화한다.
        list.clear();
        // 빈 list의 head 데이터 조회
        assertNull(list.peek());
        assertNull(list.peekFirst());
        assertNull(list.peekLast());
        // IndexOutOfBoundsException이 발생.
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
        // NoSuchElementException이 발생.
        assertThrows(NoSuchElementException.class, () -> {
            list.element();
        });
    }
}
