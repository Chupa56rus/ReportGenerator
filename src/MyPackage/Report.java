package MyPackage;

import MyPackage.Components.Page;

import java.io.*;

public class Report {

    public static void main(String[] args) {
        Page page = new Page();
        Report report = new Report();
        report.createTxtReport(page.getPageString());
    }

    public void createTxtReport(StringBuilder reportString) {
        File report = new File("example-example-report.txt");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(report), "UTF-16"));
            bufferedWriter.write(reportString.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}