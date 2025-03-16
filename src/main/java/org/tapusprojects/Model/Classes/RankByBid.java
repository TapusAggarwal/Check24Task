package org.tapusprojects.Model.Classes;

import org.tapusprojects.Model.Interfaces.RankingMechanism;

import java.util.Random;

public class RankByBid implements RankingMechanism {
    @Override
    public double calculateScore(double weight, double bidAmount) {
        return bidAmount;
    }

    @Override
    public double getWeight(Bidder bidder) {
        return 1.0;
//        Random random = new Random();
//        return random.nextDouble();
    }
}
