package com.wtf.javabase.robot;

import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WTF
 * @Date 2022/6/23 15:12
 */
public class MyMouseListener implements NativeMouseListener, NativeMouseMotionListener {

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove((Integer) 2);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
