package Masters;

import autoitx4java.AutoItX;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;

public class CustomerCategoryMaster {

        KeyStrokes key =new KeyStrokes();
        Functions functions= new Functions();
        @BeforeMethod
      public void open_screen()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.waitFor(AppDetails.PROD_TITLE,5);
    }
    @Test
    public void create_masters() {
        functions.openScreen(AppDetails.CUSTOMER_CATEGORY_MASTER);
        functions.waitFor(AppDetails.CUSTOMER_CATEGORY_MASTER, 5);
        //ccm.AI.controlFocus("","[TITLE:Customer Category Master;CLASS:ThunderRT6FormDC]","msvb_lib_toolbar1");
        List<String> cccm = new ArrayList<>();
        {
            cccm.add(MasterAttributes.ccm1);
            cccm.add(MasterAttributes.ccm2);
            for (String ccat : cccm) {
                key.type("" + ccat);
                key.pressEnter();
                key.f6();
                key.wait(2000);            }
        }
    }


@AfterMethod
               public void close_screen()
    {
        functions.activateWindow(AppDetails.PROD_TITLE);
        key.esc();
    }

           }
