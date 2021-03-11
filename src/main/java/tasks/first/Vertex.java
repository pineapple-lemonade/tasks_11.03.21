package tasks.first;

public class Vertex {
	private boolean isPassed = false;
	private int number;

	public Vertex(int number) {
		this.number = number;
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean passed) {
		isPassed = passed;
	}
}
