package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyManager {

    private static final Properties PROPERTIES;

    static {
        Path path = Paths.get("resource", "application.properties");
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileReader(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
}
