package me.gladwell.itv.kata;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class ItemTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Item.class).verify();
    }

}
