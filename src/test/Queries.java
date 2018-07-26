package test;

public class Queries {
    public static String WR_INSTALL_STATUS="select prop_value from project_properties where prop_name = 'WR_INSTALLATION_STATUS_POSTED_TO_SAM'";

    public static String UPDATE_PROJ_PROP_FRLABTEST="UPDATE PROJECT_PROPERTIES SET PROP_VALUE = 'http://labtest.gofrugal.com' WHERE PROP_NAME = 'SAM_URL'";

    public static String UPDATE_PROP_VALUE="update project_properties set prop_value = '1' where prop_name = 'WR_INSTALLATION_STATUS_POSTED_TO_SAM'";

    public static String CHECK_FIRSTSYNC_STATUS="select WI_MESSAGE from websync_info where WI_MESSAGE like '%last data sync is success%'";
}
