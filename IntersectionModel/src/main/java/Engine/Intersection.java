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
    private ArrayList<Pedestrian> pedestrianArrayList = new ArrayList<>(); //final??
    private final ArrayList<TramPath> tramPathArrayList = new ArrayList<>();

    public Intersection(int width, int height){
        this.height = height;
        this.width = width;
        map = new Road[height][width];

        //Roads
        for (int x = 67; x > 51; x--){
            StraightRoad road = new StraightRoad(null, null, null, null, new Vector(x, 19));
            roadArrayList.add(road);
            map[x][19] = road;
            if (x != 67) road.setPrevious(map[x + 1][19]);}
        for (int x = 67; x > 52; x--){map[x][19].setNext(map[x - 1][19]);}

        for (int x = 15; x > -1; x--){
            StraightRoad road = new StraightRoad(null, null, null, null, new Vector(x, 19));
            roadArrayList.add(road);
            map[x][19] = road;
            if (x != 15) road.setPrevious(map[x + 1][19]);}
        for (int x = 15; x > 0; x--){map[x][19].setNext(map[x - 1][19]);}

        for(int x = 67; x > -1; x--){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 20));
            roadArrayList.add(road);
            map[x][20] = road;
            if (x != 67) road.setPrevious(map[x + 1][20]);}
        for (int x = 67; x > 0; x--){map[x][20].setNext(map[x - 1][20]);}

        for(int x = 67; x > -1; x--){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 21));
            roadArrayList.add(road);
            map[x][21] = road;
            if (x != 67) road.setPrevious(map[x + 1][21]);}
        for (int x = 67; x > 0; x--){map[x][21].setNext(map[x - 1][21]);}

        for (int x = 67; x > 41; x--){
            Road road = new StraightRoad(null, null, null,null, new Vector(x, 22));
            roadArrayList.add(road);
            map[x][22] = road;
            if (x != 67) road.setPrevious(map[x + 1][22]);
        }

        //TODO changing points
        for (int x = 41; x > 25; x--){
            Road road = new RoadChangingPoint(null, null, new HashMap<VehicleTarget, Road>(), new Vector(x, 22));
            roadArrayList.add(road);
            map[x][22] = road;}
        for (int x = 25; x > -1; x--){
            Road road = new StraightRoad(null, null,null,null, new Vector(x, 22));
            roadArrayList.add(road);
            map[x][22] = road;
        }
        for (int x = 67; x > 0; x--){map[x][22].setNext(map[x - 1][22]);}
        for (int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.Rokicinska, map[x - 1][22]);}
        for (int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.McDonalds, map[x][23]);}
        for (int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.Prawo, map[x][23]);}

        //TODO
        for (int x = 41; x > 25; x--){
            Road road = new RoadChangingPoint(null, null , new HashMap<VehicleTarget, Road>(),  new Vector(x, 23));
            roadArrayList.add(road);
            map[x][23] = road;
        }

        for (int x = 41; x > 25; x--){map[x][23].setNext(VehicleTarget.Rokicinska, map[x][22]);}
        for (int x = 41; x > 25; x--){map[x][23].setNext(VehicleTarget.McDonalds, map[x - 1][23]);}
        for (int x = 41; x > 25; x--){map[x][23].setNext(VehicleTarget.Prawo, map[x - 1][23]);}

        for(int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.Rokicinska, map[x - 1][22]);}
        for(int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.McDonalds, map[x][23]);}
        for(int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.Prawo, map[x][23]);}


        for (int y = 0; y < 14; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(20, y));
            roadArrayList.add(road);
            map[20][y] = road;
            if (y != 0) map[20][y].setPrevious(map[y-1][20]);}
        //TODO changing points
        map[20][14] = new RoadChangingPoint(null, null, new HashMap<VehicleTarget, Road>(), new Vector(20, 14));
        for (int y = 0; y < 14; y++){map[20][y].setNext(map[20][y + 1]);}
        roadArrayList.add(map[20][14]);
        for (int y = 0; y < 14; y++){map[20][y].setNext(map[20][y + 1]);}
        map[20][14].setNext(VehicleTarget.McDonalds, map[20][15]);
        map[20][14].setNext(VehicleTarget.PuszkinaIn, map[20][15]);
        map[20][14].setNext(VehicleTarget.Prawo, map[20][15]);

        for (int y = 15; y < 31; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(20, y));
            roadArrayList.add(road);
            map[20][y] = road;}
        for (int y = 15; y < 31; y++){map[20][y].setNext(map[20][y + 1]);}

        for (int y = 0; y < 31; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(21, y));
            roadArrayList.add(road);
            map[21][y] = road;
            if (y != 0) road.setPrevious(map[21][y - 1]);}
        for (int y = 0; y < 31; y ++){map[21][y].setNext(map[21][y + 1]);}


        for (int y = 27; y < 31; y++){
            Road road = new RoadChangingPoint(null, null, new HashMap<VehicleTarget, Road>(), new Vector(22, y));
            roadArrayList.add(road);
            map[22][y] = road;}


        for (int y = 31; y < 39; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(21, y));
            roadArrayList.add(road);
            map[21][y] = road;
            if (y != 31) map[21][y].setPrevious(map[21][y - 1]);}
        for (int y = 31; y < 38; y++){map[21][y].setNext(map[21][y + 1]);}
        map[20][30].setNext(map[21][31]);


        for (int y = 51; y < 67; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(21, y));
            roadArrayList.add(road);
            map[21][y] = road;
            if (y != 51)road.setPrevious(map[21][y - 1]);}
        for (int y = 51; y < 66; y++){map[21][y].setNext(map[21][y+1]);}

        for (int y = 31; y < 39; y++){
            Road road = new RoadChangingPoint(null, null, new HashMap<VehicleTarget,Road>(), new Vector(22, y));
            roadArrayList.add(road);
            map[22][y] = road;}

        for (int y = 39; y < 67; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(22, y));
            roadArrayList.add(road);
            map[22][y] = road;}
        for (int y = 39; y < 66; y++){map[22][y].setNext(map[22][y + 1]);}


        for (int y = 31; y < 39; y++){map[22][y].setNext(map[22][y + 1]);}

        for (int y = 27; y < 31; y++){map[22][y].setNext(VehicleTarget.McDonalds, map[21][y]);}
        for (int y = 27; y < 31; y++){map[22][y].setNext(VehicleTarget.Prawo, map[22][y + 1]);}
        for (int y = 27; y < 31; y++){map[22][y].setNext(VehicleTarget.PuszkinaOut, map[22][y + 1]);}

        map[21][38].setNext(map[22][39]);
        map[21][30].setNext(map[22][31]);

        for (int y = 31; y < 39; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(23, y));
            roadArrayList.add(road);
            map[23][y] = road;}
        for (int y = 31; y < 38; y++){map[23][y].setNext(map[23][y + 1]);}
        //TODO changing points
        roadArrayList.add(new RoadChangingPoint(null, null, null, new Vector(23, 39)));

        for (int y = 40; y < 67; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(23, y));
            roadArrayList.add(road);
            map[23][y] = road;
            if (y != 40) road.setPrevious(map[23][y - 1]);}
        for (int y = 40; y < 66; y++){map[23][y].setNext(map[23][y + 1]);}
        map[22][38].setNext(map[23][39]);

        for (int y = 31; y < 39; y++){map[22][y].setNext(VehicleTarget.McDonalds, map[22][y + 1]);}
        for (int y = 31; y < 39; y++){map[22][y].setNext(VehicleTarget.Prawo, map[23][y]);}
        for (int y = 31; y < 39; y++){map[22][y].setNext(VehicleTarget.PuszkinaOut, map[23][y]);}

        for (int x = 28; x < 40; x++){
            Road road = new RoadChangingPoint(null, null, new HashMap<VehicleTarget,Road>(), new Vector(x, 43));
            roadArrayList.add(road);
            map[x][43] = road;
            if (x != 28) road.setPrevious(map[x-1][43]);}
        for (int x = 28; x < 39; x++){map[x][43].setNext(map[x+1][43]);}
        for (int x = 28; x < 39; x++){map[x][43].setNext(VehicleTarget.PuszkinaOut, map[x+1][43]);}
        for (int x = 28; x < 39; x++){map[x][43].setNext(VehicleTarget.Rokicinska, map[x+1][43]);}

        for (int x = 0; x < 28; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 44));
            roadArrayList.add(road);
            map[x][44] = road;}
        //TODO changing points
        for (int x = 28; x < 40; x++){
            Road road = new RoadChangingPoint(null, null, new HashMap<VehicleTarget, Road>(), new Vector(x, 44));
            roadArrayList.add(road);
            map[x][44] = road;}
        for (int x = 40; x < 68; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 44));
            roadArrayList.add(road);
            map[x][44] = road;}
        for (int x = 28; x < 39; x++){map[x][43].setNext(VehicleTarget.Prawo, map[x][44]);}

        for (int x = 0; x < 67; x++){map[x][44].setNext(map[x + 1][44]);}
        for (int x = 1; x < 68; x++){map[x][44].setPrevious(map[x - 1][44]);}
        //Changing points
        for (int x = 28; x < 40; x++){map[x][44].setNext(VehicleTarget.Prawo, map[x+1][44]);}
        for (int x = 28; x < 40; x++){map[x][44].setNext(VehicleTarget.PuszkinaOut, map[x][43]);}
        for (int x = 28; x < 40; x++){map[x][44].setNext(VehicleTarget.Rokicinska, map[x][43]);}

        for (int x = 0; x < 68; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 45));
            roadArrayList.add(road);
            map[x][45] = road;
            if (x > 0) {road.setPrevious(map[x - 1][45]);}
        }
        for (int x = 0; x < 67; x++){map[x][45].setNext(map[x+1][45]);}

        for (int x = 0; x < 68; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 46));
            roadArrayList.add(road);
            map[x][46] = road;
            if (x > 0) {road.setPrevious(map[x - 1][46]);}}
        for (int x = 0; x < 67; x++){map[x][46].setNext(map[x+1][46]);}

        for (int x = 0; x < 18; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 47));
            roadArrayList.add(road);
            map[x][47] = road;
            if (x > 0) {road.setPrevious(map[x - 1][47]);}
        }
        for (int x = 0; x < 17; x++){map[x][47].setNext(map[x+1][47]);}

        for (int x = 50; x < 68; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 47));
            roadArrayList.add(road);
            map[x][47] = road;
            if (x != 50) road.setPrevious(map[x-1][47]);}
        for (int x = 50; x < 67; x++){map[x][47].setNext(map[x+1][47]);}

        for (int y = 66; y > 50; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(46, y));
            roadArrayList.add(road);
            map[46][y] = road;
            if ( y != 66 ) road.setPrevious(map[46][y+1]);}
        for(int y = 66; y > 51; y--){map[46][y].setNext(map[46][y-1]);}

        //TODO
        for (int y = 66; y > 38; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(45, y));
            roadArrayList.add(road);
            map[45][y] = road;
            if ( y != 66 ){road.setPrevious(map[45][y+1]);}
        }
        for (int y = 66; y > 39; y--){map[45][y].setNext(map[45][y-1]);}


        for (int y = 66; y > 40; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(44, y));
            roadArrayList.add(road);
            map[44][y] = road;
            if (y != 66) road.setPrevious(map[44][y + 1]);}
        for (int y = 40; y> 30; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(44, y));
            roadArrayList.add(road);
            map[44][y] = road;
            if (y != 66) road.setPrevious(map[44][y + 1]);}

        for (int y = 66; y > 40; y--){map[44][y].setNext(map[44][y - 1]);}
        for (int y = 40; y > 30; y--){map[44][y].setNext(map[44][y - 1]);}

        for (int y = 38; y > 30; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(45, y));
            roadArrayList.add(road);
            map[45][y] = road;
            if (y != 38)road.setPrevious(map[45][y + 1]);}
        for (int y = 39; y > 31; y--){map[45][y].setNext(map[45][y - 1]);}


        for (int y = 38; y > 30; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(46, y));
            roadArrayList.add(road);
            map[46][y] = road;
            if (y != 38) road.setPrevious(map[46][y+1]);}
        for (int y = 38; y > 31; y--){map[46][y].setNext(map[46][y - 1]);}
        map[46][31].setNext(map[47][30]);
        map[45][39].setNext(map[46][38]);

        for (int y = 30; y > 27; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(45, y));
            roadArrayList.add(road);
            map[45][y] = road;
            road.setPrevious(map[45][y + 1]);}
        Road road3 = new RoadChangingPoint(null, null, new HashMap<VehicleTarget, Road>(), new Vector(45, 27));
        map[45][27] = road3;

        roadArrayList.add(road3);
        for (int y = 30; y > 27; y--){map[45][y].setNext(map[45][y - 1]);}
        map[44][31].setNext(map[45][30]);

        for (int y = 30; y > -1; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(46, y));
            roadArrayList.add(road);
            map[46][y] = road;
            road.setPrevious(map[46][y + 1]);}
        for (int y = 30; y>0; y--){map[46][y].setNext(map[46][y - 1]);}
        map[45][31].setNext(map[46][30]);





        for (int y = 30; y > -1; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(47, y));
            roadArrayList.add(road);
            map[47][y] = road;
            if(y != 30) map[47][y].setPrevious(map[47][y+1]);}
        for (int y = 30; y > 0; y--){map[47][y].setNext(map[47][y-1]);}
        map[46][31].setNext(map[47][30]);

        //TODO map[45][27] should be changing point
        map[45][27].setNext(map[46][26]);
        map[45][27].setNext(VehicleTarget.PuszkinaOut, map[46][26]);



        for (int x = 51; x > 47; x--){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, x - 33));
            roadArrayList.add(road);
            map[x][x - 33] = road;
            road.setPrevious(map[x + 1][x - 32]);}
        for (int x = 52; x > 47; x--){map[x][x - 33].setNext(map[x - 1][x - 34]);}

        for (int x = 19; x > 15; x--){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 34 - x));
            roadArrayList.add(road);
            map[x][34 - x] = road;
            road.setPrevious(map[x + 1][33 - x]);}
        for (int x = 19; x > 15; x--){map[x][34 - x].setNext(map[x - 1][35 - x]);}

        for (int x = 18; x < 21; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, x + 30));
            roadArrayList.add(road);
            map[x][x + 30] = road;
            road.setPrevious(map[x - 1][x + 29]);
        }
        for (int x = 17; x < 21; x++){map[x][x + 30].setNext(map[x+1][x+31]);}

        for (int x = 47; x < 50; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 97 - x));
            roadArrayList.add(road);
            map[x][97-x] = road;}
        for (int x = 46; x < 50; x++){map[x][97 - x].setNext(map[x + 1][96 - x]);}

        for (int x = 40; x < 44; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 82 - x));
            roadArrayList.add(road);
            map[x][82 - x] = road;
            road.setPrevious(map[x - 1][83 - x]);}
        map[39][43].setNext(VehicleTarget.PuszkinaOut, map[40][42]);
        map[39][43].setNext(VehicleTarget.Rokicinska, map[40][42]);

        for (int y = 66; y > 39; y--){
            Road road = new StraightRoad(null, null, null, null, new Vector(43, y));
            roadArrayList.add(road);
            map[43][y] = road;
            if ( y != 66 ) road.setPrevious(map[43][y + 1]);}
        for (int y = 66; y > 39; y--){map[43][y].setNext(map[43][y - 1]);}



        for(int x = 39; x < 44; x++){map[x][82 - x].setNext(map[x + 1][81 - x]);}

        for (int x = 23; x < 26; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, 49 - x));
            roadArrayList.add(road);
            map[x][49 - x] = road;}

        map[26][23].setNext(VehicleTarget.Rokicinska, map[26][22]);
        map[26][23].setNext(VehicleTarget.McDonalds, map[25][24]);
        map[26][23].setNext(VehicleTarget.Prawo, map[25][24]);
        for (int x = 25; x > 22; x--){map[x][49 - x].setNext(map[x -1][50 - x]);}

        for (int x = 24; x < 28; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, x + 15));
            roadArrayList.add(road);
            map[x][x + 15] = road;}
        for (int x = 24; x < 28; x++){map[x][x + 15].setNext(map[x + 1][x + 16]);}
        map[23][38].setNext(map[24][39]);

        for (int x = 42; x < 45; x++){
            Road road = new StraightRoad(null, null, null, null, new Vector(x, x - 18));
            roadArrayList.add(road);
            map[x][x - 18] = road;}
        for (int x = 44; x > 41; x--){map[x][x - 18].setNext(map[x - 1][x - 19]);}

        map[45][27].setNext(VehicleTarget.Rokicinska, map[44][26]);
        map[45][27].setNext(VehicleTarget.McDonalds, map[44][26]);
        map[45][27].setNext(VehicleTarget.Prawo, map[44][26]);
        map[20][14].setNext(VehicleTarget.Rokicinska, map[19][15]);
        map[20][14].setNext(VehicleTarget.McDonalds, map[20][15]);

        //TODO: add destination points
        //TODO: trams
        //TODO: roads

        this.probVehDir = new HashMap<VehicleTarget, Pair<Double, Road>>();

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
                    if(!edge_case(a, b) && !edge_case(b, a)){
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
    }

    public ArrayList<Road> getRoadArrayList(){return this.roadArrayList;}
    public HashMap<VehicleTarget, Pair<Double, Road>> getProbVehDir(){return this.probVehDir;}
    public ArrayList<Environment> getEnvironmentElements(){return this.environmentElements;}
    public ArrayList<Pedestrian> getPedestrianArrayList(){return this.pedestrianArrayList;} //czy potrzeben
    public ArrayList<PedestrianPath> getPedestrianPathArrayList(){return this.pedestrianPathArrayList;}
    public void setPedestrianArrayList(ArrayList<Pedestrian> pedestrians){this.pedestrianArrayList = pedestrians;}
    public Road[][] getMap(){return this.map;}

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
