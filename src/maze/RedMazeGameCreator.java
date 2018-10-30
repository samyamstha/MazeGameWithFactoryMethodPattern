package maze;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class RedMazeGameCreator  extends MazeGameCreator{

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
        return new RedWall();
    }

    public   Door makeDoor(Room room1, Room room2){
        return new Door(room1, room2);
    }

    public  Room makeRoom(int number){
        return new RedRoom(number);
    }


}
