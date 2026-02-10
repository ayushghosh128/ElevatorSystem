package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Elevator implements Runnable {
    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    private final BlockingQueue<Request> requestQueue;
    public Elevator(int id){
        this.id=id;
        this.currentFloor=0;
        this.direction=Direction.IDLE;
        this.state=ElevatorState.IDLE;
        this.requestQueue=new LinkedBlockingQueue<>();
    }
    public void addRequest(Request request){
        requestQueue.offer(request);
    }
    @Override
    public void run(){
        try{
            while(true){
                Request request=requestQueue.take();
                processRequest(request);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();;
        }
    }
    private void processRequest(Request request) throws InterruptedException{
        moveToFloor(request.getSourceFloor());
        moveToFloor(request.getDestinationFloor());
        state=ElevatorState.IDLE;
        direction=Direction.IDLE;
    }
    private void moveToFloor(int targetFloor) throws InterruptedException{
        state=ElevatorState.MOVING;
        direction=targetFloor>currentFloor?Direction.UP:Direction.DOWN;
        while(currentFloor!=targetFloor){
            Thread.sleep(1000);
            currentFloor+=direction==Direction.UP?1:-1;
            System.out.println("Elevator "+id+" at floor "+currentFloor);
        }
        System.out.println("Elevator "+id+" stopped at floor "+currentFloor);
        Thread.sleep(500);

    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public int getId() {
        return id;
    }
}
