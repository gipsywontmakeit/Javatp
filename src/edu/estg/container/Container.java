package edu.estg.container;

import edu.maen.core.enumerations.WasteType;
import edu.maen.core.exceptions.MeasurementException;
import edu.maen.core.interfaces.IMeasurement;

public class Container implements IContainerWithMeasurements {

    private final String code;
    private double capacity = 0;
    private WasteType type = null;
    private IMeasurement[] measurements;
    private int measurementIndex = 0;
    private int measurementSize = 10;

    // Dummy Constructor for Importer.java
    public Container(String code) {
        this.code = code;
    }

    public Container(String code, double capacity, WasteType type) {
        this.code = code;
        this.capacity = capacity;
        this.type = type;
    }

    @Override
    public String getCode() {
        return this.code;
    }


    @Override
    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public WasteType getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {

        // Se o objeto é exatamente igual a este objeto
        // Compara o endereço de memoria
        if (o == this) {
            return true;
        }

        // Verificar se o objecto é um Container
        if (!(o instanceof Container)) {
            return false;
        }

        Container container = (Container) o;

        return container.code.equals(this.code);
    }

    @Override
    public IMeasurement[] getMeasurements() {
        return this.measurements;
    }

    @Override
    public boolean addMeasurement(IMeasurement measurement) throws MeasurementException {

        if (measurement == null) {
            throw new MeasurementException("Measurement is null");
        }
        // TODO Passo 0: criar as vars -> measurementIndex(done), measurementSize(done) e o array de measurement(done)

        // TODO Passo 1: Verificar se o measurement existe (done)

        for (int i = 0; i < this.measurementIndex; i++) {
            if (measurement.equals(this.measurements[i])) {
                return false;
            }
        }

        // TODO Passo 2: Verificar se é preciso expandir o array (done)
        if (this.measurementSize == this.measurementIndex) {
            expandMeasurementArray();
        }

        // TODO Passo 3: Adicionar a ultima posição (done)
        this.measurements[this.measurementIndex++] = measurement;

        return true;
    }

    /**
     * Expande o tamanho do array no dobro
     */
    private void expandMeasurementArray() {
        IMeasurement[] resizedArray = new IMeasurement[measurementSize *= 2];

        for (int i = 0; i < this.measurementIndex; i++) {
            resizedArray[i] = this.measurements[i];
        }

        this.measurements = resizedArray;
    }

    @Override
    public String toString() {
        return "Container{" +
                "code='" + code + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                ", measurementIndex=" + measurementIndex +
                ", measurementSize=" + measurementSize +
                '}';
    }
}
