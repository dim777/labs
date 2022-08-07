package ru.ineb.exam.q0;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class ExamApplicationTests {


    @Test
    public void q0() {
        A a = new A("Bye");
        Immutable im = new Immutable(a);
        System.out.print(im);

        a.setA(" bye");
        System.out.print(im);
    }


    final class A {
        private String s;

        public A(String s) {
            this.s = s;
        }

        public String toString() {
            return s;
        }

        public void setA(String a) {
            this.s += a;
        }
    }

    public final class Immutable {
        private final A a;

        public Immutable(A a) {
            this.a = a;
        }

        public String toString() {
            return a.toString();
        }
    }

}
