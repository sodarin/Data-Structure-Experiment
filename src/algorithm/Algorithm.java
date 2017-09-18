package algorithm;

import vertex.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ZhangHaodong on 2017/9/4.
 */
public class Algorithm {
    public static void quickSort(ArrayList<Vertex> list, int low, int high, String method){
        int start = low;
        int end = high;

        if(method.equals("population")) {
            int key = list.get(low).getPopulation();
            while (end > start) {
                while (end > start && list.get(end).getPopulation() >= key) {
                    end--;
                }
                if (list.get(end).getPopulation() <= key) {
                    int temp = list.get(end).getPopulation();
                    String desc = list.get(end).getIntroduction();
                    String name = list.get(end).getName();
                    boolean hasToilet = list.get(end).isHasToilet();
                    boolean hasRestArea = list.get(end).isHasRestArea();
                    list.get(end).setPopulation(list.get(start).getPopulation());
                    list.get(end).setIntroduction(list.get(start).getIntroduction());
                    list.get(end).setName(list.get(start).getName());
                    list.get(end).setHasToilet(list.get(start).isHasToilet());
                    list.get(end).setHasRestArea(list.get(start).isHasRestArea());

                    list.get(start).setPopulation(temp);
                    list.get(start).setIntroduction(desc);
                    list.get(start).setName(name);
                    list.get(start).setHasToilet(hasToilet);
                    list.get(start).setHasRestArea(hasRestArea);
                }
                while (end > start && list.get(start).getPopulation() <= key) {
                    start++;
                }
                if (list.get(start).getPopulation() >= key) {
                    int temp = list.get(start).getPopulation();
                    String name = list.get(start).getName();
                    String desc = list.get(start).getIntroduction();
                    boolean hasToilet = list.get(start).isHasToilet();
                    boolean hasRestArea = list.get(start).isHasRestArea();
                    list.get(start).setPopulation(list.get(end).getPopulation());
                    list.get(start).setIntroduction(list.get(end).getIntroduction());
                    list.get(start).setName(list.get(end).getName());
                    list.get(start).setHasToilet(list.get(end).isHasToilet());
                    list.get(start).setHasRestArea(list.get(end).isHasRestArea());

                    list.get(end).setPopulation(temp);
                    list.get(end).setIntroduction(desc);
                    list.get(end).setName(name);
                    list.get(end).setHasToilet(hasToilet);
                    list.get(end).setHasRestArea(hasRestArea);
                }
            }
            if(start > low)
                quickSort(list, low, start - 1, method);
            if(end < high)
                quickSort(list, end + 1, high, method);
        }else if(method.equals("vertex")){
            int key = list.get(low).getNumOfNode();
            while (end > start) {
                while (end > start && list.get(end).getNumOfNode() >= key) {
                    end--;
                }
                if (list.get(end).getNumOfNode() <= key) {
                    int temp = list.get(end).getPopulation();
                    String desc = list.get(end).getIntroduction();
                    String name = list.get(end).getName();
                    int num = list.get(end).getNumOfNode();
                    boolean hasToilet = list.get(end).isHasToilet();
                    boolean hasRestArea = list.get(end).isHasRestArea();
                    HashMap<Vertex, Integer> node = list.get(end).getNearByVertex();
                    list.get(end).setPopulation(list.get(start).getPopulation());
                    list.get(end).setIntroduction(list.get(start).getIntroduction());
                    list.get(end).setName(list.get(start).getName());
                    list.get(end).setNumOfNode(list.get(start).getNumOfNode());
                    list.get(end).setNearByVertex(list.get(start).getNearByVertex());
                    list.get(end).setHasToilet(list.get(start).isHasToilet());
                    list.get(end).setHasRestArea(list.get(start).isHasRestArea());

                    list.get(start).setPopulation(temp);
                    list.get(start).setIntroduction(desc);
                    list.get(start).setName(name);
                    list.get(start).setNumOfNode(num);
                    list.get(start).setNearByVertex(node);
                    list.get(start).setHasToilet(hasToilet);
                    list.get(start).setHasRestArea(hasRestArea);
                }
                while (end > start && list.get(start).getNumOfNode() <= key) {
                    start++;
                }
                if (list.get(start).getNumOfNode() >= key) {
                    int temp = list.get(start).getPopulation();
                    String name = list.get(start).getName();
                    String desc = list.get(start).getIntroduction();
                    int num = list.get(start).getNumOfNode();
                    boolean hasToilet = list.get(start).isHasToilet();
                    boolean hasRestArea = list.get(start).isHasRestArea();
                    HashMap<Vertex, Integer> node = list.get(start).getNearByVertex();
                    list.get(start).setPopulation(list.get(end).getPopulation());
                    list.get(start).setIntroduction(list.get(end).getIntroduction());
                    list.get(start).setName(list.get(end).getName());
                    list.get(start).setNumOfNode(list.get(end).getNumOfNode());
                    list.get(start).setNearByVertex(list.get(end).getNearByVertex());
                    list.get(start).setHasToilet(list.get(end).isHasToilet());
                    list.get(start).setHasRestArea(list.get(end).isHasRestArea());

                    list.get(end).setPopulation(temp);
                    list.get(end).setIntroduction(desc);
                    list.get(end).setName(name);
                    list.get(end).setNumOfNode(num);
                    list.get(end).setNearByVertex(node);
                    list.get(end).setHasToilet(hasToilet);
                    list.get(end).setHasRestArea(hasRestArea);
                }
            }
            if(start > low)
                quickSort(list, low, start - 1, method);
            if(end < high)
                quickSort(list, end + 1, high, method);
        }

    }
}
