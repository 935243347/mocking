package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    CarDaoImpl carDao;

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

    @Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        ParkingLot parkingLot = new ParkingLot("parkinglot1", 1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        Receipt receipt1 = new Receipt();
        receipt1.setCarName(car1.getName());
        receipt1.setParkingLotName(parkingLot.getName());

        Receipt receipt2 = new Receipt();
        receipt2.setCarName(car2.getName());
        receipt2.setParkingLotName(NO_PARKING_LOT);

        //when
        VipParkingStrategy vipParkingStrategyMock = spy(new VipParkingStrategy());
        vipParkingStrategyMock.park(parkingLots, car1);
        vipParkingStrategyMock.park(parkingLots, car2);
        //then
        verify(vipParkingStrategyMock, times(1)).createReceipt(any(), any());
        verify(vipParkingStrategyMock, times(1)).createNoSpaceReceipt(any());
        doReturn(receipt1).when(vipParkingStrategyMock).createReceipt(parkingLot, car1);
        doReturn(receipt2).when(vipParkingStrategyMock).createNoSpaceReceipt(car2);
        assertEquals("car1", receipt1.getCarName());
        assertEquals("car2", receipt2.getCarName());
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        Car car1 = createMockCar("car1");
        Car car2 = createMockCar("car2");
        CarDao carDao = mock(CarDaoImpl.class);
        ParkingLot parkingLot = new ParkingLot("parkinglot1", 1);
        parkingLot.getParkedCars().add(car1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        Receipt receipt2 = new Receipt();
        receipt2.setCarName(car2.getName());
        receipt2.setParkingLotName(NO_PARKING_LOT);

        //when
        when(carDao.isVip(any())).thenReturn(false);
        VipParkingStrategy vipParkingStrategyMock = spy(new VipParkingStrategy());
        vipParkingStrategyMock.park(parkingLots, car2);

        //then
        verify(vipParkingStrategyMock, times(1)).createNoSpaceReceipt(any());
        doReturn(receipt2).when(vipParkingStrategyMock).createNoSpaceReceipt(car2);
        assertEquals("car2", receipt2.getCarName());
        assertEquals("No Parking Lot", receipt2.getParkingLotName());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car1 = createMockCar("Acar1");
        ParkingLot parkingLot = new ParkingLot("parkinglot1", 1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        //when
        when(carDao.isVip(anyString())).thenReturn(true);
        boolean allow = vipParkingStrategy.isAllowOverPark(car1);

        //then
        assertTrue(allow);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car1 = createMockCar("Bcar1");
        ParkingLot parkingLot = new ParkingLot("parkinglot1", 1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        //when
        when(carDao.isVip(anyString())).thenReturn(true);
        boolean allow = vipParkingStrategy.isAllowOverPark(car1);

        //then
        assertFalse(allow);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
