package shop.cart;

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

            for(Product p: newProducts) {
                receiptValue += p.getPrice();
            }
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

}
