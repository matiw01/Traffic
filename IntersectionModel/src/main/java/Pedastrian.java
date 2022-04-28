public class Pedastrian{
    private int type; //0 - human; 1 - cyclist
    private int length; //1 - human; 1 - cyclist
    private int speed; //curr speed
    private int maxSpeed; //1 - human; 2 - cyclist
    private Vector location;
    public Pedastrian(){
        this.speed = 1;
        if(Math.random() < 0.5){
            this.type = 0;
            this.length = 1;
            this.maxSpeed = 1;
        }
        else{
            this.type = 1;
            this.length = 2;
            this.maxSpeed = 2;
        }
    }
    public int getType(){return this.type;}
    public int getLength(){return this.length;}
    public int getSpeed(){return this.speed;}
    public int getMaxSpeed(){return this.maxSpeed;}
    public Vector getLocation(){return this.location;}
    public void move(){
        ;
    }
}

//Pedastrian
//Sidewalk
//Crossroad