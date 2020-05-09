package com.FinalTask;

public class customer implements Comparable<customer> {
    private int id;
    private String LastName;
    private String FirstName;
    private String SecondName;
    private String Address;
    private int ncard;
    private int nbank;

    public int getId(){
        return id;
    }
    public void setId(int id){
        if(id > 0) {
            this.id = id;
        }
        else
            System.out.println("Error");
    }
    public String getLastName(){
        return LastName;
    }
    public void setLastName(String LastName){
        this.LastName = LastName;
    }
    public String getFirstName(){
        return FirstName;
    }
    public void setFirstName(String FirstName){
        this.FirstName = FirstName;
    }
    public String getSecondName(){
        return SecondName;
    }
    public void setSecondName(String SecondName){
        this.SecondName = SecondName;
    }
    public String getAddress(){
        return Address;
    }
    public void setAddress(String Adress){
        this.Address = Adress;
    }
    public int getNcard(){
        return ncard;
    }
    public void setNcard(int ncard) {
        this.ncard = ncard;
    }
    public int getNbank(){
        return nbank;
    }
    public void setNbank(int nbank){
        this.nbank = nbank;
    }
    @Override
    public String toString(){
        return "[id: " + id + ", LastName: " + LastName + ", FirstName: " + FirstName + ", SecondName: " + SecondName + ", Address:  " + Address + ", Number of credit card " + ncard + ", Number of bank account: " + nbank + "]";
    }
    @Override
    public int compareTo(customer o) {
        return this.LastName.compareTo(o.LastName);
    }

}

