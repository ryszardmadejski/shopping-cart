package shop.cart;

import org.junit.Before;
import shop.cart.Till;
import org.junit.Test;
import shop.cart.products.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TillTest {

    Till till;

    @Before
    public void init() {
        till = new Till();
    }

    @Test
    public void addProductList_multipleProducts() {
        List<Product> productList = Arrays.asList(
                new Apple(),
                new Orange(),
                new Apple()
        );

        till.addProducts(productList);
        List<Product> returnedProducts = till.getProducts();

        assertEquals("Product size mismatch", productList.size(), returnedProducts.size());
        for(int i = 0; i < productList.size(); i++) {
            assertTrue(String.format("Returned product does not match at index {0}", i), productList.get(i) == returnedProducts.get(i));
        }
    }

    @Test
     public void addProductList_empty() {
        List<Product> productList = new ArrayList<>();

        till.addProducts(productList);
        List<Product> returnedProducts = till.getProducts();
        assertEquals("Product size mismatch", 0, returnedProducts.size());
    }

    @Test
    public void addProductList_null() {
        List<Product> productList = null;

        till.addProducts(productList);
        List<Product> returnedProducts = till.getProducts();
        assertEquals("Product list size is not 0", 0, returnedProducts.size());
    }

    @Test
    public void getReceiptValue_multipleProducts() {
        List<Product> productList = Arrays.asList(
                new Apple(),
                new Orange(),
                new Orange()
        );

        till.addProducts(productList);
        int totalValue = till.getReceiptValue();
        int expectedValue = getProductListExpectedValue(productList, 0, 0);

        assertEquals(String.format("Returned products value is not equal to {0}", expectedValue), expectedValue, totalValue);
    }

    @Test
    public void getReceiptValue_multipleProductsWithAppleOffer() {
        List<Product> productList = Arrays.asList(
                new Apple(),
                new Orange(),
                new Apple()
        );

        till.addProducts(productList);
        int totalValue = till.getReceiptValue();
        int expectedValue = getProductListExpectedValue(productList, 1, 0);

        assertEquals(String.format("Returned products value is not equal to {0}", expectedValue), expectedValue, totalValue);
    }

    @Test
    public void getReceiptValue_multipleProductsWithOrangeOffer() {
        List<Product> productList = Arrays.asList(
                new Orange(),
                new Orange(),
                new Apple(),
                new Orange()
        );

        till.addProducts(productList);
        int totalValue = till.getReceiptValue();
        int expectedValue = getProductListExpectedValue(productList, 0, 1);

        assertEquals(String.format("Returned products value is not equal to {0}", expectedValue), expectedValue, totalValue);
    }

    @Test
    public void getReceiptValue_listEmpty() {
        List<Product> productList = new ArrayList<>();

        till.addProducts(productList);
        int totalValue = till.getReceiptValue();
        int expectedValue = getProductListExpectedValue(productList, 0, 0);

        assertEquals(String.format("Returned products value is not equal to {0}", expectedValue), expectedValue, totalValue);
    }

    @Test
    public void getReceiptValue_noInput() {
        int totalValue = till.getReceiptValue();

        assertEquals("Returned products value is not equal to 0", 0, totalValue);
    }

    private int getProductListExpectedValue(List<Product> products, int numApplesToDiscount, int numOrangesToDiscount) {
        int value = 0;

        for(Product p: products) {
            value += p.getPrice();
        }

        value -= numApplesToDiscount * 60;
        value -= numOrangesToDiscount * 25;

        return value;
    }
}
