package creational.factory_method.logistics.creator;

import creational.factory_method.logistics.transport.Transport;

public abstract class Logistics {
    public abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}
