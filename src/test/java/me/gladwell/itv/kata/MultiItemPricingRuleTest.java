package me.gladwell.itv.kata;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class MultiItemPricingRuleTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(MultiItemPricingRule.class).verify();
    }

}
