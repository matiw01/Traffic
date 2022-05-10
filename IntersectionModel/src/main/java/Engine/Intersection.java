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


    }

    public ArrayList<Road> getRoadArrayList(){return this.roadArrayList;}
    public HashMap<VehicleTarget, Pair<Double, Road>> getProbVehDir(){return this.probVehDir;}
    public ArrayList<Environment> getEnvironmentElements(){return this.environmentElements;}
    public ArrayList<Pedestrian> getPedestrianArrayList(){return this.pedestrianArrayList;}
    public ArrayList<PedestrianPath> getPedestrianPathArrayList(){return this.pedestrianPathArrayList;}
    public Road[][] getMap(){return this.map;}
}