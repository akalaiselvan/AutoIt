package AutoPatch;

import Transactions.PurchaseEntry;
import Transactions.ReceptNote;
import Utils.ConnectDB;
import Utils.Log;
import org.apache.commons.io.FileUtils;
import test.AppDetails;
import test.ControlDetails;
import test.Functions;
import test.KeyStrokes;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WebInstallerHelper {
    private static CloudPatchHelper cloudPatchHelper = new CloudPatchHelper();
    private static KeyStrokes key = new KeyStrokes();
    private static Functions functions = new Functions();
    private static PurchaseEntry purchase = new PurchaseEntry();
    private static ReceptNote receiptNote = new ReceptNote();
    private static ConnectDB connectDB = new ConnectDB();
    private static String PTYPE = "LABTEST";


    public static void startWebInstaller() throws IOException, InterruptedException {
        String ID = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        String num = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(1);
        //Install product with Web installer
        File downloadPath = new File(AppDetails.DOWNLOAD_PATH);
        if (downloadPath.exists()) {
            try {
                FileUtils.deleteDirectory(new File(AppDetails.DOWNLOAD_PATH));
                Log.writelog(downloadPath + " found and deleted");
            } catch (Exception e) {
                Log.writelog("" + e);
            }
        }
        cloudPatchHelper.downloadRequiredFiles();
        functions.openApp(AppDetails.WI_PATH);
        functions.minimizeAll();
        Log.startlog("WI started");
        functions.waituntil(AppDetails.WI_APPLICATION_TITLE);
        boolean ccheck = false;
        while (!ccheck) {
            ccheck = functions.controlCheck(AppDetails.APPLICATION_TITLE, ControlDetails.ID_TEXTBOX);
            key.waitw(5);
            Log.writelog("Waiting for WI");
        }
        if (ccheck) {
            Log.writelog("control found");
        }
        functions.focusOn(AppDetails.APPLICATION_TITLE, ControlDetails.ID_TEXTBOX);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.ID_TEXTBOX);
        System.out.println(ControlDetails.ID_TEXTBOX);
        key.type(ID);//giving customer id and contact number
        key.pressTab();
        key.type(num);
        key.pressEnter();
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.LICENSEAGREE_BUTTON);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.CONTINUE_BUTTON);
        key.waitw(10000);
    }

    public static void verticalAndSetupSelection() throws FileNotFoundException {
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.SELECT_HARDWARE);//vertical selection
        Log.writelog("Vertical selected");
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.WI_NEXT);
        Log.writelog("Next button clicked");
        key.waitw(3000);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.WI_IHAVESETUP);//select setup
        key.waitw(1000);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.WI_BROWSE_SETUP);//browse setup
        functions.waitFor(AppDetails.SETUP_BROWSE, 5000);
        functions.click(AppDetails.SETUP_BROWSE, ControlDetails.BROWSE_PATH);
        key.pressBack();
        key.waitw(2000);
        key.type(AppDetails.PROD_SETUP_PATH);//giving setup path
        key.pressEnter();
        Log.writelog("Setup selected");
        functions.waitFor(AppDetails.WI_APPLICATION_TITLE, 5000);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.WI_START_INSTALLATION);
        key.pressTab();
        key.pressEnter();
    }

    public static void installationByRestoringBackUp() throws FileNotFoundException {
        functions.waitForText(AppDetails.APPLICATION_TITLE, AppDetails.REINSTALL_TEXT, 60000);
        key.waitw(3000);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.REINSTALL_BUTTON);// Installing with existing database
        key.pressTab();
        functions.focusOn(AppDetails.APPLICATION_TITLE, ControlDetails.WI_NEXT2);//Continue button
        key.pressEnter();
        functions.waitFor(AppDetails.RESTORE_WINDOW, 5000);
        functions.click(AppDetails.RESTORE_WINDOW, ControlDetails.BROWSE_BACKUP);
        key.waitw(2000);
        key.pressEnter();
        functions.waitFor(AppDetails.ZIPBACKUP_BROWSE, 5000);
        key.end();
        key.pressEnter();//selectong bak files using end key
        Log.writelog("Back up selected");
        key.waitw(3000);
        key.pressEnter();
        functions.waitFor(AppDetails.RESTORE_WINDOW, 5000);
        key.pressEnter();
        boolean wsc;
        wsc = functions.checkFor(AppDetails.RESTORE_WINDOW);
        if (wsc) {
            key.altR();
            Log.writelog("Restore started ");
        }
        functions.waitFor(AppDetails.LAST_TRANSCHECK, 60000);
        functions.click(AppDetails.LAST_TRANSCHECK, ControlDetails.CHECK_TRANS);
        key.waitw(5000);
        key.pressTab();
        key.waitw(5000);
        key.pressEnter();
        key.waitw(5000);
        //Log.endlog(method);
        key.esc();
    }

    public static void installSetup() throws FileNotFoundException {
        functions.waitForText(AppDetails.APPLICATION_TITLE, AppDetails.REINSTALL_TEXT, 60000);
        key.waitw(3000);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.REINSTALL_BUTTON);// Installing with existing database
        key.waitw(2000);
        functions.click(AppDetails.APPLICATION_TITLE, ControlDetails.REINSTALL_BUTTON);// Installing with existing database
        key.typeIn(AppDetails.APPLICATION_TITLE, KeyStrokes.TAB);
        key.typeIn(AppDetails.APPLICATION_TITLE, KeyStrokes.TAB);
        key.typeInControl(AppDetails.APPLICATION_TITLE, ControlDetails.TIN_GST_TEXTBOX, AppDetails.GST_NO);
        key.pressTabFor(2);
        key.typeInControl(AppDetails.APPLICATION_TITLE, ControlDetails.WI_ADDRESS, AppDetails.WI_ADDRESS);
        key.pressTabFor(2);
        key.typeInControl(AppDetails.APPLICATION_TITLE, ControlDetails.PIN_ZIPCODE, AppDetails.WI_PINZIP);
        key.pressTabFor(6);
        functions.focusOn(AppDetails.APPLICATION_TITLE, ControlDetails.WI_NEXT2);//Continue button
        key.pressEnter();
    }

    public static void completeInstallation() throws FileNotFoundException {
        functions.waitForText(AppDetails.APPLICATION_TITLE, AppDetails.RESTARTNOW_TEXT, 600000);
        boolean waitforstartapp;
        waitforstartapp = functions.controlCheck(AppDetails.APPLICATION_TITLE, ControlDetails.WI_START_APPLICATION);
        if (!waitforstartapp){
            functions.exitAutomation("Installation not completed Sucessfully");
        }
        functions.click(AppDetails.APPLICATION_TITLE,ControlDetails.WI_START_APPLICATION);
        functions.waitForText(AppDetails.PROD_TITLE,AppDetails.RESTART_WINDOW_TEXT,10000);
        functions.click(AppDetails.PROD_TITLE,ControlDetails.RESTART_NO_BUTTON);
        functions.waitForScreen(AppDetails.AUTO_DB_BACKUP,10000);
        functions.killProcess(AppDetails.AUTOBACKUP_EXE);





    }
    public static void switchLabtest() throws IOException, InterruptedException {
        String ID = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        String lic_screen = "GoFrugal License Activation [ Customer Id : " + ID + " ]";
        String lic_msg = "License updated successfully";
        boolean sp;
        sp = functions.checkFor(lic_screen);
        if (sp) {
            Log.writelog("License screen found");
            if (PTYPE.equals(AppDetails.PTYPE_LABTEST)) {
                Log.writelog("Entering Service user details");
                functions.focusOn(lic_screen, ControlDetails.LIC_EDIT);
                key.waitw(1000);
                boolean edit_btn = functions.controlCheck(lic_screen, ControlDetails.LIC_EDIT);
                if (!edit_btn) {
                    Log.writelog("Cant give service user credentials");
                }
                boolean edit_btn1 = functions.controlCheck(lic_screen, ControlDetails.LIC_EDIT);
                if (edit_btn1) {
                    Log.writelog("License edit function key found ");
                    key.typeIn(lic_screen, KeyStrokes.ENTER);
                }
                functions.click(lic_screen, ControlDetails.LIC_EDIT);
                key.waitw(2000);

                // Fill Service user details

                key.typeInControl(lic_screen, "ThunderRT6TextBox6", AppDetails.SUSER_NAME);
                key.typeInControl(lic_screen, "ThunderRT6TextBox7", AppDetails.SUSER_PW);
                key.typeInControl(lic_screen, "ThunderRT6TextBox8", AppDetails.SUSER_REASON);
                key.typeInControl(lic_screen, "ThunderRT6TextBox9", AppDetails.SUSER_OTHer);
                functions.clicks(lic_screen, "ThunderRT6UserControlDC5", 2);


                Log.writelog("Service user details given");
                key.waitw(2000);
                functions.focusOn(lic_screen, ControlDetails.LIC_TEXTBOX);
                functions.click(lic_screen, ControlDetails.LIC_TEXTBOX);

                // Copy and Paste the licence key

                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                String prod_lic = Files.readAllLines(Paths.get(AppDetails.INSTALL_DATA_PATH)).get(2);
                StringSelection selection = new StringSelection(prod_lic);
                clipboard.setContents(selection, null);
                String prod_lic_key = Files.readAllLines(Paths.get(AppDetails.INSTALL_DATA_PATH)).get(2);
                functions.copyText(prod_lic_key);
                key.paste();
                key.pressTabFor(2);
                key.pressDelFor(4);
                key.waitw(2000);
                key.type("0001");
                key.typeIn(lic_screen, KeyStrokes.TAB);
                key.typeIn(lic_screen, KeyStrokes.ENTER);
                key.altS();
            } else if (PTYPE.equals(AppDetails.PTYPE_SAM)) {
                Log.writelog("install type - SAM");
                functions.focusOn(AppDetails.LOGIN_SCREEN, ControlDetails.LIC_SYNCBUTTON);
                key.pressTab();
                key.waitw(2000);
                key.pressEnter();
            }

        }
        functions.waitFor(AppDetails.LIC_OK, 90000);
        functions.waitForText(AppDetails.LIC_OK, lic_msg, 2000);
        functions.focusOn(AppDetails.LIC_OK, "");
        key.pressEnter();
        key.waitw(5000);
        functions.refreshApp();
    }

    public static void fillSuserDetails() throws IOException, InterruptedException {
        String ID = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        functions.minimizeAll();
        String method = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.startlog(method);
        functions.openPos();
        key.waitw(5000);
        boolean chk;
        chk=functions.checkFor(AppDetails.KEY_MISMATCH_WNDW);
        if (chk){
            Log.writelog("Key mismatch founmd");
            key.typeIn(AppDetails.KEY_MISMATCH_WNDW,KeyStrokes.ENTER);
        }
        switchLabtest();
    }

}
