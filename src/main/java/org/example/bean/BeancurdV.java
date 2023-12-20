package org.example.bean;

public class BeancurdV extends Person implements IDao {

    private String direction;

    public BeancurdV(String name, int age, int sender) {
        super(name, age, sender);
    }

    @Override
    public void enlightenment() {
        System.out.println("I am trying！！！");
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
