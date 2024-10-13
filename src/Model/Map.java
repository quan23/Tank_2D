package Model;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Objects> objList;
    public Map() {
        objList = new ArrayList();
    }

    public ArrayList<Objects> getObjList() {
        return objList;
    }
    
    public void addObject(Objects obj) {
        objList.add(obj);
    }
    
    public void deleteObject(Objects obj) {
        objList.remove(obj);
    }
    
    
}
