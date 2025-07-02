package io.github.clupthegreat.wheresjason;

public class MyJsonObject {

    private Object variableKey;
    private Object variableValue;

    MyJsonObject(int Key, String Value){
        this.variableKey = Key;
        this.variableValue = Value;
    }

    MyJsonObject(String Key, String Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }

    MyJsonObject(int Key, int Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }

    MyJsonObject(String Key, int Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }

    MyJsonObject(int Key, Object[] Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }

    MyJsonObject(String Key, Object[] Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }


    MyJsonObject(int Key, MyJsonObject Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }

    MyJsonObject(String Key, MyJsonObject Value){

        this.variableKey = Key;
        this.variableValue = Value;
    }
}
