package edu.school21.printer;

import edu.school21.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private final Renderer renderer;
    private String prefix;
    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String str) {
        renderer.out(STR."\{prefix} \{str}");
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
