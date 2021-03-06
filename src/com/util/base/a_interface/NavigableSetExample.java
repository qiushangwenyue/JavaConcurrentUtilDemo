package com.util.base.a_interface;

import java.util.*;

/**
 * @author y15079
 * @create: 9/18/17 11:24 PM
 * @desc:
 *
 * NavigableSet接口

  NavigableSet 工作类似于NavigableMap，但是没有键值对。在NavigableMap中的3组方法，这里有其中的两组。
  你可以可以获取一个可导航的子集，带 有navigableHeadSet(toElement), navigableSubSet(fromElement, toElement), 和 navigableTailSet(E fromElement)方法。
  或者你可以获得特定的元素，通过ceiling(element), floor(element), higher(element), 和lower（element)方法。
  你也可以获取或删除元素，通过pollFirst()和pollLast()方法，升序的迭代 descendingIterator()补充了iterator()。

   在表面之下，CouncurrentSkipListSet使用ConcurrentSkipListSet来完成所有的工作。
 *
 */
public class NavigableSetExample {

    public static void main(String[] args) {
        /**
         * NavigableMap Map
         */

        NavigableMap<String, Integer> map = new TreeMap<String, Integer>();

        map.put("1", 11);
        map.put("2", 22);
        map.put("3", 33);
        map.put("4", 44);

        //小于该key且离该key最近的一个key
        String lowerKey = (String) map.lowerKey("2");
        System.out.println("lowerKey:" + lowerKey);

        Map.Entry entry = map.higherEntry("2");
        System.out.println("entry.getKey():" + entry.getKey());
        System.out.println("entry.getValue():" + entry.getValue());

        //小于等于该key且离该key最近的一个key
        String floorKey = map.floorKey("2");
        System.out.println("floorKey:" + floorKey);

        //大于等于该key且离该key最近的一个key
        String ceilingKey = map.ceilingKey("2");
        System.out.println("ceilingKey:" + ceilingKey);

        //小于
        SortedMap headMap = map.headMap("2");
        System.out.println("headMap:" + headMap);

        //大于等于
        SortedMap tailMap = map.tailMap("2");
        System.out.println("tailMap:" + tailMap);

        /**
         * NavigableSet Set
         */
        NavigableSet<Integer> set = new TreeSet<Integer>();
        set.add(1001);
        set.add(1002);
        set.add(1003);
        set.add(1004);

        //小于
        Integer lower = set.lower(1003);
        System.out.println("lower:" + lower);
        //小于等于
        Integer floor = set.floor(1003);
        System.out.println("floor:" + floor);
    }
}
