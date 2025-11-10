package com.petcare.model;

public class Pet {
    private int petId;
    private String name;
    private String type;
    private int age;
    private String ownerName;

    public Pet() {}

    public Pet(int petId, String name, String type, int age, String ownerName) {
        this.petId = petId;
        this.name = name;
        this.type = type;
        this.age = age;
        this.ownerName = ownerName;
    }

    // Getters and setters
    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}
