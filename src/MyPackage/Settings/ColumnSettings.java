package MyPackage.Settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
public class ColumnSettings {

    @XmlElement
    private String title;

    @XmlElement
    private int width;

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

}
