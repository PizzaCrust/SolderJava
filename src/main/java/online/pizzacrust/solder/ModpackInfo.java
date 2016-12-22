package online.pizzacrust.solder;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ModpackInfo {

    public String name;
    @SerializedName("display_name")
    public String displayName;
    public String url;
    public String icon;
    @SerializedName("icon_md5")
    public String iconMD5;
    public String logo;
    @SerializedName("logo_md5")
    public String logoMD5;
    public String background;
    @SerializedName("background_md5")
    public String backgroundMD5;
    public String recommended;
    public String latest;
    public String[] builds;

    public ModpackInfo() {

    }
    public static ModpackInfo fromfile(String slug) throws IOException {
        File file = new File(System.getProperty("user.dir"), slug + ".json");
        return new Gson().fromJson(new FileReader(file), ModpackInfo.class);
    }

}
