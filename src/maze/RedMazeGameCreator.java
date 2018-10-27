package maze;

public class RedMazeGameCreator  extends MazeGameCreator{

    public  Maze createMaze(){
        Maze amaze = new Maze();
        return amaze;
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
