package com.example.demo.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wx")
public class WXConfig {

    private String appid;
    private String appsecret;
    private String accessTokenUrl;
    private String oauthUrl;
    private String ticketUrl;


}
