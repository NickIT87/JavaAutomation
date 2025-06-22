package creational.factory_method.logistics.transport;

public class Truck implements Transport {
    public void deliver() {
        System.out.println("Delivery by land in a box.");
    }
}
