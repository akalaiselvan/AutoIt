package Inventory;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class ChangeSelling {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.CHANGE_SELLINGSCREEN);

    @BeforeTest
    public void openStockUpdate() throws FileNotFoundException {
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.CHANGE_SELLING);
        functions.waitForScreen(SCREEN, 5);
    }

    @Test
    void changeSellingTrans() throws FileNotFoundException {
        key.waitw(1000);
        key.typeIn(SCREEN,KeyStrokes.ENTER);
        key.typeLOV(KeyStrokes.ENTER);
        key.type("2000");
        key.pressRightFor(4);
        key.type("2000");
        functions.tranSaveGnric(AppDetails.CHANGE_SELLING);
    }

    @AfterTest()
    public void close() {
        functions.close_screen();
    }

}
