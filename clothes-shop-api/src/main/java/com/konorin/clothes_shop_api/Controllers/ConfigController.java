package com.konorin.clothes_shop_api.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
@RestController
@RefreshScope
public class ConfigController {
    @Value("${message:dark}")
    private String setting;

    @GetMapping("/config")
    public String setting(){
        return setting;
    }
}