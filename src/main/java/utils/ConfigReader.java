package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads test configuration from config.properties (on classpath).
 * Use ConfigReader.get("key") or the typed helpers below.
 */
public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found on classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    private ConfigReader() { /* utility class */ }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) throw new RuntimeException("Missing property: " + key);
        return value.trim();
    }

    public static String getBaseUrl()       { return get("app.url"); }
    public static String getEmail()         { return get("test.email"); }
    public static String getPassword()      { return get("test.password"); }
    public static int    getImplicitWait()  { return Integer.parseInt(get("implicit.wait")); }
    public static int    getPageLoadTimeout(){ return Integer.parseInt(get("page.load.timeout")); }
    public static int    getExplicitWait()  { return Integer.parseInt(get("explicit.wait")); }
}
