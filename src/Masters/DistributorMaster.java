package Masters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;

public class DistributorMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    @BeforeMethod
    public void open_master()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.DISTRIBUTOR_MASTER);
        functions.waitFor(AppDetails.DISTRIBUTOR_MASTERWINDOW,5);
    }
    @Test
    public void create_masters() {
        List<String> Dm = new ArrayList<>();
        {
            Dm.add(MasterAttributes.DISTRIBUTOR_SHIVLALTRADERS);
            Dm.add(MasterAttributes.DISTRIBUTOR_RONCHAL);
            Dm.add(MasterAttributes.DISTRIBUTOR_JAYKAR);
            Dm.add(MasterAttributes.DISTRIBUTOR_JK);
            for (String mast : Dm) {
                key.wait(1000);
                key.pressBack();
                key.type("" + mast);
                key.pressEnterFor(6);
                key.type("u");
                key.pressEnterFor(5);
                key.f2();
                if (mast == MasterAttributes.DISTRIBUTOR_SHIVLALTRADERS || mast == MasterAttributes.DISTRIBUTOR_RONCHAL) {
                    key.type(MasterAttributes.SELECT_STATE_TAMIL_NADU);
                } else if (mast == MasterAttributes.DISTRIBUTOR_JAYKAR) {
                    key.type(MasterAttributes.SELECT_STATE_GOA);
                } else if (mast == MasterAttributes.DISTRIBUTOR_JK) ;
                {
                    key.type(MasterAttributes.SELECT_STATE_ASSAM);
                }
                key.pressEnter();
                if (mast == MasterAttributes.DISTRIBUTOR_JAYKAR || mast == MasterAttributes.DISTRIBUTOR_JK) {
                    key.pressEnter();
                    key.f2();
                    key.type(MasterAttributes.LOCATION_INTERSTATE);
                    key.pressEnter();
                }
                key.f6();
            }


        }
    }
    @AfterMethod
    public void close_master()
    {
        key.esc();

    }
}

