package edu.estg.container;

import edu.maen.core.interfaces.IContainer;
import edu.maen.core.interfaces.IMeasurement;
import edu.maen.core.interfaces.IRecyclingBin;

import java.time.LocalDateTime;

public class Measurement implements IMeasurement {

    private double value;
    private LocalDateTime time;
    private int id;
    private IContainer container;
    private IRecyclingBin recyclingBin;

    public Measurement(double value, LocalDateTime time, int id, IContainer container, IRecyclingBin recyclingBin) {
        this.value = value;
        this.time = time;
        this.id = id;
        this.container = container;
        this.recyclingBin = recyclingBin;
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
