package com.xing.bikeinventory.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("users")
public class UserProperties {
    private List<UserDetailsProperties> list;
}
