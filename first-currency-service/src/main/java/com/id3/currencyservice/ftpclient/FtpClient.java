package com.id3.currencyservice.ftpclient;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class FtpClient {
    @Value("${ftpclient.server}")
    private String server;
    @Value("${ftpclient.port}")
    private int port;
    @Value("${ftpclient.user}")
    private String user;
    @Value("${ftpclient.password}")
    private String password;
    private FTPClient ftp;

    public void open() throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(server,port);

        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        ftp.login(user, password);
    }

    public void close() throws IOException {
        if (ftp.isConnected()) {
            ftp.logout();
            ftp.disconnect();
        }
    }

    public abstract void uploadFile(File file, String remotePath);
    public abstract void downloadFile(String remotePath, File localFile);

    protected FTPClient getFtpClient() {
        return ftp;
    }

}
