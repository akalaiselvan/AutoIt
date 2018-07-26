package test;
import Utils.Log;
import autoitx4java.AutoItX;
import org.testng.annotations.AfterMethod;

import javax.swing.*;
import java.io.FileNotFoundException;

public class KeyStrokes {
    public static String altC = "{ALTDOWN}{c}{ALTUP}";
    public static String ALTa = "{ALTDOWN}{a}{ALTUP}";
    public static String ALTg = "{ALTDOWN}{g}{ALTUP}";
    public static String ALTu = "{ALTDOWN}{u}{ALTUP}";
    public static String S_USER = "{ALTDOWN}{CTRLDOWN}{SHIFTDOWN}{l}{ALTUP}{CTRLUP}{SHIFTUP}";
    public static String TAB="{TAB}";
    public static String ESC="{ESC}";
    public static String ENTER="{ENTER}";
    public static String LCTRL="{LCTRL}";
    public static String BACK="{BACK}";
    public static String UP="{UP}";
    public static String DOWN="{DOWN}";
    public static String RIGHT="{RIGHT}";
    public static String LEFT="{LEFT}";
    public static String F6="{F6}";
    public static String F4="{F4}";






    public static AutoItX AI = new AutoItX();
    public void type(String str)
    {
        AI.send(str);
    }
    public void mouseClick(String button,int x,int y)
    {
        AI.mouseClick(button,x,y);
    }
    public void mouseDoubleClick(String button,int x,int y)
    {
        AI.mouseClick(button,x,y,2,10);
    }
    public void typeInLOV(String str){
        AI.controlFocus("Form1","","ThunderRT6TextBox5");
        AI.controlSend("Form1","","ThunderRT6TextBox5",str);
    }
    public void typeIn(String menu,String str){
        AI.controlFocus(menu,"","");
        AI.controlSend(menu,"","",str);
    }

    public void typeInScreen(String menu,String text , String str){
        AI.controlFocus(menu,text,"");
        AI.controlSend(menu,text,"",str);
    }

    public void typeInControl(String menu,String ctrl,String str){
        AI.controlFocus(menu,"","");
        AI.controlSend(menu,"",ctrl,str);
    }
    public void typeLOV(String str){
        AI.controlFocus("Form1","","ThunderRT6TextBox5");
        AI.send(str,false);
    }

//    public void typeLOV(String str){
//        AI.controlFocus("Form1","","ThunderRT6TextBox5");
//        AI.send("{"+str+"}",false);
//    }

    public void pressTab()
    {
        AI.send("{TAB}",false);

    }
    public void pressTabFor(int i)
    {
        for(int m=1;m<=i;m++) {
            AI.send("{TAB}", false);
        }
    }

    public  void pressBack()
    {
        AI.send("{BACKSPACE}",false);
    }
    public void pressEnter()
    {
        AI.send("{ENTER}", false);
        AI.sleep(1000);
    }
    public void wait(int n)
    {
        AI.sleep(1000);
    }
    public void waitw(int n)
    {
        AI.sleep(n);
    }

    public void up()
    {
        AI.send("{UP}",false);
    }
    public void right()
    {
        AI.send("{RIGHT}",false);
    }
    public void left()
    {
        AI.send("{LEFT}",false);
    }
    public void down()
    {
        AI.send("{DOWN}",false);
    }
    public void f6()
    {
        AI.send("{F6}",false);
    }
    public void f2()
    {
        AI.send("{F2}",false);
    }
    public void f3()
    {
        AI.send("{F3}",false);
    }
    public void f11()
    {
        AI.send("{F11}",false);
    }
    public void esc()
    {
        AI.send("{ESC}",false);
    }
    public void end()
    {
        AI.send("{END}",false);
    }
    public void space()
    {
        AI.send("{SPACE}",false);
    }
    public void pressEnterFor(int i)
    {
        //AI.send("{ENTER}", false);
        for(int m=1;m<=i;m++) {
            AI.send("{ENTER}", false);
        }
    }

    public void typeKeyinScreenFor(String screen,String key ,int i)
    {
        for(int m=1;m<=i;m++) {
            AI.controlFocus(screen,"","");
            AI.controlSend(screen,"","",key);
        }
    }
    public void pressDelFor(int i)
    {
        //AI.send("{ENTER}", false);
        for(int m=1;m<=i;m++) {
            AI.send("{DEL}", false);
        }
    }
    public void pressLeftFor(int i)
    {
        for(int m=1;m<=i;m++) {
            AI.send("{LEFT}",false);        }
    }
    public void pressRightFor(int i)
    {
        for(int m=1;m<=i;m++) {
            AI.send("{RIGHT}",false);        }
    }
    public void pressDwnFor(int i)
    {
        for(int m=1;m<=i;m++) {
            AI.send("{DOWN}",false);        }
    }
    public void pressUpFor(int i)
    {
        for(int m=1;m<=i;m++) {
            AI.send("{UP}",false);        }
    }
    public void keyCombination(String key)
    {
        AI.send(""+key,false);
    }

    @AfterMethod
    public void close_screen() {
        // to close purchase invoice screen
      esc();
    }
    public void altR()
    {
        AI.send("{ALTDOWN}{r}{ALTUP}",false);
    }
    public void altA()
    {
        AI.send("{ALTDOWN}{a}{ALTUP}",false);
    }
    public void altN()
    {
        AI.send("{ALTDOWN}{n}{ALTUP}",false);
    }
    public void altS()
    {
        AI.send("{ALTDOWN}{s}{ALTUP}",false);
    }
    public void altY()
    {
        AI.send("{ALTDOWN}{y}{ALTUP}",false);
    }
    public void ctrlF6()
    {
        AI.send("{CTRLDOWN}{F6}{CTRLUP}",false);
    }

    public void altF4()
    {
        AI.send("{ALTDOWN}{f4}{ALTUP}",false);
    }

    public void altC()
    {
        AI.send("{ALTDOWN}{c}{ALTUP}",false);
    }
    public void altG()
    {
        AI.send("{ALTDOWN}{g}{ALTUP}",false);
    }
    public void cTrlF4()
    {
        AI.send("{CTRLDOWN}{F4}{CTRLUP}",false);
    }
    public void paste()
    {
        AI.send("{CTRLDOWN}{v}{CTRLUP}",false);
    }

    public void pressS()
    {
        AI.send("s",false);
    }

}
