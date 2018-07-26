package Inventory;

import Masters.MasterAttributes;
import Utils.Log;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.ControlDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

public class BarcodePrint {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.BARCODE_PRINT);

    @BeforeTest
    public void openStockUpdate() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openBarcodePrint();
    }

    @Test
    void selectItemandPrint() throws FileNotFoundException {
        key.waitw(2000);
        key.typeIn(SCREEN,KeyStrokes.ENTER);
        key.typeInLOV(MasterAttributes.ITEM_ANNIVERSARY_CARDS);
        key.typeLOV(KeyStrokes.ENTER);
        key.waitw(500);
        key.typeIn(SCREEN,"100");
        key.typeIn(SCREEN,KeyStrokes.F4);
        key.waitw(1000);
        functions.confirm_OK();
        key.waitw(1000);
        functions.confirm_YES();
        key.waitw(1000);
        functions.activateWindow(AppDetails.PROD_TITLE);

    }

    @AfterTest()
    public void close() throws FileNotFoundException {
        Log.writelog("Closing Barcode print");
        functions.close_screen();
    }

}
