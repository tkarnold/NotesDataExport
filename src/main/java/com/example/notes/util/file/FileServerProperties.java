package com.example.notes.util.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="fileserver")
@Data
public class FileServerProperties {
    private String serverPath;
}
