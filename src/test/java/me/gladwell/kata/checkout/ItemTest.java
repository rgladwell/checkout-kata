package me.gladwell.kata.checkout;

import me.gladwell.kata.checkout.Item;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class ItemTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Item.class).verify();
    }

}
