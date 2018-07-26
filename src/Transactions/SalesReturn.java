package Transactions;

import Masters.MasterAttributes;
import Utils.Log;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SalesReturn {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN=functions.setScreen(AppDetails.SALES_RETURN);

    @BeforeTest
    public void open_screen() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openSalesReturn();
        functions.waitForScreen(SCREEN,5);

    }
    @Test
    public void salesReturnTrans() throws FileNotFoundException {
        Log.startlog(AppDetails.SALES_RETURN);
        //To delete
        functions.focusOn(SCREEN,"");
        key.pressUpFor(3);
        key.pressDelFor(1);
        key.pressDwnFor(1);
        key.pressDelFor(1);
        key.pressDwnFor(2);

        key.typeIn(SCREEN,KeyStrokes.ENTER);
        key.waitw(500);
        key.typeLOV(KeyStrokes.LCTRL);
        key.waitw(500);
        key.pressDwnFor(2);key.type(" ");
        key.typeInLOV(MasterAttributes.CUSTOMER_VICKY);
        key.waitw(2000);
        key.typeLOV(KeyStrokes.ENTER);
        key.typeLOV(KeyStrokes.ENTER);
// save Return
        key.f6();
        key.wait(1000);
        key.pressEnter();
        key.left();
        key.pressEnter();
        key.waitw(3000);
        Log.endlog(SCREEN);
    }
    @AfterTest
    public void closeScreen(){functions.close_screen();}
}
