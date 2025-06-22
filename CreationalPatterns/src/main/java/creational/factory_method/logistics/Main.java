package creational.factory_method.logistics;

import creational.factory_method.logistics.creator.Logistics;
import creational.factory_method.logistics.creator.RoadLogistics;
import creational.factory_method.logistics.creator.SeaLogistics;

public class Main {
    public static void main(String[] args) {
        Logistics logistics;

        logistics = new RoadLogistics();
        logistics.planDelivery();

        logistics = new SeaLogistics();
        logistics.planDelivery();
    }
}
