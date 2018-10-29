package MyPackage.Readers;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSVReader {

    private List<List<String>> sourceData = new ArrayList<>();

    public List<List<String>> TSVRead(String fileName) {
        TsvParser parser = new TsvParser(new TsvParserSettings());
        List<String[]> sourceDataArr;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            sourceDataArr = parser.parseAll(new InputStreamReader(fis, "UTF-16"));
            for (int i = 0; i < sourceDataArr.size(); i++) {
                sourceData.add(new ArrayList<>(Arrays.asList(sourceDataArr.get(i))));
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sourceData;
    }
}
