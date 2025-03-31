package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShoppingCartAddProductToCartTests {

    static Stream<Arguments> addProductToCartTestDataStream() {
        return Stream.of(
                // 10 apple + 5 banana => 2 products 10 apple 5 banana:
                Arguments.of(1, "apple", 1.0, 10.0, 2, "banana", 1.0, 5.0, 2, 10, 5),

                // 10 apple + 5 apple => 1 product 15 apple 0 banana:
                Arguments.of(1, "apple", 1.0, 10.0, 1, "apple", 1.0, 5.0, 1, 15, 0),

                // 0 apple + 5 apple => 1 product 5 apple 0 banana:
                Arguments.of(1, "apple", 1.0, 0.0, 1, "apple", 1.0, 5.0, 1, 5, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("addProductToCartTestDataStream")
    public void addProductToCartTest(
            int productId1, String productName1, double productPrice1, double productQuantity1,
            int productId2, String productName2, double productPrice2, double productQuantity2,
            int expectedShoppingCartSize, int expectedProduct1QuantityInCart, int expectedProduct2QuantityInCart) {

        Product product1 = new Product(productId1, productName1, productPrice1, productQuantity1);
        Product product2 = new Product(productId2, productName2, productPrice2, productQuantity2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        shoppingCart.addProductToCart(product2);

        Assertions.assertEquals(expectedShoppingCartSize, shoppingCart.getProducts().size());
        Assertions.assertEquals(expectedProduct1QuantityInCart, shoppingCart.getProducts().get(0).getQuantity());
        Assertions.assertEquals(expectedProduct2QuantityInCart, shoppingCart.getProducts().get(1).getQuantity());


    }
}
