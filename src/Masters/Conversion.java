package Masters;

import Utils.Log;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class Conversion {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    String SCREEN=functions.setScreen(AppDetails.CONVERSION_MASTER);


    @BeforeTest
    public void openmaster() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.CONVERSION_MENU);
        functions.waitForScreen(AppDetails.CONVERSION_MASTER, 5);
    }
    @Test
    public void create_mast() throws FileNotFoundException {
        Log.startlog(SCREEN);
        key.typeIn(AppDetails.CONVERSION_MASTER,"{BACKSPACE}");
        key.type(MasterAttributes.CONVERSION_ITEM);key.pressEnterFor(1);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,10);
        key.pressS();
        key.pressEnter();
        key.pressEnter();
        key.pressEnter();
        key.f6();
        Log.endlog(SCREEN);
    }
@AfterTest
    public void close()
    {
        key.typeIn(AppDetails.CONVERSION_MASTER,"{ESC}");
        key.waitw(2000);
    }
}
