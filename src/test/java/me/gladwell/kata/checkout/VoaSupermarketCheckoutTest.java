package me.gladwell.kata.checkout;

import static com.google.common.collect.Sets.newHashSet;
import static me.gladwell.kata.checkout.IndividualPricingRule.pricingRuleFor;
import static me.gladwell.kata.checkout.MultiItemPricingRule.multiItemPricingRuleFor;
import static org.junit.Assert.*;

import java.util.Set;

import me.gladwell.kata.checkout.KataSupermarketCheckout.Transaction;

import org.junit.Test;

public class VoaSupermarketCheckoutTest {

    private static final Item APPLE = new Item("Apple");
    private static final Item ORANGE = new Item("Orange");

    private KataSupermarketCheckout checkout = new KataSupermarketCheckout();

    @Test
    public void calculateTotalPriceOfIndividuallyPricedItems() {
        Set<PricingRule> thisWeeksPrices = newHashSet(
            pricingRuleFor(APPLE).atPrice(60),
            pricingRuleFor(ORANGE).atPrice(25),
            multiItemPricingRuleFor(ORANGE).buy(3).forPrice(50)
        ); 

        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(APPLE)
                .scan(APPLE)
                .scan(ORANGE)
                .scan(APPLE);

        assertEquals(205, checkout.calculateTotalPrice(transaction));
    }

    @Test
    public void calculateAppleBogof() {
        Set<PricingRule> thisWeeksPrices = newHashSet(
                pricingRuleFor(APPLE).atPrice(60),
                pricingRuleFor(ORANGE).atPrice(25),
                multiItemPricingRuleFor(APPLE).buy(2).forPrice(60)
            ); 

        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(APPLE)
                .scan(APPLE);

        assertEquals(60, checkout.calculateTotalPrice(transaction));
    }

    @Test
    public void calculateOrange3For2() {
        Set<PricingRule> thisWeeksPrices = newHashSet(
                pricingRuleFor(APPLE).atPrice(60),
                pricingRuleFor(ORANGE).atPrice(25),
                multiItemPricingRuleFor(ORANGE).buy(3).forPrice(50)
            ); 

        Transaction transaction = checkout.startTransaction(thisWeeksPrices)
                .scan(ORANGE)
                .scan(ORANGE)
                .scan(ORANGE);

        assertEquals(50, checkout.calculateTotalPrice(transaction));
    }

}
