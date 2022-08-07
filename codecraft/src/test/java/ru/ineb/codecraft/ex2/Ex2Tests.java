package ru.ineb.codecraft.ex2;

import org.junit.Assert;
import org.junit.Test;

public class Ex2Tests {
    static Ex2 ex2;
    static Ex2.Size size;
    static Ex2.Matrix matrix;
    static Ex2.Location bombLoc;
    static Ex2.Location heroLoc;
    static Ex2.Bomb bomb;
    static Ex2.Hero hero;
    static Ex2.Game game;

    static {
        ex2 = new Ex2();
        size = ex2.new Size(10, 10);
        matrix = ex2.new Matrix(size);
        bombLoc = ex2.new Location(4, 7);
        bomb = ex2.new Bomb(bombLoc);
        heroLoc = ex2.new Location(5, 2);
        hero = ex2.new Hero(6, heroLoc, matrix);
        game = ex2.new Game(matrix, bomb, hero);
    }


    @Test
    public void testDirHandler_U() {
        Ex2.Location exp = ex2.new Location(5, 6);
        Ex2.Location actual = ex2.handleDirU(size, heroLoc);
        Assert.assertEquals(exp, actual);
    }
}
