package it.uniupo.reti2.restAPI;

import com.google.gson.annotations.SerializedName;

public class RootRoutesInfo {

    @SerializedName(value = "root")
    private RoutesInfoRoot root;

    public RoutesInfoRoot getRoot() {
        return this.root;
    }
}

