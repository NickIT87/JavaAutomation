package creational.factory_method.logistics.transport;

public class Ship implements Transport {
    public void deliver() {
        System.out.println("Delivery by sea in a container.");
    }
}
