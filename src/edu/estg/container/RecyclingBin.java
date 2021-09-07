package edu.estg.container;

import edu.estg.route.Path;
import edu.maen.core.enumerations.WasteType;
import edu.maen.core.exceptions.ContainerException;
import edu.maen.core.exceptions.RecyclingBinException;
import edu.maen.core.interfaces.IContainer;
import edu.maen.core.interfaces.IGeographicCoordinates;
import edu.maen.core.interfaces.IPath;
import edu.maen.core.interfaces.IRecyclingBin;

public class RecyclingBin implements IRecyclingBin {

    private final String code;
    private String zone = null;
    private String refLocal = null;
    private IGeographicCoordinates coordinates = null;

    private IContainer[] containers;
    private int containerSize = 10;
    private int containerIndex = 0;

    public IPath[] paths;
    private int pathSize = 10;
    private int pathIndex = 0;

    // Dummy constructor
    public RecyclingBin(String code) {
        this.code = code;
    }

    public RecyclingBin(String code, String zone, String refLocal, IGeographicCoordinates coordinates) {
        this.code = code;
        this.zone = zone;
        this.refLocal = refLocal;
        this.coordinates = coordinates;
        this.containers = new IContainer[this.containerSize];
        this.paths = new IPath[this.pathSize];
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getZone() {
        return this.zone;
    }

    @Override
    public String getRefLocal() {
        return this.refLocal;
    }

    @Override
    public String getType() {
        return "A RecyclingBin can have more than one type";
    }

    @Override
    public IPath getDistanceAndDuration(IRecyclingBin irb) throws RecyclingBinException {

        return null;

    }

    @Override
    public IGeographicCoordinates getCoordinates() {
        return this.coordinates;
    }


    @Override
    public boolean addContainer(IContainer container) throws ContainerException {
        if (container == null) {
            throw new ContainerException("Container is null");
        }

        if (containerExists(container)) {
            return false;
        }

        // Expandir o array
        if (this.containerSize == this.containerIndex) {
            // A capacidade esgotou
            expandContainerArray();
        }

        this.containers[this.containerIndex++] = container;

        return true;
    }

    @Override
    public IContainer getContainer(WasteType wasteType) throws ContainerException {
        for (int i = 0; i < this.containerIndex; i++) {
            if (containers[i].getType().equals(wasteType)) {
                return containers[i];
            }
        }

        throw new ContainerException("Container not found");
    }

    @Override
    public IContainer[] getContainers() {
        return this.containers;
    }

    @Override
    public boolean addPath(IPath path) throws RecyclingBinException {
        if (path == null) {
            throw new RecyclingBinException("Path is null");
        }

        if (pathExists(path)) {
            return false;
        }

        if (this.pathSize == this.pathIndex) {
            expandPathArray();
        }

        this.paths[this.pathIndex++] = path;

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof IRecyclingBin)) {
            return false;
        }

        RecyclingBin recyclingBin = (RecyclingBin) o;

        return recyclingBin.code.equals(this.code);
    }

    private void expandContainerArray() {
        // Passo 1 Criar um novo array
        IContainer[] resizedArray = new IContainer[this.containerSize *= 2];

        // Passo 2 Copiar os elementos do array antigo para o novo array
        for (int i = 0; i < this.containerIndex; i++) {
            resizedArray[i] = this.containers[i];
        }

        // Passo 3 Trocar os apontadores de memoria
        this.containers = resizedArray;
    }

    public boolean containerExists(IContainer iContainer) {
        Container container = (Container) iContainer;

        for (int i = 0; i < this.containerIndex; i++) {
            if (container.equals(this.containers[i])) {
                return true;
            }
        }

        return false;
    }

    private void expandPathArray() {
        IPath[] resizedArray = new IPath[this.pathSize *= 2];

        for (int i = 0; i < this.pathIndex; i++) {
            resizedArray[i] = this.paths[i];
        }

        this.paths = resizedArray;
    }

    public boolean pathExists(IPath iPath) {
        Path path = (Path) iPath;

        for (int i = 0; i < this.pathIndex; i++) {
            if (path.equals(this.paths[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "RecyclingBin{" +
                "code='" + code + '\'' +
                ", zone='" + zone + '\'' +
                ", refLocal='" + refLocal + '\'' +
                ", coordinates=" + coordinates +
                ", containerSize=" + containerSize +
                ", containerIndex=" + containerIndex +
                '}';
    }
}
