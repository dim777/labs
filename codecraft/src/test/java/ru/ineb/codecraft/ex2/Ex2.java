package ru.ineb.codecraft.ex2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;

public class Ex2 {
    /**
     * 10 10     Building has 100 windows (10x10)
     * 6         Batman has 6 jumps to find the bombs
     * 2 5       Batman starts at position (2,5)
     * Constraints
     * 1 ≤ W ≤ 10000
     * 1 ≤ H ≤ 10000
     * 2 ≤ N ≤ 100
     * 0 ≤ X, X0 < W
     * 0 ≤ Y, Y0 < H
     * Response time per turn ≤ 150ms
     * Response time per turn ≤ 150ms
     */
    @Test
    public void testBombFixedLocation() {
        Matrix matrix = new Matrix(new Size(10, 10));
        Bomb bomb = new Bomb(new Location(4, 7));
        Hero hero = new Hero(6, new Location(5, 2), matrix);
        Game game = new Game(matrix, bomb, hero);

        // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
        while (game.nextTurn()) {

        }
        // game loop
        while (true) {
            //String bombDir = in.next();

            System.out.println("0 0");
        }
    }

    @Test
    public void testBombChangesLocation() {
        int W = 10; // width of the building.
        int H = 10; // height of the building.
        int N = 6; // maximum number of turns before game over.
        int X0 = 2;
        int Y0 = 5;

        // game loop
        while (true) {
            //String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // the location of the next window Batman should jump to.
            System.out.println("0 0");
        }
    }

    //in game (7,4) means 7 is column and 4 is a row
    class Location {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        /**
         * for game in different order -> col/row
         *
         * @return
         */
        @Override
        public String toString() {
            return y + " " + x;
        }
    }


    class Bomb {
        private Location location;

        Bomb(Location location) {
            this.location = location;
        }
    }

    class Size {
        private int i;
        private int j;

        Size(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    class Matrix {
        private int[][] matrix;
        private Size mSize;

        Matrix(Size mSize) {
            this.mSize = mSize;
        }

        private void init() {
            for (int i = 0; i < mSize.i; i++) {
                for (int j = 0; j < mSize.j; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    class Hero {
        private int avTurns;
        private Location current;
        private Matrix matrix;
        private Map<String, BiFunction<Size, Location, Location>> actions = new HashMap<>();

        Hero(int avTurns, Location current, Matrix matrix) {
            this.avTurns = avTurns;
            this.current = current;
            this.matrix = matrix;
            init();
        }

        /**
         * Possible directions: U, UR, R, DR, D, DL, L or UL
         */
        void init() {
            actions.put("U", Ex2.this::handleDirU);
            actions.put("UR", Ex2.this::handleDirUR);
            actions.put("R", Ex2.this::handleDirR);
            actions.put("DR", Ex2.this::handleDirDR);
            actions.put("D", Ex2.this::handleDirD);
            actions.put("DL", Ex2.this::handleDL);
            actions.put("L", Ex2.this::handleDirL);
            actions.put("UL", Ex2.this::handleDirU);
        }

        Location go(String direction) {
            return actions.get(direction).apply(matrix.mSize, current);
        }
    }

    Location handleDirL(final Size size, final Location current) {
        int x = current.x / 2;
        return new Location(x, current.y);
    }

    Location handleDirD(final Size size, final Location current) {
        int y = current.y / 2;
        return new Location(current.x, y);
    }

    Location handleDirR(final Size size, final Location current) {
        int x = ((size.i - 1) - current.x) / 2;
        return new Location(size.i - x, current.y);
    }

    Location handleDirU(final Size size, final Location current) {
        int y = ((size.j - 1) - current.y) / 2;
        return new Location(current.x, size.j - y);
    }

    Location handleDirUL(final Size size, final Location current) {
        return new Location(this.handleDirL(size, current).x, this.handleDirU(size, current).y);
    }


    Location handleDL(final Size size, final Location current) {
        return new Location(this.handleDirL(size, current).x, this.handleDirD(size, current).y);
    }

    Location handleDirDR(final Size size, final Location current) {
        return new Location(this.handleDirR(size, current).x, this.handleDirD(size, current).y);
    }

    Location handleDirUR(final Size size, final Location current) {
        return new Location(this.handleDirR(size, current).x, this.handleDirU(size, current).y);
    }

    interface BombPosition {
        Location pos();
    }

    class StaticPosition implements BombPosition {
        private Location initial;

        public StaticPosition(Location initial) {
            this.initial = initial;
        }

        @Override
        public Location pos() {
            return initial;
        }
    }

    class RandomPosition implements BombPosition {
        private Location initial;
        private Size mSize;

        public RandomPosition(Location initial, Size mSize) {
            this.initial = initial;
            this.mSize = mSize;
        }

        @Override
        public Location pos() {
            return new Location((new Random().nextInt()) * mSize.i, (new Random().nextInt()) * mSize.j);
        }
    }

    class GameFailEx extends RuntimeException {
        public GameFailEx(String message) {
            super(message);
        }
    }

    class Game {
        private Matrix matrix;
        private Bomb bomb;
        private Hero hero;
        private int turn;

        Game(Matrix matrix, Bomb bomb, Hero hero) {
            this.matrix = matrix;
            this.bomb = bomb;
            this.hero = hero;
        }

        boolean nextTurn() {
            turn++;
            if (turn > hero.avTurns) throw new GameFailEx("Available turns ended");
            return true;
        }

        void turn(String direction) {
            hero.go(direction);
        }

    }
}
