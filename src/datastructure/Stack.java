package datastructure;

import exception.StackUnderflowError;

/**
 * Created by ZhangHaodong on 2017/9/11.
 */
public class Stack<E> {
    private E[] list;
    private static final int defaultMaxValue = 10;
    private int firstFree;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack(){
        firstFree = 0;
        list = (E[])new Object[defaultMaxValue];
        size = defaultMaxValue;
    }

    @SuppressWarnings("unchecked")
    public Stack(int size){
        firstFree = 0;
        list = (E[])new Object[size];
        this.size = size;
    }

    public void push(E o){
        try{
            if(firstFree >= size){
                throw new StackOverflowError();
            }
            list[firstFree] = o;
            firstFree++;
        }catch(StackOverflowError e){
            System.out.println("Sorry, the stack is full!");
        }
    }

    public E pop(){
        E o;
        try{
            if(firstFree == 0){
                throw new StackUnderflowError();
            }
            firstFree--;
            o = list[firstFree];
            list[firstFree] = null;
            return o;
        }catch(StackUnderflowError e){
            e.printStackTrace();
            System.out.println("Pop: Sorry, the stack is empty!");
            return null;
        }
    }

    public E get(int index){
        return list[index];
    }

    public E peek(){
        E o;
        try{
            if(firstFree == 0){
                throw new StackUnderflowError();
            }
            o = list[firstFree-1];
            return o;
        }catch(StackUnderflowError e){
            System.out.println("Peek: Sorry, the stack is empty!");
            return null;
        }
    }

    public int depth(){
        return firstFree;
    }

    public boolean isEmpty(){
        if(firstFree == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFull(){
        if(firstFree == size){
            return true;
        }
        else{
            return false;
        }
    }

    public int getFirstFree(){
        return firstFree;
    }

    public String toString(){
        String intro = "The elements of this stack is:\n";
        int i = 0;
        for(i=0;i<firstFree;i++){
            intro += list[i].toString();
            intro += "\n";
        }
        return intro;
    }
}
