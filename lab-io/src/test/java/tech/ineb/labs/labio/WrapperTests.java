package tech.ineb.labs.labio;

import org.junit.Test;

import java.io.*;

public class WrapperTests {
    /**
     * A class with the word InputStream or OutputStream in its name is used for reading or
     * writing binary data, respectively.
     *
     * A class with the word Reader or Writer in its name is used for reading or writing
     * character or string data, respectively.
     *
     * Most, but not all, input classes have a corresponding output class.
     *
     * A low-level stream connects directly with the source of the data.
     *
     * A high-level stream is built on top of another stream using wrapping.
     *
     * A class with Buffered in its name reads or writes data in groups of bytes or characters
     * and often improves performance in sequential file systems.
     */
    @Test
    public void testBufferedReader() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("zoo-data.txt"))) {
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectBufferedReader() throws IOException {
        try (ObjectInputStream objectStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("zoo-data.txt")))) {
            System.out.println(objectStream.readObject());
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
