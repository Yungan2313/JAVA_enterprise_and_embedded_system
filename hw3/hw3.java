import java.util.ArrayList;
public class hw3{
    public static void main(String[] args){
        ArrayList<CarbonFootprint> greenhouse = new ArrayList<CarbonFootprint>();
        greenhouse.add(new Building(50));
        greenhouse.add(new Bicycle(40));
        greenhouse.add(new Car(55));

        for(CarbonFootprint i : greenhouse){
            System.out.println(i.getClass().getName() + " : " + i.getCarbonFootprint());
        }
    }
}
