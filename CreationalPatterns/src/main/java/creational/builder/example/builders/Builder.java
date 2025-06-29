package creational.builder.example.builders;

import creational.builder.example.cars.CarType;
import creational.builder.example.components.Engine;
import creational.builder.example.components.GPSNavigator;
import creational.builder.example.components.Transmission;
import creational.builder.example.components.TripComputer;

/**
 * Builder interface defines all possible ways to configure a product.
 */
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}