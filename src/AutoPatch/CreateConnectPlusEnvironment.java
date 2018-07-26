package AutoPatch;

public class CreateConnectPlusEnvironment {
    public static void main(String[] args) throws Exception {
//        WebInstaller.apiCall();
//        WebInstallerHelper.startWebInstaller();
//        WebInstallerHelper.verticalAndSetupSelection();
//        WebInstallerHelper.installSetup();
//        WebInstallerHelper.completeInstallation();
        WebInstaller.delRegistry();
        WebInstaller.changeIni();
        WebInstallerHelper.fillSuserDetails();
        WebInstaller.InstallWebReporter();
        WebInstaller.addonSync();
        WebInstaller.backupRestoreSync();
    }
}
