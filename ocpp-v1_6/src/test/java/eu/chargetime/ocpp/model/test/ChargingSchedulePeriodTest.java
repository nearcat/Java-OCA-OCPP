package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.model.core.ChargingSchedulePeriod;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class ChargingSchedulePeriodTest {
    ChargingSchedulePeriod chargingSchedulePeriod;

    @Before
    public void setUp() throws Exception {
        chargingSchedulePeriod = new ChargingSchedulePeriod();
    }

    @Test
    public void setStartPeriod_anInteger_startPeriodIsSet() {
        // Given
        Integer anInteger = 42;

        // When
        chargingSchedulePeriod.setStartPeriod(anInteger);

        // Then
        assertThat(chargingSchedulePeriod.getStartPeriod(), equalTo(anInteger));
    }

    @Test
    public void setLimit_aDouble_limitIsSet() {
        // Given
        Double aDouble = 4.2;

        // When
        chargingSchedulePeriod.setLimit(aDouble);

        // Then
        assertThat(chargingSchedulePeriod.getLimit(), equalTo(aDouble));
    }

    @Test
    public void setNumberPhases_anInteger_numberPhases() {
        // Given
        Integer anInteger = 42;

        // When
        chargingSchedulePeriod.setNumberPhases(anInteger);

        // Then
        assertThat(chargingSchedulePeriod.getNumberPhases(), equalTo(anInteger));
    }

    @Test
    public void validate_returnFlase() {
        // When
        boolean isValid = chargingSchedulePeriod.validate();

        // Then
        assertThat(isValid, is(false));
    }

    @Test
    public void validate_startPeriodAndLimitIsSet_returnTrue() {
        // Given
        chargingSchedulePeriod.setStartPeriod(42);
        chargingSchedulePeriod.setLimit(4.2);

        // When
        boolean isValid = chargingSchedulePeriod.validate();

        // Then
        assertThat(isValid, is(true));
    }

}