package MyPackage.Components;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Row {


    public StringBuilder createRow(List<String> data, List<Integer> columnsWidth) {
        String startString = "| ";
        String columnsSeparator = " | ";
        String endString = " |";
        StringBuilder resultString = new StringBuilder();
        StringBuilder currentString;
        List<String> remainderString = new ArrayList<>();
        resultString.append(startString);
        int nullCounter = 0;

        for (int i = 0; i < columnsWidth.size(); i++) {
            remainderString.add(null);
            if (data.get(i) != null) {
                if (columnsWidth.get(i) < data.get(i).length()) {
                    if (data.get(i).split("[^А-Яа-яA-Za-z0-9]+").length == 1) {
                        remainderString.set(i, data.get(i).substring(columnsWidth.get(i)));
                        data.set(i, data.get(i).substring(0, columnsWidth.get(i)));
                    } else {
                        currentString = new StringBuilder(cutString(data.get(i), columnsWidth.get(i)));
                        remainderString.set(i, data.get(i).replaceFirst(currentString.toString(), "").trim());
                        data.set(i, currentString.toString().trim());
                    }
                }
            }
        }

        for (int i = 0; i < columnsWidth.size(); i++) {
            if (data.get(i) != null) {
                int diff = columnsWidth.get(i) - data.get(i).length();
                if (i == columnsWidth.size() - 1)
                    resultString.append(data.get(i)).append(createSpaces(diff).append(endString));
                else
                    resultString.append(data.get(i)).append(createSpaces(diff).append(columnsSeparator));
            } else {
                if (i == columnsWidth.size() - 1)
                    resultString.append(createSpaces(columnsWidth.get(i)).append(endString));
                else
                    resultString.append(createSpaces(columnsWidth.get(i)).append(columnsSeparator));
            }
        }

        for (int i = 0; i < remainderString.size(); i++) {
            if (remainderString.get(i) == null) nullCounter++;
        }
        if (nullCounter != remainderString.size()) {
            resultString.append("\r\n");
            resultString.append(createRow(remainderString, columnsWidth));
        }

        return resultString;
    }

    public StringBuilder createSpaces(int count) {
        StringBuilder result = new StringBuilder("");
        for (int j = 0; j < count; j++) {
            result.append(" ");
        }
        return result;
    }

    public StringBuilder createRowSeparator(int width) {
        StringBuilder result = new StringBuilder("");
        for (int j = 0; j < width; j++) {
            result.append("-");
        }
        return result;
    }

    private String cutString(String string, int maxWidth) {
        List<String> wordsList = getWordsList(string);
        StringBuilder result = new StringBuilder();

        int cnt = 0;
        int index = 0;
        int length = wordsList.size();

        while (index != length) {
            if (wordsList.get(index).trim().length() <= maxWidth) {
                if (length == 1) {
                    result.append(wordsList.get(index));
                    cnt = result.length();
                    break;
                }
                while (cnt + wordsList.get(index).trim().length() <= maxWidth) {
                    cnt += wordsList.get(index).length();
                    result.append(wordsList.get(index));
                    index++;
                }
            }
            if (cnt >= maxWidth) break;

            if (wordsList.get(index).length() > maxWidth) {
                int diff = maxWidth - cnt;
                result.append(wordsList.get(index).substring(0, diff));
            }
            break;
        }
        return result.toString().trim();
    }

    private List<String> getWordsList(String s) {
        StringBuilder string = new StringBuilder(s);
        List<String> wordsList = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^А-Яа-яA-Za-z0-9]+");
        Matcher matcher = pattern.matcher(string);
        int idx = 0;
        while (matcher.find(idx)) {
            if (matcher.start() == 0) {
                idx = matcher.end();
                continue;
            }
            idx = matcher.end();
            String word = string.substring(0, matcher.end());
            wordsList.add(word);
            string.delete(0, idx);
            idx = 0;
        }
        wordsList.add(string.toString());
        return wordsList;
    }
}
