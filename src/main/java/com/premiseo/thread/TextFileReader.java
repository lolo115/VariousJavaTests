package com.premiseo.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class TextFileReader implements Runnable {
    private String filepath;
    private StringBuilder sb;

    public TextFileReader(String filepath) {
        this.filepath = filepath;
        this.sb = new StringBuilder();
    }

    public String getFilepath() {
        return filepath;
    }

    public StringBuilder getSb() {
        return sb;
    }
    public void run() {
        long startTime = System.nanoTime();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e){
            System.err.format("IOException: %s%n", e);
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Thread terminated in "+(float)totalTime/1000000000+" sec");
    }
}
