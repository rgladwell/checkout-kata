package me.gladwell.itv.kata;

public final class Item {

    private final String sku;

    public Item(String sku) {
        super();
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
        Item other = (Item) obj;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return sku;
    }

}
