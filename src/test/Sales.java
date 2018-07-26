package test;

import autoitx4java.AutoItX;

public class Sales {
    public static AutoItX AI = new AutoItX();


    public static void main(String[] args) throws InterruptedException {
        Sales s = new Sales();
        Functions functions= new Functions();
        KeyStrokes key =new KeyStrokes();
        s.AI.winMinimizeAll();
       // s.AI.winActivate("GoFrugal RPOS7");
        s.AI.winActivate("GoFrugal RPOS7");
        //s.AI.controlFocus("","[CLASS:ThunderRT6FormDC]","");
        //s.AI.send("{ENTER}", false);
        //s.AI.controlClick("","","","","");
        //s.AI.controlFocus("","[TITLE:GoFrugal RPOS7;CLASS:ThunderRT6MDIForm]","ThunderRT6PictureBoxDC8");
        //s.AI.controlClick("","[TITLE:GoFrugal RPOS7;CLASS:ThunderRT6MDIForm]","ThunderRT6PictureBoxDC8");
        s.AI.controlFocus("","[TITLE:Auro DLite POS;CLASS:ThunderRT6MDIForm]","ThunderRT6PictureBoxDC8");
        s.AI.controlClick("","[TITLE:Auro DLite POS;CLASS:ThunderRT6MDIForm]","ThunderRT6PictureBoxDC8");
        s.AI.send("{CTRLDOWN}{s}{CTRLUP}",false);
        key.pressEnter();
        s.AI.send("TEST",false);
        key.pressEnter();
        key.pressEnter();
        key.f6();
        s.AI.controlFocus("","[CLASS:ThunderRT6FormDC]","");
        key.pressEnter();
        key.left();
        key.pressEnter();

    }
}