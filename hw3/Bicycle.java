public class Bicycle implements CarbonFootprint{
    private double kilogrim;
    private static double CarbonPerKilo = 21.0;//單位 g

    public Bicycle(double kilo){
        kilogrim = kilo;
    }

    public double getCarbonFootprint(){
        return kilogrim * CarbonPerKilo;
    }
}