package at.ac.univie.a0908270.nncloud.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Process {

    @JsonProperty("id")
    public String id;

    @JsonProperty("trainingProcess")
    public String trainingProcess;
}
