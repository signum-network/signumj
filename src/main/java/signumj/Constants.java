package signumj;

public class Constants {
    /**
     * The signumj version, e.g. "v1.4.1". Taken from the jar manifest, which the build fills from
     * -Pversion (the release tag on JitPack and in release.yml); "dev" when not running from a release jar.
     */
    public static final String VERSION = versionFromManifest();

    public static final String HTTP_NODE_EUROPE = "https://europe.signum.network";
    public static final String HTTP_NODE_EUROPE1 = "https://europe1.signum.network";
    public static final String HTTP_NODE_EUROPE2 = "https://europe2.signum.network";
    public static final String HTTP_NODE_CANADA = "https://canada.signum.network";
    public static final String HTTP_NODE_BRAZIL = "https://brazil.signum.network";
    public static final String HTTP_NODE_LOCAL = "http://localhost:8125";
    public static final String HTTP_NODE_LOCAL_TESTNET = "http://localhost:6876";

    public static final String HTTP_NODE_TESTNET = "https://europe3.testnet.signum.network";

	public static final int HTTP_REQUEST_TIMEOUT_SECS = 30;

    private static String versionFromManifest() {
        Package pkg = Constants.class.getPackage();
        String version = pkg == null ? null : pkg.getImplementationVersion();
        if (version == null || version.isEmpty() || "unspecified".equals(version)) {
            return "dev";
        }
        return version;
    }
}
