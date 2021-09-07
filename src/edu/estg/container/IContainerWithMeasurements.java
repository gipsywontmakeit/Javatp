package edu.estg.container;

import edu.maen.core.exceptions.MeasurementException;
import edu.maen.core.interfaces.IContainer;
import edu.maen.core.interfaces.IMeasurement;

interface IContainerWithMeasurements extends IContainer {

    public IMeasurement[] getMeasurements();

    public boolean addMeasurement(IMeasurement measurement) throws MeasurementException;
}

