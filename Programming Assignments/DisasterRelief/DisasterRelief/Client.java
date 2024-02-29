// Ameila Li
// 02/20/2024
// CSE 123
// PA2: Disaster Relief
// TA: Jake Page

import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // This method takes a double budget and a list of locations, returns an allocation of locations 
    // under budget that helps the most people, or the allocation that costs the least if more than one 
    // results in most people getting help.
    public static Allocation allocateRelief(double budget, List<Location> sites) {
        // TODO: implement your method here
        
        return allocateRelief(budget, sites, new Allocation());
    }

    // pre: Takes a double budget, a list of locations, and an allocation being considered
    // post: Recursively looks at different allocation combinations, and returns an allocation of locations 
    //       under budget that helps the most people. If more than one allocation helps the same number of people, 
    //       it returns the allocation that costs the least.
    // TODO: add any of your own helper methods here
    private static Allocation allocateRelief(double budget, List<Location> sites, Allocation soFar)
    {
        Allocation currBest = soFar;
        
        if (!sites.isEmpty())
        {
            for (int i = 0; i < sites.size(); i++)
            {
                Location temp = sites.get(i);

                sites.remove(i);

                Allocation result = allocateRelief(budget, sites, soFar.withLoc(temp));

                if (currBest.totalCost() <= budget && result.totalCost() <= budget)
                {
                    if (compare(result, currBest) > 0)
                    {
                        currBest = result;
                    }
                }
                
                sites.add(i, temp);
            }
        }

        return currBest;
    }

    // pre: Takes two allocations.
    // post: Returns 1 if the first allocation helps more people. If both allocations help the same amount of people,
    //       returns a positive number if the first allocation costs less, a negative number if the second allocation
    //       costs less; otherwise, returns -1.
    private static int compare(Allocation a1, Allocation a2)
    {
        if (a1.totalPeople() > a2.totalPeople())
        {
            return 1;
        }
        else if (a1.totalPeople() == a2.totalPeople())
        {
            return (int)(a2.totalCost() - a1.totalCost());
        }
        return -1;
    }


    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
