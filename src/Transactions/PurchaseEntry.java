package Transactions;

import Masters.MasterAttributes;
import Utils.Log;
import autoitx4java.AutoItX;
import org.testng.annotations.*;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class PurchaseEntry {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = AppDetails.PROD_TITLE + " " + "-" + " " + "[" + AppDetails.PURCHASE_SCREEN + "]";

    @BeforeTest
    public void open_screen() throws FileNotFoundException {
        //open purchase invoice screen
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreens(AppDetails.PURCHASESCREEN_DETAILS);
        functions.waitForScreen(SCREEN, 5);

    }

    @Test
    public void trans() throws FileNotFoundException {
        // Invoice 1
        key.wait(2000);
        //fill purchase details
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JAYKAR, "1", 24624.67);
        //sselect items
        functions.selectStdItm(MasterAttributes.ITEM_FLOWER_BOQUET, 100);
        functions.fillPsm(123.1234, 1234.6957, 1234.6957);
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL, 100);
        functions.fillPsm(123.1234, 1234.6957, 1234.6957);
        functions.tranSave();

        //Invoice 2
        functions.focusOn(SCREEN,"");
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JK, "2", 23493.24);
        //select tree wall clock
        functions.selectSrlItm(MasterAttributes.ITEM_TREEWALL_CLOCK, 100, "s2");
        functions.setFocusForSrlItm();
        functions.fillPsm(123.6958, 1569.9587, 1569.9587);
        //select fossil table clock
        functions.selectSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK, 100, "s2");
        functions.setFocusForSrlItm();
        key.left();
        functions.fillPsm(111.2365, 1695.6854, 1695.6854);
        functions.tranSave();

        //Invoice 3
        functions.focusOn(SCREEN,"");
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_RONCHAL, "3", 26148.15);
        functions.selectMatrixItem(MasterAttributes.ITEM_BIRHDAY_CARDS, 100);
        key.wait(1000);
        key.left();
        functions.fillPsm(123.3654, 1256.3658, 1256.3658);
        functions.selectMatrixItem(MasterAttributes.ITEM_ANNIVERSARY_CARDS, 100);
        key.wait(1000);
        key.left();
        functions.fillPsm(125.6589, 1365.6587, 1365.6587);
        functions.tranSave();
        //invoice 4
        functions.focusOn(SCREEN,"");
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_SHIVLALTRADERS, "4", 26003);
        functions.selectMatrixItem(MasterAttributes.ITEM_VALENTINE_CARDS,100);
        key.wait(1000);
        key.left();
        functions.fillPsm(123.3658, 1265.3698, 1265.3698);
        functions.selectMatrixItem(MasterAttributes.ITEM_FAREWELL_CARDS,100);
        key.wait(1000);
        key.left();
        functions.fillPsm(136.6589, 1236.6548, 1236.6548);
        functions.tranSave();

        //invoice 5
        functions.focusOn(SCREEN,"");
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_SHIVLALTRADERS, "5", 50149);
        functions.selectMatrixItemPremiumcards(100,125.3654,1369.3698,1369.3698);
        key.wait(1000);
        functions.tranSave();

        //invoice 6 expiry item
        functions.focusOn(SCREEN,"");
        key.waitw(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_RONCHAL,"21",21950);
        key.pressEnter();
        key.type(MasterAttributes.ITEM_SNOWSPRAY);
        key.pressEnter();
        functions.waitandcheck(AppDetails.TYPE_IN_EXPIRY,5);
        functions.fillExpiryDetails("exp123","329");
        key.type("100");key.pressEnter();key.type("100");
        key.pressEnter();key.type("1000");key.pressEnter();
        key.type("1000");key.pressEnter();
        functions.tranSave();
        Log.writelog("Expiry invoice done");

    }
    public void wi_Purchase(String invoice) throws FileNotFoundException {
        key.wait(2000);
        //fill purchase details
        Log.writelog("purchase trans started for before patch");
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JAYKAR, invoice, 24624.67);
        Log.writelog("lets check");
        //select items
        functions.selectStdItm(MasterAttributes.ITEM_FLOWER_BOQUET, 100);
        key.pressEnterFor(3);
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL, 100);
        key.pressEnterFor(3);
        functions.selectMatrixItem(MasterAttributes.ITEM_VALENTINE_CARDS,10);
        key.wait(1000);
        key.left();
        functions.fillPsm(200, 300, 300);
        Log.writelog("Deleting an item in between");
        key.pressUpFor(2);
        key.pressDelFor(1);
        key.waitw(1000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.typeIn(AppDetails.CONFIRMATION_MSG,KeyStrokes.LEFT);
        key.waitw(1000);
        key.altY();
        Log.writelog("item deleted");
        functions.activateWindow(SCREEN);
        key.waitw(2000);
        key.typeIn(SCREEN,KeyStrokes.ENTER);
        functions.tranSave();key.waitw(2000);
        // Edit after trans
//        key.ctrlF6();
//        functions.focusFilter();
//        key.pressUpFor(3);
//        key.typeIn(AppDetails.FILTER,invoice);
//        key.pressEnter();
//        key.altA();
//        key.pressUpFor(2);
//        key.pressDelFor(1);
//        key.waitw(2000);
//        functions.tranSave();
//        key.waitw(2000);
        Log.writelog("Before patch Purchase trans completed");
    }

    @AfterTest
    public void close_screen()
    {
        key.esc();
    }

}


