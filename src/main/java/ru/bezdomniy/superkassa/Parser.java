package ru.bezdomniy.superkassa;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<List<String>> parse(File file) throws IOException {

        String text = Files.readString(file.toPath());
        return parse(text);

    }

    public List<List<String>> parse(String text){
        JSONArray wrapArray = new JSONArray(text);
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < wrapArray.length(); i++) {
            List<String> line = new ArrayList<>();
            JSONArray jsonArray = wrapArray.getJSONArray(i);
            for(int j = 0; j<jsonArray.length();j++)
                line.add(jsonArray.optString(j, "null"));
            result.add(line);

        }
        return result;
    }
}
