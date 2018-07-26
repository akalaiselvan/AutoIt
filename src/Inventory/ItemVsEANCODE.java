package Inventory;

import Masters.MasterAttributes;
import Utils.Log;
import javafx.scene.layout.Priority;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class ItemVsEANCODE {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN=functions.setScreen(AppDetails.ITEMVSEANCODE);

    @BeforeTest
    public void openmaster() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.ITEMVSEANCODE);
        functions.waitForScreen(AppDetails.ITEMVSEANCODE, 5);
    }

    @Test(priority = 1)
    public void create_data() throws FileNotFoundException {
        Log.startlog(SCREEN);
        functions.click(AppDetails.ITEMVSEANCODE,"DataGridWndClass3");
        functions.pickStdItm(MasterAttributes.ITEM_SNOWSPRAY,1);
        key.type(MasterAttributes.SNOWAPRAY_EAN);
        key.pressEnter();key.pressBack();
        key.type("1000");key.pressEnter();
        key.f6();
        key.typeIn(AppDetails.CONFIRMATION_MSG,"{ENTER}");
        functions.close_screen();
    }
    @AfterTest()
    public void close()
    {functions.close_screen();}

    @Test(priority = 2)
    public void purchase_withEAN() throws FileNotFoundException {
        Log.writelog("Purchase with Ean code");
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openPurchaseScreen();
        String SCREEN=functions.setScreen(AppDetails.PURCHASE_SCREEN);
        functions.waitForScreen(SCREEN, 5000);
        key.waitw(3000);
        //item selection
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_RONCHAL,"13",21950);
        functions.selectFromLOV(MasterAttributes.ITEM_SNOWSPRAY);
        functions.waitForScreen(AppDetails.TYPE_IN_EXPIRY,2000);
        functions.fillExpiryDetails("1","1219");
        key.type("100");
        functions.fillPsm(100,1000,1000);
        key.waitw(1000);
        functions.tranSave();
        Log.writelog("purchase done");
        key.esc();

    }
    @Test(priority = 3)
    public void sales_withEAN() throws FileNotFoundException {
        key.waitw(2000);
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openSalesScreen();
        String SCREEN=functions.setScreen(AppDetails.SALES_SCREEN);
        functions.waitForScreen(SCREEN, 5000);
        key.typeIn(SCREEN,MasterAttributes.SNOWAPRAY_EAN);
        key.pressEnter();key.type("1");key.pressEnter();
        functions.saveBillAs(MasterAttributes.MODE_CASH);

    }

}
