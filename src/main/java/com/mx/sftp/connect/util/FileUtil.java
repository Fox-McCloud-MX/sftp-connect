package com.mx.sftp.connect.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class FileUtil {

    public static void save(
        FileObject file,
        String tempDirectory,
        String fileName,
        String encoding
    ) throws IOException {

        Path dir = Paths.get(tempDirectory);

        if (!new File(tempDirectory + fileName).exists()) {
            Files.createFile(dir.resolve(fileName));
        }

        String myString = IOUtils.toString(
            file.getContent().getInputStream(),
            encoding
        );

        Files.write(
            dir.resolve(fileName),
            myString.getBytes(),
            StandardOpenOption.TRUNCATE_EXISTING
        );

        log.info("File created at {}", dir.resolve(fileName));
    }
}
