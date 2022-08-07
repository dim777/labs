package ru.ineb.labconcurrency;

import org.junit.Test;

import java.util.*;
import java.io.*;
import java.math.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
public class Player {
    private TreeSet<Mountain> mountains;

    @Test
    public void testGameOne() {
        Player player = new Player();
        player.mountains = new TreeSet<>();

        for (int i = 0; i < 8; i++) {
            Mountain mountain = new Mountain();
            mountain.setIdx(i);
            mountain.setHeight(new Random().nextInt());
            player.mountains.add(mountain);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(player.mountains.last().getIdx()); // The index of the mountain to fire on.
    }

    static class Mountains implements Iterable<Mountain> {

        @Override
        public Iterator<Mountain> iterator() {
            return null;
        }
    }

    class Mountain implements Comparable<Mountain> {
        private int height;
        private int idx;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Mountain mountain = (Mountain) o;
            return height == mountain.height &&
                idx == mountain.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(height, idx);
        }

        @Override
        public String toString() {
            return "Mountain{" +
                "height=" + height +
                ", idx=" + idx +
                '}';
        }

        @Override
        public int compareTo(Mountain o) {
            if (o == null) throw new UnsupportedOperationException();
            if (this.height < o.getHeight()) return -1;
            else if (this.height == o.getHeight()) return 0;
            return 1;
        }
    }
}