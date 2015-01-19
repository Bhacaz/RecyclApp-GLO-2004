package recyclapp.utilitaires;

public class Grille {
	private int m_distance;
	private boolean m_magnetique;

	public int reqDistance() {
		return this.m_distance;
	}

	public void asgDistance(int p_distance) {
		this.m_distance=p_distance;
	}

	public boolean reqMagnetique() {
		return this.m_magnetique;
	}

	public void asgMagnetique(Boolean p_magnetique) {
		this.m_magnetique = p_magnetique;
	}
}