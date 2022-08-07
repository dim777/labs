package ru.ineb.exam.q0;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ClassTest {
    @Test
    public void test1() {
        A a = new B();
        B b = new B();

        System.out.println(b.getVar());
        System.out.println(a.getVar());
    }

    @Test
    public void test2() {
        A a = new B();
        B b = new B();

        System.out.println(b.a);
        System.out.println(a.a);
    }

    class A {
        String a = "a";

        String getVar() {
            return a;
        }
    }

    class B extends A {
        String a = "b";

        String getVar() {
            return a;
        }
    }

    @Test
    public void test3() {
        Bus bus = new Bus();

        System.out.println(null instanceof Bus);
        System.out.println(bus instanceof Vehicle);
        System.out.println(bus instanceof Bus);
        //System.out.println(bus instanceof ArrayList);
        //System.out.println(bus instanceof Thread);
        System.out.println(bus instanceof Collection);

        Building b = new Building();
        House h = new House();
        Building bh = new House();
        House p = (House) b;
        House q = (House) h;
        House r = (House) bh;

    }

    @Test
    public void test4() {
        final Robot r = new Robot();
        r.apply(x -> System.out.print(x + " bent!"), 5);
    }

    interface Bend {
        void bend(double tensileStrength);
    }

    static interface Vehicle {
    }

    class Tool {
        String use() {
            return "";
        }     // r1
    }

    class Hammer extends Tool {
        //private Double use() {
        //}  // r2

        public void bang() {
        }  // r3
    }

    static class Bus implements Vehicle {
    }

    @Test
    public void convert() {
        Building b = new Building();
        House h = new House();
        Building bh = new House();
        //class cast ex
        Building p = (House) b;
        //House q = (Building) h;
        Building r = (Building) bh;
        House s = (House) bh;
    }

    class Building {
    }

    class House extends Building {
    }
}
