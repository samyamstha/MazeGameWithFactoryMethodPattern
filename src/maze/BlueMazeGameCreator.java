package maze;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class BlueMazeGameCreator extends MazeGameCreator {

    @Override
    public Maze createMaze() {
        return super.createMaze();
    }

    @Override
    public Maze makeMaze(String path) throws FileNotFoundException {
        return super.makeMaze(path);
    }

    @Override
    public void decideCase(ArrayList<HashMap> roomList, int i, ArrayList<Door> doorObjs, ArrayList<Room> roomObjs, String direction, Direction dir) {
        super.decideCase(roomList, i, doorObjs, roomObjs, direction, dir);
    }

    public  Wall makeWall(){
        return new BlueWall();
    }

    public   Door makeDoor(Room room1, Room room2){
        return new BrownDoor(room1, room2);
    }

    public  Room makeRoom(int number){
        return new GreenRoom(number);
    }



}
