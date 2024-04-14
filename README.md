# Assignment A3 - Maze Runner Revisited

  * **Student**: [GURMANJOT MINHAS](minhag7@mcmaster.ca)
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 
  * Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory. 
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
- Give the program the file path to an example maze using the -i or --input flags
- By default, the program will solve the maze using a graph based shortest path algorithm
    - To select which method to use, use the -method or --method flags
    - Currently supported methods:
        - -method bfs
        - -method righthand 
- The program can take a path as input and verify if it's a legit one using the -p flag .
    - Paths may be canonical or factorized
- Can also choose to baseline performance of different methods by using the -baseline flag, followed by a supported method
    - Ensure that when using this, you specify a method for -method AND -baseline. Or else the program will not work!

## How to run this software?

To build the program, simply package it with Maven:

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ mvn clean package -q 
```

To run use:

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar -i examples/straight.maz.txt
```



### Delivered version

Example: No input file specified

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar
[ERROR] Main /!\ No input file given /!\
[ERROR] Main /!\ Terminating Program /!\
```

Example: Input file specified

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar -i examples/straight.maz.txt
[INFO ] Main **** Received input maze file examples/straight.maz.txt
[INFO ] Maze Beginning conversion of maze file to a valid maze
[INFO ] Maze Maze Processed
Path: 4F
```

Example: Path Verification

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar -i examples/straight.maz.txt -p FFFF
[INFO ] Main **** Received input maze file examples/straight.maz.txt
[INFO ] Maze Beginning conversion of maze file to a valid maze
[INFO ] Maze Maze Processed
[INFO ] Path Verifying path from western entry...
[INFO ] Path Verifying path from eastern entry...

**This path is valid**
```

Example: Maze Solving Algorithm Specifier 

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar -i examples/small.maz.txt -method righthand
[INFO ] Main **** Received input maze file examples/small.maz.txt
[INFO ] Maze Beginning conversion of maze file to a valid maze
[INFO ] Maze Maze Processed
Path: 1F 1R 2F 1L 2F 1R 2F 1R 2F 2L 8F 2L 2F 1R 2F 1R 2F 1L 2F 2L 2F 1R 2F 1R 4F 1R 2F 1L 1F 1R 1F
```
```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar -i examples/small.maz.txt -method bfs
[INFO ] Main **** Received input maze file examples/small.maz.txt
[INFO ] Maze Beginning conversion of maze file to a valid maze
[INFO ] Maze Maze Processed
Path: 1F 1L 1F 1R 2F 1L 6F 1R 4F 1R 2F 1L 2F 1R 2F 1L 1F
```

Example: Baseline

```
gurmanjot11@DESKTOP-IN4GGPF:~/a3-maze-runner-take-two-gurmanjot11$ java -jar target/mazerunner.jar -i examples/small.maz.txt -method bfs -baseline righthand
[INFO ] Main **** Received input maze file examples/small.maz.txt
[INFO ] Maze Beginning conversion of maze file to a valid maze
[INFO ] Maze Maze Processed
Time spent loading maze from file (ms): 2.00
Time spent using method: method algorithm (ms): 6.00
Time spent using baseline: baseline algorithm (ms): 0.00
Speedup in terms of path length - baseline/method: 1.90
```



#### Command line arguments

The delivered program at the end of this assignment should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze;
- `-method METHOD`: activates path computation mode with the specified METHOD algorithm;
- `-baseline BASELINE`: activates benchmark mode which compares performance of the specified METHOD and BENCHMARK algorithms;


ENJOY!



