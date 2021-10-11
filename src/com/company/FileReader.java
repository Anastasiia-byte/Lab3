package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> readFromFile(String fileName){
        List<String> stringsPerLine = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251");
            while (scanner.hasNextLine()) {
                stringsPerLine.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return stringsPerLine;
    }
}
