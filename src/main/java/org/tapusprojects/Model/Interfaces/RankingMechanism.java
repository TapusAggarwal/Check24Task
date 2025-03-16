package org.tapusprojects.Model.Interfaces;

import org.tapusprojects.Model.Classes.Bidder;

public interface RankingMechanism {
    double calculateScore(double weight, double bidAmount);

    double getWeight(Bidder bidder);

}
