package com.hongbao.api.util.support;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

/**
 * Created by wuqiang on 15-8-6. 用于把异常的堆栈输出
 */
public class ThrowablePrintStream extends PrintStream {

    private StringBuffer buffer = new StringBuffer();
    private String cause = null;
    private Integer maxSize = null;
    private String fullMag = "\n$[Buffer Full]$";
    private char fullCharFlag = '$';


    public ThrowablePrintStream() {
        // printStackTrace
        super(System.err);
    }

    /**
     * 指定最大输出长度
     */
    public ThrowablePrintStream(int maxSize) {
        super(System.err);
        if (maxSize > 0) {
            this.maxSize = maxSize;
        }
    }

    public ThrowablePrintStream(Throwable throwable) {
        // printStackTrace
        super(System.err);
        throwable.printStackTrace(this);
    }

    private void newLine() {
        writeToStringBuffer("\n");
    }

    private void writeToStringBuffer(Object msg) {
        String s = String.valueOf(msg);
        if (maxSize != null) {
            int buffer_length = buffer.length();
            if ((buffer_length + s.length() + fullMag.length()) >= maxSize) {
                char lastChar = buffer.charAt(buffer_length - 1);
                if (lastChar != fullCharFlag) {
                    buffer.append(fullMag);
                }
                return;
            }
        }
        if (s != null && s.startsWith("Caused by: ")
                && (!s.equals("Caused by: "))) {
            try {
                String tmp = s.substring(s.indexOf("Caused by: ")
                        + "Caused by: ".length());
                this.cause = tmp.trim();
                if (tmp.contains("Exception: ")) {
                    tmp = tmp.substring(tmp.indexOf("Exception: ")
                            + "Exception: ".length());
                    this.cause = tmp.trim();
                }
            } catch (Exception e) {
            }
        }
        buffer.append(s);
    }

    public void println(Object o) {
        print(o);
        newLine();
    }

    public void println(String s) {
        print(s);
        newLine();
    }

    public void print(Object obj) {
        writeToStringBuffer(String.valueOf(obj));
    }

    public void print(String s) {
        writeToStringBuffer(s);
    }

    /**
     * 获取输出结果
     */
    public StringBuffer getBuffer() {
        return buffer;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Deprecated
    @Override
    public void flush() {
        //
    }

    @Deprecated
    @Override
    public void close() {
        //
    }

    @Deprecated
    @Override
    public boolean checkError() {
        return false;
    }

    @Deprecated
    @Override
    protected void setError() {
        //
    }

    @Deprecated
    @Override
    protected void clearError() {
        //
    }

    @Override
    public void write(int b) {
        writeToStringBuffer(b);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        if (buf == null || buf.length <= 0) {
            return;
        }
        if ((off | len | (buf.length - (len + off)) | (off + len)) < 0) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < len; i++) {
            write(buf[off + i]);
        }
    }

    @Deprecated
    @Override
    public void print(boolean b) {
        writeToStringBuffer(b + "");
    }

    @Deprecated
    @Override
    public void print(char c) {
        writeToStringBuffer(c + "");
    }

    @Deprecated
    @Override
    public void print(int i) {
        writeToStringBuffer(i + "");
    }

    @Deprecated
    @Override
    public void print(long l) {
        writeToStringBuffer(l + "");
    }

    @Deprecated
    @Override
    public void print(float f) {
        writeToStringBuffer(f + "");
    }

    @Deprecated
    @Override
    public void print(double d) {
        writeToStringBuffer(d + "");
    }

    @Deprecated
    @Override
    public void print(char[] s) {
        for (char xi : s) {
            writeToStringBuffer(xi + "");
        }
    }

    @Deprecated
    @Override
    public void println() {
        newLine();
    }

    @Deprecated
    @Override
    public void println(boolean x) {
        newLine();
        writeToStringBuffer(x + "");
    }

    @Deprecated
    @Override
    public void println(char x) {
        newLine();
        writeToStringBuffer(x + "");
    }

    @Deprecated
    @Override
    public void println(int x) {
        newLine();
        writeToStringBuffer(x + "");
    }

    @Deprecated
    @Override
    public void println(long x) {
        newLine();
        writeToStringBuffer(x + "");
    }

    @Deprecated
    @Override
    public void println(float x) {
        newLine();
        writeToStringBuffer(x + "");
    }

    @Deprecated
    @Override
    public void println(double x) {
        newLine();
        writeToStringBuffer(x + "");
    }

    @Deprecated
    @Override
    public void println(char[] x) {
        newLine();
        for (char xi : x) {
            writeToStringBuffer(xi + "");
        }
    }

    @Deprecated
    @Override
    public PrintStream printf(String format, Object... args) {
        return this;
    }

    @Deprecated
    @Override
    public PrintStream printf(Locale l, String format, Object... args) {
        return this;
    }

    @Deprecated
    @Override
    public PrintStream format(String format, Object... args) {
        return this;
    }

    @Deprecated
    @Override
    public PrintStream format(Locale l, String format, Object... args) {
        return this;
    }

    @Deprecated
    @Override
    public PrintStream append(CharSequence csq) {
        writeToStringBuffer(csq);
        return this;
    }

    @Deprecated
    @Override
    public PrintStream append(CharSequence csq, int start, int end) {
        writeToStringBuffer(csq.subSequence(start, end));
        return this;
    }

    @Deprecated
    @Override
    public PrintStream append(char c) {
        writeToStringBuffer(c);
        return this;
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b == null ? 0 : b.length);
    }
}
