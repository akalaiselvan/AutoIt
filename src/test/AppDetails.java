package test;

import AutoPatch.WebInstaller;
import Utils.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static AutoPatch.WebInstaller.*;

public class AppDetails  {
    public static String fpath="C:\\Users\\admin\\Desktop\\New Text Document (3).txt";
    public static String INSTALL_DATA_PATH="C:\\Users\\admin\\Desktop\\InstallData.txt";
    public static String AUTOMATION_DATA_PATH="C:\\Users\\admin\\Desktop\\AutomationData.txt";
    public static String LOG_PATH="";
    public static String CMMD="Administrator: C:\\Windows\\system32\\cmd.exe";

//List<String> myString = new ArrayList<String>();
    /*public static List<String> ccm=new ArrayList<>();
    {
       // HashMap<String, String> rotype = new HashMap<String, String>();
        ccm.add("Loc1");
        ccm.add("Loc2");
    }*/

    //rpos7 details
    public static String APPLICATION_TITLE = "GoFrugal RPOS Installer";
    // public static String PROD_TITLE="Activa DLite POS";
    public static String PROD_TITLE="GoFrugal RPOS7";
    public static String CONFIRMATION_MSG="[CLASS:ThunderRT6FormDC]";
    public static String WI_APPLICATION_TITLE = "GoFrugal RPOS Installer";
    public static String SETUP_BROWSE ="Browse the POS setup file";
    public static String RESTORE_WINDOW=" Application restore...";
    public static String BACKUP_BROWSE="Select compressed backup source file (*.zip) ";
    public static String ZIPBACKUP_BROWSE="Browse for Folder";
    public static String LAST_TRANSCHECK="last transactions...";
    public static String MANAGE_TAB_WINDOW="Manage Tabs & Fields ...";
    public static String FILTER="Filter";
    public static String APPLY_FILTER="Apply Filter";
    public static String NEW_MSGBOX="Form1";
    public static String AUTO_DB_BACKUP="GoFrugal Auto Database Backup";
    public static String KEY_MISMATCH_WNDW="GoFrugal";

    //Installation details

    public static String GST_NO="332145698745558";
    public static String WI_ADDRESS="113,T.H Road";
    public static String WI_PINZIP="601204";






    //For Connect plus Cloud patch project public static String

    public static String REINSTALL_TEXT="I want to Re-install the application to restore my existing database backup";
    public static String RESTARTNOW_TEXT="Restart now";
    public static String RESTART_WINDOW_TEXT="System restart is mandatory to register all the new resources.";



    public static String PTYPE_LABTEST="LABTEST";
    public static String PTYPE_SAM="SAM";

    //Service user details
    public static String SUSER_NAME="sathishjaganathan";
    public static String SUSER_PW="gft12345";
    public static String SUSER_REASON="123456789123456789123456789123456789123456789";
    public static String SUSER_OTHer="hmm";

    //BACK and RESTORE functionality

    public static String BR_DASHBOARD="GoFrugal cloud data sync dashboard";
    public static String BR_WORKLIKECHARM="Backup & Restore";
    public static String BR_MSG="Congratulations!. Your Data Locker is working like a charm.";
    //services
    public static String SERVICE_GFT="MSSQL$GFT";
    public static String SERVICE_WEBSYNC="WebSync";
    public static String SERVICE_WEBCLIENT="WebClient";


    //Wi trans

    public static String PUR_INV1="WI_1";
    public static String PUR_INV2="WI_2";
    public static String RN_INV1="RN_1";
    public static String RN_INV2="RN_2";

    // API related data
    public static String RPOS7_PROF_LICENSE="500-07.0PL";
    public static String BACKup_RESTORE_LICENSE="706-01.0RCT30";

    //API URL
    public static String SAMTEST_LOGIN_API="https://labtest.gofrugal.com/ismile/ismile_login.php";
    public static String LEAD_CREATION_API = "https://labtest.gofrugal.com/ismile/new_lead.php";
    public static String CREATE_LICENSE_API = "https://labtest.gofrugal.com/ismile/order_conversion_submit.php";



    //JSON

    public static String SAMTEST_JSON="{\"type\":\"employee\",\"app_version\":\"2.6.0.0\",\"user\":\"sathishjaganathan\",\"password\":\"ebdb115480737d73c6437d85a004d020\",\"otp\":\"12345\"}\n";





    //Screens
    public static String CUSTOMER_CATEGORY_MASTER="Customer Category Master";
    public static String CUSTOMER_MASTER="Customer Master";
    public static String DEPTVSCAT_MASTER="Department Vs Cate";
    public static String DEPTVSCAT_MASTERSCREEN="Department Vs Categories..";
    public static String MANUFACTURER_MASTER="Manufacturer";
    public static String MANUFACTURER_MASTERWINDOW="Manufacturer Master";
    public static String DISTRIBUTOR_MASTER="Distributor";
    public static String DISTRIBUTOR_MASTERWINDOW="Distributor Master";
    public static String DISTRIBUTOR_CATEGORY_MASTER="Distributor Category Master";
    public static String SALESMAN_MASTER="SalesMan Master";
    public static String ITEM_MASTER="Item Master";
    public static String CATEGORY_MASTER="Category Master";
    public static String OPENING_STOCK_ENTRY="Opening Stock Entry";
    public static String PURCHASE_SCREEN="Purchase Entry";
    public static String SALES_SCREEN="bill entry screen";
    public static String RECEIPT_NOTE ="Receipt Note Entry";
    public static String PURCHASE_RETURN="Purchase Return Entry";
    public static String PURCHASERET_SCREEN="Purchase Returns";
    public static String LOGIN_SCREEN="GoFrugal RPOS7 - Login";
    public static String LIC_OK="GoFrugal";
    public static String EXPIRY_RETURN="Expiry Returns";
    public static String EXPIRY_RETURN_FILTER="Expiry Return Options";
    public static String RECEIPTNOTE_RETURN="Receipt Note Returns";
    public static String QUOTATION_SCREEN="Quotation Screen";
    public static String QUOTATION_MENU="Sales Quotation";
    public static String CONVERSION_MENU="Conversion";
    public static String CONVERSION_MASTER="Conversion Master";
    public static String ITEMVSEANCODE="Item Vs EANCODE";
    public static String ABOUT="About";
    public static String ABOUT_POS="About "+PROD_TITLE;
    public static String ADDON_SYNC="Add-on product License";
    public static String STOCK_UPDATE="Stock Update";
    public static String STOCK_UPDATION="Stock Updation";
    public static String CHANGE_SELLING="Change Selling";
    public static String CHANGE_SELLINGSCREEN="Change Selling Price";
    public static String REORDER="Reorder";
    public static String REORDER_SCREEN="Reorder filter";
    public static String UPDATE_ZERO="UPDATE ZERO";
    public static String BARCODE_PRINT="BarCode Print";
    public static String SALES_RETURN="sales return screen";
    public static String REPRINT="reprint";
    public static String PURCHASE_FORMULA="Purchase Formula";


    //For Open screen function
    public static String PURCHASE_INVOICE="Purchase Invoice";
    public static String SALES_BILL="Sales Bill";





    //Screen shortkeys

    public static String PURCHASE="{CTRLDOWN}{p}{CTRLUP}";
    public static String BARCODEPRINT="{CTRLDOWN}{b}{CTRLUP}";
    public static String SALES="{CTRLDOWN}{s}{CTRLUP}";
    public static String REPRNT="{CTRLDOWN}{t}{CTRLUP}";
    public static String RECEIPTNOTE="{CTRLDOWN}{r}{CTRLUP}";


    // 0 - Screen's shortcut , 1 - Screen name used to search in gotp tab , 2 - screen's Title name

    public static String[] PURCHASESCREEN_DETAILS={"{CTRLDOWN}{p}{CTRLUP}","Purchase Invoice","Purchase Entry"};

















    public static String FPATH = "C:\\Users\\admin\\Desktop\\AutomationData.txt";
    //public static String FPATH = "C:\\Users\\admin\\Desktop\\ConnectAutomationDetails.txt";


    //WI application path
    public static String WI_PATH = "C:\\Users\\admin\\Desktop\\Rpos7\\ConnectPlus\\Downloads\\webinstaller.exe";
    public static String POS_PATH="D:\\GoFrugal\\GoFrugalRPOS7\\RetailPOS.exe";
    public static String POS_PATH_E="E:\\GoFrugal\\GoFrugalRPOS7\\RetailPOS.exe";
    public static String PATCH_PATH = "C:\\Users\\admin\\Desktop\\Rpos7\\ConnectPlus\\Downloads\\Patch.exe";
    public static String  WEBREPORTER_PATH = "C:\\Users\\admin\\Desktop\\Rpos7\\ConnectPlus\\Downloads\\WebReporter.exe";
    public static String PROD_SETUP_PATH="C:\\Users\\admin\\Desktop\\Rpos7\\ConnectPlus\\Downloads\\GoFrugalRPOS7_AUTO.exe";
    public static String DOWNLOAD_PATH="C:\\Users\\admin\\Desktop\\Rpos7\\ConnectPlus\\Downloads";





    public static String WI_PASSWORD_BOX="ThunderRT6TextBox2";

    //patch credentials
    public static String PATCH_TERMS="Terms And Conditions";
    public static String TERMS_CHECK="ThunderRT6CheckBox1";
    public static String PATCH_COMPLETE="ServicePack";
    public static String PATCH_COMP_MSG="Service pack applied successfully. You can start using the system now, Enjoy selling.";
    public static String PATCH_SUCESS="Patch sucess";
    public static String PATCH_FAILURE="Patch fail";






    //public static void main(String[] args) throws InterruptedException {


    //Controls
    public static String TYPE_IN_EXPIRY="[CLASS:ThunderRT6FormDC]";
    public static String BACKUP_PROCESS="BackRest.exe";
    public static String POS_EXE="RetailPOS.exe";
    public static String AUTOBACKUP_EXE="DatabaseBackup.exe";


    // WR installation
    public static String WR_INSTALLATION_SUCCESS="ExitCode=0";
    public static String WR_INSTALLATION_FAILED="ExitCode=5001";

    //Purchase formula
    public static String PF_GST="GST";
    public static String PF_CASHDSICOUNT="Cash Discount";
    public static String PF_SCHEMEDISCONT="Scheme Discount";
    public static String PF_RATEDEDUC="Rate Discount";





}
//Sales screen



