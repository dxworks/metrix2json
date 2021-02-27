package org.dxworks.metrix2json.Process;

import com.google.gson.Gson;
import org.dxworks.metrix2json.Entity.Metrix2JsonOutput;
import org.dxworks.metrix2json.Entity.MetrixppOutput;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessMetrixppOutput {

    public static String processMatrixpp(List<MetrixppOutput> metrixppOutputs) {

        Map<String, List<MetrixppOutput>> listMap = new TreeMap<>();

        for (MetrixppOutput entity: metrixppOutputs) {
            if (!listMap.containsKey(entity.getFile())) {
                List<MetrixppOutput> entry = new LinkedList<>();
                entry.add(entity);
                listMap.put(entity.getFile(), entry);
            } else {
                List<MetrixppOutput> entry = listMap.get(entity.getFile());
                entry.add(entity);
            }
        }

        List<Metrix2JsonOutput> metrix2JsonOutputs = listMap.entrySet()
                .stream()
                .map(entry -> Metrix2JsonOutput.buildOutput(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        Gson gson = new Gson();

        return gson.toJson(metrix2JsonOutputs);
    }

}
