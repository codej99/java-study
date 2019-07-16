package org.study.java.collections;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class TreeMapTest {

    @Test
    public void treeMapTest() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(90, "김구");
        map.put(50, "김원봉");
        map.put(10, "안창호");
        map.put(80, "노무현");
        map.put(40, "김대중");
        // 제일 윗순위의 Map 반환
        Map.Entry<Integer, String> fEntry = map.firstEntry();
        assertEquals("안창호", fEntry.getValue());
        // 제일 하위순위의 Map 반환
        Map.Entry<Integer, String> lEntry = map.lastEntry();
        assertEquals("김구", lEntry.getValue());
        Map.Entry<Integer, String> lowerEntry = map.lowerEntry(30); // 지정된 키보다 바로 아래 순위의 Map 반환
        assertSame(10, lowerEntry.getKey());
        Map.Entry<Integer, String> higherEntry = map.higherEntry(30); // 지정된 키보다 바로 위 순위의 Map 반환
        assertSame(40, higherEntry.getKey());
        Map.Entry<Integer, String> ceilingEntry = map.ceilingEntry(30); // 동등 or 바로 아래 순위의 Map 반환
        assertSame("김대중", ceilingEntry.getValue());

        Map.Entry<Integer, String> pollFirstEntry = map.pollFirstEntry();
        // 제일 위 순위의 Map 반환 후 컬렉션에서 삭제
        assertEquals("안창호", pollFirstEntry.getValue());
        Map.Entry<Integer, String> pollLastEntry = map.pollLastEntry();
        // 동등 or 바로 아래 순위의 Map 반환 후 컬렉션에서 삭제
        assertEquals("김구", pollLastEntry.getValue());

        // 내림 차순으로 수정
        NavigableMap<Integer, String> descendingMap = map.descendingMap();
        descendingMap = descendingMap.subMap(50, true, 30, true); // 범위조건, 없으면 전체 순위 반환
        Set<Map.Entry<Integer, String>> descendingEntrySet = descendingMap.entrySet();
        /**
         50-김원봉
         40-김대중
         */
        for (Map.Entry<Integer, String> entry : descendingEntrySet) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
