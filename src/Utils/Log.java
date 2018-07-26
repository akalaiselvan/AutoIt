package Utils;
import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import GUI.CreateGUI;
import org.jetbrains.annotations.Contract;
import org.testng.SkipException;
import org.testng.annotations.Test;

import javax.swing.*;

public class Log {
    public static void writelog(String str) throws FileNotFoundException,NullPointerException {
        try {
            String timeLog = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime());
            File logFile = new File("Log.txt");
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            String name = new Object(){}.getClass().getEnclosingMethod().getName();
            FileWriter fw = new FileWriter(logFile,true);
            BufferedWriter writer=new BufferedWriter(fw);
            writer.newLine();
            writer.write(timeLog + "  :  " + str);
            writer.close();
            JTextArea jTextArea=CreateGUI.writelog;
            if (!(jTextArea == null)){
                CreateGUI.writelog.append(str+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void startlog(String name) throws FileNotFoundException,NullPointerException {
        try {
            String str="Started";
            String timeLog = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime());
            File logFile = new File("Log.txt");
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            FileWriter fw = new FileWriter(logFile,true);
            BufferedWriter writer=new BufferedWriter(fw);
            writer.newLine();
            writer.write(timeLog + "  :  "+name+"-"+ str);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void endlog(String name) throws FileNotFoundException,NullPointerException {
        try {
            String str="Completed";
            String timeLog = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime());
            File logFile = new File("Log.txt");
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            FileWriter fw = new FileWriter(logFile,true);
            BufferedWriter writer=new BufferedWriter(fw);
            writer.newLine();
            writer.write(timeLog + "  :  "+name+"-"+ str);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void skipTest(String methodname,String reason) throws FileNotFoundException {
        writelog(methodname+" skipped.Reason : "+reason);
        throw new SkipException("");
    }
    public static void exitTest(String reason) throws FileNotFoundException {
        writelog(" Automation Failed.Since :  "+reason);
        System.exit(0);
    }


}