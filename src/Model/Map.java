package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {
    private ArrayList<Entity> objList;
    TextureManager txtMana;
    public Map() {
        objList = new ArrayList();
        txtMana = new TextureManager();
    }

    public void setObjList(ArrayList<Entity> objList) {
        this.objList = objList;
    }
    
    public ArrayList<Entity> getObjList() {
        return objList;
    }
    
    public void addObject(Entity obj) {
        objList.add(obj);
    }
    
    public void deleteObject(Entity obj) {
        objList.remove(obj);
    }
    public void loadMap(String link) throws IOException{
        try(Scanner scanner = new Scanner(new File(link))) {   
            String tmp = null;
            int i = 0;
            while(scanner.hasNextLine() && i < 21){        
                    String[] line = scanner.nextLine().split(" ");
                    for(int j = 0;j<21;j++)
                    {
                        if(line[j].equals("0")){
                            objList.add(new Block(j*32,i*32,txtMana.getImage(0)));
                        }
                        else {
                            objList.add(new Block(j*32,i*32,txtMana.getImage(1)));
                        }
                    }
                    i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
}
