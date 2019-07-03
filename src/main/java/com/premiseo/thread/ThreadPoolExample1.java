package com.premiseo.thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPoolExample1
{
    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(32);
        List<String> r =null;

        try (Stream<Path> walk = Files.walk(Paths.get("D:\\temp\\files"))) {
            r = walk.map(x -> x.toString()).filter(f->f.contains("file100Mb")).collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> al = new ArrayList<String>(r);

        int filesToProcess=24;
        for (int i = 1; i <= filesToProcess; i++)
        {
            TextFileReader fr = new TextFileReader(al.get(i%25));
            System.out.println("Created a thread to read : " + fr.getFilepath()+" -- Active Thread Count"+executor.getActiveCount());
            executor.execute(fr);
        }
        executor.shutdown();

    }
}