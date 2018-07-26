package Masters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;
import java.util.ArrayList;
import java.util.List;

 public class CustomerMaster {
        KeyStrokes key = new KeyStrokes();
        Functions functions = new Functions();
@BeforeMethod
        public void open_master()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.CUSTOMER_MASTER);
        functions.waitFor(AppDetails.CUSTOMER_MASTER,5);
    }

@Test()
    public void create_masters()
        {
            List<String> cccm=new ArrayList<>();
            {
                cccm.add(MasterAttributes.CUSTOMER_VICKY);
                cccm.add(MasterAttributes.CUSTOMER_SHAHANA);
                for (String ccat : cccm) {
                    key.pressBack();
                    key.type("" + ccat);
                    key.pressEnter();
                    //customer address
                    if (ccat==MasterAttributes.CUSTOMER_VICKY) {
                        key.type(MasterAttributes.CUSTOMER_VICKY_ADRESS);
                    }
                    if(ccat==MasterAttributes.CUSTOMER_SHAHANA) {
                        key.type(MasterAttributes.CUSTOMER_SHANANA_ADRESS);
                    }
                    key.pressEnter();
                    //customer place
                    if (ccat==MasterAttributes.CUSTOMER_VICKY) {
                        key.type(MasterAttributes.CUSTOMER_VICKY_PLACE);
                    }
                    if(ccat==MasterAttributes.CUSTOMER_SHAHANA) {
                        key.type(MasterAttributes.CUSTOMER_SHANANA_PLACE);
                    }
                    key.pressEnterFor(10);
                    key.f2();
                    //customer state state
                    if (ccat==MasterAttributes.CUSTOMER_VICKY) {
                        key.type(MasterAttributes.SELECT_STATE_TAMIL_NADU);
                        key.pressEnterFor(5);
                        key.pressBack();
                        key.type(MasterAttributes.CUSTOMER_VICKY_PH_NO);
                        key.pressEnter();
                        key.f6();
                        key.wait(1000);

                    }
                    if(ccat==MasterAttributes.CUSTOMER_SHAHANA) {
                        key.wait(1000);
                        key.type(MasterAttributes.SELECT_STATE_GOA);
                        key.pressEnter();
                        key.pressEnter();
                        key.type("i");
                        key.pressEnterFor(4);
                        key.pressBack();
                        key.type(MasterAttributes.CUSTOMER_SHANANA_PH_NO);
                        key.pressEnter();
                        key.f6();
                        key.wait(1000);
                    }
                }

            }
        }
     @AfterMethod
     public void close_screen(){
         key.esc();

     }
    }
