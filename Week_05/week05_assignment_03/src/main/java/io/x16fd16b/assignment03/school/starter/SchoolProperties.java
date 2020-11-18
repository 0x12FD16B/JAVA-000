package io.x16fd16b.assignment03.school.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * SchoolProperties
 *
 * @author David Liu
 */
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {

    private String name;

    private List<Klass> klasses;

    private boolean enablePrintInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Klass> getKlasses() {
        return klasses;
    }

    public void setKlasses(List<Klass> klasses) {
        this.klasses = klasses;
    }

    public boolean isEnablePrintInfo() {
        return enablePrintInfo;
    }

    public void setEnablePrintInfo(boolean enablePrintInfo) {
        this.enablePrintInfo = enablePrintInfo;
    }
}
