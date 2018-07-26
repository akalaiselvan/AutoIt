package test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import autoitx4java.AutoItX;

import com.jacob.com.LibraryLoader;
public class Main {

    //Choose the 'JACOB' dll based on the JVM bit version.
    final String JACOB_DLL_TO_USE = System.getProperty("sun.arch.data.model").contains("32") ?
            "jacob-1.18-x86.dll" : "jacob-1.18-x64.dll";

    final String APPLICATION_TITLE = "Calculator";
    final String APPLICATION = "calc.exe";

    private AutoItX control;

    private Map<Integer,String> calcNumPad_ObjectRepository;
    private Map<String,String> calcOperPad_ObjectRepository;
    {
        //Object Repository for Calculator Numbers
        calcNumPad_ObjectRepository = new HashMap<Integer,String>();
        calcNumPad_ObjectRepository.put(0, "130");
        calcNumPad_ObjectRepository.put(1, "131");
        calcNumPad_ObjectRepository.put(2, "132");
        calcNumPad_ObjectRepository.put(3, "133");
        calcNumPad_ObjectRepository.put(4, "134");
        calcNumPad_ObjectRepository.put(5, "135");
        calcNumPad_ObjectRepository.put(6, "136");
        calcNumPad_ObjectRepository.put(7, "137");
        calcNumPad_ObjectRepository.put(8, "138");
        calcNumPad_ObjectRepository.put(9, "139");

        //Object Repository for Calculator Operators
        calcOperPad_ObjectRepository = new HashMap<String,String>();
        calcOperPad_ObjectRepository.put("+", "93");
        calcOperPad_ObjectRepository.put("-", "94");
        calcOperPad_ObjectRepository.put("*", "92");
        calcOperPad_ObjectRepository.put("/", "91");
        calcOperPad_ObjectRepository.put("=", "121");
        calcOperPad_ObjectRepository.put("clear", "81");

        //Load the jacob dll.
       // File file = new File(System.getProperty("D:\\QTPCO\\AUTOIT\\lib"), JACOB_DLL_TO_USE);
        //System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

     control = new AutoItX();
    }


    public static void main(String[] args) throws InterruptedException {

        Main ct = new Main();

        //Launch 'Calculator' application.
        ct.control.run("%windir%\\system32\\calc.exe");
        ct.control.winActivate("Calculator");
        ct.control.winWaitActive("Calculator");

        //Perform Addition
        System.out.println("Addition of 1,3 - Actual Results: "+ct.add(1,3)+",  Expected Results: 4");
        System.out.println("Addition of 17,3 - Actual Results: "+ct.add(17,3)+",  Expected Results: 20");

        //Perform Subtraction
        System.out.println("Subtraction of 5,1 - Actual Results: "+ct.subtraction(5,1)+",  Expected Results: 4");
        System.out.println("Subtraction of 90,7 - Actual Results: "+ct.subtraction(90,7)+",  Expected Results: 83");

        //Perform Multiplication
        System.out.println("Multiplication of 8,2 - Actual Results: "+ct.multiplication(8,2)+",  Expected Results: 16");
        System.out.println("Multiplication of 15,4 - Actual Results: "+ct.multiplication(15,4)+",  Expected Results: 60");

        //Perform Division
        System.out.println("Division of 9,3 - Actual Results: "+ct.division(9,3)+",  Expected Results: 3");
        System.out.println("Division of 100,2 - Actual Results: "+ct.division(100,2)+",  Expected Results: 50");

        //Close 'Calculator' application.
        ct.control.winClose("Calculator");
    }

    //Perform 'Addition'.
    public int add(int a, int b) throws InterruptedException{
        performOperation("clear");

        clickNumber(a);
        performOperation("+");
        clickNumber(b);
        performOperation("=");

        return Integer.parseInt(getResults().trim());
    }

    //Perform 'Subtraction'.
    public int subtraction(int a, int b) throws InterruptedException{
        performOperation("clear");

        clickNumber(a);
        performOperation("-");
        clickNumber(b);
        performOperation("=");

        return Integer.parseInt(getResults().trim());
    }

    //Perform 'Multiplication'.
    public int multiplication(int a, int b) throws InterruptedException{
        performOperation("clear");

        clickNumber(a);
        performOperation("*");
        clickNumber(b);
        performOperation("=");

        return Integer.parseInt(getResults().trim());
    }

    //Perform 'Division'.
    public int division(int a,int b) throws NumberFormatException, InterruptedException{
        performOperation("clear");
        clickNumber(a);
        performOperation("/");
        clickNumber(b);
        performOperation("=");

        return Integer.parseInt(getResults().trim());
    }

    //Fetch the results after performing the operations
    private String getResults() throws InterruptedException{
        Thread.sleep(1000);
        return control.winGetText(APPLICATION_TITLE);
    }

    //Click the Number in the calculator application.
    private void clickNumber(int number) throws InterruptedException{

        String sNumber = String.valueOf(number);
        for(int i = 0; i < sNumber.length(); i++) {
            control.controlClick(APPLICATION_TITLE, "", calcNumPad_ObjectRepository.get(Character.digit(sNumber.charAt(i), 10)));
            Thread.sleep(1000);
        }

    }

    //Perform operations.
    private void performOperation(String controlD) throws InterruptedException{
        control.controlClick(APPLICATION_TITLE, "", calcOperPad_ObjectRepository.get(controlD));
        Thread.sleep(1000);
    }

}