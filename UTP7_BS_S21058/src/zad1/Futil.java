package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        Charset codePlik= Charset.forName("Cp1250");
       ArrayList<String> list= new ArrayList<>();
        try {
            Files.walk(Paths.get(dirName)).filter(Files::isRegularFile).forEach(l->{
                try {
                    Files.lines(l,codePlik).forEach(list::add);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.write(Paths.get(resultFileName),list,StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
