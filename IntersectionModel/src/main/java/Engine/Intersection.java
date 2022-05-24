package Engine;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
    private ArrayList<Tram> tramsArrayList = new ArrayList<>();
    private final ArrayList<LightsGroup> pedestrianLightsGroupsArrayList = new ArrayList<>();
    private final ArrayList<LightsGroup> vehicleLightsGroupsArrayList = new ArrayList<>();
    private final ArrayList<LightsGroup> tramLightsGroupsArrayList = new ArrayList<>();
    private final HashMap<Vector, LightsGroup> pedestrianLightsHashMap = new HashMap<>();
    private final HashMap<Vector, LightsGroup> vehicleLightsHashMap = new HashMap<>();
    private final HashMap<Vector, LightsGroup> tramLightsHashMap = new HashMap<>();
    private final LinkedList<Zone> zoneLinkedList = new LinkedList<>();
    private final LinkedList<TramZone> tramZoneLinkedList = new LinkedList<>();

    public Intersection(int width, int height){
        this.height = height;
        this.width = width;
        map = new Road[height][width];

        //Roads
        this.probVehDir = new HashMap<VehicleTarget, Pair<Double, Road>>();

        generatePedestrianPath();
        generateTramPath();
        generateRoads();
        generatePedestrianLights();
        generateVehicleLights();
        generateTramLights();
        generateZones();
        generateTramZones();
    }
    public ArrayList<Road> getRoadArrayList(){return this.roadArrayList;}
    public HashMap<VehicleTarget, Pair<Double, Road>> getProbVehDir(){return this.probVehDir;}
    public ArrayList<Environment> getEnvironmentElements(){return this.environmentElements;}
    public ArrayList<Pedestrian> getPedestrianArrayList(){return this.pedestrianArrayList;} //czy potrzeben
    public ArrayList<PedestrianPath> getPedestrianPathArrayList(){return this.pedestrianPathArrayList;}
    public ArrayList<TramPath> getTramPathArrayList(){return this.tramPathArrayList;}
    public void setPedestrianArrayList(ArrayList<Pedestrian> pedestrians){this.pedestrianArrayList = pedestrians;}
    public Road[][] getMap(){return this.map;}
    public TramPath getAtLocation(int x, int y){for(TramPath tramPath : this.tramPathArrayList){if(tramPath.getLocation().getPos_x() == x && tramPath.getLocation().getPos_y() == y){return tramPath;}}return null;} //nie jednoznaczne gdy 2 TramPaths w jednej lokalizacji
    public ArrayList<Tram> getTramsArrayList(){return this.tramsArrayList;}
    public void setTramsArrayList(ArrayList<Tram> trams){this.tramsArrayList = trams;}
    public HashMap<Vector, LightsGroup> getVehicleLightsHashMap(){return this.vehicleLightsHashMap;}
    public ArrayList<LightsGroup> getVehicleLightsGroupsArrayList(){return this.vehicleLightsGroupsArrayList;}
    public HashMap<Vector, LightsGroup> getPedestrianLightsHashMap(){return this.pedestrianLightsHashMap;}
    public ArrayList<LightsGroup> getPedestrianLightsGroupsArrayList(){return this.pedestrianLightsGroupsArrayList;}
    public HashMap<Vector, LightsGroup> getTramLightsHashMap(){return this.tramLightsHashMap;}
    public ArrayList<LightsGroup> getTramLightsGroupsArrayList(){return this.tramLightsGroupsArrayList;}
    public LinkedList<Zone> getZoneLinkedList(){return this.zoneLinkedList;}

    private void generateTramPath(){
        for(int x = 0; x<41; x++){tramPathArrayList.add(new Rails(x, 31));}
        tramPathArrayList.add(new TramChangingPoint(41, 31));
        for(int x = 42; x<68; x++){tramPathArrayList.add(new Rails(x, 31));}
        tramPathArrayList.add(new Rails(34, 38));
        tramPathArrayList.add(new Rails(35, 38));
        tramPathArrayList.add(new Rails(35, 37));
        tramPathArrayList.add(new Rails(36, 37));
        tramPathArrayList.add(new Rails(36, 36));
        tramPathArrayList.add(new Rails(37, 36));
        tramPathArrayList.add(new Rails(37, 35));
        tramPathArrayList.add(new Rails(38, 35));
        tramPathArrayList.add(new Rails(38, 34));
        tramPathArrayList.add(new Rails(39, 34));
        tramPathArrayList.add(new Rails(39, 33));
        tramPathArrayList.add(new Rails(40, 33));

        for(int x = 0; x<26; x++){tramPathArrayList.add(new Rails(x, 32));}
        tramPathArrayList.add(new TramChangingPoint(26, 32));
        tramPathArrayList.add(new TramChangingPoint(27, 32));
        tramPathArrayList.add(new TramChangingPoint(40, 32));
        for(int x = 28; x<40; x++){tramPathArrayList.add(new Rails(x, 32));}
        for(int x = 41; x<68; x++){tramPathArrayList.add(new Rails(x, 32));}

        for(int y = 39; y<67; y++){tramPathArrayList.add(new Rails(33, y));}
        for(int y = 40; y<67; y++){tramPathArrayList.add(new Rails(34, y));}
        tramPathArrayList.add(new TramChangingPoint(34, 39));
        tramPathArrayList.add(new Rails(33, 38));
        tramPathArrayList.add(new Rails(32, 38));
        tramPathArrayList.add(new Rails(32, 37));
        tramPathArrayList.add(new Rails(31, 37));
        tramPathArrayList.add(new Rails(31, 36));
        tramPathArrayList.add(new Rails(30, 36));
        tramPathArrayList.add(new Rails(30, 35));
        tramPathArrayList.add(new Rails(29, 35));
        tramPathArrayList.add(new Rails(29, 34));
        tramPathArrayList.add(new Rails(28, 34));
        tramPathArrayList.add(new Rails(28, 33));
        tramPathArrayList.add(new Rails(27, 33));

        for(int x = 0; x<67; x++){
            getAtLocation(x,32).setNext(getAtLocation(x+1,32));
        }
        for(int x = 67; x>0; x--){
            getAtLocation(x,31).setNext(getAtLocation(x-1,31));
        }
        for(int y = 39; y<67; y++){
            getAtLocation(33,y).setNext(getAtLocation(33,y+1)); //33,39
        }
        for(int y = 66; y>=40; y--){
            getAtLocation(34,y).setNext(getAtLocation(34,y-1));
        }
        for(int x = 26; x<33; x++){
            getAtLocation(x,x+6).setNext(getAtLocation(x+1,x+7));
        }
        for(int x = 34; x>26; x--){
            getAtLocation(x,x+5).setNext(getAtLocation(x-1,x+4));
        }
        int k = 0;
        for(int x = 34; x<41; x++){
            getAtLocation(x,x+5-2*k).setNext(getAtLocation(x+1,x+4-2*k));
            k++;
        }
        k = 0;
        for(int x = 41; x>33; x--){
            getAtLocation(x,x-10+2*k).setNext(getAtLocation(x-1,x-9+2*k));
            k++;
        }
    }
    private void generatePedestrianPath(){
        pedestrianPathArrayList.add(new Sidewalk(16, 15));
        pedestrianPathArrayList.add(new Sidewalk(51, 15));
        pedestrianPathArrayList.add(new Sidewalk(18, 50));
        pedestrianPathArrayList.add(new Sidewalk(49, 50));

        pedestrianPathArrayList.add(new PedestrianDestination(19, 0, PedestrianTarget.TOP_LEFT));
        pedestrianPathArrayList.add(new PedestrianDestination(34, 0, PedestrianTarget.TOP_CENTER));
        pedestrianPathArrayList.add(new PedestrianDestination(48, 0, PedestrianTarget.TOP_RIGHT));
        pedestrianPathArrayList.add(new PedestrianDestination(20, 66, PedestrianTarget.BOTTOM_LEFT));
        pedestrianPathArrayList.add(new PedestrianDestination(32, 66, PedestrianTarget.BOTTOM_CENTER_LEFT));
        pedestrianPathArrayList.add(new PedestrianDestination(35, 66, PedestrianTarget.BOTTOM_CENTER_RIGHT));
        pedestrianPathArrayList.add(new PedestrianDestination(47, 66, PedestrianTarget.BOTTOM_RIGHT));

        pedestrianPathArrayList.add(new PedestrianDestination(0, 18, PedestrianTarget.LEFT_UP));
        pedestrianPathArrayList.add(new PedestrianDestination(67, 18, PedestrianTarget.RIGHT_UP));
        pedestrianPathArrayList.add(new PedestrianDestination(67, 30, PedestrianTarget.RIGHT_CENTER_UP));
        pedestrianPathArrayList.add(new PedestrianDestination(67, 33, PedestrianTarget.RIGHT_CENTER_DOWN));
        pedestrianPathArrayList.add(new PedestrianDestination(67, 48, PedestrianTarget.RIGHT_DOWN));
        pedestrianPathArrayList.add(new PedestrianDestination(0, 48, PedestrianTarget.LEFT_DOWN));

        //horizontal
        for(int x = 17; x<20; x++){pedestrianPathArrayList.add(new Sidewalk(x,14));}
        for(int x = 22; x<46; x++){pedestrianPathArrayList.add(new Sidewalk(x, 14));}
        for(int x = 48; x<51; x++){pedestrianPathArrayList.add(new Sidewalk(x, 14));}
        for(int x = 20; x<22; x++){pedestrianPathArrayList.add(new Crossing(x, 14));}
        for(int x = 46; x<48; x++){pedestrianPathArrayList.add(new Crossing(x, 14));}

        for(int x = 19; x<21; x++){pedestrianPathArrayList.add(new Sidewalk(x, 51));}
        for(int x = 24; x<43; x++){pedestrianPathArrayList.add(new Sidewalk(x, 51));}
        for(int x = 47; x<49; x++){pedestrianPathArrayList.add(new Sidewalk(x, 51));}
        for(int x = 21; x<24; x++){pedestrianPathArrayList.add(new Crossing(x, 51));}
        for(int x = 43; x<47; x++){pedestrianPathArrayList.add(new Crossing(x, 51));}
        //vertical
        for(int y = 16; y<19; y++){pedestrianPathArrayList.add(new Sidewalk(15, y));}
        for(int y = 23; y<31; y++){pedestrianPathArrayList.add(new Sidewalk(15, y));}
        for(int y = 33; y<39; y++){pedestrianPathArrayList.add(new Sidewalk(16, y));}
        for(int y = 39; y<44; y++){pedestrianPathArrayList.add(new Sidewalk(17, y));}
        for(int y = 48; y<50; y++){pedestrianPathArrayList.add(new Sidewalk(17, y));}
        for(int y = 19; y<23; y++){pedestrianPathArrayList.add(new Crossing(15, y));}
        for(int y = 31; y<33; y++){pedestrianPathArrayList.add(new Crossing(16, y));}
        for(int y = 44; y<48; y++){pedestrianPathArrayList.add(new Crossing(17, y));}

        for(int y = 16; y<19; y++){pedestrianPathArrayList.add(new Sidewalk(52, y));}
        for(int y = 23; y<31; y++){pedestrianPathArrayList.add(new Sidewalk(52, y));}
        for(int y = 33; y<39; y++){pedestrianPathArrayList.add(new Sidewalk(51, y));}
        for(int y = 39; y<44; y++){pedestrianPathArrayList.add(new Sidewalk(50, y));}
        for(int y = 48; y<50; y++){pedestrianPathArrayList.add(new Sidewalk(50, y));}
        for(int y = 19; y<23; y++){pedestrianPathArrayList.add(new Crossing(52, y));}
        for(int y = 31; y<33; y++){pedestrianPathArrayList.add(new Crossing(51, y));}
        for(int y = 44; y<48; y++){pedestrianPathArrayList.add(new Crossing(50, y));}

        for(int x = 1; x<15; x++){pedestrianPathArrayList.add(new Sidewalk(x, 18));}
        for(int x = 53; x<67; x++){pedestrianPathArrayList.add(new Sidewalk(x, 18));}
        for(int x = 1; x<17; x++){pedestrianPathArrayList.add(new Sidewalk(x, 48));}
        for(int x = 51; x<67; x++){pedestrianPathArrayList.add(new Sidewalk(x, 48));}
        for(int x = 53; x<67; x++){pedestrianPathArrayList.add(new Sidewalk(x, 30));}
        for(int x = 52; x<67; x++){pedestrianPathArrayList.add(new Sidewalk(x, 33));}

        for(int y = 1; y<14; y++){pedestrianPathArrayList.add(new Sidewalk(19, y));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new Sidewalk(20, y));}
        for(int y = 1; y<14; y++){pedestrianPathArrayList.add(new Sidewalk(48, y));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new Sidewalk(47, y));}
        for(int y = 1; y<14; y++){pedestrianPathArrayList.add(new Sidewalk(34, y));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new Sidewalk(32, y));}
        for(int y = 52; y<66; y++){pedestrianPathArrayList.add(new Sidewalk(35, y));}

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
            if(a.isDestination()) {
                ((PedestrianDestination) a).BFS(pedestrianPathArrayList);
            }
        }
        for(PedestrianPath a : pedestrianPathArrayList){
            a.redirect();
        }
    }
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
    private void generateRoads(){
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

        for (int x = 41; x > 25; x--){
            Road road = new RoadChangingPoint(null, null , new HashMap<VehicleTarget, Road>(),  new Vector(x, 23));
            roadArrayList.add(road);
            map[x][23] = road;
        }

        for (int x = 41; x > 25; x--){map[x][23].setNext(VehicleTarget.Rokicinska, map[x - 1][22]);}
        for (int x = 41; x > 25; x--){map[x][23].setNext(VehicleTarget.McDonalds, map[x - 1][23]);}
        for (int x = 41; x > 25; x--){map[x][23].setNext(VehicleTarget.Prawo, map[x - 1][23]);}

        for(int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.Rokicinska, map[x - 1][22]);}
        for(int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.McDonalds, map[x - 1][23]);}
        for(int x = 41; x > 25; x--){map[x][22].setNext(VehicleTarget.Prawo, map[x - 1][23]);}


        for (int y = 0; y < 14; y++){
            Road road = new StraightRoad(null, null, null, null, new Vector(20, y));
            roadArrayList.add(road);
            map[20][y] = road;
            if (y != 0) map[20][y].setPrevious(map[y-1][20]);}

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
        for (int x = 28; x < 40; x++){map[x][44].setNext(VehicleTarget.PuszkinaOut, map[x + 1][43]);}
        for (int x = 28; x < 40; x++){map[x][44].setNext(VehicleTarget.Rokicinska, map[x + 1][43]);}

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

    }
    private void generatePedestrianLights(){
//pedastrian traffic lights
        LinkedList<TrafficLights> l1 = new LinkedList<>();
        l1.add(new TrafficLights(51,31));
        l1.add(new TrafficLights(51,32));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l1));

        LinkedList<TrafficLights> l2 = new LinkedList<>();
        l2.add(new TrafficLights(16,31));
        l2.add(new TrafficLights(16,32));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l2));

        LinkedList<TrafficLights> l3 = new LinkedList<>();
        l3.add(new TrafficLights(33,51));
        l3.add(new TrafficLights(34,51));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l3));

        LinkedList<TrafficLights> l4 = new LinkedList<>();
        l4.add(new TrafficLights(17,44));
        l4.add(new TrafficLights(17,45));
        l4.add(new TrafficLights(17,46));
        l4.add(new TrafficLights(17,47));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l4));

        //wolne l5

        LinkedList<TrafficLights> l6 = new LinkedList<>();
        l6.add(new TrafficLights(15,20));
        l6.add(new TrafficLights(15,21));
        l6.add(new TrafficLights(15,22));
        l6.add(new TrafficLights(15,19));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l6));

        //wolne l7

        LinkedList<TrafficLights> l8 = new LinkedList<>();
        l8.add(new TrafficLights(52,20));
        l8.add(new TrafficLights(52,21));
        l8.add(new TrafficLights(52,22));
        l8.add(new TrafficLights(52,19));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l8));

        //wolne l9

        LinkedList<TrafficLights> l10 = new LinkedList<>();
        l10.add(new TrafficLights(50,44));
        l10.add(new TrafficLights(50,45));
        l10.add(new TrafficLights(50,46));
        l10.add(new TrafficLights(50,47));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l10));

        //wolne l11

        LinkedList<TrafficLights> l12 = new LinkedList<>();
        l12.add(new TrafficLights(21,51));
        l12.add(new TrafficLights(22,51));
        l12.add(new TrafficLights(23,51));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l12));

        LinkedList<TrafficLights> l13 = new LinkedList<>();
        l13.add(new TrafficLights(43,51));
        l13.add(new TrafficLights(44,51));
        l13.add(new TrafficLights(45,51));
        l13.add(new TrafficLights(46,51));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l13));

        //wolne l14

        LinkedList<TrafficLights> l15 = new LinkedList<>();
        l15.add(new TrafficLights(20,14));
        l15.add(new TrafficLights(21,14));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l15));

        //wolne l16

        LinkedList<TrafficLights> l17 = new LinkedList<>();
        l17.add(new TrafficLights(46,14));
        l17.add(new TrafficLights(47,14));
        //this.pedestrianLightsGroupsArrayList.add(new LightsGroup(l17));

        l12.addAll(l13);
        l12.addAll(l15);
        l12.addAll(l17);
        LightsGroup bigGroup5 = new LightsGroup(l12, 5);
        //
        l4.addAll(l6);
        l4.addAll(l8);
        l4.addAll(l10);
        LightsGroup bigGroup4 = new LightsGroup(l4);
        //tram big group
        l1.addAll(l2);
        l1.addAll(l3);
        LightsGroup bigGroup3 = new LightsGroup(l1);
        pedestrianLightsGroupsArrayList.add(bigGroup3);
        pedestrianLightsGroupsArrayList.add(bigGroup4);
        pedestrianLightsGroupsArrayList.add(bigGroup5);


        int flag = 0;
        for(int x = 0; x<68; x++){
            for(int y = 0; y<67; y++){
                for(LightsGroup lightsGroup : this.pedestrianLightsGroupsArrayList){
                    for(TrafficLights lights : lightsGroup.getLights()){
                        if(x == lights.getLocation().getPos_x() && y == lights.getLocation().getPos_y()){
                            flag = 1;
                            this.pedestrianLightsHashMap.put(lights.getLocation(), lightsGroup);
                        }
                    }
                }
                if(flag == 0){
                    this.pedestrianLightsHashMap.put(new Vector(x, y), null);
                }
                flag = 0;
            }
        }

    }
    private void generateVehicleLights(){
        //vechicle traffic lights
        LinkedList<TrafficLights> l18 = new LinkedList<>();
        l18.add(new TrafficLights(16,44));
        l18.add(new TrafficLights(16,45));
        l18.add(new TrafficLights(16,46));
        LightsGroup lg18 = new LightsGroup(l18);
//        this.vehicleLightsGroupsArrayList.add(lg18);
        //this.lightsHashMap.put(new Vector(51,31), lg4);
        LinkedList<TrafficLights> l19 = new LinkedList<>();
        l19.add(new TrafficLights(16,47));
        LightsGroup lg19 = new LightsGroup(l19);
//        this.vehicleLightsGroupsArrayList.add(lg19);
//        this.lightsHashMap.put(new Vector(51,31), lg5);

        LinkedList<TrafficLights> l20 = new LinkedList<>();
        l20.add(new TrafficLights(43,52));
        l20.add(new TrafficLights(44,52));
        l20.add(new TrafficLights(45,52));
        LightsGroup lg20 = new LightsGroup(l20);
//        this.vehicleLightsGroupsArrayList.add(lg20);
        //this.lightsHashMap.put(new Vector(51,31), lg13);
        LinkedList<TrafficLights> l21 = new LinkedList<>();
        l21.add(new TrafficLights(46,52));
        LightsGroup lg21 = new LightsGroup(l21);
//        this.vehicleLightsGroupsArrayList.add(lg21);
        //this.lightsHashMap.put(new Vector(51,31), lg14);

        LinkedList<TrafficLights> l22 = new LinkedList<>();
        l22.add(new TrafficLights(53,20));
        l22.add(new TrafficLights(53,21));
        l22.add(new TrafficLights(53,22));
        LightsGroup lg22 = new LightsGroup(l22);
//        this.vehicleLightsGroupsArrayList.add(lg22);
        //this.lightsHashMap.put(new Vector(51,31), lg8);
        LinkedList<TrafficLights> l23 = new LinkedList<>();
        l23.add(new TrafficLights(53,19));
        LightsGroup lg23 = new LightsGroup(l23);
//        this.vehicleLightsGroupsArrayList.add(lg23);
        //this.lightsHashMap.put(new Vector(51,31), lg9);

        LinkedList<TrafficLights> l24 = new LinkedList<>();
        l24.add(new TrafficLights(20,13));
        LightsGroup lg24 = new LightsGroup(l24);
//        this.vehicleLightsGroupsArrayList.add(lg24);
//        this.lightsHashMap.put(new Vector(51,31), lg15);
        LinkedList<TrafficLights> l25 = new LinkedList<>();
        l25.add(new TrafficLights(21,13));
        LightsGroup lg25 = new LightsGroup(l25);
//        this.vehicleLightsGroupsArrayList.add(lg25);
//        this.lightsHashMap.put(new Vector(51,31), lg16);


        LinkedList<TrafficLights> l26 = new LinkedList<>();
        l26.add(new TrafficLights(44,33));
        l26.add(new TrafficLights(45,33));
        l26.add(new TrafficLights(46,33));
        LightsGroup lg26 = new LightsGroup(l26);
//        this.vehicleLightsGroupsArrayList.add(lg26);

        LinkedList<TrafficLights> l27 = new LinkedList<>();
        l27.add(new TrafficLights(42,24));
        l27.add(new TrafficLights(46,23));
        l27.add(new TrafficLights(47,23));
        LightsGroup lg27 = new LightsGroup(l27);
//        this.vehicleLightsGroupsArrayList.add(lg27);

        LinkedList<TrafficLights> l28 = new LinkedList<>();
        l28.add(new TrafficLights(22,20));
        l28.add(new TrafficLights(22,21));
        l28.add(new TrafficLights(22,22));
        l28.add(new TrafficLights(23,26));
        LightsGroup lg28 = new LightsGroup(l28);
//        this.vehicleLightsGroupsArrayList.add(lg28);

        LinkedList<TrafficLights> l29 = new LinkedList<>();
        l29.add(new TrafficLights(20,30));
        l29.add(new TrafficLights(21,30));
        l29.add(new TrafficLights(22,30));
        LightsGroup lg29 = new LightsGroup(l29);
//        this.vehicleLightsGroupsArrayList.add(lg29);

        LinkedList<TrafficLights> l30 = new LinkedList<>();
        l30.add(new TrafficLights(22,43));
        l30.add(new TrafficLights(23,43));
        l30.add(new TrafficLights(27,42));
        LightsGroup lg30 = new LightsGroup(l30);
//        this.vehicleLightsGroupsArrayList.add(lg30);

        LinkedList<TrafficLights> l31 = new LinkedList<>();
        l31.add(new TrafficLights(32,43));
        l31.add(new TrafficLights(32,44));
        l31.add(new TrafficLights(32,45));
        l31.add(new TrafficLights(32,46));
        LightsGroup lg31 = new LightsGroup(l31);
//        this.vehicleLightsGroupsArrayList.add(lg31);

        LinkedList<TrafficLights> l32 = new LinkedList<>();
        l32.add(new TrafficLights(42,44));
        l32.add(new TrafficLights(42,45));
        l32.add(new TrafficLights(42,46));
        l32.add(new TrafficLights(42,40));
        LightsGroup lg32 = new LightsGroup(l32);
//        this.vehicleLightsGroupsArrayList.add(lg32);

        //big groups
        l18.addAll(l31);
        l18.addAll(l32);
        l18.addAll(l22);
        l18.addAll(l23);
        l18.addAll(l28);

        //


        LightsGroup bigGroup1 = new LightsGroup(l18, 5);
        vehicleLightsGroupsArrayList.add(bigGroup1);

        l30.addAll(l29);
        l30.addAll(l27);
        l30.addAll(l25);
        l30.addAll(l24);
        l30.addAll(l20);
//        l30.addAll(l21);
        l30.addAll(l26);
        //

        //


        LightsGroup bigGroup2 = new LightsGroup(l30);
        vehicleLightsGroupsArrayList.add(bigGroup2);

        //tram big groups

        //vechicles
        int flag = 0;
        for(int x = 0; x<68; x++) {
            for (int y = 0; y < 67; y++) {
                for (LightsGroup lightsGroup : this.vehicleLightsGroupsArrayList) {
                    for (TrafficLights lights : lightsGroup.getLights()) {
                        if (x == lights.getLocation().getPos_x() && y == lights.getLocation().getPos_y()) {
                            flag = 1;
                            this.vehicleLightsHashMap.put(lights.getLocation(), lightsGroup);
                        }
                    }
                }
                if (flag == 0) {
                    this.vehicleLightsHashMap.put(new Vector(x, y), null);
                }
                flag = 0;
            }
        }
    }
    private void generateTramLights(){
        LinkedList<TrafficLights> l1 = new LinkedList<>();
        l1.add(new TrafficLights(15,31));
        l1.add(new TrafficLights(15,32));

        LinkedList<TrafficLights> l2 = new LinkedList<>();
        l2.add(new TrafficLights(33,52));
        l2.add(new TrafficLights(34,52));

        LinkedList<TrafficLights> l3 = new LinkedList<>();
        l3.add(new TrafficLights(52,31));
        l3.add(new TrafficLights(52,32));

        LinkedList<TrafficLights> l4 = new LinkedList<>();
        l4.add(new TrafficLights(33,42));
        l4.add(new TrafficLights(34,42));

        l1.addAll(l3);
        LightsGroup bigGroup1 = new LightsGroup(l1);
        tramLightsGroupsArrayList.add(bigGroup1);

        l2.addAll(l4);
        LightsGroup bigGroup2 = new LightsGroup(l2, 5);
        tramLightsGroupsArrayList.add(bigGroup2);

        int flag = 0;
        for(int x = 0; x<68; x++) {
            for (int y = 0; y < 67; y++) {
                for (LightsGroup lightsGroup : this.tramLightsGroupsArrayList) {
                    for (TrafficLights lights : lightsGroup.getLights()) {
                        if (x == lights.getLocation().getPos_x() && y == lights.getLocation().getPos_y()) {
                            flag = 1;
                            this.tramLightsHashMap.put(lights.getLocation(), lightsGroup);
                        }
                    }
                }
                if (flag == 0) {
                    this.tramLightsHashMap.put(new Vector(x, y), null);
                }
                flag = 0;
            }
        }
    }

    private void generateZones(){
        Zone zone1 = new Zone(new Vector(0,44), new Vector(16,46), vehicleLightsHashMap.get(new Vector(16, 44)));
        Zone zone2 = new Zone(new Vector(20,0), new Vector(21,13), vehicleLightsHashMap.get(new Vector(20, 13)));//ok
        Zone zone3 = new Zone(new Vector(43,53), new Vector(45,66), vehicleLightsHashMap.get(new Vector(43, 52)));
        Zone zone4 = new Zone(new Vector(54,20), new Vector(67,22), vehicleLightsHashMap.get(new Vector(53, 20)));//ok
        this.zoneLinkedList.add(zone1);
        this.zoneLinkedList.add(zone2);
        this.zoneLinkedList.add(zone3);
        this.zoneLinkedList.add(zone4);
    }

    private void generateTramZones(){
        TramZone zone1 = new TramZone(new Vector(0,32), new Vector(15,32)); //left
        TramZone zone2 = new TramZone(new Vector(52,31), new Vector(67,31)); //right
        TramZone zone3 = new TramZone(new Vector(33,42), new Vector(33,39)); //bottom upper //polepszyc/czy potrzebne itd
        TramZone zone4 = new TramZone(new Vector(34,52), new Vector(34,66)); //bottom lower
        this.tramZoneLinkedList.add(zone1);
        this.tramZoneLinkedList.add(zone2);
        this.tramZoneLinkedList.add(zone3);
        this.tramZoneLinkedList.add(zone4);
    }

    public LinkedList<TramZone> getTramZoneLinkedList(){return this.tramZoneLinkedList;}
}
