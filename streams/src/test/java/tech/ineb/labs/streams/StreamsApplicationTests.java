package tech.ineb.labs.streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class StreamsApplicationTests {
    private static final List<TestClazz> testCollection = new ArrayList<>();

    static {
        testCollection.add(new TestClazz(UUID.randomUUID(), "testClazz1"));
        testCollection.add(new TestClazz(UUID.randomUUID(), "testClazz2"));
        testCollection.add(new TestClazz(UUID.randomUUID(), "testClazz3"));
    }

    @Test
    public void testStreamsAggregate() {
        int res = Stream.of(1, 2, 3).reduce(0, (acc, accumulator) -> acc + accumulator);
        Assert.assertEquals(6, res);
    }

}
