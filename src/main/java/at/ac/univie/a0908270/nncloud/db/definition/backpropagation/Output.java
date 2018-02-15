package at.ac.univie.a0908270.nncloud.db.definition.backpropagation;

public class Output extends BackpropagationStructure {
	public Output(String id, Integer dimension, Integer size) {
		this.id = id;
		this.dimension = dimension;
		this.size = size;
	}
	
	public Output() {
	}
}
