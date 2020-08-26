package parking;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    Car car = mock(Car.class);
    ParkingLot parkingLot = mock(ParkingLot.class);

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        //when
        when(car.getName()).thenReturn("car1");
        when(parkingLot.getName()).thenReturn("parkinglot1");
        when(parkingLot.isFull()).thenReturn(false);
        Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

        //then
        assertEquals("car1", receipt.getCarName());
        assertEquals("parkinglot1", receipt.getParkingLotName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        //given
        ParkingLot parkingLot1 = new ParkingLot("parkinglot1", 0);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);

        //when
        when(car.getName()).thenReturn("car1");
        Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

        //then
        assertEquals("No Parking Lot", receipt.getParkingLotName());
    }

    @Test
    @PrepareForTest(InOrderParkingStrategy.class)
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() throws Exception {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify
        to test the situation for no available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
