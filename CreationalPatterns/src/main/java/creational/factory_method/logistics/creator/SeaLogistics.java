package creational.factory_method.logistics.creator;

import creational.factory_method.logistics.transport.Ship;
import creational.factory_method.logistics.transport.Transport;

public class SeaLogistics extends Logistics {
    public Transport createTransport() {
        return new Ship();
    }
}
