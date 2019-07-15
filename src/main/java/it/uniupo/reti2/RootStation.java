package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class RootStation {

    @SerializedName(value = "?xml")
    private Map<String , ?> xml;

    @SerializedName(value = "root")
    private Root root;

    public Root getRoot() {
        return this.root;
    }
}
