package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {
    private String path_raw;
    private static final Logger logger = LogManager.getLogger();

    public Path(String path){
        this.path_raw=path;
    }
    public String getCanonicalPath(){
        if (path_raw.length()==0){
            return "";
        }
        String c_path="";
        for (int i=0; i<path_raw.length()-1;i++){
            c_path+=path_raw.charAt(i);
            if (path_raw.charAt(i)!=path_raw.charAt(i+1)){
                c_path+=" ";
            }
        }
        c_path+=path_raw.charAt(path_raw.length()-1);
        return c_path;

    }
    public String getFactorizedPath(){
        if (path_raw.length()==0){
            return "";
        }
        String f_path="";
        String c_path="";
        for (int i=0; i<path_raw.length()-1;i++){
            c_path+=path_raw.charAt(i);
            if (path_raw.charAt(i)!=path_raw.charAt(i+1)){
                c_path+=" ";
            }
        }
        c_path+=path_raw.charAt(path_raw.length()-1);
       
        String[] f_path_array=c_path.split(" ");
        for(int i=0; i<f_path_array.length;i++){
            f_path_array[i]=Integer.toString(f_path_array[i].length())+f_path_array[i].charAt(0);
        }
        
        for(int i=0; i<f_path_array.length;i++){
            f_path+=f_path_array[i] + " ";
        } 

        return f_path;

    }
    /**Verifies only valid characters are passed in the path */
    private boolean ensureCharactersValid(){
        for (int i=0; i<path_raw.length();i++){
            if (path_raw.charAt(i)=='R' | path_raw.charAt(i)=='F' | path_raw.charAt(i)=='L'| path_raw.charAt(i)==' '){
                continue;
            }
            else{
                try {
                    Integer.parseInt(String.valueOf(path_raw.charAt(i)));
                }
                catch(NumberFormatException nfe){
                    return false;
                }
            }
        }
        return true;
    }
    public Path defactorPath(){
        //assume valid chars
        if (!ensureCharactersValid()){
            logger.error("/!\\ Invalid path given /!\\");
            logger.error("/!\\ Ensure all capitals and only R,F,L /!\\");
            logger.error("/!\\ Terminating Program /!\\");
            System.exit(1);
        }
        String path_defactored="";
        // cases (assuming valid input: factored path: 3F4R2F -> [3F,4R,2F], canonical: FFFRFFF
        //Case canonical path passed to validate, will return a spaceless path which can be analyzed
        if (path_raw.charAt(0) == 'F' | path_raw.charAt(0) == 'R' | path_raw.charAt(0) == 'L'){
            for (int i=0; i<path_raw.length();i++){
                if (!(path_raw.charAt(i) == 'F' | path_raw.charAt(i) == 'R' | path_raw.charAt(i) == 'L')){
                    logger.error("/!\\ Invalid path given /!\\");
                    logger.error("/!\\ Ensure path is fully factorized or canonical w/o spaces /!\\");
                    logger.error("/!\\ Terminating Program /!\\");
                    System.exit(1);
                }
            }
            return new Path(this.path_raw);
        }
        //Case factorized path provided, will split at each different instruction and then create corresponding spaceless canonical path
        else{
            String[] factors = path_raw.split("(?=\\d)(?<!\\d)"); //splits after every int_str pattern
            for (int i=0; i<factors.length;i++){
                try{
                    Integer.parseInt(factors[i].substring(0,factors[i].length()-1));
                }
                catch(NumberFormatException nfe){
                    logger.error("/!\\ Invalid path given /!\\");
                    logger.error("/!\\ Ensure path is fully formatted as canonical or factorized w/o spaces /!\\");
                    logger.error("/!\\ Terminating Program /!\\");
                    System.exit(1);
                }
                int fac=Integer.parseInt(factors[i].substring(0,factors[i].length()-1));
                char direction= (factors[i].charAt(factors[i].length()-1));
                for (int j=0; j<fac;j++){
                    path_defactored+=direction;
                }
            }
            path_raw=path_defactored;
            return new Path(this.path_raw);
        }
    }
    public Integer getPathLength(){
        return (this.path_raw).length();
    }
    private char getStepAt(int i){
        return path_raw.charAt(i);
    }
    public boolean verifyPath(Maze maze){
        boolean l_valid=verifyLeftPath(maze);
        boolean r_valid=verifyRightPath(maze);
        return (l_valid|r_valid);
    }
    private boolean verifyLeftPath(Maze maze){
        logger.info("Verifying path from western entry...");
        Coordinates west_entry=maze.getWestEntry();
        Coordinates east_entry=maze.getEastEntry();
        Coordinates pos=new Coordinates(west_entry.getX(), west_entry.getY());
        DIRECTION direct= DIRECTION.EAST;
        for (int i=0; i<this.getPathLength();i++){
            char move=this.getStepAt(i);
            switch(move){
                case 'F' ->{
                    pos.move(direct);
                }
                case 'L' ->{
                    direct=direct.rotateLeft();
                }
                case 'R' ->{
                    direct=direct.rotateRight();
                }
                default -> {
                    return false;
                }
            }
            if(!maze.isOpen(pos)){
                return false;
            }
        }
        if (pos.coordEquals(east_entry)){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean verifyRightPath(Maze maze){
        logger.info("Verifying path from eastern entry...");
        Coordinates west_entry=maze.getWestEntry();
        Coordinates east_entry=maze.getEastEntry();
        Coordinates pos= new Coordinates(east_entry.getX(),east_entry.getY());
        DIRECTION direct= DIRECTION.WEST;
        for (int i=0; i<this.getPathLength();i++){
            char move=this.getStepAt(i);
            switch(move){
                case 'F' ->{
                    pos.move(direct);
                }
                case 'L' ->{
                    direct=direct.rotateLeft();
                }
                case 'R' ->{
                    direct=direct.rotateRight();
                }
                default -> {
                    return false;
                }
            }
            if(!maze.isOpen(pos)){
                return false;
            }
        }
        if (pos.coordEquals(west_entry)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return path_raw;
    }
}
