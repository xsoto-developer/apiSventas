package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static GetProperties instance = null;
    private String host;

    public GetProperties() {
        Properties properties = new Properties();
        String nameFile = "sventas.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        if(inputStream != null){
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        host = properties.getProperty("host");
    }

    public static GetProperties getInstance(){
        if(instance==null)
            instance = new GetProperties();
        return instance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
