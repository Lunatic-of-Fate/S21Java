package edu.school21.printer;

import edu.school21.renderer.Renderer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class PrinterWithDateTimeImpl implements Printer {
    private final Renderer renderer;
    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }
    @Override
    public void print(String str) {
        renderer.out(setDataTime(str));
    }
    private String setDataTime(String str) {
        return STR."\{LocalDateTime.now().toString()} \{str}";
    }
}
