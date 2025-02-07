package ex00;

import java.io.*;
import java.util.*;

public class FileSignatureAnalyzer {
    private static final String SIGNATURES_FILE = "src/ex00/signature.txt";
    private static final String RESULT_FILE = "src/ex00/result.txt";
    private static Map<String, byte[]> signaturesMap = new HashMap<>();


    public static void fileSignatureAnalyzer(String inputFilePath) {
        signatureLoad();
        writeResults(analyzeFile(inputFilePath));
    }

    private static String analyzeFile(String inputFilePath) {
        File file = new File(inputFilePath);
        if (!file.exists()) {
            System.out.println("Файл не найден");
            return "UNDEFINED";
        }

        try (InputStream is = new FileInputStream(file)) {
            byte[] bytes = new byte[8];
            int bytesRead = is.read(bytes);
            if (bytesRead == -1) {
                System.out.println("File is empty");
                return "UNDEFINED";
            }
            for (Map.Entry<String, byte[]> entry : signaturesMap.entrySet()) {
                byte[] signature = entry.getValue();
                if (bytesRead >= signature.length) {
                    boolean match = true;
                    for (int i = 0; i < signature.length; i++) {
                        if (bytes[i] != signature[i]) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        return entry.getKey();
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return "UNDEFINED";
    }

    private static void signatureLoad() {
        try (BufferedReader br = new BufferedReader(new FileReader(SIGNATURES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String fileType = parts[0];
                String[] fileSignature = parts[1].split(" ");
                byte[] signatureArr = new byte[fileSignature.length];
                for (int i = 0; i < fileSignature.length; i++) {
                    signatureArr[i] = (byte) Integer.parseInt(fileSignature[i], 16);
                }
                signaturesMap.put(fileType, signatureArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(signaturesMap);
    }

    private static void writeResults(String result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT_FILE, true))) {
            bw.write(result);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла результатов: " + e.getMessage());
        }
    }
}