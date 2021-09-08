package edu.estg.json;

import edu.estg.container.Container;
import edu.estg.container.Measurement;
import edu.estg.container.RecyclingBin;
import edu.estg.route.GeographicCoordinates;
import edu.estg.route.Path;
import edu.maen.core.enumerations.WasteType;
import edu.maen.core.exceptions.CityException;
import edu.maen.core.exceptions.ContainerException;
import edu.maen.core.exceptions.MeasurementException;
import edu.maen.core.exceptions.RecyclingBinException;
import edu.maen.core.interfaces.ICity;
import edu.maen.core.interfaces.IRecyclingBin;
import edu.maen.io.interfaces.IImporter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Importer implements IImporter {

    public boolean isBinsFileImported = false;
    public static boolean isDistancesFileImported = false;
    public static boolean isMeasurementsFileImported = false;

    private FileType detectFileType(JSONArray jsonArray) {

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;

            if (jsonObject.containsKey("to") && jsonObject.containsKey("from")) {
                return FileType.DISTANCES_FILE;
            }

            if (jsonObject.containsKey("contentor") &&
                    jsonObject.containsKey("data") &&
                    jsonObject.containsKey("valor")
            ) {
                return FileType.MEASUREMENTS_FILE;
            }

            if (jsonObject.containsKey("Codigo") &&
                    jsonObject.containsKey("Ref. Localização") &&
                    jsonObject.containsKey("Zona") &&
                    jsonObject.containsKey("Latitude") &&
                    jsonObject.containsKey("Longitude") &&
                    jsonObject.containsKey("Contentores")
            ) {
                return FileType.BINS_FILE;
            }
        }

        return FileType.UNDETECTED;
    }

    /**
     * Método para associar o WasteType ao código do ecoponto*
     *
     * @param containerCode código do ecoponto
     * @return
     */
    private WasteType assignWasteType(String containerCode) {
        if (containerCode.startsWith("V")) {
            return WasteType.V;
        } else if (containerCode.startsWith("B")) {
            return WasteType.B;
        } else if (containerCode.startsWith("E")) {
            return WasteType.E;
        } else if (containerCode.startsWith("P")) {
            return WasteType.P;
        }

        return null;
    }

    private void importDistances(ICity city, JSONArray binData) throws RecyclingBinException {
        for (Object o : binData) {
            JSONObject jsonObject = (JSONObject) o;

            JSONArray binsArray = (JSONArray) jsonObject.get("to");

            RecyclingBin dummyBin = new RecyclingBin(jsonObject.get("from").toString());
            for (IRecyclingBin iBin : city.getRecyclingBin()) {
                if (iBin == null) {
                    continue;
                }

                RecyclingBin bin = (RecyclingBin) iBin;
                if (bin.equals(dummyBin)) {

                    for (Object binObject : binsArray) {
                        JSONObject jsonBinObject = (JSONObject) binObject;

                        if (!(jsonBinObject.containsKey("name") &&
                                jsonBinObject.containsKey("distance") &&
                                jsonBinObject.containsKey("duration"))
                        ) {
                            throw new RecyclingBinException("Invalid parameters");
                        }

                        RecyclingBin dummyDestinationBin = new RecyclingBin(jsonBinObject.get("name").toString());

                        Path path = new Path(
                                dummyDestinationBin,
                                Integer.parseInt(jsonBinObject.get("distance").toString()),
                                Integer.parseInt(jsonBinObject.get("duration").toString())

                        );

                        if (!bin.addPath(path)) {
                            throw new RecyclingBinException("Path cannot be added!");
                        }
                    }
                }
            }
        }
    }

    private void importBins(ICity city, JSONArray binData) throws RecyclingBinException, ContainerException {
        for (Object o : binData) {
            JSONObject jsonObject = (JSONObject) o;

            // passar a [ objeto -> string -> double ]
            GeographicCoordinates coordinates = new GeographicCoordinates(
                    Double.parseDouble(jsonObject.get("Latitude").toString()),
                    Double.parseDouble(jsonObject.get("Longitude").toString())
            );

            RecyclingBin bin = new RecyclingBin(
                    jsonObject.get("Codigo").toString(),
                    jsonObject.get("Ref. Localização").toString(),
                    jsonObject.get("Zona").toString(),
                    coordinates
            );

            JSONArray containersArray = (JSONArray) jsonObject.get("Contentores");
            // todos os elementos que estiverem no array passam a objeto
            for (Object containerObject : containersArray) {
                JSONObject container = (JSONObject) containerObject;

                if (!(container.containsKey("codigo") && container.containsKey("capacidade"))) {
                    throw new RecyclingBinException("Invalid Containers");
                }

                // Para extrair o WasteType do Contentor temos que olhar para a primeira letra do Codigo
                String containerCode = container.get("codigo").toString();

                // Ler JsonArray "Contentores"
                // Injetar propiedades na class Container
                // Injetar Container no RecyclingBin
                Container binContainer = new Container(
                        containerCode,
                        Double.parseDouble(container.get("capacidade").toString()),
                        assignWasteType(containerCode)
                );

                if (!bin.addContainer(binContainer)) {
                    throw new ContainerException("Cannot add container");
                }
            }

            if (!city.addRecyclingBin(bin)) {
                throw new RecyclingBinException("Cannot add bin");
            }
        }
    }

    private void importMeasurements(ICity city, JSONArray readingData) throws ContainerException, MeasurementException {
        for (Object o : readingData) {
            JSONObject jsonObject = (JSONObject) o;

            // ISO8601 -> estudar bem sobre isto
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(
                    jsonObject.get("data").toString(),
                    formatter
            );

            String containerId = jsonObject.get("contentor").toString();

            Container dummyContainer = new Container(containerId);

            Measurement measurement = new Measurement(
                    jsonObject.get("contentor").toString(),
                    dateTime,
                    Double.parseDouble(jsonObject.get("valor").toString())
            );

            if (!(city.addMeasurement(measurement, dummyContainer))) {
                throw new MeasurementException("Cannot Add Measurement -> " + dummyContainer.getCode());
            }
        }
    }

    /**
     * @param city
     * @param path
     * @throws IOException
     * @throws CityException
     */
    @Override
    public void importData(ICity city, String path) throws IOException, CityException {

        if (city == null) {
            throw new CityException("An error occurred... quitting! (City doesn't exist.");
        }

        if (!path.endsWith(".json")) {
            throw new IOException("Invalid file type!");
        }

        // Passar o ficheiro Json para um JsonArray
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(path));
        } catch (ParseException e) {
            throw new IOException("Cannot read the file");
        }

        switch (detectFileType(jsonArray)) {
            case BINS_FILE:
                try {
                    importBins(city, jsonArray);
                    this.isBinsFileImported = true;
                } catch (RecyclingBinException | ContainerException e) {
                    throw new IOException(e.toString());
                }

                break;
            case DISTANCES_FILE:
                if (!this.isBinsFileImported) {
                    throw new IOException("Bins file must be imported first!");
                }

                try {
                    importDistances(city, jsonArray);
                    isDistancesFileImported = true;
                } catch (RecyclingBinException e) {
                    throw new IOException(e.toString());
                }

                break;
            case MEASUREMENTS_FILE:
                if (!this.isBinsFileImported) {
                    throw new IOException("Bins file must be imported first!");
                }

                try {
                    importMeasurements(city, jsonArray);
                    isMeasurementsFileImported = true;
                } catch (ContainerException | MeasurementException e) {
                    throw new IOException(e.toString());
                }
                break;
            case UNDETECTED:
                throw new IOException("Invalid File");

        }
    }

    /**
     * @param s
     * @throws IOException
     */
    @Override
    public void report(String s) throws IOException {
    }
}
