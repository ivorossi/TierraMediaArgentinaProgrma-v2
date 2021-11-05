package productos;

import excepcion.ExcepcionDeProducto;

public  class Atraccion extends Producto {

	private int cupo;

	public Atraccion(int idAtraccion, String nombre, String tipoDeProducto, int costo, double tiempo, int cupo)
			throws ExcepcionDeProducto {
		super(idAtraccion , nombre, tipoDeProducto, costo, tiempo);
		if (cupo <= 0)
			throw new ExcepcionDeProducto(cupo + ": se esperan cupos mayores a 0");

		this.cupo = cupo;
	}

	@Override
	public boolean hayCupo() {
		return cupo > 0;
	}

	@Override
	public void restarCupo() {
		this.cupo -= 1;
	}

	@Override
	public String toString() {

		return "Atraccion: " + super.toString() + "]";
	}

	public int getCupo() {
		return this.cupo;
	}

	@Override
	public boolean esPromo() {
		return false;
	}

	public boolean verificaCompra(Producto haComprar) {
		return super.equals(haComprar);
	}



}
