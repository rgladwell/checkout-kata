package me.gladwell.itv.kata;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class IndividualPricingRuleTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(IndividualPricingRule.class).verify();
    }

}
