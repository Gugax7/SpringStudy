package com.ggx.dev;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SettingsLoaderService {
    private Map<String, String> settings = new HashMap<>();

    @PostConstruct
    public void loadSettings(){
        settings.put("ProgramName", "PathWay");
        settings.put("version", "1.0.0");
        settings.put("Author", "Gustavo Salmazo");

        System.out.println("Settings have been loaded");
    }

    public Map<String, String> getSettings() {
        return settings;
    }
}
