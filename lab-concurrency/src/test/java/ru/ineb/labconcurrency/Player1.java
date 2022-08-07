package ru.ineb.labconcurrency;

import java.util.*;

class Player1 {
    private static TreeSet<Mountain> mountains;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // game loop
        while (true) {
            mountains = new TreeSet<>();

            for (int i = 0; i < 8; i++) {
                Mountain mountain = new Mountain();
                mountain.setIdx(i);
                mountain.setHeight(in.nextInt());
                mountains.add(mountain);
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(mountains.last().getIdx()); // The index of the mountain to fire on.
        }
    }
    static class Mountains implements Iterable<Mountain> {

        @Override
        public Iterator<Mountain> iterator() {
            return null;
        }
    }

    static class Mountain implements Comparable<Mountain> {
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