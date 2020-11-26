package GenericList;

import java.util.Objects;

public class MayaGods implements Comparable<MayaGods> {
    private String godName;
    private String godType;
    private String godDescription;

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public String getGodType() {
        return godType;
    }

    public void setGodType(String godType) {
        this.godType = godType;
    }

    public String getGodDescription() {
        return godDescription;
    }

    public void setGodDescription(String godDescription) {
        this.godDescription = godDescription;
    }

    public MayaGods()
    {

    }

    public MayaGods(String name)
    {
        this.godName=name;
    }

    public MayaGods(String name, String type, String desc)
    {
        this.godName = name;
        this.godType = type;
        this.godDescription = desc;
    }

    public boolean equals(MayaGods g) {
        return this.getGodName().equals(g.getGodName());
    }

    @Override
    public int compareTo(MayaGods o) {
        return this.getGodName().compareTo(o.getGodName());
    }

    public String toString() {
        return this.getGodName();
    }
}
