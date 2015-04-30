package me.gladwell.kata.checkout;

import me.gladwell.kata.checkout.IndividualPricingRule;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class IndividualPricingRuleTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(IndividualPricingRule.class).verify();
    }

}
