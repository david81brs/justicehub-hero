package com.pegasus.justicehub;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class Properties {

    JusticeAppProps justiceAppProps;

    public static class JusticeAppProps {
        String username;
        String password;
        String url;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public JusticeAppProps getJusticeAppProps() {
        return justiceAppProps;
    }

    public void setJusticeAppProps(JusticeAppProps justiceAppProps) {
        this.justiceAppProps = justiceAppProps;
    }
}
