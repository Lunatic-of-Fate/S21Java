package edu.school21.preProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String str) {
        return str.toUpperCase();
    }
}
