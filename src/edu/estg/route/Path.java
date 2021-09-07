package edu.estg.route;

import edu.estg.container.RecyclingBin;
import edu.maen.core.exceptions.RecyclingBinException;
import edu.maen.core.interfaces.IPath;
import edu.maen.core.interfaces.IRecyclingBin;

public class Path implements IPath {

    private final RecyclingBin bin;
    private final int distance;
    private final int duration;

    public Path(IRecyclingBin iBin, int distance, int duration) throws RecyclingBinException {

        if (iBin == null) {
            throw new RecyclingBinException("Bin is null");
        }

        if (!(iBin instanceof RecyclingBin)) {
            throw new RecyclingBinException("Bin is not a RecyclingBin Class type");
        }

        this.bin = (RecyclingBin) iBin;
        this.distance = distance;
        this.duration = duration;
    }


    @Override
    public IRecyclingBin getTo() {
        return this.bin;
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
                "to=" + bin.getCode() +
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

        return this.bin.equals(path.getTo()) &&
                this.distance == path.getDistance() &&
                this.duration == path.getDuration();
    }
}
