package com.mx.sftp.connect;

import com.mx.sftp.connect.client.SFTPClient;
import com.mx.sftp.connect.config.SFTPConfig;
import com.mx.sftp.connect.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
public class SFTPTest {
    
    @Autowired
    SFTPClient sFTPClient;
    
    @Autowired
    SFTPConfig sFTPConfig;
    
    @Test
    public void downloadSFTP() throws IllegalArgumentException, IOException, URISyntaxException {
        
        String date = LocalDate.now().toString();
        
        String fileName = sFTPConfig.getTempDirectory()
                    .concat(sFTPConfig.getFile())
                    .concat("_")
                    .concat(date)
                    .concat(sFTPConfig.getFileExtension());
        
        FileUtil.save(
            sFTPClient.download(),
            sFTPConfig.getTempDirectory(),
            fileName,
            sFTPConfig.getEncoding()
        );
        
        File file = new File(fileName);
        
        log.info("File saved...");
    }
    
}
