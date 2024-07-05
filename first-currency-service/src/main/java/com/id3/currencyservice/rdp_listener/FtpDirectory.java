package com.id3.currencyservice.rdp_listener;

import com.github.drapostolos.rdp4j.spi.FileElement;
import com.github.drapostolos.rdp4j.spi.PolledDirectory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FtpDirectory implements PolledDirectory {
    @Value("${ftpclient.server}")
    private String host;
    @Value("${ftpclient.workdir}")
    private String workingDirectory;
    @Value("${ftpclient.user}")
    private String username;
    @Value("${ftpclient.password}")
    private String password;


    public Set<FileElement> listFiles() throws IOException {
        FTPClient ftp = null;
        try{
            ftp = new FTPClient();
            ftp.connect(host);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                throw new IOException("Exception when connecting to FTP Server: " + ftp);
            }
            ftp.login(username, password);
            ftp.changeWorkingDirectory(workingDirectory);

            Set<FileElement> result = new LinkedHashSet<FileElement>();
            for(FTPFile file : ftp.listFiles(workingDirectory)){
                result.add(new FtpFile(file));
            }
            return result;
        } catch (Exception e){
            throw new IOException(e);
        } finally {
            try {
                if(ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch(Throwable t) {
                // do nothing
            }
        }
    }
}