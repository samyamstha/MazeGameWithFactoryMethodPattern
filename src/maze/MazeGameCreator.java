/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class MazeGameCreator {
    /**
     * Creates a small maze.
     */
    public Maze createMaze() {

        Maze maze = new Maze();

        Room room0 = makeRoom(0);
        Room room1 = makeRoom(1);
        Door door0 = makeDoor(room0, room1);
        maze.addRoom(room0);
        maze.addRoom(room1);
        room0.setSide(Direction.North, makeWall());
        room0.setSide(Direction.East, door0);
        room0.setSide(Direction.South, makeWall());
        room0.setSide(Direction.West, makeWall());
        room1.setSide(Direction.North, makeWall());
        room1.setSide(Direction.East, makeWall());
        room1.setSide(Direction.South, makeWall());
        room1.setSide(Direction.West, door0);

        maze.setCurrentRoom(0);

        System.out.println("The maze does not have any rooms yet!");
        return maze;
    }

    public Maze loadMaze(final String path) throws FileNotFoundException {
        Maze maze = new Maze();


        HashMap<String, String> roomMap = null;
        HashMap<String, String> doorMap = null;
        System.out.println("Please load a maze from the file!");

        ArrayList<HashMap> roomList = new ArrayList<>();
        ArrayList<HashMap> doorList = new ArrayList<>();

        //open the file and start reading
        File file = new File(path);
        Scanner sc = new Scanner(file);


        ArrayList<String> mazeInfo = new ArrayList<>(); //ArrayList to add the lines of the file
        ArrayList<Room> roomObjs = new ArrayList<>();  //ArrayList to add the Room objects
        ArrayList<Door> doorObjs = new ArrayList<>();   //ArrayList to add the Door objects

        //Run till the file has line in it
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            //Only add the line if it is not blank
            if (line.length() > 0){
                mazeInfo.add(line);
            }

        }

        //Split the line and add the attributes of room and doors to respective maps
        for (String info : mazeInfo) {

            String[] element = info.split(" ");

            if (element[0].equals("room")) {
                roomMap = new HashMap<>();
                roomMap.put("number", element[1]);
                roomMap.put("north", element[2]);
                roomMap.put("south", element[3]);
                roomMap.put("east", element[4]);
                roomMap.put("west", element[5]);
                roomList.add(roomMap);
            } else {
                doorMap = new HashMap<>();
                doorMap.put("name", element[1]);
                doorMap.put("room1", element[2]);
                doorMap.put("room2", element[3]);
                doorMap.put("openOrClose", element[4]);
                doorList.add(doorMap);
            }
        }


        System.out.println();

        //Instantiate Room objects and add them to the maze, and also to the ArrayList of the Room objects
        for (HashMap<String, String> rmap : roomList) {
            System.out.println(rmap.get("number"));
            int roomNum = Integer.parseInt(rmap.get("number"));
            Room room = makeRoom(roomNum);
            roomObjs.add(room);
            maze.addRoom(room);
        }

        //Instantiate Door objects and add them to the ArrayList of Door objects
        for (HashMap<String, String> dmap : doorList) {
            String doorName = dmap.get("name");
            int room1 = Integer.parseInt(dmap.get("room1"));
            int room2 = Integer.parseInt(dmap.get("room2"));

            Door door = makeDoor(roomObjs.get(room1), roomObjs.get(room2));
            if (dmap.get("openOrClose").equals("close")) {
                door.setOpen(false);
            } else {
                door.setOpen(true);
            }
            doorObjs.add(door);

        }

        //Iterate through the rooms and add set the sides
        for (int i = 0; i < roomObjs.size(); i++) {

            decideCase(roomList, i, doorObjs, roomObjs, "north", Direction.North);
           decideCase(roomList, i, doorObjs, roomObjs, "south", Direction.South);
           decideCase(roomList, i, doorObjs, roomObjs, "east", Direction.East);
            decideCase(roomList, i, doorObjs, roomObjs, "west", Direction.West);
        }

        maze.setCurrentRoom(0);

        return maze;
    }

    //Function that decides what to set on the sides of the rooms
    public  void decideCase(ArrayList<HashMap> roomList, int i, ArrayList<Door> doorObjs, ArrayList<Room> roomObjs, String direction, Direction dir) {

        String lookUp = roomList.get(i).get(direction).toString();

        //Add wall
        if (lookUp.equals("wall")){
            roomObjs.get(i).setSide(dir, makeWall());

            //Add respective door
        }else if(lookUp.charAt(0) == 'd'){
            int doorNum = (Character.getNumericValue(lookUp.charAt(1)));
            roomObjs.get(i).setSide(dir, doorObjs.get(doorNum));

            //Add respective room
        }else {
            int roomNum = Integer.parseInt(lookUp);
            roomObjs.get(i).setSide(dir, roomObjs.get(roomNum));

        }

    }

    public  Wall makeWall(){
        return new Wall();
    }

    public  Door makeDoor(Room room1, Room room2){
        return new Door(room1, room2);
    }

    public Room makeRoom(int number){
        return new Room(number);
    }


    public static void main(String[] args) throws FileNotFoundException {

        Maze maze = null;
        MazeGameCreator mgc = new MazeGameCreator();
        String filePath;
        String mazeColor;
        //check if the argument has been passed to the program
        //call loadMaze() if argument is passed
        //else call createMaze()
        if (args.length == 1) {
            if (args[0].equals("red")){
                mgc = new RedMazeGameCreator();
                maze = mgc.createMaze();
            }
            else if (args[0].equals("blue")){
                mgc = new BlueMazeGameCreator();
                maze = mgc.createMaze();
            }
            else{
                filePath = args[0];
                System.out.println("The argument passed is " + filePath);
                try{
                    maze = mgc.loadMaze(filePath);
                }catch (Exception e){
                    System.out.println("Please provide a valid file.\nExiting the system.");
                    System.exit(0);
                }

            }

        } else if(args.length == 2){

            mazeColor = args[0];
            filePath = args[1];

            System.out.println("The arguments passed is "  + mazeColor + " " + filePath);

            switch  (mazeColor){
                case "red":
                    mgc = new RedMazeGameCreator();
                    maze = mgc.loadMaze(filePath);
                    break;
                case "blue":
                    mgc = new BlueMazeGameCreator();
                    maze = mgc.loadMaze(filePath);
                    break;
            }


        }
        else {
            maze = mgc.createMaze();
        }

        MazeViewer viewer = new MazeViewer(maze);
        viewer.run();
    }
}
