package com.wtf.javabase.robot;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * @Author WTF
 * @Date 2022/6/23 14:36
 */
public class MyKeyListener implements NativeKeyListener {

    Set<Integer> keys = new HashSet<>();
    List<Integer> close = new Vector<>(2);


    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        keys.add(e.getKeyCode());
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            close.add(NativeKeyEvent.VC_ESCAPE);
            try {
                if (close.size() == 2) {
                    GlobalScreen.unregisterNativeHook();
                }
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
        if (e.isActionKey() || NativeKeyEvent.VC_SPACE == e.getKeyCode()) {
            return;
        }
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }

        //L zuo移
        if (keys.contains(NativeKeyEvent.VC_E) && keys.contains(NativeKeyEvent.VC_L)) {
            if (keys.contains(NativeKeyEvent.VC_SHIFT)) {
                robot.mouseMove(x - 100, y);
                if (keys.contains(NativeKeyEvent.VC_SPACE)) {
                    robot.mouseWheel(-100);
                }
            } else {
                robot.mouseMove(x - 10, y);
            }
        }

        // ‘ you移
        if (keys.contains(NativeKeyEvent.VC_E) && keys.contains(NativeKeyEvent.VC_QUOTE)) {
            if (keys.contains(NativeKeyEvent.VC_SHIFT)) {
                robot.mouseMove(x + 100, y);
            } else {
                robot.mouseMove(x + 10, y);
            }
        }

        // o zuoshang
        if (keys.contains(NativeKeyEvent.VC_E) && keys.contains(NativeKeyEvent.VC_O)) {
            if (keys.contains(NativeKeyEvent.VC_SHIFT)) {
                robot.mouseMove(x - 100, y - 100);
            } else {
                robot.mouseMove(x - 10, y - 10);
            }
        }

        // P shang
        if (keys.contains(NativeKeyEvent.VC_E) && keys.contains(NativeKeyEvent.VC_P)) {
            if (keys.contains(NativeKeyEvent.VC_SHIFT)) {
                robot.mouseMove(x, y - 100);
            } else {
                robot.mouseMove(x, y - 10);
            }
        }
        //; xia
        if (keys.contains(NativeKeyEvent.VC_E) && keys.contains(NativeKeyEvent.VC_SEMICOLON)) {
            if (keys.contains(NativeKeyEvent.VC_SHIFT)) {
                robot.mouseMove(x, y + 100);
            } else {
                robot.mouseMove(x, y + 10);
            }
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_K) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }


    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        keys.remove((Integer) e.getKeyCode());
        if (e.getKeyCode() == NativeKeyEvent.VC_E) {
            keys = new HashSet<>();
        }
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
        System.out.println("Key Typed: " + e.getKeyCode() + " " + e.getRawCode() + " " + e.getKeyChar() + " " + e.getKeyLocation());
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new MyKeyListener());
    }

}
