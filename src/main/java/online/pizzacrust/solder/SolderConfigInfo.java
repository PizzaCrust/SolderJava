package online.pizzacrust.solder;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class SolderConfigInfo {

    @SerializedName("modpacks")
    public Map<String, String> registeredModpacks;

    @SerializedName("rooturl")
    public String rootURL; // do not add / at the end

    public static class Default {

        public static SolderConfigInfo get() {
            SolderConfigInfo configInfo = new SolderConfigInfo();
            configInfo.registeredModpacks = new HashMap<>();
            configInfo.rootURL = "";
            return configInfo;
        }

    }

}
