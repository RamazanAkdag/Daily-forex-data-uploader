package com.id3.currencyservice.rdp_listener;

import com.github.drapostolos.rdp4j.spi.FileElement;
import org.apache.commons.net.ftp.FTPFile;

public class FtpFile implements FileElement {
    private final FTPFile file;
    private final String name;
    private final boolean isDirectory;

    public FtpFile(FTPFile file) {
        this.file = file;
        this.name = file.getName();
        this.isDirectory = file.isDirectory();
    }

    @Override
    public long lastModified() {
        return file.getTimestamp().getTimeInMillis();
    }

    @Override
    public boolean isDirectory() {
        return isDirectory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
