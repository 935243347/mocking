package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */
        //given
        //when
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        Integer price = vipParkingStrategy.calculateHourlyPrice();
        //then
        assertEquals(50, Integer.parseInt(String.valueOf(price)));

    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */


    }
}
