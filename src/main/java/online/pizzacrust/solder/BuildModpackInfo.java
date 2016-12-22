package online.pizzacrust.solder;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildModpackInfo {

    public String minecraft;
    @SerializedName("minecraft_md5")
    public String minecraftMD5;
    public String forge;
    public ModInfo[] mods;

    public static BuildModpackInfo fromfile(String loc) throws IOException {
        File file = new File(System.getProperty("user.dir"), loc);
        return new Gson().fromJson(new FileReader(file), BuildModpackInfo.class);
    }

    public static class ModInfo {
        public String name;
        public String version;
        public String md5;
        public String url;
    }

}
