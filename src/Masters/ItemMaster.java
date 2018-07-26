package Masters;
import Utils.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AppDetails;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ItemMaster {
    KeyStrokes key = new KeyStrokes();
    Functions functions = new Functions();
    @BeforeMethod
    public void open_master()
    {
        functions.minimizeAll();
        functions.activateWindow(AppDetails.PROD_TITLE);
        functions.openScreen(AppDetails.ITEM_MASTER);
        functions.waitFor(AppDetails.ITEM_MASTER,5);
    }
    @Test
    public void create_master() throws FileNotFoundException {
        List<String> Im = new ArrayList<>();
        {
            Im.add(MasterAttributes.ITEM_FLOWER_BOQUET);
            Im.add(MasterAttributes.ITEM_GREETINGCARD_SMALL);
            Im.add(MasterAttributes.ITEM_GREETINGCARD_LARGE);
            Im.add(MasterAttributes.ITEM_TREEWALL_CLOCK);
            Im.add(MasterAttributes.ITEM_FOSSIL_TABLECLOCK);
            Im.add(MasterAttributes.ITEM_ATOMIC_CLOCKFANCY);
            Im.add(MasterAttributes.ITEM_BIRHDAY_CARDS);
            Im.add(MasterAttributes.ITEM_ANNIVERSARY_CARDS);
            Im.add(MasterAttributes.ITEM_VALENTINE_CARDS);
            Im.add(MasterAttributes.ITEM_FAREWELL_CARDS);
            Im.add(MasterAttributes.ITEM_PREMIUM_CARDS);
            Im.add(MasterAttributes.ITEM_SNOWSPRAY);
        }

        for (String mast : Im )  {
            String ptype=null;
            //attribute enable for specific items
            if (mast==MasterAttributes.ITEM_SNOWSPRAY) {
                key.cTrlF4();
                functions.waitandcheck(AppDetails.MANAGE_TAB_WINDOW, 5);
                key.pressTab();
                key.pressDwnFor(7);
                key.space();
                key.altA();
                Log.writelog("Expiry field enabled");
                functions.waitFor(AppDetails.ITEM_MASTER,5);
            }

            // to set product type
            if (mast==MasterAttributes.ITEM_FLOWER_BOQUET||mast==MasterAttributes.ITEM_GREETINGCARD_SMALL
              ||mast==MasterAttributes.ITEM_GREETINGCARD_LARGE||mast==MasterAttributes.ITEM_SNOWSPRAY)
                {ptype=MasterAttributes.PTYPE_STANDARD;}
       else if (mast==MasterAttributes.ITEM_TREEWALL_CLOCK||mast==MasterAttributes.ITEM_FOSSIL_TABLECLOCK
              ||mast==MasterAttributes.ITEM_ATOMIC_CLOCKFANCY)
                {ptype=MasterAttributes.PTYPE_SERIALIZED;}
       else if (mast==MasterAttributes.ITEM_BIRHDAY_CARDS||mast==MasterAttributes.ITEM_ANNIVERSARY_CARDS
              ||mast==MasterAttributes.ITEM_VALENTINE_CARDS||mast==MasterAttributes.ITEM_FAREWELL_CARDS
              ||mast==MasterAttributes.ITEM_PREMIUM_CARDS)
                {ptype=MasterAttributes.PTYPE_MATRIX;}

            key.wait(2000);
            key.pressBack();
            key.type(""+mast);
            key.pressEnter();
            key.f2();
            key.type(ptype);
            if (mast==MasterAttributes.ITEM_SNOWSPRAY){
                key.pressEnterFor(2);
                key.f2();
                key.pressUpFor(1);
            }
                key.pressEnterFor(8);
                key.wait(1000);
                key.pressBack();
            // enter hsn code
            if (mast==MasterAttributes.ITEM_FLOWER_BOQUET)
            {key.type(MasterAttributes.HSN1);}
            else if (mast==MasterAttributes.ITEM_GREETINGCARD_SMALL)
            {key.type(MasterAttributes.HSN2);}
            else if (mast==MasterAttributes.ITEM_GREETINGCARD_LARGE)
            {key.type(MasterAttributes.HSN3);}
            else if (mast==MasterAttributes.ITEM_TREEWALL_CLOCK)
            {key.type(MasterAttributes.HSN4);}
            else if (mast==MasterAttributes.ITEM_FOSSIL_TABLECLOCK)
            {key.type(MasterAttributes.HSN5);}
            else if (mast==MasterAttributes.ITEM_ATOMIC_CLOCKFANCY)
            {key.type(MasterAttributes.HSN6);}
            else if (mast==MasterAttributes.ITEM_BIRHDAY_CARDS)
            {key.type(MasterAttributes.HSN7);}
            else if (mast==MasterAttributes.ITEM_ANNIVERSARY_CARDS)
            {key.type(MasterAttributes.HSN8);}
            else if (mast==MasterAttributes.ITEM_VALENTINE_CARDS)
            {key.type(MasterAttributes.HSN9);}
            else if (mast==MasterAttributes.ITEM_FAREWELL_CARDS)
            {key.type(MasterAttributes.HSN10);}
            else if (mast==MasterAttributes.ITEM_PREMIUM_CARDS)
            {key.type(MasterAttributes.HSN11);}
            else if (mast==MasterAttributes.ITEM_SNOWSPRAY)
            {key.type(MasterAttributes.HSN12);}

            key.pressEnter();
            key.f2();

            // gst tax type selection

            if (mast==MasterAttributes.ITEM_FLOWER_BOQUET||mast==MasterAttributes.ITEM_GREETINGCARD_SMALL
              ||mast==MasterAttributes.ITEM_BIRHDAY_CARDS||mast==MasterAttributes.ITEM_ANNIVERSARY_CARDS
              ||mast==MasterAttributes.ITEM_VALENTINE_CARDS||mast==MasterAttributes.ITEM_FAREWELL_CARDS
              ||mast==MasterAttributes.ITEM_PREMIUM_CARDS)
                  { key.type(MasterAttributes.GST_5); }
            if (mast==MasterAttributes.ITEM_GREETINGCARD_LARGE||mast==MasterAttributes.ITEM_TREEWALL_CLOCK
              ||mast==MasterAttributes.ITEM_FOSSIL_TABLECLOCK||mast==MasterAttributes.ITEM_ATOMIC_CLOCKFANCY
                    ||mast==MasterAttributes.ITEM_SNOWSPRAY)
                  { key.type(MasterAttributes.GST_12); }
            key.pressEnter();


       if(ptype==MasterAttributes.PTYPE_MATRIX)
            {
            key.pressEnterFor(9);
            key.f2();
            if (mast==MasterAttributes.ITEM_VALENTINE_CARDS||mast==MasterAttributes.ITEM_FAREWELL_CARDS)
                 {key.down();}
            if (mast==MasterAttributes.ITEM_PREMIUM_CARDS)
                 {
                     key.space();
                     key.down();
                     key.space();
                 }
            key.pressEnterFor(2);
            key.f2();
            if(mast==MasterAttributes.ITEM_FAREWELL_CARDS)
                {key.down();}
            if (mast==MasterAttributes.ITEM_PREMIUM_CARDS)
                {
                    key.space();
                    key.down();
                    key.space();
                }
            key.pressEnter();
            }
            if (mast==MasterAttributes.ITEM_PREMIUM_CARDS)
            {
                key.pressTabFor(7);
                key.pressEnter();
            }
            else
            {
                key.f6();
            }
            //disabling attributes
            if (mast==MasterAttributes.ITEM_SNOWSPRAY) {
                key.waitw(2000) ;
                key.cTrlF4();
                functions.waitandcheck(AppDetails.MANAGE_TAB_WINDOW, 5);
                key.pressTab();
                key.pressDwnFor(7);
                key.space();
                key.altA();
                Log.writelog("Expiry field disabled");
                functions.waitFor(AppDetails.ITEM_MASTER,5);
            }
        }
        key.esc();

    }
 }

