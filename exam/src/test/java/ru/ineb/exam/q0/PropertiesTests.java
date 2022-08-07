package ru.ineb.exam.q0;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PropertiesTests {
    @Test
    public void propertyTest() {
        Properties properties = new Properties();
        properties.setProperty("welcome1", "Good day!");

        System.out.println(properties.getProperty("welcome1"));
        System.out.println(properties.getProperty("welcome2", "Test"));//line n1
        System.out.println(properties.getProperty("welcome3"));
    }


    @Test
    public void pathTest() {
        Path p1 = Paths.get("/Pics/MyPic.jpeg");
        System.out.println(p1.getNameCount() +
                ":" + p1.getName(1) +
                ":" + p1.getFileName());
    }

    @Test
    public void testInPath() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter GDP: ");
        //int GDP0 = Integer.parseInt(br.readline());
        int GDP1 = br.read();
        //int GDP2 = br.nextInt();
        //int GDP3 = Integer.parseInt(br.next());
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void copyTest() throws IOException {
        ClassLoader classLoader = PropertiesTests.class.getClassLoader();
        String filePathSource = Objects.requireNonNull(classLoader.getResource("log.txt")).getFile();
        Path source = Paths.get(filePathSource);
        Path destination = Paths.get("/Users/dim777/Documents/JavaProjects/labs/exam/out/test/");
        Files.copy(source, destination);
    }

    @Test
    public void groupBy() {
        List<Student> stds = Arrays.asList(
                new Student("Jessy", "Java ME", "Chicago"),
                new Student("Helen", "Java EE", "Houston"),
                new Student("Mark", "Java ME", "Chicago"));
        stds.stream()
                .collect(Collectors.groupingBy(Student::getCourse))
                .forEach((src, res) -> System.out.println(src + ":" + res));
    }

    @Test
    public void predicate() {
        List<String> strs = Arrays.asList("Java", "Java EE", "Java ME");
        Predicate<String> cf1 = s -> s.length() > 3;
        Predicate<String> cf2 = new CourseFilter() {
            public boolean test(String s) {
                return s.contains("Java");
            }
        };
        long c = strs.stream()
                .filter(cf1)
                .filter(cf2)
                .count();
        System.out.println(c);
    }

    interface CourseFilter extends Predicate<String> {
        public default boolean test(String str) {
            return str.equals("Java");
        }
    }

    class Student {
        String course, name, city;

        public Student(String name, String course, String city) {
            this.course = course;
            this.name = name;
            this.city = city;
        }

        public String toString() {
            return course + ":" + name + ":" + city;
        }

        public String getCourse() {
            return course;
        }
    }
}
