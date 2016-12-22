package online.pizzacrust.solder;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ImplInfo {

    @SerializedName("api")
    private final String implName;

    @SerializedName("version")
    private final String implVersion;

    @SerializedName("stream")
    private final String branch;

    public ImplInfo(String implName, String implVersion, String branch) {
        this.implName = implName;
        this.implVersion = implVersion;
        this.branch = branch;
    }

    public ImplInfo(String implName, String implVersion, Branch branch) {
        this.implName = implName;
        this.implVersion = implVersion;
        this.branch = branch.toString();
    }

    public static final String IMPLEMENTATION_NAME = "SolderJava";
    public static final String IMPLEMENTATION_VERSION = "0.1";
    public static final Branch BRANCH = Branch.DEV;

    public ImplInfo() {
        this(IMPLEMENTATION_NAME, IMPLEMENTATION_VERSION, BRANCH);
    }

    public enum Branch {
        BETA("beta"),
        DEV("DEV");

        private final String stringRep;

        Branch(String stringRep) {
            this.stringRep = stringRep;
        }

        @Override
        public String toString() {
            return this.stringRep;
        }

    }

    public static class SerialTest {
        public static void main(String... args) {
            System.out.println(new Gson().toJson(new ImplInfo()));
        }
    }

}
