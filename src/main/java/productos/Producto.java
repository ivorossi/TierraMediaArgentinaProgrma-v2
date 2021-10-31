package productos;

import excepcion.ExcepcionDeProducto;
import sistema.TiposDeAtraccion;

public abstract class  Producto   {
	
	private int idProducto;
	private String nombreDeProducto;
	private String tipoDeProducto;
	protected int costoTotal;
	protected double timepoDeProducto;
	

	public Producto(int idProducto, String nombreDeProducto, String tipoDeProducto) {
		this.idProducto = idProducto;
		this.nombreDeProducto = nombreDeProducto;
		this.tipoDeProducto = tipoDeProducto;
	}

	public Producto(int idProducto, String nombreDeProducto, String tipoDeProducto, int costoTotal, double tiempoDeProducto)
			throws ExcepcionDeProducto {

		if (!(TiposDeAtraccion.AVENTURA.esUnTipo(tipoDeProducto) || TiposDeAtraccion.PAISAJE.esUnTipo(tipoDeProducto)
				|| TiposDeAtraccion.DEGUSTACION.esUnTipo(tipoDeProducto)))
			throw new ExcepcionDeProducto(tipoDeProducto + ": no es un valor valido(paisaje,aventura,degustacion");
		if (costoTotal <= 0)
			throw new ExcepcionDeProducto(costoTotal + ": se esperan costos mayores a 0");
		if (tiempoDeProducto <= 0)
			throw new ExcepcionDeProducto(tiempoDeProducto + ": se esperan tiempos mayores a 0");
		this.idProducto = idProducto;
		this.nombreDeProducto = nombreDeProducto;
		this.tipoDeProducto = tipoDeProducto;
		this.costoTotal = costoTotal;
		this.timepoDeProducto = tiempoDeProducto;
	}

	public abstract boolean esPromo();

	public abstract boolean hayCupo();
	
	public abstract void restarCupo();
	
	public abstract boolean verificaCompra(Producto t);

	public String getTipoDeProducto() {
		return tipoDeProducto;
	}

	public int getCostoTotal() {
		return costoTotal;
	}


	public double getTimepoDeProducto() {
		return timepoDeProducto;
	}

	public String getNombreDeProducto() {
		return nombreDeProducto;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void SetIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	@Override
	public String toString() {
		return nombreDeProducto + "  [ Tipo: " + tipoDeProducto + ",  costo del producto: " + costoTotal + " monedas";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + costoTotal;
		result = prime * result + idProducto;
		result = prime * result + ((nombreDeProducto == null) ? 0 : nombreDeProducto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(timepoDeProducto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipoDeProducto == null) ? 0 : tipoDeProducto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (costoTotal != other.costoTotal)
			return false;
		if (idProducto != other.idProducto)
			return false;
		if (nombreDeProducto == null) {
			if (other.nombreDeProducto != null)
				return false;
		} else if (!nombreDeProducto.equals(other.nombreDeProducto))
			return false;
		if (Double.doubleToLongBits(timepoDeProducto) != Double.doubleToLongBits(other.timepoDeProducto))
			return false;
		if (tipoDeProducto == null) {
			if (other.tipoDeProducto != null)
				return false;
		} else if (!tipoDeProducto.equals(other.tipoDeProducto))
			return false;
		return true;
	}
	

}
