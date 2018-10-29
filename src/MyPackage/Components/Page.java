package MyPackage.Components;

import MyPackage.Readers.TSVReader;
import MyPackage.Readers.XMLReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Page {

    private StringBuilder pageString;

    public StringBuilder getPageString() {
        return pageString;
    }

    public Page() {
        pageString = createPage("source-data.tsv");
    }

    private StringBuilder createPage(String sourcePath) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentRow;
        Row row = new Row();
        PageHead pageHead = new PageHead();
        TSVReader tsvRead = new TSVReader();
        List<List<String>> source = tsvRead.TSVRead(sourcePath);
        int numOfLinesHead = getNumberOfLines(pageHead.getPageHead());
        int stringCounter = numOfLinesHead;
        int pageHeight = XMLReader.getSettingsInstance().getPage().getHeight();

        result.append(pageHead.getPageHead());
        for (int i = 0; i < source.size(); i++) {
            currentRow = row.createRow(source.get(i), pageHead.getColumnsWidth());
            stringCounter += getNumberOfLines(currentRow) + 1;

            if (stringCounter > pageHeight) {
                result.append("\r\n");
                result.append("~");
                result.append("\r\n");
                result.append(pageHead.getPageHead());
                stringCounter = numOfLinesHead;
            }

            result.append("\r\n");
            result.append(currentRow);
            result.append("\r\n");
            result.append(row.createRowSeparator(pageHead.getWidth()));
        }
        return result;
    }


    private int getNumberOfLines(StringBuilder string) {
        StringBuilder row = new StringBuilder(string);
        int counter = 1;
        Pattern pattern = Pattern.compile("\r\n");
        Matcher matcher = pattern.matcher(row);
        int idx = 0;
        while (matcher.find(idx)) {
            idx = matcher.end();
            row.delete(0, idx);
            idx = 0;
            counter++;
        }
        return counter;
    }
}
