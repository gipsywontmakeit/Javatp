package edu.estg.route;

import edu.maen.core.interfaces.IRecyclingBin;
import edu.maen.core.interfaces.IRoute;

import java.io.IOException;

public class Route implements IRoute {

    private int totalDistance;
    private int totalDuration;

    /**
     *
     * @param totalDistance
     * @param totalDuration
     */
    public Route(int totalDistance, int totalDuration) {
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    /**
     *
     * @param kilometersLimit
     * @param durationLimit
     * @param percentageCriteria
     * @param maxCapacity
     * @return
     */
    @Override
    public IRecyclingBin[] getRoute(double kilometersLimit, double durationLimit, double percentageCriteria, double maxCapacity) {
        return new IRecyclingBin[0];
    }

    /**
     *
     * @param iRecyclingBins
     * @return
     */
    @Override
    public double getTotalDistance(IRecyclingBin[] iRecyclingBins) {
        return this.totalDistance;
    }

    /**
     *
     * @param iRecyclingBins
     * @return
     */
    @Override
    public double getTotalDuration(IRecyclingBin[] iRecyclingBins) {
        return this.totalDuration;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Route{" +
                "totalDistance=" + totalDistance +
                ", totalDuration=" + totalDuration +
                '}';
    }

    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public String export() throws IOException {
        return null;
    }

}
