package Transactions;

import Masters.MasterAttributes;
import Utils.Log;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class SalesQuotation {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    String SCREEN=functions.setScreen(AppDetails.QUOTATION_SCREEN);


    @BeforeTest
    public void open_screen() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.QUOTATION_MENU);
        functions.waitForScreen(SCREEN, 5);
    }
@Test
    public void billing() throws FileNotFoundException {
        Log.startlog(SCREEN);
        functions.selectCustomer(MasterAttributes.CUSTOMER_VICKY);
        functions.waitForScreen(SCREEN,2000);
        functions.pickMatrixItm(MasterAttributes.ITEM_BIRHDAY_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_FAREWELL_CARDS,10);
        key.f6();
        functions.waitFor(AppDetails.CONFIRMATION_MSG,5000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        key.f6();
        functions.waitFor(AppDetails.CONFIRMATION_MSG,5000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        key.f6();
        functions.waitFor(AppDetails.CONFIRMATION_MSG,5000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.altN();

}
@AfterTest
public void close_scrn(){
    functions.close_screen();
    }

}
