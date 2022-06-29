package ru.bezdomniy.superkassa;

import org.json.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    public void write(File file, List<List<String>> result) throws IOException {

        JSONArray jsonArray = new JSONArray(result);
        try(FileWriter fw = new FileWriter(file)) {
            jsonArray.write(fw);
        }

    }

}
