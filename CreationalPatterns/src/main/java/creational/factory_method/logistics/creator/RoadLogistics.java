package creational.factory_method.logistics.creator;

import creational.factory_method.logistics.transport.Truck;
import creational.factory_method.logistics.transport.Transport;

public class RoadLogistics extends Logistics {
    public Transport createTransport() {
        return new Truck();
    }
}
