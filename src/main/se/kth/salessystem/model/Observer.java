package main.se.kth.salessystem.model;

public interface Observer {
    void update(double amount);

    public String totalToString();
}
