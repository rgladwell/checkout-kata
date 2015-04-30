package me.gladwell.kata.checkout;

import static com.google.common.collect.Sets.newHashSet;
import static me.gladwell.kata.checkout.IndividualPricingRule.pricingRuleFor;
import static me.gladwell.kata.checkout.MultiItemPricingRule.multiItemPricingRuleFor;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import me.gladwell.kata.checkout.Item;
import me.gladwell.kata.checkout.KataSupermarketCheckout;
import me.gladwell.kata.checkout.PricingRule;
import me.gladwell.kata.checkout.KataSupermarketCheckout.Transaction;

import org.junit.Test;

public class SupermarketCheckoutTest {

    private static final Item A = new Item("A");
    private static final Item B = new Item("B");
    private static final Item C = new Item("C");
    private static final Item D = new Item("D");

    private KataSupermarketCheckout checkout = new KataSupermarketCheckout();

    private Set<PricingRule> thisWeeksPrices = newHashSet(
        pricingRuleFor(A).atPrice(50),
        pricingRuleFor(B).atPrice(30),
        pricingRuleFor(C).atPrice(20),
        pricingRuleFor(D).atPrice(15),
        multiItemPricingRuleFor(A).buy(3).forPrice(130),
        multiItemPricingRuleFor(B).buy(2).forPrice(45)
    ); 

    @Test
    public void calculateTotalPriceOfIndividuallyPricedItems() {
        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(A)
                .scan(D);

        assertEquals(65, checkout.calculateTotalPrice(transaction));
    }

    @Test
    public void calculateTotalPriceOfMultiPricedItems() {
        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(A)
                .scan(A)
                .scan(A)
                .scan(C);

        assertEquals(150, checkout.calculateTotalPrice(transaction));        
    }

    @Test
    public void calculateTotalPriceOfMultiPricedItemsInAnyOrder() {
        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(B)
                .scan(A)
                .scan(B);

        assertEquals(95, checkout.calculateTotalPrice(transaction));  
    }

    @Test
    public void onlyChargeForMultiplesOfOffers() {
        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(B)
                .scan(A)
                .scan(B)
                .scan(B);

        assertEquals(125, checkout.calculateTotalPrice(transaction));  
    }

}
