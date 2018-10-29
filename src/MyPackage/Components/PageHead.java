package MyPackage.Components;

import MyPackage.Readers.XMLReader;

import java.util.ArrayList;
import java.util.List;

public class PageHead {
    private int width;
    private List<String> columnsTitle = new ArrayList<>();
    private Row row = new Row();
    private List<Integer> columnsWidth = new ArrayList<>();
    private StringBuilder pageHead;

    public StringBuilder getPageHead() {
        return pageHead;
    }

    public int getWidth() {
        return width;
    }

    public List<Integer> getColumnsWidth() {
        return columnsWidth;
    }

    public PageHead() {
        width = XMLReader.getSettingsInstance().getPage().getWidth();
        for (int i = 0; i < XMLReader.getSettingsInstance().getColumns().size(); i++) {
            columnsWidth.add(XMLReader.getSettingsInstance().getColumns().get(i).getWidth());
            columnsTitle.add(XMLReader.getSettingsInstance().getColumns().get(i).getTitle());
        }
        pageHead = createPageHead();
    }

    private StringBuilder createPageHead() {
        StringBuilder result = new StringBuilder();
        result.append(row.createRowSeparator(width));
        result.append("\r\n");
        result.append(row.createRow(columnsTitle, columnsWidth));
        result.append("\r\n");
        result.append(row.createRowSeparator(width));
        return result;
    }
}