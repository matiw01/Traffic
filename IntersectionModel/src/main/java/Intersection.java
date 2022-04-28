public class Intersection{

    public static void main(String[] args){
        System.out.println("Hello I'm intersection");
        Engine simulationEngine = new Engine();
        Thread simulationThread = new Thread(simulationEngine);
    }
}
