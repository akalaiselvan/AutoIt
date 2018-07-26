package AutoPatch;

import RPOS7kit.PreCheckValidations;
import Utils.Log;
import test.AppDetails;
import GUI.CreateGUI;
import test.Functions;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public  class CloudPatchAUtomation {
    static Functions functions = new Functions();


    public static  void connectPlusAutomation() throws Exception {

        WebInstaller.apiCall();
        // Install Rpos7 in web installer using emply Id with Old Back up
        WebInstaller.readdata();
//       //Edit registry
        WebInstaller.delRegistry();
//        //Change ini file
        WebInstaller.changeIni();
//        //  sync with customer license in labtest
        WebInstaller.licSync();
        // installer web reporter
        WebInstaller.InstallWebReporter();
//        // Sync Add-on
        WebInstaller.addonSync();
//        // To start sync using dahboard UI
        WebInstaller.backupRestoreSync();
//        // Stop web sync Service
        //WebInstaller.stopWebsyncService();
//        // Some basic Transaction made in old wnvironment
        WebInstaller.basicTrans(AppDetails.PUR_INV1, AppDetails.RN_INV1);
//        // apply new version patch
        WebInstaller.apply_patch();
//        // Changing main config to qa environment
        //WebInstaller.mainConfigLinkChange();
//        // After patch same set of transactions done
        WebInstaller.basicTrans(AppDetails.PUR_INV2, AppDetails.RN_INV2);


    }

    public static void applyPatch() throws IOException, InterruptedException {
        Log.writelog("PATCH APPLY PROCESS STARTED");
        String PATCH_VERSION= Files.readAllLines(Paths.get(AppDetails.FPATH)).get(1);
        String PATCH_LINK=Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        functions.downloadFromLink(PATCH_LINK,AppDetails.PATCH_PATH);
        if (PreCheckValidations.getCheckstatus().equals("fail")){
            return;
        }
        PatchApply.applyPatch(PATCH_VERSION);
    }



















































//    public static void main(String[] args) throws Exception {
//        //API call to create rpos7 professional license with created lead
//
//        WebInstaller.apiCall();
//       // Install Rpos7 in web installer using emply Id with Old Back up
//        WebInstaller.readdata();
////       //Edit registry
//        WebInstaller.delRegistry();
////        //Change ini file
//        WebInstaller.changeIni();
////        //  sync with customer license in labtest
//        WebInstaller.licSync();
//        // installer web reporter
//       WebInstaller.InstallWebReporter();
////        // Sync Add-on
//        WebInstaller.addonSync();
////        // To start sync using dahboard UI
//        WebInstaller.backupRestoreSync();
////        // Stop web sync Service
//        //WebInstaller.stopWebsyncService();
////        // Some basic Transaction made in old wnvironment
//        WebInstaller.basicTrans(AppDetails.PUR_INV1, AppDetails.RN_INV1);
////        // apply new version patch
//        //WebInstaller.apply_patch();
////        // Changing main config to qa environment
//        //WebInstaller.mainConfigLinkChange();
////        // After patch same set of transactions done
//        //WebInstaller.basicTrans(AppDetails.PUR_INV2, AppDetails.RN_INV2);
//    }

}
