package me.gladwell.itv.kata;

import static com.google.common.collect.Maps.newHashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.gladwell.itv.kata.PricingRule.Unit;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class KataSupermarketCheckout {

    public class Transaction {

        private final Set<PricingRule> pricingRules;
        private final Map<Item, Long> shoppingBasket = new HashMap<Item, Long>();

        Transaction(Set<PricingRule> pricingRules) {
            super();
            this.pricingRules = pricingRules;
        }

        public Transaction scan(Item item) {
            Long amount = shoppingBasket.get(item);

            if(amount != null) {
                amount++;
            } else {
                amount = 1L;
            }

            shoppingBasket.put(item, amount);
            return this;
        }
    }

    public Transaction startTransaction(final Set<PricingRule> pricingRules) {
        return new Transaction(pricingRules);
    }

    public long calculateTotalPrice(final Transaction transaction) {
        final List<PricingRule> orderedPricingRules = orderPricingRules(transaction.pricingRules);
        final Map<Item, Long> shoppingBasket = newHashMap(transaction.shoppingBasket);
        int totalPrice = 0;

        for(PricingRule rule : orderedPricingRules) {
            if(shoppingBasket.containsKey(rule.getItem())) {
                long amount = shoppingBasket.get(rule.getItem());
                Unit unit = rule.apply(amount);
                totalPrice += unit.getPrice();
                shoppingBasket.put(rule.getItem(), amount - unit.getRemainder());
            }
        }

        return totalPrice;
    }

    private List<PricingRule> orderPricingRules(Set<PricingRule> pricingRules) {
        return Ordering.natural().reverse().onResultOf(new Function<PricingRule, Integer>() {
            public Integer apply(PricingRule rule) {
                if(!rule.getClass().isAnnotationPresent(Order.class)) {
                    return 0;
                } else {
                    Order order = rule.getClass().getAnnotation(Order.class);
                    return order.value();
                }
            }
        }).sortedCopy(pricingRules);
    }

}
