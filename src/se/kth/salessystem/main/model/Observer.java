package se.kth.salessystem.main.model;

public interface Observer {
    void update(double amount);

    public String totalToString();
}
