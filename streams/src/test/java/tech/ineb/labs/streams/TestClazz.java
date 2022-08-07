package tech.ineb.labs.streams;

import java.util.Objects;
import java.util.UUID;

public class TestClazz {
    private UUID id;
    private String name;

    public TestClazz(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClazz testClazz = (TestClazz) o;
        return Objects.equals(id, testClazz.id) &&
                Objects.equals(name, testClazz.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TestClazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}