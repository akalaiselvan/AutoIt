package Inventory;

import Utils.Log;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.ControlDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class RePrint {

    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.REPRINT);

    @BeforeTest
    public void openStockUpdate() throws FileNotFoundException {
        functions.activateWindow(AppDetails.PROD_TITLE);
        key.waitw(1000);
        functions.openScreenByShortcut(AppDetails.REPRNT);
        functions.waitForScreen(SCREEN, 5);
    }

    @Test
    void viewBills() throws FileNotFoundException {
        key.waitw(2000);
    key.typeIn(SCREEN,"ppp");key.waitw(1000);
    key.typeIn(SCREEN,KeyStrokes.ALTg);key.f2();
    functions.waitForText("",ControlDetails.REPRINT_PREVIEW,5);
    boolean isPurchaseReprintPreviewDisplayed=functions.checkWindow("",ControlDetails.REPRINT_PREVIEW);
    if (isPurchaseReprintPreviewDisplayed){
        Log.writelog("purchase Reprint preview displayed ");
    }else {Log.writelog("purchase Reprint preview not shown ");}
        key.esc();
        key.waitw(500);
        key.pressTabFor(4);
        key.mouseClick("left",81,115);
        key.typeIn(SCREEN,"r");key.waitw(1000);
        key.typeIn(SCREEN,KeyStrokes.ALTg);key.f2();
        functions.waitForText("",ControlDetails.REPRINT_PREVIEW,5);
        boolean isRNReprintPreviewDisplayed=functions.checkWindow("",ControlDetails.REPRINT_PREVIEW);
        if (isRNReprintPreviewDisplayed){
            Log.writelog("Rn Reprint preview displayed ");
        }else {Log.writelog("RN Reprint not displayed ");}
        key.esc();

    }

    @AfterTest()
    public void close() {
        functions.close_screen();
    }

}
