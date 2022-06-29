package ru.bezdomniy.superkassa;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Parser parser = new Parser();
        List<List<String>> list;
        try {
            list = parser.parse(new File("src/main/resources/input.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Solution solution = new Solution(list);
        List<List<String>> result = solution.computeVariants();


        Writer writer = new Writer();
        try {
            writer.write(new File("src/main/resources/result.json"), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
