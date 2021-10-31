package sistema;

public enum TiposDeAtraccion {
	AVENTURA("aventura"), DEGUSTACION("degustacion"), PAISAJE("paisaje");

	private String nombre;

	private TiposDeAtraccion(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.nombre;
	}

	public boolean esUnTipo(String tipo) {
		if (this.nombre.equalsIgnoreCase(tipo))
			return true;
		return false;
	}
}
