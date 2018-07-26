package AutoPatch;

import org.testng.annotations.Test;
import test.AppDetails;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import Utils.Log;

import static com.sun.jmx.snmp.ThreadContext.contains;

public class CloudPatchHelper {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();

    @Test
    public void downloadRequiredFiles() throws IOException {

        String INSTALER_VERSION = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(6);
        String WEBINS_LINK="http://posdownloads/pos/downloads/WebInstaller/Release/"+INSTALER_VERSION+"/webinstaller.exe";
        String SETUP_LINK = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(3);
        String PATCH_LINK = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(4);
        String WR_VERSION="";// = Files.readAllLines(Paths.get(AppDetails.FPATH)).get(5);
        String PATCH_VERSION="";//=Files.readAllLines(Paths.get(AppDetails.FPATH)).get(2);
        String EMP_ID="";//=Files.readAllLines(Paths.get(AppDetails.FPATH)).get(0);
        String EMP_NUM="";//=Files.readAllLines(Paths.get(AppDetails.FPATH)).get(1);
        String WR_LINK = "http://posdownloads/pos/downloads/RayMedi_WebReporter/Release/" + WR_VERSION + "/WebReporter.exe";


        functions.downloadFromLink(SETUP_LINK, AppDetails.PROD_SETUP_PATH);
        functions.downloadFromLink(PATCH_LINK,AppDetails.PATCH_PATH);
        functions.downloadFromLink(WEBINS_LINK,AppDetails.WI_PATH);
        functions.downloadFromLink(WR_LINK, AppDetails.WEBREPORTER_PATH);
    }

}
