package me.gladwell.kata.checkout;

@Order(1)
public final class MultiItemPricingRule implements PricingRule {

    public static class MultiItemPricingRuleBuilder {

        private final Item item;
        private int special;

        public MultiItemPricingRuleBuilder(final Item item) {
            this.item = item;
        }

        public MultiItemPricingRuleBuilder buy(final int special) {
            this.special = special;
            return this;
        }

        public MultiItemPricingRule forPrice(final long price) {
            return new MultiItemPricingRule(item, price, special);
        }
        
    }

    public static MultiItemPricingRuleBuilder multiItemPricingRuleFor(final Item item) {
        return new MultiItemPricingRuleBuilder(item);
    }

    private final Item item;
    private final long price;
    private final int special;

    private MultiItemPricingRule(final Item item, final long price, final int special) {
        this.item = item;
        this.price = price;
        this.special = special;
    }

    public Item getItem() {
        return item;
    }

    public Unit apply(final long amount) {
        final long applies = amount / special;
        final long totalPrice = price * applies;
        return new Unit(totalPrice, applies * special);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + (int) (price ^ (price >>> 32));
        result = prime * result + special;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MultiItemPricingRule other = (MultiItemPricingRule) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (price != other.price)
            return false;
        if (special != other.special)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "item " + item + " at " + price + "p for " + special;
    }

}
