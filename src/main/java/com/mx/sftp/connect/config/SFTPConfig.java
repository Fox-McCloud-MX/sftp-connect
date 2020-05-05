package com.mx.sftp.connect.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SFTPConfig {

    @Value("${sftp.server}")
    private String server;

    @Value("${sftp.port}")
    private int port;

    @Value("${sftp.user}")
    private String user;

    @Value("${sftp.password}")
    private String password;

    @Value("${sftp.directory}")
    private String directory;
    
    @Value("${sftp.file}")
    private String file;
    
    @Value("${sftp.protocol}")
    private String protocol;

    @Value("${sftp.file.extension}")
    private String fileExtension;
    
    @Value("${sftp.temp.directory}")
    private String tempDirectory;

    @Value("${sftp.encoding}")
    private String encoding;
}
