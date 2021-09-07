package edu.estg.route;

import edu.maen.core.interfaces.IPath;
import edu.maen.core.interfaces.IRecyclingBin;

public class Path implements IPath {

    private IRecyclingBin to;
    private int distance;
    private int duration;

    public Path(IRecyclingBin to, int distance, int duration) {
        this.to = to;
        this.distance = distance;
        this.duration = duration;
    }


    @Override
    public IRecyclingBin getTo() {
        return this.to;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "Path{" +
                "to=" + binId +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof IPath)) {
            return false;
        }

        Path path = (Path) o;

        return path.getTo().equals(this.binId) &&
                path.getDistance() == this.distance &&
                path.getDuration() == this.duration;
    }
}
