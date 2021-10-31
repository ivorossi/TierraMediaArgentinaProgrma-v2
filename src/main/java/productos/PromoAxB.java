package productos;

import java.util.ArrayList;

import excepcion.ExcepcionDeProducto;

public class PromoAxB extends Promo {

	private String atraccionGratis;

	public PromoAxB(int idProducto, String nombreDeProducto, String tipoDeProducto,
			String atraccionGratis,  ArrayList<Atraccion> atracciones) throws ExcepcionDeProducto {

		super(idProducto, nombreDeProducto, tipoDeProducto, atracciones);

		this.atraccionGratis = atraccionGratis;
		this.aplicarDescuento();
	}

	private void aplicarDescuento() {
		for (Atraccion elemento : super.atracciones) {
			if (this.atraccionGratis.equals(elemento.getNombreDeProducto()))
				super.costoTotal -= elemento.getCostoTotal();
		}
	}

	@Override
	public String toString() {
		return "Promo de tipo AxB la atraccion: " + atraccionGratis + " es gratis.\n" + super.toString();
	}

	@Override
	public String getTipoDePromo() {
		return "AxB";
	}

	@Override
	public String getDescuento() {
		return this.atraccionGratis;
	}

}
