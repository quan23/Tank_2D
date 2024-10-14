package Model;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Entity> objList;
    public Map() {
        objList = new ArrayList();
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
    
    
}
