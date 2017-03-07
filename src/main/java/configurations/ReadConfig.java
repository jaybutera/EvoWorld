package configurations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ReadConfig {
    Config config;
    InputStream inputStream;

    public Config getPropValues() throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            config = new Config(
                    Integer.valueOf(prop.getProperty("food_count")),
                    Integer.valueOf(prop.getProperty("world_size")),
                    Float.valueOf(prop.getProperty("timestep"))
            );

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }

        return config;
    }
}
