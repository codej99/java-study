package org.study.java.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashSetTest {
    @Test
    public void hashSetTest() {
        Set<String> sets = new HashSet<>();
        sets.add("z");
        sets.add("b");
        sets.add("d");
        sets.add("a");
        sets.add("c");
        // iterator를 이용하여 순회
        Iterator<String> itr = sets.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("------------------------");
        // for로 순회
        for (String set : sets) {
            System.out.println(set);
        }

        // a, b set에서 공통으로 있는 값을 a에 담는다.
        Set<String> setsb = new HashSet<>();
        setsb.add("c");
        setsb.add("d");
        sets.retainAll(setsb);
        assertThat(sets, contains("c", "d"));

        // set to array 변환
        String[] array = sets.toArray(new String[sets.size()]);
        System.out.printf("set to array : %s\n", Arrays.toString(array));

        // 두개의 객체간에 동일함은 equals로만 판별되지만, set에 입력될때 유일값의 여부는 추가로 hashcode까지 확인한다.
        Set<User> setUser = new HashSet<>();
        User user1 = new User("김우성", 1, "actor");
        User user2 = new User("김우성", 1, "dancer");
        setUser.add(user1);
        setUser.add(user2);
        assertEquals(user1, user2); // 이름과 나이만 같으면 동일하다고 판별
        assertEquals(1, setUser.size()); // 동일데이터이므로 1건만 Set에 존재
    }

    public static class User {
        public String name;
        public int age;
        public String job;

        public User(String name, int age, String job) {
            this.name = name;
            this.age = age;
            this.job = job;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof User) {
                User user = (User) obj;
                return this.name.equals(user.name) && this.age == user.age;
                //                return this.job.equals(user.job);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + age;
            result = 31 * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }
    }
}
