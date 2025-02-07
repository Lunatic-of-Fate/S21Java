package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.sqrt;

public class Program {
    public static void main(String[] args) {
        String file1 = args[0];
        String file2 = args[1];


        try {
            Map<String, Integer> wordCountFile1 = getWordCount(file1);
            Map<String, Integer> wordCountFile2 = getWordCount(file2);
            Set<String> dictionary = new HashSet<>(wordCountFile1.keySet());
            dictionary.addAll(wordCountFile2.keySet());

            Vector<Integer> vectorDictionary1 = createVector(wordCountFile1, dictionary);
            Vector<Integer> vectorDictionary2 = createVector(wordCountFile2, dictionary);

            System.out.printf("%.2f%n", similarity(vectorDictionary1, vectorDictionary2));

        } catch (Exception e) {
            return;
        }
    }

    private static double similarity(Vector<Integer> vectorDictionary1, Vector<Integer> vectorDictionary2) {

        double resultSimilarity = 0;

        int chislitel = 0;
        int znamenatelA = 0;
        int znamenatelB = 0;

        for(int i = 0; i < vectorDictionary1.size(); i++) {
            chislitel += vectorDictionary1.get(i) * vectorDictionary2.get(i);
            znamenatelA += (vectorDictionary1.get(i) * vectorDictionary1.get(i));
            znamenatelB += (vectorDictionary2.get(i) * vectorDictionary2.get(i));
        }
        resultSimilarity = chislitel/(sqrt(znamenatelA) * sqrt(znamenatelB));

        return resultSimilarity;
    }


    private static Vector<Integer> createVector(Map<String, Integer> wordCountFile, Set<String> dictionary) {
        Vector<Integer> tmp = new Vector<>();
        for (String key : dictionary) {
            tmp.add(wordCountFile.getOrDefault(key, 0));
        }
        return tmp;
    }

    private static Map<String, Integer> getWordCount(String filePath) throws IOException {
        Map<String, Integer> wordCountFile = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] lineArr = line.split(" ");
            for(String s : lineArr) {
                wordCountFile.put(s, wordCountFile.getOrDefault(s, 0) + 1);
            }

        }
        return wordCountFile;
    }



}