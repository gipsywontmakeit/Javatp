package edu.estg.container;

import edu.maen.core.interfaces.IContainer;
import edu.maen.core.interfaces.IMeasurement;
import edu.maen.core.interfaces.IRecyclingBin;

import java.time.LocalDateTime;

public class Measurement implements IMeasurement {

    private final String containerId;
    private final LocalDateTime time;
    private final double value;

    public Measurement(String containerId, LocalDateTime time, double value) {
        this.containerId = containerId;
        this.time = time;
        this.value = value;
    }

    @Override
    public int getId() {
        String containerIdWithoutLetters = this.containerId.replaceAll("([A-Z])", "");
        return Integer.parseInt(containerIdWithoutLetters);
    }

    @Override
    public IContainer getContainer() {
        return null;
    }

    @Override
    public IRecyclingBin getRecyclingBin() {
        return null;
    }

    @Override
    public LocalDateTime getDate() {
        return this.time;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Measurement)) {
            return false;
        }

        Measurement measurement = (Measurement) o;

        //pois os "sensores" podem fazer a leitura ao mesmo tempo (time) e em contentores diferentes (code)
        return measurement.time.equals(this.time) && measurement.containerId.equals(this.containerId);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "containerId='" + containerId + '\'' +
                ", time=" + time +
                ", value=" + value +
                '}';
    }
}
