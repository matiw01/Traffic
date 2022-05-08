package Engine;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Intersection{

    Road[][] map;
    int width;
    int height;
    private final ArrayList<Road> roadArrayList = new ArrayList<>();
    private final HashMap<VehicleTarget, Pair<Double, Road>> probVehDir;
    private final ArrayList<Environment> environmentElements = new ArrayList<>();
    private final ArrayList<PedestrianPath> pedestrianPathArrayList = new ArrayList<>();
    private ArrayList<Pedestrian> pedestrianArrayList = new ArrayList<Pedestrian>(); //final??

    public Intersection(int width, int height){
        this.height = height;
        this.width = width;
        map = new Road[height][width];

        //pedestrains
        //points
        pedestrianPathArrayList.add(new PedestrianPath(0, 16, 15, null));
        pedestrianPathArrayList.add(new PedestrianPath(0, 51, 15, null));
        pedestrianPathArrayList.add(new PedestrianPath(0, 18, 50, null));
        pedestrianPathArrayList.add(new PedestrianPath(0, 49, 50, null));

        pedestrianPathArrayList.add(new PedestrianPath(2, 19, 0, PedestrianTarget.TOP_LEFT));
        pedestrianPathArrayList.add(new PedestrianPath(2, 34, 0, PedestrianTarget.TOP_CENTER));
        pedestrianPathArrayList.add(new PedestrianPath(2, 48, 0, PedestrianTarget.TOP_RIGHT));
        pedestrianPathArrayList.add(new PedestrianPath(2, 20, 66, PedestrianTarget.BOTTOM_LEFT));
        pedestrianPathArrayList.add(new PedestrianPath(2, 32, 66, PedestrianTarget.BOTTOM_CENTER_LEFT));
        pedestrianPathArrayList.add(new PedestrianPath(2, 35, 66, PedestrianTarget.BOTTOM_CENTER_RIGHT));
        pedestrianPathArrayList.add(new PedestrianPath(2, 47, 66, PedestrianTarget.BOTTOM_RIGHT));

        pedestrianPathArrayList.add(new PedestrianPath(2, 0, 18, PedestrianTarget.LEFT_UP));
        pedestrianPathArrayList.add(new PedestrianPath(2, 67, 18, PedestrianTarget.RIGHT_UP));
        pedestrianPathArrayList.add(new PedestrianPath(2, 67, 30, PedestrianTarget.RIGHT_CENTER_UP));
        pedestrianPathArrayList.add(new PedestrianPath(2, 67, 33, PedestrianTarget.RIGHT_CENTER_DOWN));
        pedestrianPathArrayList.add(new PedestrianPath(2, 67, 48, PedestrianTarget.RIGHT_DOWN));
        pedestrianPathArrayList.add(new PedestrianPath(2, 0, 48, PedestrianTarget.LEFT_DOWN));

        //horizontal
        for(int x = 17; x<20; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 14, null));}
        for(int x = 22; x<46; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 14, null));}
        for(int x = 48; x<51; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 14, null));}
        for(int x = 20; x<22; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 14, null));}
        for(int x = 46; x<48; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 14, null));}

        for(int x = 19; x<21; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 51, null));}
        for(int x = 24; x<43; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 51, null));}
        for(int x = 47; x<49; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 51, null));}
        for(int x = 21; x<24; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 51, null));}
        for(int x = 43; x<47; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 51, null));}
        //vertical
        for(int y = 16; y<19; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 15, y, null));}
        for(int y = 23; y<31; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 15, y, null));}
        for(int y = 33; y<39; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 16, y, null));}
        for(int y = 39; y<44; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 17, y, null));}
        for(int y = 48; y<50; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 17, y, null));}
        for(int y = 19; y<23; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 15, y, null));}
        for(int y = 31; y<33; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 16, y, null));}
        for(int y = 44; y<48; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 17, y, null));}

        for(int y = 16; y<19; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 52, y, null));}
        for(int y = 23; y<31; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 52, y, null));}
        for(int y = 33; y<39; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 51, y, null));}
        for(int y = 39; y<44; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 50, y, null));}
        for(int y = 48; y<50; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 50, y, null));}
        for(int y = 19; y<23; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 52, y, null));}
        for(int y = 31; y<33; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 51, y, null));}
        for(int y = 44; y<48; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 50, y, null));}

        for(int x = 1; x<15; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 18, null));}
        for(int x = 53; x<67; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 18, null));}
        for(int x = 1; x<17; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 48, null));}
        for(int x = 51; x<67; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 48, null));}
        for(int x = 53; x<67; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 30, null));}
        for(int x = 52; x<67; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 33, null));}

        for(int y = 1; y<14; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 19, y, null));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 20, y, null));}
        for(int y = 1; y<14; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 48, y, null));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 47, y, null));}
        for(int y = 1; y<14; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 34, y, null));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 32, y, null));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 35, y, null));}

        for(PedestrianPath a : pedestrianPathArrayList){
            for(PedestrianPath b : pedestrianPathArrayList){
                if((Math.abs(a.getLocation().getPos_x() - b.getLocation().getPos_x()) <= 1 && Math.abs(a.getLocation().getPos_y() - b.getLocation().getPos_y()) <= 1) && (a!=b)){
                    if(!edge_case(a, b)){
                        a.setNext(b);
                    }
                }
            }
        }

        for(PedestrianPath a : pedestrianPathArrayList){
            if(a.getType() == 2){
                a.BFS(pedestrianPathArrayList);
            }
        }
        for(PedestrianPath a : pedestrianPathArrayList){
            a.redirect();
        }


        //TODO: trams
        //TODO: roads

        this.probVehDir = new HashMap<VehicleTarget, Pair<Double, Road>>();

    }

    public ArrayList<Road> getRoadArrayList(){return this.roadArrayList;}
    public HashMap<VehicleTarget, Pair<Double, Road>> getProbVehDir(){return this.probVehDir;}
    public ArrayList<Environment> getEnvironmentElements(){return this.environmentElements;}
    public ArrayList<Pedestrian> getPedestrianArrayList(){return this.pedestrianArrayList;} //czy potrzeben
    public ArrayList<PedestrianPath> getPedestrianPathArrayList(){return this.pedestrianPathArrayList;}
    public void setPedestrianArrayList(ArrayList<Pedestrian> pedestrians){this.pedestrianArrayList = pedestrians;}

    private boolean edge_case(PedestrianPath a, PedestrianPath b){
        int ax = a.getLocation().getPos_x();
        int ay = a.getLocation().getPos_y();
        int bx = b.getLocation().getPos_x();
        int by = b.getLocation().getPos_y();
        return  (ax == 18 && ay == 14 && bx == 19 && by == 13) ||
                (ax == 20 && ay == 14 && bx == 19 && by == 13) ||
                (ax == 33 && ay == 14 && bx == 34 && by == 13) ||
                (ax == 35 && ay == 14 && bx == 34 && by == 13) ||
                (ax == 47 && ay == 14 && bx == 48 && by == 13) ||
                (ax == 49 && ay == 14 && bx == 48 && by == 13) ||
                (ax == 52 && ay == 17 && bx == 53 && by == 18) ||
                (ax == 52 && ay == 19 && bx == 53 && by == 18) ||
                (ax == 52 && ay == 29 && bx == 53 && by == 30) ||
                (ax == 51 && ay == 31 && bx == 53 && by == 30) ||
                (ax == 51 && ay == 32 && bx == 52 && by == 33) ||
                (ax == 51 && ay == 34 && bx == 52 && by == 33) ||
                (ax == 50 && ay == 47 && bx == 51 && by == 48) ||
                (ax == 50 && ay == 49 && bx == 51 && by == 48) ||
                (ax == 48 && ay == 51 && bx == 47 && by == 52) ||
                (ax == 46 && ay == 51 && bx == 47 && by == 52) ||
                (ax == 36 && ay == 51 && bx == 35 && by == 52) ||
                (ax == 34 && ay == 51 && bx == 35 && by == 52) ||
                (ax == 33 && ay == 51 && bx == 32 && by == 52) ||
                (ax == 31 && ay == 51 && bx == 32 && by == 52) ||
                (ax == 21 && ay == 51 && bx == 20 && by == 52) ||
                (ax == 19 && ay == 51 && bx == 20 && by == 52) ||
                (ax == 17 && ay == 49 && bx == 16 && by == 48) ||
                (ax == 17 && ay == 47 && bx == 16 && by == 48) ||
                (ax == 15 && ay == 19 && bx == 14 && by == 18) ||
                (ax == 15 && ay == 17 && bx == 14 && by == 18);
    }
}
