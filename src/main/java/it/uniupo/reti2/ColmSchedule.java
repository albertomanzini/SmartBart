package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ColmSchedule {

    @SerializedName(value = "?xml")
    private Map<String, ?> xml;

    @SerializedName(value = "root")
    private RootSchedule rootSchedule;

    public RootSchedule getRootSchedule() {
        return this.rootSchedule;
    }
}
