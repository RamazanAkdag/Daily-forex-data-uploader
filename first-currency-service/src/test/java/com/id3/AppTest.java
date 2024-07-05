package com.id3;

import com.github.drapostolos.rdp4j.DirectoryPoller;
import com.github.drapostolos.rdp4j.spi.PolledDirectory;
import com.id3.currencyservice.ftpclient.FtpClientImpl;
import com.id3.currencyservice.rdp_listener.FtpDirectory;
import com.id3.currencyservice.rdp_listener.FtpListener;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

   /* @org.junit.Test
    public void test() throws InterruptedException {

        PolledDirectory polledDirectory = new FtpDirectory();

        DirectoryPoller dp = DirectoryPoller.newBuilder()
                .addPolledDirectory(polledDirectory)
                .addListener(new FtpListener())
                .enableFileAddedEventsForInitialContent()
                .setPollingInterval(10, TimeUnit.SECONDS)
                .start();

        TimeUnit.HOURS.sleep(2);

        dp.stop();
    }*/

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
