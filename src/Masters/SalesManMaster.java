package Masters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;

public class SalesManMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();

    @BeforeMethod
    public void open_master() {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.SALESMAN_MASTER);
        functions.waitFor(AppDetails.SALESMAN_MASTER, 5);
    }

    @Test
    public void create_masters() {
        List<String> Sm = new ArrayList<>();
        {
            Sm.add(MasterAttributes.SALESMAN_RAM);
            Sm.add(MasterAttributes.SALESMAN_SAM);
            Sm.add(MasterAttributes.SALESMAN_KARAM);
        }
        for (String mast : Sm) {
            key.wait(1000);
            key.pressBack();
            key.type(""+mast);
            key.pressEnter();
            if (mast==MasterAttributes.SALESMAN_SAM)
            {
                key.pressBack();
                key.type("1");
                key.pressEnter();
            }
            else if (mast==MasterAttributes.SALESMAN_KARAM)
            {
                key.pressBack();
                key.type("2");
                key.pressEnter();
            }

            key.f6();
        }

        }
    @AfterMethod
    public void close_master() {
        key.esc();
    }
}
