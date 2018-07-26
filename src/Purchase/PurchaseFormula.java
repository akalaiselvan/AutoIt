package Purchase;

import Masters.MasterAttributes;
import Utils.Log;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;


public class PurchaseFormula {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.PURCHASE_FORMULA);

    @BeforeTest
    public void openStockUpdate() throws FileNotFoundException {
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.PURCHASE_FORMULA);
        functions.waitForScreen(SCREEN, 5);

    }

    @Test
    void createPurchaseFormula() throws FileNotFoundException {
        key.typeIn(SCREEN, "Local Purchase");
        key.typeKeyinScreenFor(SCREEN, KeyStrokes.ENTER, 2);
        key.typeIn(SCREEN, KeyStrokes.ALTa);
        key.typeKeyinScreenFor(SCREEN, KeyStrokes.ENTER, 3);
        key.typeInLOV(AppDetails.PF_CASHDSICOUNT);
        key.typeLOV(KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.DOWN);
        key.typeIn(SCREEN, KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.ALTu);

        key.typeIn(SCREEN, KeyStrokes.ENTER);
        key.waitw(500);
        key.typeInLOV(AppDetails.PF_GST);
        key.typeLOV(KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.DOWN);
        key.typeIn(SCREEN, KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.DOWN);
        key.typeIn(SCREEN, KeyStrokes.ENTER);
        key.typeIn(SCREEN, KeyStrokes.ALTu);
        //Save formula
        key.typeIn(SCREEN, KeyStrokes.F6);
        key.waitw(1000);
        functions.confirmsmall_YES();
        key.waitw(1000);
        functions.confirm_OK();

    }

    @AfterTest
    public void close() throws FileNotFoundException {
        Log.writelog("Closing Barcode print");
        functions.close_screen();
        key.waitw(500);
        functions.confirm_YES();
    }
}
