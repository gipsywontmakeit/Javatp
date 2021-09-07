package edu.estg.route;

import edu.estg.container.Container;
import edu.estg.container.RecyclingBin;
import edu.maen.core.enumerations.WasteType;
import edu.maen.core.exceptions.MeasurementException;
import edu.maen.core.exceptions.RecyclingBinException;
import edu.maen.core.interfaces.ICity;
import edu.maen.core.interfaces.IContainer;
import edu.maen.core.interfaces.IMeasurement;
import edu.maen.core.interfaces.IRecyclingBin;

import java.util.Arrays;

public class City implements ICity {

    private final String name;

    private IRecyclingBin[] bins;
    private int binsSize = 10;
    private int binsIndex = 0;

    public City(String name) {
        this.name = name;
        this.bins = new IRecyclingBin[this.binsSize];
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean addRecyclingBin(IRecyclingBin recyclingBin) throws RecyclingBinException {

        if (recyclingBin == null) {
            throw new RecyclingBinException("RecyclingBin is null!");
        }

        if (binExists(recyclingBin)) {
            return false;
        }

        if (this.binsIndex == this.binsSize) {
            expandRecyclingBin();
        }

        this.bins[this.binsIndex++] = recyclingBin;

        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean addMeasurement(IMeasurement measurement, IContainer dummyContainer) throws MeasurementException {

        if (measurement == null) {
            throw new MeasurementException("Measurement is null!");
        }

        //percorre as bins depois o "container compara com o getCode do de cima ^^^^^^^^
        for (int i = 0; i < this.binsIndex; i++) {
            IContainer[] containers = this.bins[i].getContainers();

            for (int j = 0; j < containers.length; j++) {

                if (containers[j] == null) {
                    continue; // [C, C, C, C, C, C, null, C, C, C, NULL, C, C]
                }

                //Porque quero aceder ao equals do container e não do objeto
                Container container = (Container) containers[j];
                // Comparar o containerId (container) com o containerId do DummyContainer
                if (container.equals(dummyContainer)) {
                    return container.addMeasurement(measurement);
                }
            }
        }

        return false;
    }

    @Override
    public IRecyclingBin[] getRecyclingBin() {
        return this.bins;
    }

    @Override
    public IMeasurement[] getMeasurements(IRecyclingBin iRecyclingBin, WasteType wasteType) {
        /**
         IMeasurement[] typeOfMeasurement;
         int sizeOfArray = 0;

         for (int j = 0; j < irb.length; j++) {
         for (int i = 0; i < im.length; i++) {
         try {
         if (irb[j].getContainer(wt) == im[i].getContainer()) {
         sizeOfArray++;
         }
         } catch (ContainerException ex) {
         System.out.println("An error has occurred, quitting...");
         }
         }
         }

         typeOfMeasurement = new IMeasurement[sizeOfArray];

         for (int j = 0; j < irb.length; j++) {

         for (int i = 0; i < im.length; i++) {

         try {
         if (irb[j].getContainer(wt) == im[i].getContainer()) {
         for (int k = 0; k < typeOfMeasurement.length; k++) {
         if (typeOfMeasurement[k] == null) {
         typeOfMeasurement[k] = im[i];
         }
         }
         }
         } catch (ContainerException ex) {
         System.out.println("An error occurred, quitting...");
         }


         }
         }

         return typeOfMeasurement;
         **/

        return null;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", bins=" + Arrays.toString(bins) +
                ", binsSize=" + binsSize +
                ", binsIndex=" + binsIndex +
                '}';
    }

    // TODO Para Apagar depois
    // Linha 166 DummyContainer Importer.java
    public IContainer findContainerById(String containerId) {

        for (int i = 0; i < this.binsIndex; i++) {
            IContainer[] containers = this.bins[i].getContainers();
            for (int j = 0; j < containers.length; j++) {
                if (containers[j] != null && containers[j].getCode().equals(containerId)) {
                    return containers[j];
                }
            }
        }

        return null;
    }

    private void expandRecyclingBin() {
        IRecyclingBin[] resizedArray = new IRecyclingBin[binsSize *= 2];

        for (int i = 0; i < this.binsIndex; i++) {
            resizedArray[i] = this.bins[i];
        }

        this.bins = resizedArray;
    }

    public boolean binExists(IRecyclingBin iRecyclingBin) {
        RecyclingBin bin = (RecyclingBin) iRecyclingBin;

        for (int i = 0; i < this.binsIndex; i++) {
            if (bin.equals(this.bins[i])) {
                return true;
            }
        }

        return false;
    }

}

