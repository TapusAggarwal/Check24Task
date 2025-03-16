package org.tapusprojects.Model;

import org.tapusprojects.Model.Classes.*;
import org.tapusprojects.Model.Interfaces.PricingMechanism;
import org.tapusprojects.Model.Interfaces.RankingMechanism;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create the auction system with ranking and pricing mechanisms
        AuctionSystem auctionSystem = new AuctionSystem();
        auctionSystem.rankingMechanism = new RankByBid();
        auctionSystem.pricingMechanism = new FirstPricedAuction();

        // Create random bidders with random bids
        Random random = new Random();
        String[] searchTerms = {"shoes", "laptop", "phone", "watch", "headphones"};

        // Create 5 bidders with random bids for different search terms
        for (long i = 1; i <= 5; i++) {
            Bidder bidder = new Bidder(i);

            // Each bidder bids on 2-4 random search terms
            int numBids = random.nextInt(3) + 2;
            Set<String> bidTerms = new HashSet<>();

            while (bidTerms.size() < numBids) {
                String term = searchTerms[random.nextInt(searchTerms.length)];
                bidTerms.add(term);
            }

            // Add bids for each selected search term
            for (String term : bidTerms) {
                double maxBid = 0.5 + random.nextDouble() * 9.5; // Random bid between 0.5 and 10.0
                double budget = 50.0 + random.nextDouble() * 950.0; // Random budget between 50 and 1000

                // Round to 2 decimal places
                maxBid = Math.round(maxBid * 100.0) / 100.0;
                budget = Math.round(budget * 100.0) / 100.0;

                bidder.addOrUpdateBid(term, maxBid, budget);
            }

            // Add bidder to auction system
            double weight = auctionSystem.addBidder(bidder);
            System.out.println("Bidder " + i + " added with weight: " + weight);
        }

        // Print all bidders and their bids
        System.out.println("\n--- Bidder Information ---");
        for (Bidder bidder : auctionSystem.bidderList) {
            System.out.println("Bidder ID: " + bidder.getId());

            for (String term : searchTerms) {
                Bid bid = bidder.getBid(term);
                if (bid != null) {
                    System.out.println("  Search Term: " + term +
                            ", Bid Amount: $" + bid.getBidAmount() +
                            ", Budget: $" + bid.getRemainingBudget());
                }
            }
            System.out.println();
        }

        // Simulate auctions for each search term
        System.out.println("--- Auction Results ---");
        for (String term : searchTerms) {
            System.out.println("\nAuction for search term: " + term);

            // Collect all bids for this search term
            List<Bid> bids = new ArrayList<>();
            Map<Bid, Bidder> bidToBidder = new HashMap<>();

            for (Bidder bidder : auctionSystem.bidderList) {
                Bid bid = bidder.getBid(term);
                if (bid != null && bid.getRemainingBudget() >= bid.getBidAmount()) {
                    bids.add(bid);
                    bidToBidder.put(bid, bidder);
                }
            }

            // Sort bids by bid amount (descending)
            bids.sort((b1, b2) -> Double.compare(b2.getBidAmount(), b1.getBidAmount()));

            // Display auction results
            if (bids.isEmpty()) {
                System.out.println("No valid bids for this term.");
                continue;
            }

            System.out.println("Ranking of bids:");
            for (int i = 0; i < bids.size(); i++) {
                Bid bid = bids.get(i);
                Bidder bidder = bidToBidder.get(bid);
                double price = auctionSystem.pricingMechanism.calculatePrice(bids, i);

                System.out.println("Position " + (i+1) + ": Bidder " + bidder.getId() +
                        " - Bid: $" + bid.getBidAmount() +
                        ", Price to pay: $" + price);

                // Deduct budget for winner (position 0)
                if (i == 0) {
                    bid.deductBudget(price);
                    System.out.println("  Winner's remaining budget: $" + bid.getRemainingBudget());
                }
            }
        }
    }
}