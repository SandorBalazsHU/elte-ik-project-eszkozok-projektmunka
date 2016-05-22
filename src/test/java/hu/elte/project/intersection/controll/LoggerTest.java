package hu.elte.project.intersection.controll;

import static hu.elte.project.intersection.controll.Logger.stackTracePrint;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Unit teszt a hu.elte.project.intersection.controll.<b>Logger</b> osztályhoz.
 * @author  <b>Sándor Balázs</b> - <b>Bognár Dániel</b> - <b>Kiss Erik</b>
 * @version 1.0
 * @since   2016-05-04
 */
public class LoggerTest{
    
    /**
     * Konstans a tesztek <b>időtúllépésének</b> beállításához.
     */
    private final int testTimeOut = 500;
    /**
     * testLogger - Teszt 1 - <b>Létezik-e</b> a Logger osztály?
     */
    @Test(timeout=testTimeOut) public void TestLoggerClassExists()
    {
        try {
            Class.forName("hu.elte.project.intersection.controll.Logger");
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            fail("A Logger osztály nem létezik!");
        }
    }
    
    /**
     * testLogger - Teszt 2 - A writeLog metódust meghívva <b>létrejön-e a logfile</b> ha nem létezett korábban.
     */
    @Test(timeout=testTimeOut) public void testLoggerWriteLogFileExits()
    {
        Logger.writeLog("TestLog");
        
        try{
            File logFile = new File(Logger.STRING_LOG_FILE_PATH);
            assertTrue("A log file nem létezik!", logFile.exists());
        }catch(NullPointerException  e){
            System.err.println("Hiba a teszt logfile vizsgálatakor:" + e + "\n" + stackTracePrint(e));
            fail("Hiba a teszt logfile vizsgálatakor:" + e + "\n" + stackTracePrint(e));
        }
        
        deleteLogFile();
    }
    
    /**
    * testLogger - Teszt 3 - A writeLog metódus <b>felülírja-e</b> a már létező logokat?.
    */
    @Test(timeout=testTimeOut) public void testLoggerWriteLogFileOverwrite()
    {
        String testLog01 = "TestLog01";
        String testLog02 = "TestLog02";
        
        Logger.writeLog("info", testLog01);
        Logger.writeLog("info", testLog02);
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(Logger.STRING_LOG_FILE_PATH), Charset.forName("UTF-8"));
            assertEquals(4, lines.size());
        } catch (IOException e) {
            System.err.println("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
            fail("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
        }
        
        deleteLogFile();
    }

     /**
     * testLogger - Teszt 4 - A writeLog(String level, String inLog) <b>helyes</b> adatokat ír ki a logfile-ba?
     * A messages listával konfigurálhatók a tesztelendő típusok.
     */
    @Test(timeout=testTimeOut) public void testLoggerWriteLog()
    {
        String testLogText = "TestLog_";
        List<String> messages = Arrays.asList("info", "warning", "SEVERE", "WARNING", "INFO", "CONFIG", "FINE", "FINER", "FINEST");
        List<String> results = Arrays.asList("", "INFO: TestLog_0", "", "WARNING: TestLog_1", "", "SEVERE: TestLog_2", "", "WARNING: TestLog_3", "", "INFO: TestLog_4", "", "CONFIG: TestLog_5", "", "FINE: TestLog_6", "", "FINER: TestLog_7", "", "FINEST: TestLog_8");
        for (int i = 0; i < messages.size(); i++){
            Logger.writeLog(messages.get(i), testLogText+i);
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(Logger.STRING_LOG_FILE_PATH), Charset.forName("UTF-8"));
            
            assertEquals(lines.size(), 2*messages.size());
            for (int i = 1; i < messages.size(); i+=2) assertEquals(lines.get(i), results.get(i));
                
        } catch (IOException e) {
            System.err.println("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
            fail("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
        }
        
        deleteLogFile();
    }
    
     /**
     * testLogger - Teszt 5 - A writeLog(String inLog) <b>helyes</b> adatokat ír ki a logfile-ba?
     */
    @Test(timeout=testTimeOut) public void testLoggerWriteString()
    {
        String testLogText = "TestLog";
        Logger.writeLog(testLogText);
        try {
            List<String> lines = Files.readAllLines(Paths.get(Logger.STRING_LOG_FILE_PATH), Charset.forName("UTF-8"));
            assertEquals(lines.get(1), "INFO: "+testLogText);     
        } catch (IOException e) {
            System.err.println("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
            fail("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
        }
        
        deleteLogFile();
    }
    
     /**
     * testLogger - Teszt 6 - A writeLog(Exception inLog) <b>helyes</b> adatokat ír ki a logfile-ba?
     */
    @Test(timeout=testTimeOut) public void testLoggerWriteException()
    {
        try{
            throw new NullPointerException();
        }catch(NullPointerException e){
            Logger.writeLog(e);
        }
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(Logger.STRING_LOG_FILE_PATH), Charset.forName("UTF-8"));
            assertEquals(lines.get(1), "WARNING: 	java.lang.NullPointerException");
        } catch (IOException e) {
            System.err.println("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
            fail("Hiba a teszt logfile olvasásakor:" + e + "\n" + stackTracePrint(e));
        }
        
        deleteLogFile();
    }

    /**
     * <b>Törli a logfilet.</b> Azért szügséges, hogy a tesztek <b>függetlenek</b> maradhassanak egymástól.
     */
    private void deleteLogFile() {
        try{
        File logFile = new File(Logger.STRING_LOG_FILE_PATH);
        logFile.delete();
        } catch(SecurityException | NullPointerException e){
            System.err.println("Hiba a teszt logfile törtlésekor:" + e + "\n" + stackTracePrint(e));
        }
    }
}
