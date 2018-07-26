package Transactions;
import Masters.MasterAttributes;
import autoitx4java.AutoItX;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

public class OpeningStock {

    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();

    @BeforeMethod
    public void open_screen()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.OPENING_STOCK_ENTRY);
        String SCREEN =AppDetails.PROD_TITLE+" "+"-"+" "+"["+AppDetails.OPENING_STOCK_ENTRY+"]";
        functions.waitFor(SCREEN,5);

    }

    @Test
    public void trans()
    {
        functions.selectStdItm(MasterAttributes.ITEM_GREETINGCARD_LARGE,100);
        key.pressEnter();
        key.type("250");
        key.pressEnter();
        key.wait(1000);
        functions.selectSrlItm(MasterAttributes.ITEM_ATOMIC_CLOCKFANCY,100,"S12");
        key.left();
        key.pressEnter();
        key.type("250");
        key.pressEnter();
    }

    @AfterMethod
public void save_exit()
    {
        key.f6();
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.pressEnter();
        key.wait(1000);
        key.esc();
    }

}
