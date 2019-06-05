package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Station {

    Map<String, String> stnInfo = new HashMap<String, String>();

    public Map getStnInfo() {
        return this.stnInfo;
    }

}
