package Masters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;

public class CategoryMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();

    @BeforeMethod
    public void openmaster() {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.CATEGORY_MASTER);
        functions.waitFor(AppDetails.CATEGORY_MASTER, 5);
    }
    @Test
    public void createmaster() {
        List<String> Catm = new ArrayList<>();
        {
          Catm.add(MasterAttributes.CATEGORY_MANEKA);
          Catm.add(MasterAttributes.CATEGORY_LOVELY);
          Catm.add(MasterAttributes.CATEGORY_ENGLISH);
          Catm.add(MasterAttributes.CATEGORY_TAMIL);
          for(String mat:Catm)
          {
              key.wait(1000);
              key.f2();
              if (mat==MasterAttributes.CATEGORY_ENGLISH||mat==MasterAttributes.CATEGORY_TAMIL)
              {key.down();}
              key.pressEnter();
              key.down();
              key.pressBack();
              key.type(""+mat);
              key.pressEnter();
              key.wait(500);
              key.f6();
          }

        }
    }
    @AfterMethod
    public void closemastr()
    {
        key.esc();
    }
}
