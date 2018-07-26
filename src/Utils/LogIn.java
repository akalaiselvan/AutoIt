package Utils;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.io.File;
import java.io.FileNotFoundException;

@Test
public class LogIn {
    static KeyStrokes key =new KeyStrokes();
    static Functions functions= new Functions();
    public static void POSlogIn() throws InterruptedException, FileNotFoundException {
        File Dpath = new File("D:\\GoFrugal\\GoFrugalRPOS7\\RetailPOS.exe");
        File Epath = new File("E:\\GoFrugal\\GoFrugalRPOS7\\RetailPOS.exe");
        if (Dpath.exists()){
            Log.writelog("path found in "+Dpath);
            functions.openApp(String.valueOf(Dpath));
        }
        else if(Epath.exists()){
            Log.writelog("path found in "+Epath);
            functions.openApp(String.valueOf(Epath));
        }
        functions.waitUntil(AppDetails.LOGIN_SCREEN,5,true);
        functions.focusOn(AppDetails.LOGIN_SCREEN,AppDetails.WI_PASSWORD_BOX);
        functions.click(AppDetails.LOGIN_SCREEN,AppDetails.WI_PASSWORD_BOX);
        String PTYPE="LABTEST";
if (PTYPE==AppDetails.PTYPE_LABTEST){
    key.waitw(2000);
        key.AI.send(key.S_USER,false);

}
        //key.typeIn(AppDetails.LOGIN_SCREEN,"admin");
        //key.pressEnter();
    }
}
