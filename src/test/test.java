package test;

import AutoPatch.WebInstaller;
import Masters.MasterAttributes;
import Selenium.Selenium;
import Utils.ConnectDB;
import Utils.Log;
//import org.json.JSONException;
//import org.json.JSONObject;
import autoitx4java.AutoItX;
import jdk.nashorn.internal.runtime.URIUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IRetryAnalyzer;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.naming.Name;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class test extends TestListenerAdapter {
    public static AutoItX AI = new AutoItX();
    private static int WRCOUNT=0;
    private String x;

    static KeyStrokes key = new KeyStrokes();
    static Functions functions = new Functions();
    static ConnectDB connectDB =new ConnectDB();
    private static WebDriver driver ;



    String name;
    String secname;



        public static void cname() {
            AI.run("");
            // Call System property to get the classpath value
            String classpathStr = System.getProperty("java.class.path");
            System.out.print(classpathStr);
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }


    public static void main(String[] args) throws FileNotFoundException {

        try {
            teast();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void io() throws IOException {
        functions.minimizeAll();
        String PATCH_VERSION = Files.readAllLines(Paths.get("")).get(2);
        //functions.waitForText(AppDetails.PATCH_COMPLETE,AppDetails.PATCH_COMP_MSG,7200000);
        boolean scheck = false;
        while (scheck == false) {
            scheck = functions.checkFor(AppDetails.LOGIN_SCREEN);
            //functions.waitForScreenActive(AppDetails.PATCH_COMPLETE, AppDetails.PATCH_COMP_MSG, 1);
            functions.waitForScreenActive(AppDetails.LOGIN_SCREEN, "", 1);

            Log.writelog("oo");
        }
        Log.writelog("wait over");

    }

    public static void sales() throws FileNotFoundException {
         String q=connectDB.getValueFromQuery("select msm_rcver from med_shop_mast");
        System.out.println(q);
//        functions.minimizeAll();
//        String SCREEN = functions.setScreen(AppDetails.SALES_SCREEN);
//        functions.activateWindow(AppDetails.PROD_TITLE);
//        functions.openSalesScreen();
//        functions.waitForScreen(SCREEN, 5);
//        int q;
//        for (q = 0; q <= 5; q++) {
//            key.pressEnter();
//            key.typeInLOV("{ENTER}");
//            functions.saveBillAs(MasterAttributes.MODE_CASH);
//            key.waitw(2);
//        }
    }
    public static void mainConfigLinkChange() throws InterruptedException, IOException
{
    File Dpath = new File("D:\\GoFrugal\\GoFrugalRPOS7\\websync\\MainConfig.exe.config");
    String rep="abc";
    String rep1="def";
    String c="1";
    String d="2";
    List<String> Catm = new ArrayList<>();
    {   Catm.add(rep);
        Catm.add(rep1);
        for(String mat:Catm)
        {
        if (Dpath.exists()) {
            Log.writelog("path found in " + Dpath);
            File fileToBeModified = new File(String.valueOf(Dpath));
            String oldContent = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            Log.writelog("" + line);
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }

if (mat==rep){
            String newContent = oldContent.replaceAll(mat, c);
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
            reader.close();
            writer.close();
            }
            if (mat==rep1){
                String newContent = oldContent.replaceAll(mat, d);
                FileWriter writer = new FileWriter(fileToBeModified);
                writer.write(newContent);
                reader.close();
                writer.close();
            }
            //oldContent.replaceAll("    <add key=\"websyncURL\" value=\"https://62824.qa.gofrugalretail.com\" />","text1");

        }
        }
    }
}
//
//    @Test
//    public static void  samtestLogin() throws IOException, JSONException {
//        // String url = "http://pcrdemo1:4567/compare?product=rpos7&posHost=10.0.0.219&customerId=32222";
//        String url = "https://labtest.gofrugal.com/ismile/ismile_login.php";
//        String JSON="{\"type\":\"employee\",\"app_version\":\"2.1.0.0\",\"user\":\"sathishjaganathan\",\"password\":\"gft12345\",\"otp\":\"12345\"}\n";
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setDoOutput(true);
//        con.setDoInput(true);
//        // optional default is GET
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-type","application/json");
//        con.setRequestProperty("Accept", "application/json");
//        OutputStream os = con.getOutputStream();
//        os.write(JSON.getBytes("UTF-8"));
//        os.close();
//        //add request header
////        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        int responseCode = con.getResponseCode();
//        Log.writelog("\nSending 'GET' request to URL : " + url);
//        Log.writelog("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        String res=response.toString();
//        JSONObject jsonObject = new JSONObject(res);
//        String key =jsonObject.getString("key");
//        String authToken=jsonObject.getString("authtoken");
//        Log.writelog("key:"+key);
//        Log.writelog("Atoken:"+authToken);
//        //print result
//        //System.out.println(response.toString());
//    }
//
//
//    @Test
//    public  static void leadCreation()  throws IOException, JSONException  {
//
//        String timeLog = new SimpleDateFormat("dd_HH_mm_ss").format(Calendar.getInstance().getTime());
//        String timeLog1 = new SimpleDateFormat("ddHHmmss").format(Calendar.getInstance().getTime());
//
//
//        String LEAD_JSON="{\n" +
//                "  \"shop_name\": \"Auto Lead"+timeLog+"\",\n" +
//                "  \"vertical\": \"1001\",\n" +
//                "  \"lead_address\": {\n" +
//                "    \"pin_code\": \"600078\",\n" +
//                "    \"addr_street\": null,\n" +
//                "    \"location_name\": null\n" +
//                "  },\n" +
//                "  \"contact_list\": [\n" +
//                "    {\n" +
//                "      \"contact_person_name\": \"Contact 1\",\n" +
//                "      \"contact_dtl\": [\n" +
//                "        {\n" +
//                "          \"contact_type\": \"1\",\n" +
//                "          \"contact_info\": \"77"+timeLog1+"\"\n" +
//                "        }\n" +
//                "      ],\n" +
//                "\"contact_designation\": \"1\",\n" +
//                "      \"contact_group\": \"1\"\n" +
//                "\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"submit_type\": \"1\"\n" +
//                "}";
//        URL obj = new URL(AppDetails.LEAD_CREATION_API);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setDoOutput(true);
//        con.setDoInput(true);
//        // optional default is GET
//        con.setRequestMethod("GET");
//        con.setRequestProperty("key","IYYH0");
//        con.setRequestProperty("authtoken","5995901045");
//        con.setRequestProperty("currentversion","2.1.0.0");
//        con.setRequestProperty("Content-type","application/json");
//        con.setRequestProperty("Accept", "application/json");
//        OutputStream os = con.getOutputStream();
//        os.write(LEAD_JSON.getBytes("UTF-8"));
//        os.close();
//        //add request header
////        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + AppDetails.LEAD_CREATION_API);
//        System.out.println("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer leadresponse = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            leadresponse.append(inputLine);
//        }
//        in.close();
//        String res=leadresponse.toString();
//        JSONObject jsonObject = new JSONObject(res);
//        String leadcode =jsonObject.getString("lead_code");
//        System.out.println("lead_code:"+leadcode);
//        //print result
//        System.out.println(leadresponse.toString());
//    }
//
//    @Test
//    public static void createLicense(String lictype) throws IOException, JSONException {
//        //String url = "https://labtest.gofrugal.com/ismile/order_conversion_submit.php";
//        String JSON="{\n" +
//                "  \"quotation_no\": \"356076718000001\",\n" +
//                "  \"action_type\": \"convert_to_order\",\n" +
//                "  \"cust_id\": \"485171\",\n" +
//                "  \"order_no\": \"999999999999159\",\n" +
//                "  \"splitable_order\": \"0\",\n" +
//                "  \"tax_mode\": \"4\",\n" +
//                "  \"currency_type\": \"INR\",\n" +
//                "  \"order_type\": [\n" +
//                "    \"0\"\n" +
//                "  ],\n" +
//                "  \"quotation_license_order\": [\n" +
//                "    {\n" +
//                "      \"product\": \""+lictype+"\",\n" +
//                "      \"quantity\": \"1\",\n" +
//                "      \"selling_price\": 12711.86,\n" +
//                "      \"discount_value\": 0,\n" +
//                "      \"discount_percent\": 0,\n" +
//                "      \"net_amount\": 12711.86,\n" +
//                "      \"ref_server\": [],\n" +
//                "      \"list_price\": 12711.86,\n" +
//                "      \"sales_tax_percent\": null,\n" +
//                "      \"service_tax_percent\": null\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"order_amount\": \"15000\",\n" +
//                "  \"payment_type\": \"2-0.00\",\n" +
//                "  \"collection_date\": \"2018-02-15\",\n" +
//                "  \"incentive_emp\": \"0-0-0\",\n" +
//                "  \"approval_day\": \"30\",\n" +
//                "  \"required_product_delivery\": \"Yes\",\n" +
//                "  \"product_delivery_by\": \"1\",\n" +
//                "  \"product_consultant\": \"1112\",\n" +
//                "  \"questions\": {\n" +
//                "    \"292\": \"No\",\n" +
//                "    \"304\": \"2018-02-01 00:01\",\n" +
//                "    \"305\": \"pp\",\n" +
//                "    \"306\": \"do\"\n" +
//                "  },\n" +
//                "  \"coupon_list\": \"2\",\n" +
//                "  \"comments\": \"sc\"\n" +
//                "}";
//        URL obj = new URL(AppDetails.CREATE_LICENSE_API);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setDoOutput(true);
//        con.setDoInput(true);
//        // optional default is GET
//        con.setRequestMethod("GET");
//        con.setRequestProperty("key","IYYH0");
//        con.setRequestProperty("authtoken","9934617044");
//        con.setRequestProperty("currentversion","2.1.0.0");
//        con.setRequestProperty("Content-type","application/json");
//        con.setRequestProperty("Accept", "application/json");
//        OutputStream os = con.getOutputStream();
//        os.write(JSON.getBytes("UTF-8"));
//        os.close();
//        //add request header
////        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + AppDetails.CREATE_LICENSE_API);
//        System.out.println("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        String res=response.toString();
//        JSONObject jsonObject = new JSONObject(res);
//        String customer_id =jsonObject.getString("cust_id");
//        System.out.println("cust_id:"+customer_id);
//        String order_no =jsonObject.getString("order_no");
//        System.out.println("order_no:"+order_no);
//        String message =jsonObject.getString("message");
//        System.out.println("message:"+message);
//
//        //print result
//        System.out.println(response.toString());
//    }
//    @Test
//    public void main()
//    {
//        try
//        {
//            // create our mysql database connection
//            //String myDriver = "org.gjt.mm.mysql.Driver";
//            String myUrl = "jdbc:mysql://localhost/RPOS7";
//            //Class.forName(myDriver);
//            Connection conn = DriverManager.getConnection(myUrl, "sa", "Fiduciary");
//
//            // our SQL SELECT query.
//            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM med_shop_mast";
//
//            // create the java statement
//            Statement st = conn.createStatement();
//
//            // execute the query, and get a java resultset
//            ResultSet rs = st.executeQuery(query);
//
//            // iterate through the java resultset
//            while (rs.next())
//            {
////                int id = rs.getInt("id");
////                String firstName = rs.getString("first_name");
////                String lastName = rs.getString("last_name");
////                Date dateCreated = rs.getDate("date_created");
////                boolean isAdmin = rs.getBoolean("is_admin");
////                int numPoints = rs.getInt("num_points");
//
//                // print the results
//                System.out.format("%s, %s, %s, %s, %s, %s\n");
//            }
//            st.close();
//        }
//        catch (Exception e)
//        {
//            System.err.println("Got an exception! ");
//            System.err.println(e.getMessage());
//        }
//    }
public void tranSaveGnric() throws FileNotFoundException {

            functions.minimizeAll();
    functions.activateWindow(AppDetails.PROD_TITLE);
    key.waitw(1000);
    Log.writelog("No");
    functions.checkAndClick("", "ThunderRT6UserControlDC3", 1);
    key.waitw(2000);
}

    public void downloadFromLink(String url,String pathwithname) throws IOException {
        URL link = new URL(url);
        File pathToSave= new File(pathwithname);
        try{
        FileUtils.copyURLToFile(link,pathToSave);
        if (pathToSave.exists()){Log.writelog(pathToSave+"Download completed");}
        else {Log.writelog(pathToSave+"Download not completed");}
        }catch (Exception e){
            Log.writelog(""+e);
            functions.exitAutomation(pathToSave+"Download failed");
        }
        Process process=Runtime.getRuntime().exec("Date 04/01/18");

    }
    public static void runCMD() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "04/02/18");
        Process process = pb.start();
        OutputStream cmdOut=process.getOutputStream();
        String out =cmdOut.toString();
        Log.writelog(out+"");

    //teast(".exe",3);
    }

    public static String teast( ) throws IOException, InterruptedException {
        String DW_PATH = "C:\\Users\\admin\\Desktop\\Rpos7\\WebReporter.exe";
        //functions.runCMD(DW_PATH + " /VERYSILENT /PRODUCT=RPOS7");
        key.waitw(5000);
        int cnt = 0;
        String FPATH = "";
        String WR_INSTALLSTATUS = "";
        File D = new File("D:\\GoFrugal\\GoFrugalRPOS7\\WebReporter\\wr_installation_status.ini");
        File E = new File("E:\\GoFrugal\\GoFrugalRPOS7\\WebReporter\\wr_installation_status.ini");
        boolean chkStatus = false;
        while (!chkStatus) {
            if (D.exists() || E.exists()) {
                if (D.exists()) {
                    cnt++;
                    Log.writelog("Waiting for Wr Installation in minutes : " + cnt);
                    key.waitw(60000);
                    if ((Files.readAllLines(Paths.get(D.toString())).get(1).equals("ExitCode=0"))
                            || Files.readAllLines(Paths.get(D.toString())).get(1).equals("ExitCode=5001")) {
                        WR_INSTALLSTATUS = Files.readAllLines(Paths.get(D.toString())).get(2);
                        chkStatus = true;
                        Log.writelog("Web reporter Installation Status : " + WR_INSTALLSTATUS);
                    }
                }
                if (E.exists()) {
                    cnt++;
                    Log.writelog("Waiting for Wr Installation in minutes : " + cnt);
                    key.waitw(60000);
                    if (Files.readAllLines(Paths.get(E.toString())).get(1).equals("ExitCode=0")
                            || Files.readAllLines(Paths.get(E.toString())).get(1).equals("ExitCode=5001")) {
                        WR_INSTALLSTATUS = Files.readAllLines(Paths.get(E.toString())).get(2);
                        chkStatus = true;
                        Log.writelog("Web reporter Installation Status : " + WR_INSTALLSTATUS);
                    }
                }

            }

            if (cnt >= 10) {
                chkStatus = true;
                Log.writelog("Web reporter Timeout reached");
            }

        }
        return WR_INSTALLSTATUS;

    }
    @Test
    public void tt() throws IOException, InterruptedException {
        try {
            functions.runInCMD("net stop GoFrugalRPOS7WRP & sc start GoFrugalRPOS7WRP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        key.waitw(60000);
      Log.writelog("Hmm completd");
      }

      @Test
      public void te(){
            functions.minimizeAll();
            functions.activateWindow("GoFrugal License Activation [ Customer Id : 1178 ]");
          String lic_screen = "GoFrugal License Activation [ Customer Id : 1178 ]";
          key.typeInControl(lic_screen,"ThunderRT6TextBox6",AppDetails.SUSER_NAME);
          key.typeInControl(lic_screen, "ThunderRT6TextBox7",AppDetails.SUSER_PW);
          key.typeInControl(lic_screen,"ThunderRT6TextBox8", AppDetails.SUSER_REASON);
          key.typeInControl(lic_screen, "ThunderRT6TextBox9",AppDetails.SUSER_OTHer);
          functions.clicks(lic_screen,"ThunderRT6UserControlDC5",2);

      }

      @Test
      public void firstsync() throws FileNotFoundException {
          for (int n=0;n<=20;){
              String WS_INFO=connectDB.getValueFromQuery(Queries.CHECK_FIRSTSYNC_STATUS);
              if (!WS_INFO.equals("")){
                  Log.writelog("First sync done in" + n + "minutes");
                  n=21;
              }
              if (n==20){
                  Log.writelog("First sync not done in" + n + "minutes");
              }
              n++;
          }

      }

      @Test
      public void checkFreeSpace() throws FileNotFoundException {
          File file =new File("c:");
          double calcSize1=file.getFreeSpace();
          Log.writelog(""+calcSize1 / 1000000000);
      }

      @Test
      public void imwaiting(){
          JFrame label=functions.showLabel("WebReporter Installation in Progress.. !");

          functions.showLabel("WebReporter Installation in Progress.. !");
          key.waitw(5000);
          functions.showLabel("WebReporter Installation in Progress.. !");
      }

    public static void setLibraryPath(String path) throws Exception {
        System.setProperty("java.library.path", path);

        //set sys_paths to null
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);
        sysPathsField.set(null, null);




        System.setProperty("java.library.path", path+"\\lib\\");
        String javaLibPath = System.getProperty("java.library.path");
        Map<String, String> envVars = System.getenv();
        System.out.println(envVars.get("Path"));
        String s =envVars.get("path");
        System.out.println(javaLibPath);
        Log.writelog(javaLibPath);





    }
    @Test
    public static void test1() throws FileNotFoundException, InterruptedException {
          String s =System.getProperty("os.arch");
          Log.writelog(s);

          //
//        Log.writelog(AppDetails.getRECEIsPTNOTE());
//        AppDetails.setRECEIsPTNOTE("Receipt Note");
//        Log.writelog(AppDetails.getRECEIsPTNOTE());
//        AppDetails.setRECEIsPTNOTE(AppDetails.getRECEIsPTNOTE());
//        Log.writelog(AppDetails.getRECEIsPTNOTE());


//        String s = "http://posdownloads/pos/downloads/RayMedi_RPOS_7000/Daily/master/03-07-2018/RPOS7SPack_03072018.exe";
//        String module="http://posdownloads/pos/downloads/RayMedi_RPOS_7000/Daily/master/03-07-2018/module_sp.exe";

//        if (s.contains("module")){
//            Log.writelog("s la iruku");
//        }
//        if (module.contains("module")){
//            Log.writelog("module la iruku");
//
//        }

    }

    @Test
    public void oppur() throws IOException, InterruptedException {
        String WR_INS_STATUS = functions.checkWebReporterInstallStatus(10);
        // change system properties as labtest
        connectDB.updateQuery(Queries.UPDATE_PROJ_PROP_FRLABTEST);
        key.waitw(2000);
//        // restart Wr service
        functions.runCMD("net stop GoFrugalRPOS7WRP & sc start GoFrugalRPOS7WRP");
        key.waitw(30000);
//        // restart POS
        functions.refreshApp();    }

        @Test
        public static void seltest() throws FileNotFoundException {

            Selenium.getProperty();
            driver=new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //driver.get("http://toolsqa.com/selenium-webdriver/implicit-explicit-n-fluent-wait/");
            driver.get("http://localhost:8482/tfa/");
// Maximize the window. ExpectedConditions.presenceOfElementLocated(By.id("password"))
            driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //driver.findElement(By.className("assistive-text")).click();
            //String s=driver.findElement(By.tagName("h1")).getText();
            //Log.writelog(s);
            //if (s.contains("HTTP Status 404 ")){
              //  Log.writelog("TFA NOT OPENING");
            //}

            WebElement un=driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Username')]"));
            un.sendKeys("admin");
            WebElement pw=driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Password')]"));
            pw.sendKeys("admin");
            driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
            WebDriverWait wait = new WebDriverWait(driver,15);
            boolean s=wait.until(ExpectedConditions.titleContains("Tax payer app"));
            if (s){
                Log.writelog("Tfa Opened");
            }else {
                Log.writelog("Tfa not opened");
            }
        }


    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

}