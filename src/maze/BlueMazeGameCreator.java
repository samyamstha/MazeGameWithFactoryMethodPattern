package maze;

public class BlueMazeGameCreator extends MazeGameCreator {

    public  Maze createMaze(){
        Maze amaze = new Maze();
        return amaze;
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
