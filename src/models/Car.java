package models;

import validation.Ask;

public class Car {
    @Ask
    private boolean cool;

    public Car(boolean  cool) {
        this.cool = cool;
    }
    public Car(){

    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }
    public boolean getCool(){
        return cool;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cool=" + cool +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        Car car = (Car) obj;
        return car.getCool() == cool;
    }
}