package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class RootRoute {

    @SerializedName(value = "?xml")
    private Map<String , ?> xml;

    @SerializedName(value = "root")
    private RootRoutes root;

    public RootRoutes getRoot() {
        return this.root;
    }
}
