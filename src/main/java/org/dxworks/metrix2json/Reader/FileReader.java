package org.dxworks.metrix2json.Reader;

import org.dxworks.metrix2json.Entity.UnifiableOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileReader <A extends UnifiableOutput> {

    List<A> readFileCSV(File path) throws FileNotFoundException;
}
