package Inventory;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.ControlDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class StockUpdation {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.STOCK_UPDATE);

    @BeforeTest
    public void openStockUpdate() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.STOCK_UPDATION);
        functions.waitForScreen(SCREEN, 5);
    }

    @Test
    void adjustStock() throws FileNotFoundException {
        key.waitw(1000);
        functions.checkAndClick(SCREEN, ControlDetails.SU_FILTER, 2);
        key.waitw(1000);
        functions.checkAndClick(AppDetails.APPLY_FILTER, ControlDetails.SU_FILTER_CANCEl, 1);
        functions.waitFor(AppDetails.CONFIRMATION_MSG, 1);
        functions.focusOn(AppDetails.CONFIRMATION_MSG, "");
        key.pressLeftFor(1);
        key.pressEnter();
        key.f6();
        key.esc();
        key.down();
        key.type("1000");
        key.up();
        key.type("1000");
        key.waitw(1000);
        functions.tranSaveGnric("");
    }

    @AfterTest()
    public void close() {
        functions.close_screen();
    }

}
