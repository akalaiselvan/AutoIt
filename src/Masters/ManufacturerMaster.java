package Masters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;


public class ManufacturerMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    @BeforeMethod
    public void open_master()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.MANUFACTURER_MASTER);
        functions.waitFor(AppDetails.MANUFACTURER_MASTERWINDOW,5);
    }
    @Test
    public void create_masters()
    {
        List<String> mdata=new ArrayList<>();
        {
            mdata.add(MasterAttributes.MANUFACTURER_MANEKACARDS);
            mdata.add(MasterAttributes.MANUFACTURER_LOVELYCARDS);
            for (String mmast : mdata) {
                key.wait(1000);
                key.type(""+mmast);
                key.pressEnter();
                key.f6();
                key.wait(1000);

            }
        }
    }
    @AfterMethod
    public void close_master()
    {
        key.esc();
    }
}
