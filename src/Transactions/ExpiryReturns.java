package Transactions;
import Masters.MasterAttributes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import Utils.Log;

import java.io.FileNotFoundException;
public class ExpiryReturns {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    String SCREEN=functions.setScreen(AppDetails.EXPIRY_RETURN);

@BeforeTest
    public void open_screen() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.EXPIRY_RETURN);
        functions.waitForScreen(SCREEN,5);

    }
    @Test
    public void trans() throws FileNotFoundException {
        Log.startlog(SCREEN);
        key.pressEnter();
        key.typeIn(SCREEN,"12");
        key.pressRightFor(1);
        key.typeIn(SCREEN,"2050");
        key.pressEnter();
        functions.waitandcheck(AppDetails.EXPIRY_RETURN_FILTER,5);
        key.pressTabFor(5);
        key.pressEnter();
        key.typeInLOV(MasterAttributes.DISTRIBUTOR_RONCHAL);
        key.pressEnterFor(3);
        key.typeIn(SCREEN,"100");
        key.pressEnterFor(2);
        key.f6();
        functions.waitFor(AppDetails.CONFIRMATION_MSG,10000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        functions.waitFor(AppDetails.CONFIRMATION_MSG,10000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        functions.waitFor(AppDetails.CONFIRMATION_MSG,10000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressRightFor(1);key.pressEnter();
        Log.endlog(SCREEN);
    }
    @AfterTest
public void close_screen(){
    key.waitw(3000);key.typeIn(SCREEN,"{ESC}");}
}
