package test;

import Masters.MasterAttributes;
import RPOS7kit.PreCheckValidations;
import Utils.Log;
import autoitx4java.AutoItX;
import com.sun.jna.platform.win32.W32Service;
import com.sun.jna.platform.win32.W32ServiceManager;
import com.sun.jna.platform.win32.Winsvc;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import javax.swing.*;

public class Functions {
    private JLabel jLabel;
    public static AutoItX AI = new AutoItX();
    KeyStrokes key = new KeyStrokes();


    public void openApp(String dtl) throws InterruptedException {
        AI.run(dtl);
    }

    public void openPos() throws InterruptedException {
        File Dpath = new File(AppDetails.POS_PATH);
        File Epath = new File(AppDetails.POS_PATH_E);
        if (Dpath.exists()) {
            AI.run(String.valueOf(Dpath));
        } else if (Epath.exists()) {
            AI.run(String.valueOf(Epath));
        }
    }

    public void click(String title, String dtl) {
        AI.controlClick(title, "", dtl);
    }
    public void clicks(String title, String dtl,int clicks) {
        AI.controlClick(title, "", dtl,"",clicks);
    }
    public void clickoN(String title,String text, String dtl,int clicks) {
        AI.controlClick(title, text, dtl,"",clicks);
    }

    private void doubleClick(String title, String dtl) {
        AI.controlClick(title, "", dtl, "left", 2);
    }

    public void checkAndClick(String title, String control, int click) throws FileNotFoundException {
        boolean isvisible;
        isvisible = AI.controlCommandIsVisible(title, "", control);
        if (isvisible) {
            Log.writelog(control + "Control found");
            if (click == 2) {
                doubleClick(title, control);
            } else if (click == 1) {
                click(title, control);
            }
        } else {
            Log.writelog(control + "Control not found");
        }
    }

    public boolean checkFor(String title) {
        boolean isvisible;
        isvisible = AI.controlCommandIsVisible(title, "", "");
        return isvisible;
    }

    public boolean controlCheck(String title, String control) throws FileNotFoundException {
        boolean isvisible;
        isvisible = AI.controlCommandIsVisible(title, "", control);
        if (isvisible){
            Log.writelog("Control"+control+" found");
        }else{Log.writelog("Control"+control+"not Found");}
        return isvisible;
    }

    public boolean checkForText(String title, String text) {
        boolean isvisible;
        isvisible = AI.controlCommandIsVisible(title, text, "");
        return isvisible;
    }

    public boolean checkWindow(String title, String text ) {
        boolean isvisible;
        isvisible = AI.winExists(title)||AI.winExists(text);
        return isvisible;
    }

    public void killProcess(String process) throws FileNotFoundException {
        int pckeck = AI.processExists(process);
        if (pckeck != 0) {
            Log.writelog(process + " found");
            AI.processClose(process);
            key.waitw(1000);
            int pckeck1 = AI.processExists(process);
            if (pckeck1 == 0) {
                Log.writelog(process + " killed");
            }
        } else {
            Log.writelog(process + " not found");
        }
    }

    public boolean waitProcessToStop(String process, int minutes) throws FileNotFoundException, InterruptedException {
        boolean processResult = false;
        int pckeck = 0;
        key.waitw(5000);
        for (int i = 0; i <= minutes; i++) {
            pckeck = AI.processExists(process);
            processResult=AI.processWaitClose(process,minutes);
            if (!processResult){Log.writelog("still exist");}
            if (pckeck == 0) {
                Log.writelog(process + "completed in" + minutes + "minutes");
                processResult = true;
                i = minutes;
            }
        }
        pckeck = AI.processExists(process);
        if (pckeck != 0) {
            Log.writelog("Process not stopped in" + minutes + "minutes");
        }
        return processResult;
    }
    @Test


    public void focusOn(String title, String dtl) {
        AI.controlFocus(title, "", dtl);
    }

    public void waitFor(String title, int milli_sec) {
        AI.winWait(title, "", milli_sec);
    }

    public void waitandcheck(String title, int milli_sec) throws FileNotFoundException {
        boolean scheck = false;
        AI.winWait(title, "", milli_sec);
        scheck = checkFor(title);
        if (scheck == true) {
            Log.writelog(title + "opened");
        } else {
            Log.writelog(title + "not found");
        }
    }

    public void waitForScreenActive(String title, String text, int millisec) {
        AI.winWaitActive(title, text, millisec);
    }

    public void waitForScreen(String title, int milli_sec) throws FileNotFoundException {
        AI.winWait(title, "", milli_sec);
        if (AI.winExists(title)) {
            Log.writelog(title + "screen Found");
        } else {
            Log.writelog(title + " screen not found");
        }
    }

    public void waitUntil(String title,int timeout,boolean mandatory) throws FileNotFoundException {
        int count=0;
        boolean scheck = false;
        while (!scheck) {
            count++;
            scheck = checkForText(title, "");
            waitForText(title, "", 60);
            Log.writelog("Waiting for" + title);
            if (count>=timeout){
                Log.writelog(title+" not found after "+timeout+"minutes");
                scheck=true;
                if (mandatory){
                    exitAutomation(title+" not found");
                }
            }
        }
        if (AI.winExists(title)) {
            Log.writelog(title + "Found");
        } else {
            Log.writelog(title + "not found");
        }
    }

    public void waituntil(String title) throws FileNotFoundException {
        boolean scheck = false;
        while (scheck == false) {
            scheck = checkForText(title, "");
            waitForText(title, "", 5);
        }
        if (AI.winExists(title)) {
            Log.writelog(title + "Found");
        } else {
            Log.writelog(title + "not found");
        }
    }

    public void openABout() throws FileNotFoundException {
        AI.send("{ALTDOWN}{h}{b}{ALTUP}", false);
        key.waitw(2000);
        boolean checkAbout = checkFor(AppDetails.ABOUT_POS);
        if (!checkAbout){
            Log.writelog("Shortcut key not worked to open screen");
            openScreen("About");
        }
    }


    public void waitForText(String title, String text, int millisec) throws FileNotFoundException {
        AI.winWait(title, text, millisec);
        if (AI.winExists(title)||AI.winExists(text)) {
            Log.writelog(title + text + " screen Found");
        } else {
            Log.writelog(title + text + " screen not found");
        }

    }

    public void activateWindow(String title) {
        AI.winActivate("" + title);
    }

    public void openScreen(String title) {

        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        String SCREEN = AppDetails.PROD_TITLE;
        key.typeIn(SCREEN,KeyStrokes.ALTg);key.waitw(500);
        key.typeInLOV(title + "{ENTER}{ENTER}");
    }

    public void minimizeAll() {
        AI.winMinimizeAll();
    }


    public void selectStdItm(String name, int qty) {
        key.pressEnter();
        key.type(name);
        key.pressEnter();
        key.type("" + qty);
    }

    public void pickStdItm(String name, int qty) {
        selectStdItm(name, qty);
        key.pressEnter();
    }

    public void pickSrlItm(String name, int qty) {
        key.pressEnter();
        key.type(name);
        key.pressEnter();
        waitFor("Serial Numbers", 5);
        focusOn("Serial Numbers", "");
        key.pressTabFor(3);
        key.type("" + qty);
        key.pressEnter();
    }

    public void pickMatrixItm(String name, int qty) {
        selectStdItm(name, qty);
        key.pressEnter();
    }

    public void pickPremiumCardItem(String brand, String classification, int qty) {
        key.pressEnter();
        key.type(MasterAttributes.ITEM_PREMIUM_CARDS);
        key.pressEnter();
        key.type(brand);
        key.pressTab();
        key.type(classification);
        key.pressEnter();
        key.type("" + qty);
        key.pressEnter();
    }

    public void selectSrlItm(String name, int qty, String serialno) {
        key.pressEnter();
        key.type(name);
        key.pressEnter();
        waitFor("Serial Numbers", 5);
        AI.controlClick("Serial Numbers", "", "ThunderRT6CheckBox3");
        key.pressTabFor(3);
        key.type("" + qty);
        key.pressTab();
        key.type(serialno);
        key.pressTab();
        key.pressEnter();
        key.pressTabFor(2);
        key.pressEnter();
    }

    public void selectMatrixItem(String item, int qty) {
        key.pressEnter();
        key.type(item);
        key.pressEnter();
        key.pressEnter();
        key.type("" + qty);
        key.wait(1000);
        key.keyCombination("{ALTDOWN}{o}{ALTUP}");
    }

    public void openScreenByShortcut(String screenshortcut) {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send(screenshortcut, false);
    }

    public void openScreens(String[] str) throws FileNotFoundException {
        minimizeAll();
        activateWindow(AppDetails.PROD_TITLE);
        String SCREEN = setScreen(str[2]);
        openScreenByShortcut(str[0]);
        waitForScreen(SCREEN, 5);
        boolean checkAbout = checkFor(SCREEN);
        if (!checkAbout) {
            Log.writelog("Shortcut key not worked to open "+str[2]+" , trying open screen function");
            openScreen(str[1]);
            checkAbout = checkFor(SCREEN);
            if (checkAbout) {
                Log.writelog("Purchase screen found");
            } else {
                Log.writelog("Purchase Scren not all found ... ");
            }
        }
    }

    public void openPurchaseScreen() {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send("{CTRLDOWN}{p}{CTRLUP}", false);
    }

    public void openBarcodePrint() {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send("{CTRLDOWN}{b}{CTRLUP}", false);
    }

    public void openSalesReturn() {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send("{CTRLDOWN}{n}{CTRLUP}", false);
    }

    public void openReprint() {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send("{CTRLDOWN}{t}{CTRLUP}", false);
    }

    public void openReceiptNote() {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send("{CTRLDOWN}{r}{CTRLUP}", false);
    }

    public void fillPurchaseDetails(String Distname, String Invno, double Invamt) {
        key.pressEnterFor(2);
        key.typeInLOV(Distname);
        key.pressEnter();
        key.pressBack();
        key.type(Invno);
        key.pressEnterFor(2);
        key.pressBack();
        key.type("" + Invamt);
        key.pressEnter();
    }

    public void purchaseFromRN(String distname, int invNo) {
        key.wait(2000);
        key.down();
        key.pressEnterFor(2);
        key.type(distname);
        key.pressEnterFor(2);
        key.wait(1000);
        if (AI.winExists("Form1")) {
            key.pressEnter();
            System.out.println("It enters");
        }
        key.pressBack();
        key.type("" + invNo);
        key.pressEnterFor(3);
    }

    public void fillPsm(double purrate, double selling, double mrp) {
        key.pressEnter();
        key.type("" + purrate);
        key.pressEnter();
        key.type("" + selling);
        key.pressEnter();
        key.type("" + mrp);
        key.pressEnter();
    }

    public void tranSave() {
        key.f6();
        waitFor(AppDetails.CONFIRMATION_MSG, 1);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressEnter();
        waitFor(AppDetails.CONFIRMATION_MSG, 1);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressEnter();
        key.waitw(5000);
        boolean scheck = AI.winExists(AppDetails.CONFIRMATION_MSG);
        if (scheck) {
            key.pressEnter();
        }
        key.waitw(5000);
        if (scheck) {
            key.wait(1000);
            key.pressEnter();
        }
    }

    public void tranSaveGnric(String trans) throws FileNotFoundException {
        key.f6();
        if (trans.equals(AppDetails.CHANGE_SELLING)) {
            key.left();
        }
        confirm_YES();
        if (trans.equals(AppDetails.UPDATE_ZERO)) {
            key.waitw(1000);
            confirm_YES();
        }
        key.waitw(1000);
        Log.writelog("No");
        waitFor(AppDetails.CONFIRMATION_MSG, 1);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressEnter();
        key.waitw(2000);
    }

    public void rnSave() {
        key.f6();
        waitFor(AppDetails.CONFIRMATION_MSG, 5000);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressEnter();

        waitFor(AppDetails.CONFIRMATION_MSG, 10000);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressEnter();
    }

    public void setFocusForSrlItm() {
        key.f2();
        key.wait(1000);
        key.pressEnterFor(2);
        key.wait(1000);
        key.pressRightFor(2);
    }

    public void selectMatrixItemPremiumcards(int qty, double purrate, double selling, double mrp) {
        key.pressEnter();
        key.type(MasterAttributes.ITEM_PREMIUM_CARDS);
        key.pressEnter();
        key.pressEnter();
        key.space();
        key.down();
        key.space();
        key.pressEnterFor(2);
        key.type("" + qty);
        fillPsm(purrate, selling, mrp);
        key.pressEnter();
        key.space();
        key.down();
        key.space();
        key.pressEnter();
        key.down();
        key.pressEnter();
        key.type("" + qty);
        fillPsm(purrate, selling, mrp);
        key.wait(1000);
        key.keyCombination("{ALTDOWN}{o}{ALTUP}");
    }

    public void openSalesScreen() {
        AI.controlFocus("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.controlClick("", "[TITLE:" + AppDetails.PROD_TITLE + ";CLASS:ThunderRT6MDIForm]", "ThunderRT6PictureBoxDC8");
        AI.send("{CTRLDOWN}{s}{CTRLUP}", false);
    }

    public void selectCustomer(String cname) {
        key.wait(1000);
        key.f11();
        key.pressEnter();
        key.typeInLOV(cname);
        key.pressEnter();
    }

    public void selectCustomerByMobileNo(String mobilenum) {
        key.wait(1000);
        key.f11();
        key.type(mobilenum);
        key.pressEnter();
    }

    public void saveBillAs(String mode) {
        key.esc();
        key.wait(1000);
        if (mode.equals(MasterAttributes.MODE_CASH)) {
            key.pressEnter();
        }
        key.left();
        key.pressEnter();
    }

    public void close_screen() {
        key.esc();
    }

    public String setScreen(String str) {
        String SCREEN = AppDetails.PROD_TITLE + " " + "-" + " " + "[" + str + "]";
        return SCREEN;
    }

    public void pickDistributor(String dist) {
        key.pressEnter();
        key.typeInLOV(dist);
        key.typeLOV(KeyStrokes.ENTER);
    }

    public void focusFilter() throws FileNotFoundException {
        boolean fCheck;
        AI.winWait("Filter", "", 5);
        fCheck = AI.controlFocus("Filter", "", "ThunderRT6FormDC");
        if (fCheck) {
            Log.writelog("filter found");
        } else {
            Log.writelog("filer screen not found");
        }
    }

    public void fillExpiryDetails(String batch, String expiry) {
        key.type(batch);
        key.typeLOV(KeyStrokes.TAB);
        key.type(expiry);
        key.typeLOV(KeyStrokes.ENTER);
    }

    public void selectFromLOV(String str) {
        key.pressEnter();
        key.typeInLOV(str);
        key.pressEnter();
    }

    public void copyText(String str) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(str);
        clipboard.setContents(selection, null);
//            Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
//            DataFlavor flavor = DataFlavor.stringFlavor;
//            if (clipboard.isDataFlavorAvailable(flavor)) {
//                try {
//                    String text = (String) clipboard.getData(flavor);
//                    System.out.println(text);
//                } catch (UnsupportedFlavorException e) {
//                    System.out.println(e);
//                }
//            }

    }

    public static void startService(String servicename) {
        W32ServiceManager serviceManager = new W32ServiceManager();
        serviceManager.open(Winsvc.SC_MANAGER_ALL_ACCESS);
        W32Service service = serviceManager.openService(servicename, Winsvc.SC_MANAGER_ALL_ACCESS);
        service.startService();
        service.close();
    }

    public static void stopService(String servicename) {
        W32ServiceManager serviceManager = new W32ServiceManager();
        serviceManager.open(Winsvc.SC_MANAGER_ALL_ACCESS);
        W32Service service = serviceManager.openService(servicename, Winsvc.SC_MANAGER_ALL_ACCESS);
        service.stopService();
        service.close();
    }

    public static void writeToInstallData(String str) throws FileNotFoundException, NullPointerException {
        try {
            File logFile = new File(AppDetails.INSTALL_DATA_PATH);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileWriter fw = new FileWriter(logFile, true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.newLine();
            writer.write(str);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToAutomationData(String str) throws FileNotFoundException, NullPointerException {
        try {
            File logFile = new File(AppDetails.AUTOMATION_DATA_PATH);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileWriter fw = new FileWriter(logFile, true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(str);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void waitAndFocuson(String window, int waitTime) throws FileNotFoundException {
        waitandcheck(window, waitTime);
        focusOn(window, "");
        key.waitw(waitTime);
    }

    public void waitAndFocusonControl(String window, String control, int waitTime) throws FileNotFoundException {
        waitandcheck(window, waitTime);
        focusOn(window, control);
        key.waitw(waitTime);
    }

    @Test
    public void refreshApp() throws FileNotFoundException, InterruptedException {
        Log.writelog("Refreshing Application");
        killProcess(AppDetails.POS_EXE);
        Log.writelog("App killed");
        key.waitw(2000);
        try {
            openPos();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitAndFocusonControl(AppDetails.LOGIN_SCREEN, ControlDetails.LOGIN_PWBOX, 10);
        click(AppDetails.LOGIN_SCREEN, ControlDetails.LOGIN_PWBOX);
        key.typeIn(AppDetails.LOGIN_SCREEN, "admin");
        key.pressEnter();
        waitAndFocuson(AppDetails.CONFIRMATION_MSG, 5000);
        key.pressEnter();
        key.waitw(5000);
        boolean msgchk = checkFor(AppDetails.CONFIRMATION_MSG);
        if (msgchk) {
            Log.writelog("backup msg appears");
            key.typeIn(AppDetails.CONFIRMATION_MSG, KeyStrokes.ENTER);
        }
        key.waitw(5000);
        boolean producttour=checkFor(AppDetails.NEW_MSGBOX);
        if (producttour){
            click(AppDetails.NEW_MSGBOX,ControlDetails.NEWMSG_BOX_OK);
        }
        boolean msgchk1 = checkFor(AppDetails.CONFIRMATION_MSG);
        if (msgchk1) {
            key.pressEnter();
            key.altY();
        }
        key.waitw(5000);
        killProcess(AppDetails.BACKUP_PROCESS);
        minimizeAll();
        activateWindow(AppDetails.PROD_TITLE);
        waitAndFocuson(AppDetails.PROD_TITLE, 5000);
    }

    public void exitAutomation(String reason) throws FileNotFoundException {
        showWarning("Automation failed. Reason : "+reason);
        Log.writelog("Automation failed .Reason :  " + reason);
        //System.exit(0);
    }

    public void skipMethod(String methodname, String reason) throws FileNotFoundException {
        Log.writelog(methodname + " skipped.Reason : " + reason);
    }

    public void confirm_YES() throws FileNotFoundException {
        waitFor(AppDetails.CONFIRMATION_MSG, 1);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        Log.writelog("yes");
        try {
            checkAndClick("", ControlDetails.CONFIRMATION_MSG_BOX_YES, 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void confirmsmall_YES() throws FileNotFoundException {
        waitFor(AppDetails.CONFIRMATION_MSG, 1);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        Log.writelog("yes");
        try {
            checkAndClick("", ControlDetails.CONFIRMATION_MSG_SMALLBOX_YES, 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void confirm_OK() throws FileNotFoundException {
        waitFor(AppDetails.CONFIRMATION_MSG, 1);
        focusOn(AppDetails.CONFIRMATION_MSG, "");
        Log.writelog("OK - Confirmation ");
        try {
            checkAndClick("", ControlDetails.CONFIRMATION_MSG_BOX_YES, 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newMsgBoxOk() throws FileNotFoundException {
        waitFor(AppDetails.NEW_MSGBOX, 1);
        focusOn(AppDetails.NEW_MSGBOX, "");
        Log.writelog("yes");
        try {
            checkAndClick(AppDetails.NEW_MSGBOX, ControlDetails.NEWMSG_BOX_OK, 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void downloadFromLink(String url, String pathwithname) throws IOException {
        File pathToSave = new File(pathwithname);
        try {
            URL link = new URL(url);
            if (pathToSave.exists()){
                pathToSave.delete();Log.writelog(pathwithname+" Found and Deleted");
            }
            FileUtils.copyURLToFile(link, pathToSave);
            if (pathToSave.exists()) {
                Log.writelog(pathToSave + " Download completed");
            } else {
                Log.writelog(pathToSave + " Download not completed");
            }
        } catch (Exception e) {
            Log.writelog("" + e);
            PreCheckValidations.setCheckstatus("fail");
            exitAutomation(pathToSave + " Download failed");
        }

    }



    public String checkWebReporterInstallStatus(int minutes) throws IOException {
        key.waitw(5000);
        int cnt = 0;
        String WR_INSTALLSTATUS = "";
        File D = new File("D:\\GoFrugal\\GoFrugalRPOS7\\WebReporter\\wr_installation_status.ini");
        File E = new File("E:\\GoFrugal\\GoFrugalRPOS7\\WebReporter\\wr_installation_status.ini");
        boolean chkStatus = false;
        while (!chkStatus) {
            cnt++;
            Log.writelog("Waiting for Wr Installation in minutes : " + cnt);
           // method works based on this wait statement , dont edit / remove this
            key.waitw(60000);

            if (D.exists() || E.exists()) {
                if (D.exists()) {
                    if ((Files.readAllLines(Paths.get(D.toString())).get(1).equals(AppDetails.WR_INSTALLATION_SUCCESS))
                            || Files.readAllLines(Paths.get(D.toString())).get(1).equals(AppDetails.WR_INSTALLATION_FAILED)) {
                        WR_INSTALLSTATUS = Files.readAllLines(Paths.get(D.toString())).get(1);
                        chkStatus = true;
                        Log.writelog("Web reporter Installation Status : " + WR_INSTALLSTATUS);
                    }
                }
                if (E.exists()) {
                    if (Files.readAllLines(Paths.get(E.toString())).get(1).equals(AppDetails.WR_INSTALLATION_SUCCESS)
                            || Files.readAllLines(Paths.get(E.toString())).get(1).equals(AppDetails.WR_INSTALLATION_FAILED)) {
                        WR_INSTALLSTATUS = Files.readAllLines(Paths.get(E.toString())).get(1);
                        chkStatus = true;
                        Log.writelog("Web reporter Installation Status : " + WR_INSTALLSTATUS);
                    }
                }
            }
            if (cnt >= minutes) {
                chkStatus = true;
                Log.writelog("Web reporter installation taking more that "+cnt+"minutes Timeout reached");
            }
        }
        return WR_INSTALLSTATUS;

    }

    @Test
    public void runInCMD(String cmmd) throws IOException, InterruptedException {
        key.mouseClick("left",25,750);
        key.type("cmd");
        key.pressEnter();
        waitUntil(AppDetails.CMMD,5,false);
        key.waitw(1000);
        key.typeIn(AppDetails.CMMD,cmmd);
        key.typeIn(AppDetails.CMMD,KeyStrokes.ENTER);
    }

    @Test
    public void runCMD(String cmd) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", cmd);
        Process process = pb.start();
        OutputStream cmdOut=process.getOutputStream();
        String out =cmdOut.toString();
        Log.writelog(cmd+"executed in cmd");

    }

    @Test
    public void runCsMD() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "net stop GoFrugalRPOS7WRP & sc start GoFrugalRPOS7WRP");
        Process process = pb.start();
        OutputStream cmdOut=process.getOutputStream();
        String out =cmdOut.toString();
        Log.writelog("executed in cmd");

    }
    public void copyFile(String from,String to) throws IOException {
        File copyFrom =new File(from);
        File copyto =new File(to);
        Files.copy(copyFrom.toPath(),copyto.toPath());
    }

    public double checkFreeSpaceIn(String drive) throws FileNotFoundException {
        File file =new File(drive+":");
        double calcSize=file.getFreeSpace();
        double calcSize1=file.getFreeSpace() / 1000000000;
        Log.writelog("Free space available in "+drive+": "+calcSize +"GB , Rounded space : "+calcSize1);
        return calcSize1;
    }

    @Test
    public JFrame showLabel(String text ){
        JFrame frame = new JFrame("");
        JLabel label = new JLabel(text,SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        frame.setSize(500,100);
        frame.setTitle("Connect Plus Automation");
        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }




    public JOptionPane showWarning(String content){
        JOptionPane msgbox = new JOptionPane( content,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = msgbox.createDialog("Connect Plus Automation");
        dialog.setAlwaysOnTop(true); // to show top of all other application
        dialog.setVisible(true);
        return msgbox;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }
}

