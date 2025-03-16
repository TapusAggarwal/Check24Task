package org.tapusprojects.Model.Interfaces;


import org.tapusprojects.Model.Classes.Bid;

import java.util.List;

public interface PricingMechanism {
    double calculatePrice(List<Bid> sortedBids, int position);
}
