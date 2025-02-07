package edu.school21.renderer;

import edu.school21.preProcessor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private final PreProcessor preProcessor;
    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void out(String str) {
        System.err.println(preProcessor.process(str));
    }

}
