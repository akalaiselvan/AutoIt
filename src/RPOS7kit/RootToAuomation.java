package RPOS7kit;

import AutoPatch.CloudPatchAUtomation;
import RPOS7kit.PreCheckValidations;

public class RootToAuomation {
    static PreCheckValidations preCheckValidations;
    public static void proceedTestCase(String cases) throws Exception {
        if (cases.equals("Connect plus")){
            PreCheckValidations.connectPlusValidations();
            if (PreCheckValidations.getCheckstatus().equals("fail")){
                return;
            }
            CloudPatchAUtomation.connectPlusAutomation();

        }

        if (cases.equals("patch")){
            PreCheckValidations.patchApplyValidations();
            if (PreCheckValidations.getCheckstatus().equals("fail")){
                return;
            }
            CloudPatchAUtomation.applyPatch();
        }
    }
}
