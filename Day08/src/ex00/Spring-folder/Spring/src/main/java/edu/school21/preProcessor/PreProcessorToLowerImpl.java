package edu.school21.preProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String process(String str) {
        return str.toLowerCase();
    }
}
