package com.mx.sftp.connect.client;

import com.mx.sftp.connect.config.SFTPConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class SFTPClient {
    
    @Autowired
    SFTPConfig sFTPConfig;
    
    public FileObject download() throws FileSystemException, URISyntaxException {

        FileSystemOptions opts = new FileSystemOptions();
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
        //SftpFileSystemConfigBuilder.getInstance().setPreferredAuthentications(opts, "publickey,keyboard-interactive,password");
        FileSystemManager fileSystemManager = VFS.getManager();

        String userInfo = sFTPConfig.getUser()
                .concat(":")
                .concat(sFTPConfig.getPassword());

        String filePath = sFTPConfig.getDirectory()
                .concat(sFTPConfig.getFile())
                .concat(sFTPConfig.getFileExtension());

        URI sftpURI = new URI(
            sFTPConfig.getProtocol(),
            userInfo,
            sFTPConfig.getServer(),
            sFTPConfig.getPort(),
            filePath,
            null,
            null
        );

        log.info("Try to get file from:\n{}", sftpURI.toString());

        try (FileObject fo = fileSystemManager.resolveFile(sftpURI.toString(), opts)) {
            log.info("EXIST: {}", fo.exists());

            return fo;
        }
    }
    
    
}
