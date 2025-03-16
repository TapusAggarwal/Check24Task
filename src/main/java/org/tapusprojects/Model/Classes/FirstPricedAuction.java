package org.tapusprojects.Model.Classes;

import org.tapusprojects.Model.Interfaces.PricingMechanism;

import java.util.List;

public class FirstPricedAuction implements PricingMechanism {
    @Override
    public double calculatePrice(List<Bid> sortedBids, int position) {
        return sortedBids.get(position).getBidAmount();
    }
}
