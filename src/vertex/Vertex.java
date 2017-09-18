package vertex;

import java.util.HashMap;

/**
 * Created by ZhangHaodong on 2017/8/31.
 */
public class Vertex implements Cloneable {
    private String name;
    private String introduction;
    private int population;
    private boolean hasRestArea;
    private boolean hasToilet;
    private int numOfNode;
    private HashMap<Vertex, Integer> nearByVertex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setNumOfNode(int num) {this.numOfNode = num;}

    public int getNumOfNode() {return numOfNode;}

    public void setNearByVertex(HashMap<Vertex, Integer> nearByVertex) { this.nearByVertex = nearByVertex; }

    public HashMap<Vertex, Integer> getNearByVertex() {return nearByVertex;}

    public boolean isHasRestArea() {
        return hasRestArea;
    }

    public void setHasRestArea(boolean hasRestArea) {
        this.hasRestArea = hasRestArea;
    }

    public boolean isHasToilet() {
        return hasToilet;
    }

    public void setHasToilet(boolean hasToilet) {
        this.hasToilet = hasToilet;
    }

    public String getToilet() {
        if(isHasToilet())
            return "Y";
        else
            return "N";
    }

    public String getRestArea() {
        if(isHasRestArea())
            return "Y";
        else
            return "N";
    }

    public Vertex(String name, String introduction, int population,String hasToilet , String hasRestArea){
        this.setIntroduction(introduction);
        this.setName(name);
        this.setPopulation(population);
        if(hasRestArea.equals("y")||hasRestArea.toUpperCase().equals("Y"))
            this.setHasRestArea(true);
        else
            this.setHasRestArea(false);
        if(hasToilet.equals("y")||hasToilet.toUpperCase().equals("Y"))
            this.setHasToilet(true);
        else
            this.setHasToilet(false);
        this.setNumOfNode(0);
        this.nearByVertex = new HashMap<>();
    }

    public void addNumOfNode(){
        this.numOfNode++;
    }

    public void addNearByVertex(Vertex vertex, int value){
        this.nearByVertex.put(vertex, value);
        this.numOfNode++;
    }

    public Vertex clone(){
        Vertex clone = null;
        try{
            clone = (Vertex)super.clone();
            clone.name = new String(name);
            clone.introduction = new String(introduction);
            clone.population = new Integer(population);
            clone.hasRestArea = new Boolean(hasRestArea);
            clone.hasToilet = new Boolean(hasToilet);
            clone.nearByVertex = new HashMap<Vertex, Integer>();
            clone.nearByVertex.putAll(nearByVertex);
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vertex) {
            if (this.getName().equals(((Vertex) obj).getName())){
                return true;
            }
            else
                return false;
        }
        return false;
    }



}
