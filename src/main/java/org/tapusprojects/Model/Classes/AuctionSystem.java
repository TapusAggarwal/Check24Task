package org.tapusprojects.Model.Classes;

import org.tapusprojects.Model.Interfaces.PricingMechanism;
import org.tapusprojects.Model.Interfaces.RankingMechanism;

import java.util.ArrayList;
import java.util.HashMap;

public class AuctionSystem {
    public ArrayList<Bidder> bidderList = new ArrayList<>();
    public HashMap<Long, Double> bidderWeights = new HashMap<>();
    public RankingMechanism rankingMechanism;
    public PricingMechanism pricingMechanism;

    public double addBidder(Bidder bidder) {
        this.bidderList.add(bidder);
        double weight = this.rankingMechanism.getWeight(bidder);
        this.bidderWeights.put(bidder.getId(), weight);
        return weight;
    }

    public void NewAuction(String searchTerm) {
        // Implementation for new auction
    }
}