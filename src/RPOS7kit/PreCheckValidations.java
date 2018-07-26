package RPOS7kit;

import AutoPatch.CloudPatchHelper;
import Utils.Log;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PreCheckValidations {
    KeyStrokes key = new KeyStrokes();
    static Functions functions = new Functions();
    private static CloudPatchHelper cloudPatchHelper=new CloudPatchHelper();
    private static String checkstatus;

    @Test
    public static void exeValidation(String exe){
        if (!exe.contains(".exe")){
            functions.showWarning("Invalid exe : "+exe);
            setCheckstatus("fail");
        }
    }


    public static String connectPlusValidations() throws IOException {
        setCheckstatus("pass");
        String INSTALER_VERSION = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(6);
        String WEBINS_LINK = "http://posdownloads/pos/downloads/WebInstaller/Release/" + INSTALER_VERSION + "/webinstaller.exe";
        String SETUP_LINK =  Files.readAllLines(Paths.get(AppDetails.FPATH)).get(3);
        String PATCH_LINK =  Files.readAllLines(Paths.get(AppDetails.FPATH)).get(4);
        String WR_VERSION =  Files.readAllLines(Paths.get(AppDetails.FPATH)).get(5);
        String PATCH_VERSION =Files.readAllLines(Paths.get(AppDetails.FPATH)).get(2);
        String EMP_ID = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        String EMP_NUM =Files.readAllLines(Paths.get(AppDetails.FPATH)).get(1);

        String WR_LINK = "http://posdownloads/pos/downloads/RayMedi_WebReporter/Release/" + WR_VERSION + "/WebReporter.exe";

        // Module service pack selection validation

        List<String> ls = new ArrayList<>();
        {
            ls.add(EMP_ID);
            ls.add(EMP_NUM);
            ls.add(PATCH_VERSION);
            ls.add(SETUP_LINK);
            ls.add(PATCH_LINK);
            ls.add(WR_VERSION);
            ls.add(INSTALER_VERSION);
        }
        int rowcount = 0;
        for (String pathverision : ls) {
            pathverision = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(rowcount);
            if (pathverision.equals("")) {
                rowcount++;
                Log.writelog("Line number " + rowcount + " found empty. Please verify it");
                functions.showWarning("Line " + rowcount + " found empty. Please verify it");
                rowcount++;
                setCheckstatus("fail");
                return checkstatus;
            }
            rowcount++;
        }

        if (!PATCH_LINK.contains("module")) {
            functions.showWarning("Module service pack link seems wrong");
            setCheckstatus("fail");
            return checkstatus;        }
        if (!PATCH_VERSION.contains("RC")) {
            functions.showWarning("Please give complete patch version");
            setCheckstatus("fail");
            return checkstatus;        }
            exeValidation(SETUP_LINK);
            exeValidation(PATCH_LINK);
            cloudPatchHelper.downloadRequiredFiles();
        return checkstatus;
    }

    public static String patchApplyValidations() throws IOException {
        setCheckstatus("pass");
        String PATCH_VERSION=Files.readAllLines(Paths.get(AppDetails.FPATH)).get(1);
        String PATCH_LINK=Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);

        List<String> ls = new ArrayList<>();
        {
            ls.add(PATCH_VERSION);
            ls.add(PATCH_LINK);
        }
        int rowcount = 0;
        for (String pathverision : ls) {
            pathverision = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(rowcount);
            if (pathverision.equals("")) {
                rowcount++;
                Log.writelog("Line number " + rowcount + " found empty. Please verify it");
                functions.showWarning("Line " + rowcount + " found empty. Please verify it");
                rowcount++;
                setCheckstatus("fail");
                return checkstatus;
            }
            rowcount++;
        }

        if (!PATCH_VERSION.contains("RC")) {
            functions.showWarning("Please give complete patch version");
            setCheckstatus("fail");
            return checkstatus;
        }

        exeValidation(PATCH_LINK);

        return checkstatus;
    }
    public static String getCheckstatus() {
        return checkstatus;
    }

    public static void setCheckstatus(String checkstatus) {
        PreCheckValidations.checkstatus = checkstatus;
    }
}
