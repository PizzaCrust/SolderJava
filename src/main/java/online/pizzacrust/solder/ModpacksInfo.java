package online.pizzacrust.solder;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ModpacksInfo {

    @SerializedName("modpacks")
    public Map<String, String> modpacksOnDatabase;

    @SerializedName("mirror_url")
    public String mirrorLink;

    public ModpacksInfo(Map<String, String> modpacksOnDatabase, String mirrorLink) {
        this.modpacksOnDatabase = modpacksOnDatabase;
        this.mirrorLink = mirrorLink;
    }


}
