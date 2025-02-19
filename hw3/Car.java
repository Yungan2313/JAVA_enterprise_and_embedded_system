public class Car implements CarbonFootprint{
    private double kilogrim;//每平方公尺
    private static double CarbonPerkilo = 192.0;//單位192g

    public Car(double kilo){
        kilogrim = kilo;
    }

    public double getCarbonFootprint(){
        return kilogrim * CarbonPerkilo;
    }
}
