package main.model;

public interface Observer {
    void update(double amount);

    public String totalToString();
}
