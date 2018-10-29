package MyPackage.Settings;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportSettings {

    @XmlElement
    private PageSettings page;

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    private List<ColumnSettings> columns;

    public PageSettings getPage() {
        return page;
    }

    public List<ColumnSettings> getColumns() {
        return columns;
    }
}
