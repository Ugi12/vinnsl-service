package at.ac.univie.a0908270.nncloud.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lstm {

    @JsonProperty("id")
    public String id;

    @JsonProperty("text")
    public String text;

    @JsonProperty("textChanged")
    public boolean textChanged;

}
