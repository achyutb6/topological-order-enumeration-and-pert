package aab180004;

public class ArrayStack<T> {
    T[] arr;
    int top;
    int size;
    int maxSize;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.arr = (T[]) new Object[maxSize];
        this.size = 0;
        this.top = -1;
    }

    public T pop(){
        if(this.size == 0){
            return null;
        }
        T topElement = this.arr[top--];
        this.size--;

        return topElement;
    }

    public T peek(){
        if(this.size == 0){
            return null;
        }
        return this.arr[top];
    }

    public boolean push(T e){
        if(!isFull()){
            return false;
        }
        this.size++;
        this.arr[++top] = e;
        return false;
    }

    public boolean isFull(){
        if (this.size == this.maxSize){
            return false;
        }
        return true;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }


}
