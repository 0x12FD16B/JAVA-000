package io.x16fd16b.assignment03.school.starter;

import java.util.List;

/**
 * School
 *
 * @author David Liu
 */
public class School {

    private String name;

    private List<Klass> klasses;

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

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", klasses=" + klasses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (name != null ? !name.equals(school.name) : school.name != null) return false;
        return klasses != null ? klasses.equals(school.klasses) : school.klasses == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (klasses != null ? klasses.hashCode() : 0);
        return result;
    }
}
