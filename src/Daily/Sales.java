package Daily;

import Masters.MasterAttributes;
import Transactions.PurchaseEntry;
import org.testng.annotations.Test;
import test.Functions;
import test.KeyStrokes;

import java.io.FileNotFoundException;

@Test
public class Sales {
    private Functions functions = new Functions();
    private KeyStrokes key = new KeyStrokes();
    private Transactions.Sales sales = new Transactions.Sales();
    private PurchaseEntry purchaseEntry = new PurchaseEntry();

    public void nee() throws FileNotFoundException {
        String salestitle = "GoFrugal RPOS7 (Client) - 10.0.3.211\\GFT - [bill entry screen]";
        sales.open_screen();
        for (int n = 0; n <= 10; n++) {
            key.typeIn(salestitle, "12323");
            key.typeIn(salestitle, KeyStrokes.ENTER);
            key.typeIn(salestitle, "1");
            key.typeIn(salestitle, KeyStrokes.ENTER);
            key.typeIn(salestitle, "4155");
            key.typeIn(salestitle, KeyStrokes.ENTER);
            key.typeIn(salestitle, "1");
            key.typeIn(salestitle, KeyStrokes.ENTER);
            functions.saveBillAs(MasterAttributes.MODE_CASH);
            key.waitw(1000);

        }
    }

    public void pur() {
        try {
            purchaseEntry.open_screen();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
