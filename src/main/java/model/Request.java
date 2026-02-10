package model;

public class Request {
    private final int sourceFloor;
    private final int destinationFloor;
    private final Direction direction;
    public Request(int sourceFloor,int destinationFloor){
        this.sourceFloor=sourceFloor;
        this.destinationFloor=destinationFloor;
        this.direction=
                destinationFloor>sourceFloor? Direction.UP:Direction.DOWN;
    }
    public int getSourceFloor(){
        return sourceFloor;
    }
    public int getDestinationFloor(){
        return destinationFloor;
    }
    public Direction getDirection(){
        return direction;
    }
}
