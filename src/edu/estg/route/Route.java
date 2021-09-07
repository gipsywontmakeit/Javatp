package edu.estg.route;

import edu.maen.core.interfaces.IRecyclingBin;
import edu.maen.core.interfaces.IRoute;

import java.io.IOException;

public class Route implements IRoute {

    private int totalDistance;
    private int totalDuration;

    public Route(int totalDistance, int totalDuration) {
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    @Override
    public IRecyclingBin[] getRoute(double kilometersLimit, double durationLimit, double percentageCriteria, double maxCapacity) {
        return new IRecyclingBin[0];
    }

    @Override
    public double getTotalDistance(IRecyclingBin[] iRecyclingBins) {
        return this.totalDistance;
    }

    @Override
    public double getTotalDuration(IRecyclingBin[] iRecyclingBins) {
        return this.totalDuration;
    }

    @Override
    public String toString() {
        return "Route{" +
                "totalDistance=" + totalDistance +
                ", totalDuration=" + totalDuration +
                '}';
    }

    @Override
    public String export() throws IOException {
        return null;
    }

}
