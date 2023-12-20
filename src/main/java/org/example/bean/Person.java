package org.example.bean;

public class Person implements IFriendly {
    private String name;
    private int age;

    private int sender;

    public Person(String name, int age, int sender) {
        this.name = name;
        this.age = age;
        this.sender = sender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    @Override
    public void makeFriend(IFriendly f) {
        System.out.println("I am " + name + " , and glad to be a friend of " + f);
    }
}
