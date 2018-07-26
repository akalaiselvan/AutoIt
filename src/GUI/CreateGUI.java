package GUI;


import Utils.Log;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import test.Functions;


public class CreateGUI {

    public static JTextArea writelog;
    public static JButton installationButton;
    public static JButton automationButton;
    public static JButton proceedButton;
    private static Color inPanel;
    private static Color tab1color;
    private static Color tab2color;
    private static Color kitcolor;
    public static HashMap<String, JTextField> textFields = new HashMap<>();
    Functions functions=new Functions();


    public static String[] installsetup = {"Employ_ID",
            "Contactnumber",
            "SetuLink",
            "Vertical.",
            "GstorVat"};

    public static String[] applypatch = {"PatchLink",
            "PatchVersion"};

    public static String[] restorebackupandpatch = {"Backup path",
            "Patch Lnk",
            "Patch vrsion"};


    public static String[] partnerautomationdetails = {"setup",
            "Dsf Path",
            "Partner Name",
            "Verticl",
            "Gst r Vat"};


    public static String[] cpautomationdetails = {"EmployID",
            "Cont. detail",
            "Patch Vrsion",
            "Setup Lnk",
            "Patch Lnk",
            "WR Vrsion",
            "WI Vrsion"};


    public static String[] rposautomationdetails = {"Employ ID",
            "Ph number",
            "Setup Link",
            "Vertical",
            "Gst or Vat",
            "WR Version"};

    @Test


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(Container container) throws FileNotFoundException {

        inPanel = Color.LIGHT_GRAY;
        tab1color = Color.DARK_GRAY;
        tab2color = Color.DARK_GRAY;
        kitcolor = Color.gray;


        JPanel setupDetails = new JPanel();
        setupDetails.setBackground(inPanel);
        GUI.GUIhelper.createfieldandlabel(textFields, installsetup, setupDetails);
        setupDetails.setPreferredSize(new Dimension(400, 200));


        JPanel patchDetails = new JPanel();
        patchDetails.setBackground(inPanel);
        GUI.GUIhelper.createfieldandlabel(textFields, applypatch, patchDetails);
        patchDetails.setPreferredSize(new Dimension(400, 200));


        JPanel restoreAndUpdatePatch = new JPanel();
        restoreAndUpdatePatch.setBackground(inPanel);
        GUI.GUIhelper.createfieldandlabel(textFields, restorebackupandpatch, restoreAndUpdatePatch);
        restoreAndUpdatePatch.setPreferredSize(new Dimension(400, 200));

        String[] INSTALL_DTLS = {"Set up", "patch", "restore backup and Update patch"};
        JComboBox ins = new JComboBox(INSTALL_DTLS);
        ins.setPreferredSize(new Dimension(300, 30));
        ins.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    String news = (String) ins.getSelectedItem();
                    setupDetails.setVisible(false);
                    patchDetails.setVisible(false);
                    restoreAndUpdatePatch.setVisible(false);
                    if (news.equals("Set up")) {
                        setupDetails.setVisible(true);
                        installationButton.setName(news);
                        installationButton.setText("install " + news);
                    }
                    if (news.equals("patch")) {
                        patchDetails.setVisible(true);
                        installationButton.setName(news);
                        installationButton.setText("update " + news);
                    }
                    if (news.equals("restore backup and Update patch")) {
                        restoreAndUpdatePatch.setVisible(true);
                        installationButton.setName(news);
                        installationButton.setText("Start " + news);

                    }
                }
            }
        });

        JPanel connectplus = new JPanel();
        connectplus.setBackground(inPanel);
        GUI.GUIhelper.createfieldandlabel(textFields, cpautomationdetails, connectplus);
        connectplus.setPreferredSize(new Dimension(400, 200));


        JPanel RPOS7 = new JPanel();
        RPOS7.setBackground(inPanel);
        GUI.GUIhelper.createfieldandlabel(textFields, rposautomationdetails, RPOS7);
        RPOS7.setPreferredSize(new Dimension(400, 200));


        JPanel partnerProduct = new JPanel();
        partnerProduct.setBackground(inPanel);
        GUI.GUIhelper.createfieldandlabel(textFields, partnerautomationdetails, partnerProduct);
        partnerProduct.setPreferredSize(new Dimension(400, 200));

        String[] AUTOMATION = {"Rpos7-Complete", "Connect plus", "Partner Product"};
        JComboBox inst = new JComboBox(AUTOMATION);
        inst.setPreferredSize(new Dimension(300, 30));
        inst.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED
                        || itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    String news = (String) inst.getSelectedItem();
                    RPOS7.setVisible(false);
                    connectplus.setVisible(false);
                    partnerProduct.setVisible(false);
                    if (news.equals("Rpos7-Complete")) {
                        RPOS7.setVisible(true);
                        automationButton.setName(news);
                        automationButton.setText("Start " + news + "automation");
                    }
                    if (news.equals("Connect plus")) {
                        connectplus.setVisible(true);
                        automationButton.setName(news);
                        automationButton.setText("Start " + news + "automation");
                    }
                    if (news.equals("Partner Product")) {
                        partnerProduct.setVisible(true);
                        automationButton.setName(news);
                        automationButton.setText("Start " + news + "automation");
                    }
                }
            }
        });

        //Create and set up the window.


        installationButton = new JButton();
        automationButton = new JButton();
        proceedButton = new JButton();


        installationButton.addActionListener(GUIhelper.buttonclick);
        automationButton.addActionListener(GUIhelper.buttonclick);


        JFrame frame = new JFrame("RPOS7 Kit");
        frame.setSize(500, 550);

        JTabbedPane jtp = new JTabbedPane();
        jtp.setSize(200, 200);

        writelog = new JTextArea(7, 40);
        JScrollPane scrollPane = new JScrollPane(writelog);


        JPanel tab1 = new JPanel();
        tab1.setPreferredSize(new Dimension(500, 350));
        tab1.setBackground(tab1color);
        tab1.add(ins);
        installationButton.setName(String.valueOf(ins.getSelectedItem()));
        installationButton.setText("Install " + String.valueOf(ins.getSelectedItem()));
        tab1.add(setupDetails).setVisible(true);
        tab1.add(patchDetails).setVisible(false);
        tab1.add(restoreAndUpdatePatch).setVisible(false);
        tab1.add(installationButton).setPreferredSize(new Dimension(300, 40));


        JPanel tab2 = new JPanel();
        tab2.setPreferredSize(new Dimension(500, 350));
        tab2.setBackground(tab2color);
        automationButton.setName(String.valueOf(inst.getSelectedItem()));
        automationButton.setText("Start " + String.valueOf(inst.getSelectedItem()));
        tab2.add(inst);
        tab2.add(connectplus).setVisible(false);
        tab2.add(RPOS7).setVisible(true);
        tab2.add(partnerProduct).setVisible(false);
        tab2.add(automationButton).setPreferredSize(new Dimension(300, 40));


        jtp.addTab("Automation", tab2);
        jtp.addTab("Installation", tab1);

        JPanel finals = new JPanel();
        finals.setSize(1000, 100);
        finals.setBackground(kitcolor);
        finals.add(jtp);
        finals.add(scrollPane);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(finals);
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI(new Container());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



