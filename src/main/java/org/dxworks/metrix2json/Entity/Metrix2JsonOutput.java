package org.dxworks.metrix2json.Entity;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metrix2JsonOutput {

    private String file;

    private Integer minCyclo;

    private Integer maxCyclo;

    private Integer avgCyclo;

    private List<MetrixppOutput> metrixppOutputs;

    public static Metrix2JsonOutput buildOutput(String file, List<MetrixppOutput> metrixppEntities) {

        List<Integer> ccn = metrixppEntities.stream().map(entity -> {
            if (entity.getCyclomaticComplexity() == null)
                return 0;
            else
                return entity.getCyclomaticComplexity();
        }).collect(Collectors.toList());

        int min = ccn.stream().min(Integer::compareTo).orElse(0);

        int max = ccn.stream().max(Integer::compareTo).orElse(0);

        int avg = ccn.stream().reduce(0, Integer::sum) / ccn.size();

        return Metrix2JsonOutput.builder()
                .file(file)
                .minCyclo(min)
                .maxCyclo(max)
                .avgCyclo(avg)
                .metrixppOutputs(metrixppEntities)
                .build();
    }
}
