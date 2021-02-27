package org.dxworks.metrix2json.Entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UnifiedOutput {

    private LocalDate dateOfAnalysis;

    private Integer token;

    private Integer param;

    private Integer length;

    private String location;

    private String file;

    private String methodeNameAndContext;

    private String methodSignature;

    private Integer lineStart;

    private Integer lineEnd;

    private String region;

    private String type;

    private String modified;

    private Integer cyclomaticComplexity;

    private Integer maxindentComplexity;

    private Integer codeLines;

    private Integer preprocessorLines;

    private Integer commentsLines;

    private Integer simpleMi;

    private Integer globalsMember;

}
