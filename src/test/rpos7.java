package test;
import autoitx4java.AutoItX;
import test.AppDetails;

import javax.swing.undo.AbstractUndoableEdit;
import test.Functions;


public class rpos7    {
   // public static AutoItX AI;
    //public static AutoItX AI = new AutoItX();

public static void product_install()throws InterruptedException
{
    KeyStrokes key =new KeyStrokes();
    Functions functions= new Functions();
    functions.openApp(AppDetails.WI_PATH);
    String text_id = "WindowsForms10.EDIT.app.0.378734a5";
    String r_btn = "WindowsForms10.BUTTON.app.0.378734a9";


    String cont_btn = "WindowsForms10.BUTTON.app.0.378734a10";
    functions.waitFor("",1000);
    functions.focusOn(AppDetails.APPLICATION_TITLE,text_id);
    functions.click(AppDetails.APPLICATION_TITLE,text_id);
    key.type("1178");
    key.pressTab();
    key.type("9787086356");
    key.pressEnter();
    functions.click(AppDetails.APPLICATION_TITLE,r_btn);
    functions.click(AppDetails.APPLICATION_TITLE,cont_btn);
}

    public static void main(String[] args) throws  InterruptedException {
    try
    {
        product_install();
    }
    catch(Exception e)
        {
            System.out.println(e);
        }

    }
}






