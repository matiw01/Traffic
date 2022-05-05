package Engine;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Intersection{

    Road[][] map;
    int width;
    int height;
    private final ArrayList<Road> roadArrayList = new ArrayList<Road>();
    private final HashMap<VehicleTarget, Pair<Double, Road>> probVehDir;
    private final ArrayList<Environment> environmentElements = new ArrayList<Environment>();
    private final ArrayList<PedestrianPath> pedestrianPathArrayList = new ArrayList<PedestrianPath>();
    private final ArrayList<Pedestrian> pedestrianArrayList = new ArrayList<Pedestrian>();

    public Intersection(int width, int height){
        this.height = height;
        this.width = width;
        map = new Road[height][width];

        //pedestrains
        //points
        pedestrianPathArrayList.add(new PedestrianPath(0, 16, 15));
        pedestrianPathArrayList.add(new PedestrianPath(0, 51, 15));
        pedestrianPathArrayList.add(new PedestrianPath(0, 18, 50));
        pedestrianPathArrayList.add(new PedestrianPath(0, 49, 50));
        //horizontal
        for(int x = 17; x<20; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 14));}
        for(int x = 22; x<46; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 14));}
        for(int x = 48; x<51; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 14));}
        for(int x = 20; x<22; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 14));}
        for(int x = 46; x<48; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 14));}

        for(int x = 19; x<21; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 51));}
        for(int x = 24; x<44; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 51));}
        for(int x = 47; x<49; x++){pedestrianPathArrayList.add(new PedestrianPath(0, x, 51));}
        for(int x = 21; x<24; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 14));}
        for(int x = 44; x<47; x++){pedestrianPathArrayList.add(new PedestrianPath(1, x, 14));}
        //vertical
        for(int y = 16; y<19; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 15, y));}
        for(int y = 23; y<31; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 15, y));}
        for(int y = 33; y<39; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 16, y));}
        for(int y = 39; y<44; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 17, y));}
        for(int y = 48; y<50; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 17, y));}
        for(int y = 19; y<23; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 15, y));}
        for(int y = 31; y<33; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 16, y));}
        for(int y = 44; y<48; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 17, y));}

        for(int y = 16; y<19; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 52, y));}
        for(int y = 23; y<31; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 52, y));}
        for(int y = 33; y<39; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 51, y));}
        for(int y = 39; y<44; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 50, y));}
        for(int y = 48; y<50; y++){pedestrianPathArrayList.add(new PedestrianPath(0, 50, y));}
        for(int y = 19; y<23; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 52, y));}
        for(int y = 31; y<33; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 51, y));}
        for(int y = 44; y<48; y++){pedestrianPathArrayList.add(new PedestrianPath(1, 50, y));}

        //TODO: add destination points
        //TODO: trams
        //TODO: roads

        this.probVehDir = new HashMap<VehicleTarget, Pair<Double, Road>>();
        /*
//      vertical
        for(int i = 0; i < width; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(1,i));
            roadArrayList.add(straightRoad);
            map[1][i] = straightRoad;
            if (i == 0){
                probVehDir.put(VehicleTarget.values()[5], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < width; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(2,i));
            roadArrayList.add(straightRoad);
            map[2][i] = straightRoad;
            if (i == width-1){
                System.out.println(map[2][i]);
                probVehDir.put(VehicleTarget.values()[5], new Pair<Double, Road>(1.0, straightRoad));
                System.out.println(probVehDir.get(VehicleTarget.values()[5]).getValue());
            }
        }
        for(int i = width-1; i >= 0; i--){
            if (i-1 > 0){
                map[1][i].setNext(map[1][i-1]);
                map[2][i].setNext(map[2][i-1]);
            }
            if (i+1 < width){
                map[1][i].setPrevious(map[1][i+1]);
                map[2][i].setPrevious(map[2][i+1]);
            }
            map[1][i].setRight(map[2][i]);
            map[2][i].setLeft(map[1][i]);
        }
        System.out.println(probVehDir.get(VehicleTarget.values()[5]).getValue().getNext());
        System.out.println(map[2][width-1].getNext());



        /*for(int i = 0; i < width; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(6,i));
            roadArrayList.add(straightRoad);
            map[6][i] = new StraightRoad(null, null, null,null, new Vector(6,i));
            if (i == 0){
                probVehDir.put(VehicleTarget.values()[3], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < width; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(7,i));
            roadArrayList.add(straightRoad);
            map[7][i] = new StraightRoad(null, null, null,null, new Vector(7,i));
            if (i == 0){
                probVehDir.put(VehicleTarget.values()[3], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < width; i++){
            if (i+i < width){
                map[6][i].setNext(map[6][i+1]);
                map[7][i].setNext(map[7][i+1]);
            }
            if (i-1 > 0){
                map[6][i].setPrevious(map[6][i-1]);
                map[7][i].setPrevious(map[7][i-1]);
            }
            map[6][i].setRight(map[7][i]);
            map[7][i].setLeft(map[6][i]);
        }

//      horizontal
        for(int i = 0; i < height; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(i,2));
            roadArrayList.add(straightRoad);
            map[i][2] = new StraightRoad(null, null, null,null, new Vector(i,2));
            if (i == 0){
                probVehDir.put(VehicleTarget.values()[7], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < height; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(i,3));
            roadArrayList.add(straightRoad);
            map[i][3] = new StraightRoad(null, null, null,null, new Vector(i,3));
            if (i == 0){
                probVehDir.put(VehicleTarget.values()[7], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < height; i++){
            if (i+i < width){
                map[i][2].setNext(map[i+1][2]);
                map[i][3].setNext(map[i+1][3]);
            }
            if (i-1 > 0){
                map[i][2].setPrevious(map[i-1][2]);
                map[i][3].setPrevious(map[i-1][3]);
            }
            map[i][3].setRight(map[i][2]);
            map[i][2].setLeft(map[i][3]);
        }

        for(int i = 0; i < height; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(i,8));
            roadArrayList.add(straightRoad);
            map[i][8] = new StraightRoad(null, null, null,null, new Vector(i,8));
            if (i == height-1){
                probVehDir.put(VehicleTarget.values()[4], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < height; i++){
            StraightRoad straightRoad = new StraightRoad(null, null, null,null, new Vector(i,9));
            roadArrayList.add(straightRoad);
            map[i][9] = new StraightRoad(null, null, null,null, new Vector(i,9));
            if (i == height-1){
                probVehDir.put(VehicleTarget.values()[4], new Pair<Double, Road>(1.0, straightRoad));
            }
        }
        for(int i = 0; i < height; i++){
            if (i+i < width){
                map[i][8].setNext(map[i+1][8]);
                map[i][9].setNext(map[i+1][9]);
            }
            if (i-1 > 0){
                map[i][8].setPrevious(map[i-1][8]);
                map[i][9].setPrevious(map[i-1][9]);
            }
            map[i][8].setRight(map[i][9]);
            map[i][9].setLeft(map[i][8]);
        }



        */

        /*for(int i = width-3; i<width; i++){
            for(int j = 0; j<height; j++){
                PedestrianPath point = new PedestrianPath(0, i, j);
                pedestrianPathArrayList.add(point);
                if(j%4 == 0){
                    pedestrianArrayList.add(new Pedestrian(point));
                }
            }
        }

        for(int i = 3; i<width-3; i++){
            for(int j = 0; j<height; j++){
                environmentElements.add(new Environment(0, i, j));
            }
        }*/
    }

    public ArrayList<Road> getRoadArrayList(){return this.roadArrayList;}
    public HashMap<VehicleTarget, Pair<Double, Road>> getProbVehDir(){return this.probVehDir;}
    public ArrayList<Environment> getEnvironmentElements(){return this.environmentElements;}
    public ArrayList<Pedestrian> getPedestrianArrayList(){return this.pedestrianArrayList;}
    public ArrayList<PedestrianPath> getPedestrianPathArrayList(){return this.pedestrianPathArrayList;}
}
