package tech.ineb.labs.labio;

import java.io.*;

public class AbstractClasses {
    //InputStream, OutputStream, Reader, and Writer

    class InputStreamImpl extends InputStream {
        @Override
        public int read() throws IOException {
            return 0;
        }
    }

    class OutputStreamImpl extends OutputStream {

        @Override
        public void write(int b) throws IOException {

        }
    }

    class ReaderImpl extends Reader {

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            return 0;
        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public boolean markSupported() {
            return true;
        }
    }

    class WriterImpl extends Writer {

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {

        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public void close() throws IOException {

        }
    }
}
