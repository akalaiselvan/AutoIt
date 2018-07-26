package Transactions;
import Masters.MasterAttributes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import Utils.Log;
import java.io.FileNotFoundException;

public class ReceiptNoteReturns {
    private KeyStrokes key = new KeyStrokes();
    private Functions functions = new Functions();
    private String SCREEN = functions.setScreen(AppDetails.RECEIPTNOTE_RETURN);

    @BeforeTest
    public void open_screen() throws FileNotFoundException {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.RECEIPTNOTE_RETURN);
        functions.waitForScreen(SCREEN, 5);

    }
    @Test
    public void trans() throws FileNotFoundException {
        Log.startlog(AppDetails.RECEIPTNOTE_RETURN);
        key.waitw(2000);
        functions.pickDistributor(MasterAttributes.DISTRIBUTOR_JAYKAR);
        key.pressEnter();
        key.type("Poor Quality");
        key.pressEnterFor(7);
        functions.pickStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,10);
        key.pressEnterFor(3);
        functions.tranSaveGnric("");

    }
    @AfterTest
    public void closescreen(){key.esc();}
}