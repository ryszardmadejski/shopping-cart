package shop.cart;

import shop.cart.products.*;

import java.util.ArrayList;
import java.util.List;

public class Till {

    private List<Product> products;
    private int receiptValue;

    public Till() {
        products = new ArrayList<Product>();
        receiptValue = 0;
    }

    /**
     * Adds a list of shop.cart.products to the Till
     *
     * @param newProducts list of shop.cart.products to be paid for
     */
    public void addProducts(List<Product> newProducts) {
        if(newProducts != null) {
            products.addAll(newProducts);

            recalculateReceipt();
        }
    }

    /**
     * Gets the list of shop.cart.products registered in the Till
     *
     * @return current product list
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns the current value to be paid for the current shop.cart.products and clears the product list
     *
     * @return current value of shop.cart.products in pence
     */
    public int getReceiptValue() {
        return receiptValue;
    }

    private void recalculateReceipt() {
        receiptValue = 0;
        int appleCount = 0;
        int orangeCount = 0;

        for(Product p: products) {
            if(p instanceof Apple) {
                appleCount++;
                if(appleCount % 2 != 0) {
                    receiptValue += p.getPrice();
                }
            }
            else if(p instanceof Orange) {
                orangeCount++;
                if(orangeCount % 3 != 0) {
                    receiptValue += p.getPrice();
                }
            }
            else {
                receiptValue += p.getPrice();
            }
        }
    }

}
