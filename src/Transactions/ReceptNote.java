package Transactions;

import Masters.MasterAttributes;
import Utils.Log;
import autoitx4java.AutoItX;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class ReceptNote {



    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    String SCREEN=functions.setScreen(AppDetails.RECEIPT_NOTE);


    @BeforeMethod
    public void open_screen() throws FileNotFoundException {
        //open purchase invoice screen
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openReceiptNote();
        functions.waitForScreen(SCREEN,5);    }
@Test
    public void trans(){
        //RN1
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JAYKAR,"1",74200);
        functions.selectStdItm(MasterAttributes.ITEM_FLOWER_BOQUET,100);
        functions.fillPsm(123.3698,1345.99,1345.99);
        functions.selectMatrixItem(MasterAttributes.ITEM_ANNIVERSARY_CARDS,100);
        key.left();
        functions.fillPsm(136.6547,1325,1325);
        functions.selectSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,100,"s3");
        functions.rnSave();
        //RN2

        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JAYKAR,"2",21950);
        functions.selectMatrixItemPremiumcards(100,113.3214,1654.3214,1654.3214);
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,100);
        functions.fillPsm(111.3256,1563.3214,1563.3214);
        functions.rnSave();
        //RN3
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JAYKAR,"3",21950);
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,100);
        functions.rnSave();
        //close RN screen
          key.esc();
        //Purchase from Rn
          key.wait(2000);
          functions.openPurchaseScreen();
          functions.purchaseFromRN(MasterAttributes.DISTRIBUTOR_JAYKAR,15);
          functions.rnSave();
        //Purchase from rn
          key.wait(2000);
          functions.purchaseFromRN(MasterAttributes.DISTRIBUTOR_JAYKAR,16);
          functions.rnSave();

    }
    public void wi_trans(String invoice) throws FileNotFoundException {
        Log.writelog("Rn trans started");
        key.wait(2000);
        functions.fillPurchaseDetails(MasterAttributes.DISTRIBUTOR_JAYKAR,invoice,74200);
        functions.selectStdItm(MasterAttributes.ITEM_FLOWER_BOQUET,100);
        key.pressEnterFor(3);
        functions.selectMatrixItem(MasterAttributes.ITEM_ANNIVERSARY_CARDS,100);
        key.left();
        functions.fillPsm(136.6547,1325,1325);
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,100);
        key.pressEnterFor(4);
        Log.writelog("Deleting an item in between");
        key.pressUpFor(2);
        key.pressDelFor(1);
        key.waitw(1000);
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.typeIn(AppDetails.CONFIRMATION_MSG,key.LEFT);
        key.waitw(1000);
        key.altY();
        Log.writelog("item deleted");
        functions.activateWindow(SCREEN);
        key.waitw(2000);
        functions.click(SCREEN,"");
        functions.rnSave();
        Log.writelog("Rn trans completed");

    }
    @AfterMethod
    public void close_screen() {
        key.esc();
    }
}