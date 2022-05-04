package main.integration;

import main.model.Receipt;

public class ReceiptPrinter {
    /**
     * Constructs a receiptPrinter.
     */
    public ReceiptPrinter() {

    }

    /**
     * Prints a receipt to system.out
     * @param receipt the receipt to print
     * @return always returns true. (if the printer was real we'd return false when we're out of paper etc).
     *
     */
    public boolean PrintReceipt(Receipt receipt) {
        System.out.println(receipt);
        return true;
    }
}
