package edge;

import vertex.Vertex;

/**
 * Created by ZhangHaodong on 2017/8/31.
 */
public class Edge implements Cloneable{
    private Vertex fromVertex;
    private Vertex toVertex;
    private int value;

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Vertex fromVertex) {
        this.fromVertex = fromVertex;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public void setToVertex(Vertex toVertex) {
        this.toVertex = toVertex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Edge(Vertex fromVertex, Vertex toVertex, int value){
        this.setFromVertex(fromVertex);
        this.setToVertex(toVertex);
        this.setValue(value);
    }

    public Edge clone(){
        Edge clone = null;
        try{
            clone = (Edge)super.clone();
            clone.fromVertex = fromVertex.clone();
            clone.toVertex = toVertex.clone();
            clone.value = new Integer(value);
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }

}
