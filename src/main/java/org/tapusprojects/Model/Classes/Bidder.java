package org.tapusprojects.Model.Classes;

import java.util.HashMap;
import java.util.Map;

public class Bidder {
    private Long id;
    private Map<String, Bid> bids = new HashMap<>();

    public Bidder(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Bid getBid(String searchTerm) {
        return bids.get(searchTerm);
    }

    public void addOrUpdateBid(String searchTerm, double maxBid, double budget) {
        bids.put(searchTerm, new Bid(searchTerm, maxBid, budget));
    }

    public void updateMaxBid(String searchTerm, double newMaxBid) {
        Bid bid = bids.get(searchTerm);
        if (bid != null) {
            bid.setBidAmount(newMaxBid);
        }
    }

    public void updateBudget(String searchTerm, double newBudget) {
        Bid bid = bids.get(searchTerm);
        if (bid != null) {
            bid.setRemainingBudget(newBudget);
        }
    }
}