package Masters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;


public class DistributorCategoryMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    @BeforeMethod
    public void open_master()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.DISTRIBUTOR_CATEGORY_MASTER);
        functions.waitFor(AppDetails.DISTRIBUTOR_CATEGORY_MASTER,5);
    }
    @Test
    public void create_masters() {
        List<String> Dcm = new ArrayList<>();
        {
            Dcm.add(MasterAttributes.LOCATION_LOCAL);
            Dcm.add(MasterAttributes.LOCATION_INTERSTATE);
        }
        for (String mast : Dcm) {
            key.wait(1000);
            key.pressBack();
            key.type(""+mast);
            key.pressEnter();
            key.f6();
        }
        }
        @AfterMethod
    public void close_master()
        {
            key.esc();
        }
}
