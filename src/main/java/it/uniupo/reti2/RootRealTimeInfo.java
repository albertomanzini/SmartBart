package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class RootRealTimeInfo {

    @SerializedName(value = "root")
    private RealTimeInfo root;

    public RealTimeInfo getRoot() {
        return root;
    }
}
