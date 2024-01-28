package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    String path_raw;
  
    public Path(String path){
        this.path_raw=path;
    }  
    public String getCanonicalPath(){
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

}
