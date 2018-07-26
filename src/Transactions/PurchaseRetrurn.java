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

@Test
public class PurchaseRetrurn {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    //String SCREEN =AppDetails.PROD_TITLE+" "+"-"+" "+"["+AppDetails.PURCHASE_RETURN+"]";
     private String SCREEN=functions.setScreen(AppDetails.PURCHASE_RETURN);

    @BeforeTest
    public void open_screen() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.PURCHASERET_SCREEN);
        functions.waitForScreen(SCREEN,5);

    }
    @Test
    public void trans() throws FileNotFoundException {
        Log.startlog(AppDetails.PURCHASERET_SCREEN);
        key.waitw(2000);
        functions.pickDistributor(MasterAttributes.DISTRIBUTOR_RONCHAL);
        Log.writelog("Distributor selected for purchase return");
        key.pressEnterFor(8);
        key.pressEnter();
        functions.focusFilter();
        key.pressEnter();
        key.typeLOV(KeyStrokes.ENTER);
        key.typeLOV(KeyStrokes.ENTER);
        key.typeLOV(KeyStrokes.ENTER);

        // to change the quantity
        key.pressUpFor(1);key.pressRightFor(1);
        key.typeIn(SCREEN,"10");key.pressUpFor(1);key.typeIn(SCREEN,"10");
        functions.tranSave();
        Log.endlog(AppDetails.PURCHASERET_SCREEN);
    }
    @AfterTest
    public void closeScreen(){functions.close_screen();}
}
