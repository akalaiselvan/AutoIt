package GUI;

import AutoPatch.CreateConnectPlusEnvironment;
import Utils.Log;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border;
import test.Functions;
import test.AppDetails;
import AutoPatch.CloudPatchAUtomation;
import RPOS7kit.RootToAuomation;


public class GUIhelper extends JPanel {
    //private static JTextField jTextField;
    static Functions functions=new Functions();
    static CloudPatchAUtomation cloudPatchAUtomation=new CloudPatchAUtomation();
    static RootToAuomation rootToAuomation= new RootToAuomation();


    private static String[] Fieldtext;
    public static void createfieldandlabel(HashMap<String, JTextField> textFields, String[] str, JPanel panel) {
        GridBagConstraints grid = new GridBagConstraints();
        int amountOfFields = str.length; // Change this to suite your
        int i;
        for (i = 0; i < amountOfFields; i++) {
            JTextField jTextField = new JTextField();
            jTextField.setName(str[i]);
            jTextField.setToolTipText("Enter " + str[i]);
            JLabel jLabel = new JLabel(str[i]);
            if (str[i].equals("Patch Vrsion")||str[i].equals("PatchVersion")){
                jTextField.setText("RC");
            }
            CreateGUI.textFields.put(str[i], jTextField);
            jLabel.setPreferredSize(new Dimension(100, 10));
            jTextField.setLayout(new GridBagLayout());
            jTextField.setPreferredSize(new Dimension(270, 20));
            jLabel.setVisible(true);
            jTextField.setVisible(true);
            panel.add(jLabel);
            panel.add(jTextField, grid);
        }

    }

    public static ActionListener buttonclick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            File txtfile = new File("C:\\Users\\admin\\Desktop\\AutomationData.txt");
            if (txtfile.exists()) {
                txtfile.delete();
            } else {
                try {
                    txtfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String[] textToPrint = new String[0];
            String action = actionEvent.getActionCommand();
            String getButtonText = "";
            if (action.equals("Install Set up")
                    || action.equals("update patch")
                    || action.equals("Start restore backup and Update patch")) {
                getButtonText = CreateGUI.installationButton.getName();
            }
            if (action.equals("Start Rpos7-Complete")
                    || action.equals("Start Connect plusautomation")
                    || action.equals("Start Partner Productautomation")) {
                getButtonText = CreateGUI.automationButton.getName();
            }


            try {
                Log.writelog(action);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // Check under which option the buton has pressed

            if (getButtonText.equals("Set up")) {
                textToPrint = CreateGUI.installsetup;
            } else if (getButtonText.equals("patch")) {
                textToPrint = CreateGUI.applypatch;
            } else if (getButtonText.equals("restore backup and Update patch")) {
                textToPrint = CreateGUI.restorebackupandpatch;
            } else if (getButtonText.equals("Partner Product")) {
                textToPrint = CreateGUI.partnerautomationdetails;
            } else if (getButtonText.equals("Connect plus")) {
                textToPrint = CreateGUI.cpautomationdetails;
            } else if (getButtonText.equals("Rpos7-Complete")) {
                textToPrint = CreateGUI.rposautomationdetails;
            }

            int checksize = textToPrint.length;
            File logFile = new File(AppDetails.AUTOMATION_DATA_PATH);
            if (logFile.exists()) {
                logFile.delete();
            }

            // Writes data into text file

            for (int i = 0; i < CreateGUI.textFields.size(); i++) {
                JTextField jTextField;
                if (i == checksize) {
                    try {
                        if (!getButtonText.equals("Connect plus")
                                &&!getButtonText.equals("patch")){
                            functions.showWarning("Right now,Kit Designed for Connect plus automation only... '"+action+"' funtionality will be added in future");
                            return;
                        }
                        rootToAuomation.proceedTestCase(getButtonText);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
                String write = textToPrint[i];
                jTextField = CreateGUI.textFields.get(write);
                String s = jTextField.getText();
                try {
                    functions.writeToAutomationData(s);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                CreateGUI.writelog.append(write + " : " + s + "\n");
            }
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void finalize() throws Throwable {
            Log.writelog("Ennadhu idhu ");
        }
    };

    public String[] getFieldtext() {
        return Fieldtext;
    }

    public void setFieldtext(String[] fieldtext) {
        Fieldtext = fieldtext;
    }
}