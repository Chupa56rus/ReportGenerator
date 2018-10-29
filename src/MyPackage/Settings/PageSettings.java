package MyPackage.Settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "page")
public class PageSettings {

    @XmlElement
    private int width;
    @XmlElement
    private int height;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
