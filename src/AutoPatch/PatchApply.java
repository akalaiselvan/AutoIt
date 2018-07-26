package AutoPatch;

import Transactions.PurchaseEntry;
import Transactions.ReceptNote;
import Utils.ConnectDB;
import Utils.Log;
import test.AppDetails;
import test.ControlDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PatchApply {
    private static CloudPatchHelper cloudPatchHelper = new CloudPatchHelper();
    private static KeyStrokes key = new KeyStrokes();
    private static Functions functions = new Functions();
    private static PurchaseEntry purchase = new PurchaseEntry();
    private static ReceptNote receiptNote = new ReceptNote();
    private static ConnectDB connectDB = new ConnectDB();


    public static void applyPatch(String PATCH_VERSION) throws IOException, InterruptedException {
        key.waitw(1000);
        String PATCH_SCREEN = "GoFrugal RPOS7 service pack [" + PATCH_VERSION + "]";
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
}
