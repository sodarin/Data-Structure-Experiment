package datastructure;

import exception.QueueOverflowError;
import exception.QueueUnderflowError;

/**
 * Created by ZhangHaodong on 2017/9/11.
 */
public class Queue<E> {
    private E[] list;
    private static final int defaultSize = 10;
    private int length;
    private int first;
    private int firstFree;

    @SuppressWarnings("unchecked")
    public Queue(){
        list = (E[])new Object[defaultSize];
        length = 0;
        first = 0;
        firstFree = 0;
    }

    @SuppressWarnings("unchecked")
    public Queue(int size){
        list = (E[])new Object[size];
        length = 0;
        first = 0;
        firstFree = 0;
    }

    public void insert(E element){
        try{
            if(length == list.length){
                throw new QueueOverflowError();
            }
            list[firstFree] = element;
            firstFree = circulate(firstFree);
            length++;
            System.out.println("Inserting successfully!");
        }catch(QueueOverflowError e){
            System.out.println(e);
        }
    }

    public E remove(){
        try{
            if(length == 0){
                throw new QueueUnderflowError();
            }
            E temp = list[first];
            first = circulate(first);
            length--;
            System.out.println("Removing successfully!");
            return temp;
        }catch(QueueUnderflowError e){
            System.out.println(e);
            return null;
        }

    }

    public E getFront(){
        try{
            if(length == 0){
                throw new QueueUnderflowError();
            }
            return list[first];
        }catch(QueueUnderflowError e){
            System.out.println(e);
            return null;
        }


    }

    public boolean isEmpty(){
        return (length == 0);
    }

    public String toString(){
        int i;
        StringBuffer tempString = new StringBuffer();
        tempString.append("In this list we got:\n");
        if(length == 0){
            tempString.append("Nothing!!!\n");
        }else if(firstFree > first){
            for(i=first;i<firstFree;i++){
                tempString.append(list[i].toString() + "\n");
            }
        }else{
            for(i=first;i<list.length;i++){
                tempString.append(list[i].toString() + "\n");
            }
            for(i=0;i<firstFree;i++){
                tempString.append(list[i].toString() + "\n");
            }
        }
        return tempString.toString();
    }

	/*public String toString(){
		int i;
		StringBuffer tempString = new StringBuffer();
		tempString.append("In this list we got:\n");
		if(length == 0){
			tempString.append("Nothing!!!\n");
			return tempString.toString();
		}
		for(i = first; i != firstFree ; i = circulate(i)){
			tempString.append(list[i].toString() + "\n");
		}
		return tempString.toString();
	}*/

    public int length(){
        return length;
    }

    private int circulate(int a){
        a = (a + 1) % list.length;
        return a;
    }
}
