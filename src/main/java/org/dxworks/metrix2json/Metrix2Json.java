package org.dxworks.metrix2json;

import org.dxworks.metrix2json.Entity.MetrixppOutput;
import org.dxworks.metrix2json.Process.ProcessMetrixppOutput;
import org.dxworks.metrix2json.Reader.FileReader;
import org.dxworks.metrix2json.Reader.MetrixppCSVFileReader;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

public class Metrix2Json {

    public static void main(String[] args) {

        FileReader<MetrixppOutput> metrixppOutputFileReader = MetrixppCSVFileReader.getInstance();

        File inputFile = getInputPath();

        List<MetrixppOutput> outputList;

        try {
            outputList = metrixppOutputFileReader.readFileCSV(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("The provided input file is not valid");
        }

        String jsonOutput = ProcessMetrixppOutput.processMatrixpp(outputList);

        if (System.getProperty("outputJson", null) == null) {
            System.out.println(jsonOutput);
        } else {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputPath()))) {
                writer.write(jsonOutput);
            } catch(IOException e){
                throw new RuntimeException("Couldn't write to output file");
            }
        }
    }

    static File getOutputPath() throws RuntimeException {

        String outputJsonPath = System.getProperty("outputJson", null);

        if (outputJsonPath == null) {
            throw new RuntimeException("No output file provided");
        }

        File outputFile = Paths.get(outputJsonPath).toFile();

        return outputFile;
    }

    static File getInputPath() throws RuntimeException {

        String inputCSVPath = System.getProperty("inputCSV", null);

        if (inputCSVPath == null) {
            throw new RuntimeException("No input file provided");
        }

        File inputFile = Paths.get(inputCSVPath).toFile();

        return inputFile;
    }

}
