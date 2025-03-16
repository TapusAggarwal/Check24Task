package org.tapusprojects.Model.Classes;

public class Bid {
    private String searchTerm;
    private double bidAmount;
    private double remainingBudget;

    public Bid(String searchTerm, double maxBid, double remainingBudget) {
        this.searchTerm = searchTerm;
        this.bidAmount = maxBid;
        this.remainingBudget = remainingBudget;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public void deductBudget(double amount) {
        this.remainingBudget = Math.max(0, this.remainingBudget - amount);
    }
}