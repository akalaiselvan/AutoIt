

package AutoPatch;

import Transactions.PurchaseEntry;
import Transactions.ReceptNote;
import Transactions.Sales;
import Utils.JSON.JSONException;
import Utils.JSON.JSONObject;
import Utils.Log;
import Utils.WinRegistry;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import test.*;
import Utils.ConnectDB;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static java.util.prefs.Preferences.userRoot;

public class WebInstaller {

    private static KeyStrokes key = new KeyStrokes();
    private static Functions functions = new Functions();
    private static PurchaseEntry purchase = new PurchaseEntry();
    private static ReceptNote receiptNote = new ReceptNote();
    private static ConnectDB connectDB = new ConnectDB();
    private static CloudPatchHelper cloudPatchHelper=new CloudPatchHelper();
    private static String checkstatus;

    private static Sales sales = new Sales();
    private static String PTYPE = "LABTEST";
    private static int RECURSECALLCOUNT = 0;


    @Test



    public static void readdata() throws Exception {
        String ID = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        String num = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(1);
        //Install product with Web installer
        File downloadPath=new File(AppDetails.DOWNLOAD_PATH);
        if (downloadPath.exists()){
            try {FileUtils.deleteDirectory(new File(AppDetails.DOWNLOAD_PATH));
                Log.writelog(downloadPath+" found and deleted");
            }
            catch (Exception e){Log.writelog(""+e);}
        }
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

    public static void delRegistry()
            throws IOException, NullPointerException, InvocationTargetException, IllegalAccessException {

        functions.minimizeAll();
        String method = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.startlog(method);
        //Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "Volatile Environment\\", "GFT-Restart","0");
        String value = WinRegistry.writeStringValue(userRoot(), WinRegistry.HKEY_CURRENT_USER, "Volatile Environment\\", "GFT-Restart", "0");
        System.out.println("Windows Distribution = " + value);
        key.waitw(2000);
        Log.writelog("Registry altered");
        Log.endlog(method);

    }

    @Test
    public static void licSync() throws Exception {
        functions.minimizeAll();
        String method = new Object() {
        }.getClass().getEnclosingMethod().getName();
        //key.waitw(30000);
        Log.startlog(method);
        functions.openPos();

        String ID = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        String lic_screen = "GoFrugal License Activation [ Customer Id : " + ID + " ]";
        String lic_msg = "License updated successfully";
        functions.waitFor(AppDetails.LOGIN_SCREEN, 25000);
        functions.focusOn(AppDetails.LOGIN_SCREEN, ControlDetails.LOGIN_PWBOX);
        functions.click(AppDetails.LOGIN_SCREEN, ControlDetails.LOGIN_PWBOX);
        key.typeIn(AppDetails.LOGIN_SCREEN, "admin");
        key.pressEnter();
        key.waitw(5000);
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

                key.typeInControl(lic_screen,"ThunderRT6TextBox6",AppDetails.SUSER_NAME);
                key.typeInControl(lic_screen, "ThunderRT6TextBox7",AppDetails.SUSER_PW);
                key.typeInControl(lic_screen,"ThunderRT6TextBox8", AppDetails.SUSER_REASON);
                key.typeInControl(lic_screen, "ThunderRT6TextBox9",AppDetails.SUSER_OTHer);
                functions.clicks(lic_screen,"ThunderRT6UserControlDC5",2);


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

            functions.waitFor(AppDetails.LIC_OK, 90000);
            functions.waitForText(AppDetails.LIC_OK, lic_msg, 2000);
            functions.focusOn(AppDetails.LIC_OK, "");
            key.pressEnter();
            key.waitw(5000);
            functions.refreshApp();
            Log.endlog(method);
        } else {
            Log.writelog("License screen not found");
            functions.killProcess(AppDetails.POS_EXE);
            licSync();
        }
    }

    public static void test() throws FileNotFoundException {
        key.waitw(5000);
        functions.killProcess(AppDetails.BACKUP_PROCESS);
    }

    @Test
    public static void addonSync() throws FileNotFoundException, InterruptedException {
        Log.writelog("Add on sync started");
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        key.waitw(2000);
        functions.openABout();
        functions.waitForScreen(AppDetails.ABOUT_POS, 5000);
        boolean checkAbout = functions.checkFor(AppDetails.ABOUT_POS);
        RECURSECALLCOUNT++;
        if (RECURSECALLCOUNT == 3) {
            functions.exitAutomation("can't able to find About menu");
        }
        if (!checkAbout) {
            Log.writelog("About screen not found..Restarting app : " + RECURSECALLCOUNT);
            functions.refreshApp();
            addonSync();
            return;
        }
        functions.waitForScreen(AppDetails.ABOUT_POS, 5000);
        functions.activateWindow(AppDetails.ABOUT_POS);
        key.mouseDoubleClick("left",348,388);
        functions.waitForScreen(AppDetails.ADDON_SYNC, 10000);
        functions.activateWindow(AppDetails.ADDON_SYNC);
        key.altS();
        key.waitw(120000);
        functions.focusOn(AppDetails.ADDON_SYNC, ControlDetails.LIC_SYNC_CLOSE);
        key.typeIn(AppDetails.ADDON_SYNC, KeyStrokes.TAB);
        key.typeIn(AppDetails.ADDON_SYNC, KeyStrokes.TAB);
        key.typeIn(AppDetails.ADDON_SYNC, KeyStrokes.TAB);
        key.waitw(1000);
        key.pressEnter();
        key.waitw(2000);
        Boolean chkbtn = functions.controlCheck(AppDetails.ADDON_SYNC, ControlDetails.LIC_SYNC_CLOSE);
        if (chkbtn) {
            key.mouseClick("left", 953, 534);
        }
        key.waitw(2000);
        functions.activateWindow(AppDetails.ABOUT_POS);
        key.waitw(2000);
        key.esc();
        key.waitw(10000);
    }

    @Test
    public static void backupRestoreSync() throws FileNotFoundException, InterruptedException {
        functions.waitUntil(AppDetails.BR_DASHBOARD, 10, true);
        Log.writelog("Dashbord found");
        boolean chkscrn = functions.checkFor(AppDetails.BR_DASHBOARD);
        if (!chkscrn) {
            String Db = "D:\\GoFrugal\\GoFrugalRPOS7\\websync\\connectplusDP.exe";
            functions.openApp(Db);
            functions.waitUntil(AppDetails.BR_DASHBOARD, 10, true);
        }
        functions.focusOn(AppDetails.BR_DASHBOARD, "");
        key.waitw(2000);
        key.typeIn(AppDetails.BR_DASHBOARD, KeyStrokes.TAB);
        key.waitw(2000);
//        ////key.typeIn(AppDetails.BR_DASHBOARD,key.TAB);
//        ////key.waitw(1000);
//        ////key.typeIn(AppDetails.BR_DASHBOARD,key.ENTER);
        key.mouseClick("left", 1211, 630);
        Log.writelog("Continue-given");
        key.waitw(240000);
        functions.focusOn(AppDetails.BR_DASHBOARD, "");
        key.waitw(2000);
        key.typeIn(AppDetails.BR_DASHBOARD, KeyStrokes.TAB);
        key.waitw(2000);
        key.typeIn(AppDetails.BR_DASHBOARD, KeyStrokes.TAB);
        key.waitw(2000);
        // key.typeIn(AppDetails.BR_DASHBOARD,KeyStrokes.TAB);
        key.waitw(1000);
        key.typeIn(AppDetails.BR_DASHBOARD, KeyStrokes.ENTER);
        key.mouseClick("left", 1214, 685);
        Log.writelog("Data Sync Started");
        functions.waitUntil(AppDetails.BR_WORKLIKECHARM, 10, true);
        functions.waitForText(AppDetails.BR_WORKLIKECHARM, AppDetails.BR_MSG, 90000);
        functions.waitForScreen(AppDetails.BR_WORKLIKECHARM, 2000);
        functions.focusOn(AppDetails.BR_WORKLIKECHARM, "");
        key.pressTab();
        key.pressEnter();
        key.typeIn(AppDetails.BR_WORKLIKECHARM, KeyStrokes.ENTER);
        Log.writelog("First Sync Verification Started ");

        // Wait for first data sync sucess status

        for (int n = 1 ; n <= 20; ) {
            key.waitw(60000);
            String WS_INFO = connectDB.getValueFromQuery(Queries.CHECK_FIRSTSYNC_STATUS);
            Log.writelog("Waiting for first sync done status " + n + "minute(s)");
            if (!WS_INFO.equals("")) {
                Log.writelog("First sync done in" + n + "minutes");
                n = 21;
            }
            if (n == 20) {
                Log.writelog("First sync not done in" + n + "minutes");
            }
            n++;
        }


    }

    @Test
    public static void apply_patch() throws IOException, InterruptedException {
        key.waitw(1000);
        String PATCH_VERSION = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(2);
        String PATCH_SCREEN = "GoFrugal RPOS7 service pack [" + PATCH_VERSION + "]";
        //String patch_path = "C:\\Users\\admin\\Desktop\\Rpos7\\Patch.exe";
        Log.writelog("Patch apply process started");
        functions.minimizeAll();
        if (new File(AppDetails.PATCH_PATH).exists()){
            functions.openApp(AppDetails.PATCH_PATH);
        }else {
            functions.exitAutomation(AppDetails.PATCH_PATH+" not found");
        }
        functions.waitForScreen(PATCH_SCREEN, 240000);
        functions.focusOn(PATCH_SCREEN, "");
        key.waitw(2000);
        key.altG();
        boolean ctrlchk = functions.controlCheck(PATCH_SCREEN, ControlDetails.PATCH_GO);
        if (ctrlchk) {
            Log.writelog("Mouse click for Go");
            key.mouseClick("left", 790, 553);
        }
        Log.writelog("Go");
        key.waitw(2000);
        functions.waitandcheck(AppDetails.PATCH_TERMS, 5000);
        functions.focusOn(AppDetails.PATCH_TERMS, AppDetails.TERMS_CHECK);
        functions.click(AppDetails.PATCH_TERMS, AppDetails.TERMS_CHECK);
        key.waitw(2000);
        key.pressTabFor(2);
        key.altC();
        Log.writelog("Patch trigerred");
        boolean scheck = false;
        Log.writelog("Waiting for" + AppDetails.PATCH_COMPLETE);
        while (!scheck) {
            scheck = functions.checkForText(AppDetails.PATCH_COMPLETE, AppDetails.PATCH_COMP_MSG);
            functions.waitForText(AppDetails.PATCH_COMPLETE, AppDetails.PATCH_COMP_MSG, 60000);
            Log.writelog("Waiting for patch to complete ");
        }
        Log.writelog("wait over for" + AppDetails.PATCH_COMPLETE);
        functions.waitForScreen(AppDetails.PATCH_COMPLETE, 5000);
        functions.focusOn(AppDetails.PATCH_COMPLETE, "");
        key.pressTab();
        key.waitw(2000);
        key.typeIn(AppDetails.PATCH_COMPLETE, KeyStrokes.ENTER);
        key.pressEnter();
        String PATCH_STATUS;
        if (scheck) {
            Log.writelog("Patch completed sucessfully");
            functions.waitForScreen(PATCH_SCREEN, 5000);
            key.typeIn(PATCH_SCREEN, KeyStrokes.altC);
            key.altC();
            PATCH_STATUS = AppDetails.PATCH_SUCESS;
        } else {
            Log.writelog("Patch failed i think ..!");
            PATCH_STATUS = AppDetails.PATCH_FAILURE;
        }
        if (PATCH_STATUS.equals(AppDetails.PATCH_SUCESS)) {
            functions.refreshApp();
            functions.waitForScreen(AppDetails.PROD_TITLE, 30000);
            key.waitw(20000);
            Log.writelog("Application opened");
        }
    }

    static void changeIni() throws IOException {
        key.waitw(10000);
        File Dpath = new File("D:\\GoFrugal\\GoFrugalRPOS7\\localdata.ini");
        File Epath = new File("E:\\GoFrugal\\GoFrugalRPOS7\\localdata.ini");
        File targetPath = null;
        if (Dpath.exists()) {
            targetPath = Dpath;
            Dpath.delete();
            Log.writelog(".ini file deleted from" + Dpath);
        }
        if (Epath.exists()) {
            targetPath = Epath;
            Epath.delete();
            Log.writelog(".ini file deleted from" + Epath);
        }
        File iniPath = new File("C:\\Users\\admin\\Desktop\\Rpos7\\localdata.ini");
        if (iniPath.exists()) {
            Files.copy(iniPath.toPath(), targetPath.toPath());
            Log.writelog("ini Replaced in" + targetPath);
        } else {
            Log.writelog("local ini not found");
        }
    }

    public static void stopWebsyncService() throws FileNotFoundException {
        Log.writelog("Stopping Web sync Service");
        Functions.stopService(AppDetails.SERVICE_WEBSYNC);
        Log.writelog("Web sync service stopped");
    }

    @Test


    public static void basicTrans(String pinvoice, String rivoice) throws FileNotFoundException, InterruptedException {

        // PURCHASE
        String PURSCREEN = AppDetails.PROD_TITLE + " " + "-" + " " + "[" + AppDetails.PURCHASE_SCREEN + "]";
        String RNSCREEN = functions.setScreen(AppDetails.RECEIPT_NOTE);
        String SalesSCREEN = functions.setScreen(AppDetails.SALES_SCREEN);


        purchase.open_screen();
        RECURSECALLCOUNT++;
        boolean purScrnChk = functions.checkFor(PURSCREEN);
        if (RECURSECALLCOUNT == 3) {
            Log.writelog(PURSCREEN + "not found..trans skipped");
            return;
        }
        if (!purScrnChk) {
            Log.writelog(PURSCREEN + "not found..Restarting app : " + RECURSECALLCOUNT);
            functions.refreshApp();
            basicTrans(pinvoice, rivoice);
            return;
        }

        purchase.wi_Purchase(pinvoice);
        purchase.close_screen();
        //RN
        receiptNote.open_screen();
        boolean Rnscrn_chk = functions.checkFor(RNSCREEN);
        if (!Rnscrn_chk) {
            Log.writelog(RNSCREEN + "not found ..Skipped");
            return;
        }
        receiptNote.wi_trans(rivoice);
        receiptNote.close_screen();
        //Sales
        sales.open_screen();
        boolean Salesscrn_chk = functions.checkFor(SalesSCREEN);
        if (!Salesscrn_chk) {
            Log.writelog(SalesSCREEN + "not found ..Skipped");
            return;
        }
        sales.wi_billing();
        sales.close_screen();
    }

    @Test
    public static void mainConfigLinkChange() throws InterruptedException, IOException {
        Log.writelog("Changing main config.exe for test mode");
        String custom_id = Files.readAllLines(Paths.get(AppDetails.INSTALL_DATA_PATH)).get(1);
        String path = "";
        File Epath = new File("E:\\GoFrugal\\GoFrugalRPOS7\\websync\\MainConfig.exe.config");
        File Dpath = new File("D:\\GoFrugal\\GoFrugalRPOS7\\websync\\MainConfig.exe.config");
        if (Dpath.exists()) {
            path = String.valueOf(Dpath);
        } else if (Epath.exists()) {
            path = String.valueOf(Epath);
        }
        String text = "    <add key=\"serverurl\" value=\"https://##CUSTID##.gofrugalconnect.com\" />";
        String text1 = "    <add key=\"websyncURL\" value=\"https://##PROD##-upload.gofrugalconnect.com\" />";
        String text2 = "    <add key=\"Env\" value=\"cp_live\" />";
        //String torep="    <add key=\"serverurl\" value=\"http://"+custom_id+".qa.gofrugalretail.com\" />";
        //String torep1="    <add key=\"websyncURL\" value=\"http://"+custom_id+".qa.gofrugalretail.com\" />";
        String torep = "    <add key=\"serverurl\" value=\"http://" + custom_id + ".qaconnectplus.com\" />";
        String torep1 = "    <add key=\"websyncURL\" value=\"http://##PROD##-upload.qaconnectplus.com\" />";
        String torep2 = "    <add key=\"Env\" value=\"cp_qa\" />";
        List<String> Catm = new ArrayList<>();
        {
            Catm.add(text);
            Catm.add(text1);
            Catm.add(text2);
            for (String mConfig : Catm) {
                if (Dpath.exists() || Epath.exists()) {
                    Log.writelog("path found in " + path);
                    File fileToBeModified = new File(String.valueOf(path));
                    String oldContent = "";
                    BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
                    String line = reader.readLine();
                    Log.writelog("" + line);
                    while (line != null) {
                        oldContent = oldContent + line + System.lineSeparator();
                        line = reader.readLine();
                    }

                    if (mConfig.equals(text)) {
                        String newContent = oldContent.replaceAll(mConfig, torep);
                        FileWriter writer = new FileWriter(fileToBeModified);
                        writer.write(newContent);
                        reader.close();
                        writer.close();
                    }
                    if (mConfig.equals(text1)) {
                        String newContent = oldContent.replaceAll(mConfig, torep1);
                        FileWriter writer = new FileWriter(fileToBeModified);
                        writer.write(newContent);
                        reader.close();
                        writer.close();
                    }
                    if (mConfig.equals(text2)) {
                        String newContent = oldContent.replaceAll(mConfig, torep2);
                        FileWriter writer = new FileWriter(fileToBeModified);
                        writer.write(newContent);
                        reader.close();
                        writer.close();
                    }

                } else {
                    Log.writelog("Main Config Manifest file not found");
                }
            }
        }
    }

    public static void compareSchemaTool() throws IOException, JSONException {
        String url = "http://pcrdemo1:4567/compare?product=rpos7&posHost=10.0.0.219&customerId=32222";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String res = response.toString();
        JSONObject jsonObject1 = new JSONObject(res);
        String misMatchStatus = jsonObject1.getString("misMatch");
        Log.writelog("MismatchStatus:" + misMatchStatus);
        //print result
        //System.out.println(response.toString());
    }


    @Test
    public static void apiCall() throws Exception {



        String path = new File(".").getCanonicalPath();
        File installdate = new File(AppDetails.INSTALL_DATA_PATH);
        File logFile = new File(path+"\\Log.txt");
        if (logFile.exists()) {
            logFile.delete();
            Log.writelog("Log file deleted");
        }
        if (installdate.exists()) {
            installdate.delete();
            Log.writelog("Install data file deleted");
        }


        functions.minimizeAll();
        URL obj = new URL(AppDetails.SAMTEST_LOGIN_API);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        OutputStream os = con.getOutputStream();
        os.write(AppDetails.SAMTEST_JSON.getBytes("UTF-8"));
        os.close();
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        Log.writelog("\nSending 'GET' request to URL : " + AppDetails.SAMTEST_LOGIN_API);
        Log.writelog("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String res = response.toString();
        JSONObject jsonObject = new JSONObject(res);
        //Log.writelog(""+res);
        String key1 = jsonObject.getString("key");
        String authToken = jsonObject.getString("authtoken");
        Log.writelog("key:" + key1);
        Log.writelog("Autoken:" + authToken);


//  For lead Creation
        key.waitw(10000);
        String timeLog = new SimpleDateFormat("dd_HH_mm_ss").format(Calendar.getInstance().getTime());
        String timeLog1 = new SimpleDateFormat("ddHHmmss").format(Calendar.getInstance().getTime());
        String shop_name = "Auto Lead" + timeLog + "";
        String LEAD_JSON = "{\n" +
                "  \"shop_name\": \"" + shop_name + "\",\n" +
                "  \"vertical\": \"1001\",\n" +
                "  \"lead_address\": {\n" +
                "    \"pin_code\": \"600078\",\n" +
                "    \"addr_street\": null,\n" +
                "    \"location_name\": null\n" +
                "  },\n" +
                "  \"contact_list\": [\n" +
                "    {\n" +
                "      \"contact_person_name\": \"Contact 1\",\n" +
                "      \"contact_dtl\": [\n" +
                "        {\n" +
                "          \"contact_type\": \"1\",\n" +
                "          \"contact_info\": \"77" + timeLog1 + "\"\n" +
                "        }\n" +
                "      ],\n" +
                "\"contact_designation\": \"1\",\n" +
                "      \"contact_group\": \"1\"\n" +
                "\n" +
                "    }\n" +
                "  ],\n" +
                "  \"submit_type\": \"1\"\n" +
                "}";
        URL lead_obj = new URL(AppDetails.LEAD_CREATION_API);
        HttpURLConnection lead_con = (HttpURLConnection) lead_obj.openConnection();
        lead_con.setDoOutput(true);
        lead_con.setDoInput(true);
        // optional default is GET
        lead_con.setRequestMethod("GET");
        lead_con.setRequestProperty("key", "IYYH0");
        lead_con.setRequestProperty("authtoken", authToken);
        lead_con.setRequestProperty("currentversion", "2.6.0.0");
        lead_con.setRequestProperty("Content-type", "application/json");
        lead_con.setRequestProperty("Accept", "application/json");
        OutputStream lead_os = lead_con.getOutputStream();
        lead_os.write(LEAD_JSON.getBytes("UTF-8"));
        lead_os.close();

        //add request header

//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int lead_responseCode = lead_con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + AppDetails.LEAD_CREATION_API);
        System.out.println("Response Code : " + lead_responseCode);
        BufferedReader lead_in = new BufferedReader(new InputStreamReader(lead_con.getInputStream()));
        String lead_inputLine;
        StringBuffer leadresponse = new StringBuffer();
        while ((lead_inputLine = lead_in.readLine()) != null) {
            leadresponse.append(lead_inputLine);
        }
        lead_in.close();
        String lead_res = leadresponse.toString();
        JSONObject lead_jsonObject = new JSONObject(lead_res);
        //Log.writelog(""+lead_res);
        String leadcode = lead_jsonObject.getString("lead_code");
        String lead_status = lead_jsonObject.getString("status");
        Log.writelog("lead creation status:" + lead_status);
        Log.writelog("lead_code:" + leadcode);
        functions.writeToInstallData(leadcode);
        //print result
        //Log.writelog(leadresponse.toString());


        key.waitw(10000);
        //FOR LICENSE


        List<String> Catm = new ArrayList<>();
        {
            Catm.add(AppDetails.RPOS7_PROF_LICENSE);
            Catm.add(AppDetails.BACKup_RESTORE_LICENSE);
            for (String mConfig : Catm) {
                String timeLog2 = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                String LIC_JSON = "{\n" +
                        "  \"quotation_no\": \"356076718000001\",\n" +
                        "  \"action_type\": \"convert_to_order\",\n" +
                        "  \"cust_id\": \"" + leadcode + "\",\n" +
                        "  \"order_no\": \"9" + timeLog2 + "\",\n" +
                        "  \"splitable_order\": \"0\",\n" +
                        "  \"tax_mode\": \"4\",\n" +
                        "  \"currency_type\": \"INR\",\n" +
                        "  \"order_type\": [\n" +
                        "    \"0\"\n" +
                        "  ],\n" +
                        "  \"quotation_license_order\": [\n" +
                        "    {\n" +
                        "      \"product\": \"" + mConfig + "\",\n" +
                        "      \"quantity\": \"1\",\n" +
                        "      \"selling_price\": 12711.86,\n" +
                        "      \"discount_value\": 0,\n" +
                        "      \"discount_percent\": 0,\n" +
                        "      \"net_amount\": 12711.86,\n" +
                        "      \"ref_server\": [],\n" +
                        "      \"list_price\": 12711.86,\n" +
                        "      \"sales_tax_percent\": null,\n" +
                        "      \"service_tax_percent\": null\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"order_amount\": \"15000\",\n" +
                        "  \"payment_type\": \"2-0.00\",\n" +
                        "  \"collection_date\": \"2018-02-15\",\n" +
                        "  \"incentive_emp\": \"0-0-0\",\n" +
                        "  \"approval_day\": \"30\",\n" +
                        "  \"required_product_delivery\": \"Yes\",\n" +
                        "  \"product_delivery_by\": \"1\",\n" +
                        "  \"product_consultant\": \"1112\",\n" +
                        "  \"questions\": {\n" +
                        "    \"292\": \"No\",\n" +
                        "    \"304\": \"2018-02-01 00:01\",\n" +
                        "    \"305\": \"pp\",\n" +
                        "    \"306\": \"do\"\n" +
                        "  },\n" +
                        "  \"coupon_list\": \"2\",\n" +
                        "  \"comments\": \"sc\"\n" +
                        "}";
                URL lic_obj = new URL(AppDetails.CREATE_LICENSE_API);
                HttpURLConnection lic_con = (HttpURLConnection) lic_obj.openConnection();
                lic_con.setDoOutput(true);
                lic_con.setDoInput(true);
                // optional default is GET
                lic_con.setRequestMethod("GET");
                lic_con.setRequestProperty("key", "IYYH0");
                lic_con.setRequestProperty("authtoken", authToken);
                lic_con.setRequestProperty("currentversion", "2.6.0.0");
                lic_con.setRequestProperty("Content-type", "application/json");
                lic_con.setRequestProperty("Accept", "application/json");
                OutputStream lic_os = lic_con.getOutputStream();
                lic_os.write(LIC_JSON.getBytes("UTF-8"));
                lic_os.close();
                //add request header
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                // int lic_responseCode = lic_con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + AppDetails.CREATE_LICENSE_API);
                //System.out.println("Response Code : " + lic_responseCode);
                BufferedReader lic_in = new BufferedReader(new InputStreamReader(lic_con.getInputStream()));
                String lic_inputLine;
                StringBuffer lic_response = new StringBuffer();
                while ((lic_inputLine = lic_in.readLine()) != null) {
                    lic_response.append(lic_inputLine);
                }
                lic_in.close();
                String lic_res = lic_response.toString();
                JSONObject lic_jsonObject = new JSONObject(lic_res);
                //Log.writelog(""+lic_res);
                String customer_id = lic_jsonObject.getString("cust_id");
                Log.writelog("cust_id:" + customer_id);
                String order_no = lic_jsonObject.getString("order_no");
                Log.writelog("order_no:" + order_no);
                if (mConfig.equals(AppDetails.RPOS7_PROF_LICENSE)) {
                    functions.writeToInstallData(order_no);
                }
                String message = lic_jsonObject.getString("message");
                Log.writelog("message:" + message);
            }
        }


        //print result
        //System.out.println(response.toString());

    }

    public static void updateWRStatus() {
        key.waitw(3000);
        connectDB.updateQuery(Queries.UPDATE_PROP_VALUE);
    }

    @Test
    public static void InstallWebReporter() throws IOException, InterruptedException {
        JFrame label=functions.showLabel("WebReporter Installation in Progress.. !");
        label.setVisible(true);

//        // Download Web Reporter from local
//       // Install Web Reporter using cmd
        key.waitw(2000);
       functions.runCMD(AppDetails.WEBREPORTER_PATH + " /VERYSILENT /PRODUCT=RPOS7");
//        // Check web reporter installation by considering wr_installation_status.ini file
        String WR_INS_STATUS = functions.checkWebReporterInstallStatus(10);
        // change system properties as labtest
        connectDB.updateQuery(Queries.UPDATE_PROJ_PROP_FRLABTEST);
        key.waitw(2000);
//        // restart Wr service
        functions.runCMD("net stop GoFrugalRPOS7WRP & sc start GoFrugalRPOS7WRP");
        key.waitw(30000);
//        // restart POS
        functions.refreshApp();
        String WR_INS_STATUS_INDB = connectDB.getValueFromQuery(Queries.WR_INSTALL_STATUS);
        Log.writelog("WR install status : " +WR_INS_STATUS+ "Install ststus on DB : "+WR_INS_STATUS_INDB);
        if (Objects.equals(WR_INS_STATUS, AppDetails.WR_INSTALLATION_SUCCESS)) {
            Log.writelog("WR installation completed ");

            if (Objects.equals(WR_INS_STATUS, AppDetails.WR_INSTALLATION_SUCCESS) && !("1\r\n").equals(WR_INS_STATUS_INDB)) {
                Log.writelog("WR installation completed but not updated to samtest , so updating the same manually");
                connectDB.updateQuery(Queries.UPDATE_PROP_VALUE);
                WR_INS_STATUS_INDB = connectDB.getValueFromQuery(Queries.WR_INSTALL_STATUS);
            }

            if (Objects.equals(WR_INS_STATUS, AppDetails.WR_INSTALLATION_SUCCESS) && WR_INS_STATUS_INDB.equals("1\r\n")) {
                Log.writelog("WR installation completed & status updated to samtest");
            }
        }
        else {
            Log.writelog("WR Installed failed");
            functions.exitAutomation("WR installation failed");
        }
        label.setVisible(false);
    }

    @Test
    public static void freeSpaceValidation() throws FileNotFoundException {
       double freeSpace=functions.checkFreeSpaceIn("c");
       int fs=(int)freeSpace;
        if (fs <= 4){
            functions.exitAutomation("Disk Space is low .. Clear some memory and try again !");
        }
    }

    public static String getCheckstatus() {
        return checkstatus;
    }

    public static void setCheckstatus(String checkstatus) {
        WebInstaller.checkstatus = checkstatus;
    }
}


