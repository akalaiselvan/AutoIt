package Transactions;

import Masters.MasterAttributes;
import Utils.Log;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class Sales {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN=functions.setScreen(AppDetails.SALES_SCREEN);


    @BeforeMethod
    public void open_screen() throws FileNotFoundException {
        //open purchase invoice screen
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openSalesScreen();
        functions.waitForScreen(SCREEN, 5);
    }
    //private static Logger Log = Logger.getLogger(Logger.class.getName());
    @Test
    public void billing() {
       //bill1
        //Log.info("Bill 1 with Standard and serial items");
        //Log.warning("be care full");
        Reporter.log("Sucessfully started ");
        functions.selectCustomer(MasterAttributes.CUSTOMER_VICKY);
        functions.pickStdItm(MasterAttributes.ITEM_FLOWER_BOQUET,10);
        functions.pickSrlItm(MasterAttributes.ITEM_TREEWALL_CLOCK,5);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill 2
        functions.selectCustomer(MasterAttributes.CUSTOMER_SHAHANA);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,10);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,5);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill3
        functions.selectCustomer(MasterAttributes.CUSTOMER_SHAHANA);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,10);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,5);
        functions.pickMatrixItm(MasterAttributes.ITEM_BIRHDAY_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_ANNIVERSARY_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_VALENTINE_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_FAREWELL_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_VALENTINE_CARDS,10);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill 4
        functions.selectCustomerByMobileNo(MasterAttributes.CUSTOMER_VICKY_PH_NO);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,10);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,5);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill5
        //Quick add customer
        key.f11();
        key.type(MasterAttributes.QADD_CUSTOMER_HARI_PH_NO);
        key.pressEnter();
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        key.type(MasterAttributes.QADD_CUSTOMER_HARI);key.pressEnter();
        key.type(MasterAttributes.QADD_CUSTOMER_HARI_ADRESS);key.pressEnter();
        key.type(MasterAttributes.QADD_CUSTOMER_HARI_PLACE);key.pressEnter();
        key.esc();
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,10);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,1);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill6 quick add with bill limit
        key.f3();
        key.type(MasterAttributes.CUSTOMER_BALAJI);
        key.pressEnterFor(2);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,10);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,1);
        functions.pickMatrixItm(MasterAttributes.ITEM_BIRHDAY_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_ANNIVERSARY_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_VALENTINE_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_FAREWELL_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_VALENTINE_CARDS,10);
        key.f6();
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        // enter customer credentials
        key.down();
        key.type(MasterAttributes.CUSTOMER_BALAJI_ADRESS);key.pressEnter();
        key.type(MasterAttributes.CUSTOMER_BALAJI_PLACE);key.pressEnterFor(2);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill 7 without customer
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,1);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,5);
        functions.pickMatrixItm(MasterAttributes.ITEM_BIRHDAY_CARDS,1);
        functions.pickMatrixItm(MasterAttributes.ITEM_ANNIVERSARY_CARDS,1);
        functions.pickMatrixItm(MasterAttributes.ITEM_VALENTINE_CARDS,10);
        functions.pickMatrixItm(MasterAttributes.ITEM_FAREWELL_CARDS,1);
        functions.pickMatrixItm(MasterAttributes.ITEM_VALENTINE_CARDS,1);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //for premium cards
        functions.selectCustomer(MasterAttributes.CUSTOMER_BALAJI);
        functions.pickPremiumCardItem(MasterAttributes.CATEGORY_LOVELY,MasterAttributes.CATEGORY_ENGLISH,1);
        functions.pickPremiumCardItem(MasterAttributes.CATEGORY_LOVELY,MasterAttributes.CATEGORY_TAMIL,1);
        functions.pickPremiumCardItem(MasterAttributes.CATEGORY_MANEKA,MasterAttributes.CATEGORY_ENGLISH,1);
        functions.pickPremiumCardItem(MasterAttributes.CATEGORY_MANEKA,MasterAttributes.CATEGORY_TAMIL,1);
        key.pressEnter();
        key.type(MasterAttributes.ITEM_PREMIUM_CARDS);
        key.pressEnter();
        key.space();key.down();key.space();key.down();key.space();key.down();key.space();
        key.pressEnterFor(2);
        functions.saveBillAs(MasterAttributes.MODE_CASH);
    }

    public void wi_billing()throws FileNotFoundException{
        functions.selectCustomer(MasterAttributes.CUSTOMER_BALAJI);
        functions.pickStdItm(MasterAttributes.ITEM_FLOWER_BOQUET,10);
        functions.pickSrlItm(MasterAttributes.ITEM_TREEWALL_CLOCK,5);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,5);
        key.pressUpFor(2);
        key.pressDelFor(1);
        Log.writelog("Item deleted in sales");
        functions.saveBillAs(MasterAttributes.MODE_CASH);
        //bill 2
        functions.selectCustomer(MasterAttributes.QADD_CUSTOMER_HARI);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL,10);
        functions.pickSrlItm(MasterAttributes.ITEM_FOSSIL_TABLECLOCK,5);
        functions.pickStdItm(MasterAttributes.ITEM_FLOWER_BOQUET,1);
        key.pressUpFor(2);
        key.pressDelFor(1);
        Log.writelog("Item deleted in sales");
        functions.saveBillAs(MasterAttributes.MODE_CASH);
    }
    @AfterMethod
    public void close_screen() {
       functions.close_screen();
    }

}
