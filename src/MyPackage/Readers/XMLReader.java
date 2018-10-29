package MyPackage.Readers;

import MyPackage.Settings.ReportSettings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class XMLReader {

    private static ReportSettings reportSettings;

    private XMLReader() {
    }


    public static ReportSettings getSettingsInstance() {
        try {
            File settingsFile = new File("settings.xml");
            reportSettings = (ReportSettings) JAXBContext.newInstance(ReportSettings.class).createUnmarshaller().unmarshal(settingsFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return reportSettings;
    }
}
