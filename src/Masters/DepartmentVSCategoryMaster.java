package Masters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;


public class DepartmentVSCategoryMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    @BeforeMethod
    public void open_screen()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.DEPTVSCAT_MASTER);
        functions.waitFor(AppDetails.DEPTVSCAT_MASTERSCREEN,5);


    }
    @Test
    public void create_master()
    {
        key.wait(2000);
        key.pressTab();
        key.pressLeftFor(6);
        key.down();
        key.type("Y");
        key.pressTabFor(2);
        key.pressEnter();
        functions.focusOn(AppDetails.CONFIRMATION_MSG,"");
        key.left();
        key.pressEnter();
        key.wait(1000);
        key.pressEnter();
    }
}
