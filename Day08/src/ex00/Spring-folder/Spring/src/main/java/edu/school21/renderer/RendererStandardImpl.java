package edu.school21.renderer;

import edu.school21.preProcessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void out(String str) {
        System.out.println(preProcessor.process(str));
    }
}
