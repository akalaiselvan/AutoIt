package Inventory;

import Masters.MasterAttributes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.ControlDetails;
import test.Functions;
import test.KeyStrokes;
import Inventory.StockUpdation;

import java.io.FileNotFoundException;

public class Reorder {
    private KeyStrokes key = new KeyStrokes();
    private StockUpdation SU = new StockUpdation();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.REORDER);


    @Test(priority = 2)
    public void openReorder() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.REORDER);
        functions.waitForScreen(AppDetails.REORDER_SCREEN, 5000);
    }

    @Test(priority = 1)
    public void makeStockZero() throws FileNotFoundException {
        SU.openStockUpdate();
        key.pressTabFor(2);
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_SMALL, 1);
        key.right();
        key.left();
        key.type("0");
        functions.tranSaveGnric(AppDetails.UPDATE_ZERO);
        SU.close();
    }

    @Test(priority = 3)
    public void reorderForZeroQty() throws FileNotFoundException {
        key.waitw(1000);
        functions.checkAndClick(AppDetails.REORDER_SCREEN, ControlDetails.REORDER_BASEDON_CURRSTOCK, 1);
        key.pressTab();
        key.waitw(1000);
        functions.checkAndClick(AppDetails.REORDER_SCREEN, ControlDetails.GENERATE_REORDER, 2);
        key.waitw(1000);
        functions.checkAndClick("", ControlDetails.CONFIRMATION_MSG_BOX_YES, 2);
        key.waitw(1000);
        key.typeIn(SCREEN, "10");
        key.f6();
        functions.confirm_YES();
        functions.newMsgBoxOk();
        functions.waitFor(AppDetails.CONFIRMATION_MSG, 1);
        functions.focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressEnter();


    }

    @AfterTest
    public void close() {
        functions.close_screen();
    }

}
