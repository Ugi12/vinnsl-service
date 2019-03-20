package at.ac.univie.a0908270.nncloud.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Statistic {

    @JsonProperty("id")
    public String id;

    @JsonProperty("createTimestamp")
    public String createTimestamp;

    @JsonProperty("lastUpdateTime")
    public String lastUpdateTime;

    @JsonProperty("trainingTime")
    public String trainingTime;

    @JsonProperty("numberOfTraining")
    public Integer numberOfTraining;

    @JsonProperty("lastResult")
    public String lastResult;

    @JsonProperty("bestResult")
    public String bestResult;

    @JsonProperty("epochs")
    public String epochs;

    @JsonProperty("learningRate")
    public String learningRate;

    @JsonProperty("loss")
    public String loss;

    @JsonProperty("batchSize")
    public String batchSize;
}
