package me.gladwell.itv.kata;

public interface PricingRule {

    public class Unit {
        private final long price;
        private final long remainder;

        public Unit(final long price, final long remainder) {
            super();
            this.price = price;
            this.remainder = remainder;
        }

        long getPrice() {
            return price;
        }

        long getRemainder() {
            return remainder;
        }

        @Override
        public String toString() {
            return "Unit [price=" + price + "p, remainder=" + remainder + "]";
        }
    }

    Item getItem();
    Unit apply(long amount);

}