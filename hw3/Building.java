public class Building implements CarbonFootprint{
    private double m2;//每平方公尺
    private static double CarbonPerm2 = 125.0;//平均為50~125g取中間值

    public Building(double m2){
        this.m2 = m2;
    }

    public double getCarbonFootprint(){
        return m2 * CarbonPerm2;
    }
}
