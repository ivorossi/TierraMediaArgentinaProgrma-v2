package sistema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

import excepcion.ExcepcionDeProducto;
import excepcion.ExcepcionDelUsuario;
import productos.Atraccion;
import productos.Producto;
import productos.PromoAbsoluta;
import productos.PromoAxB;
import productos.PromoPorcentual;
import usuario.Usuario;

public class AdministradorDeArchivos {
/*
	public ArrayDeque<Usuario> leerArchivoDeUsuario(String direccion) {

		ArrayDeque<Usuario> colaDeVisitantes = new ArrayDeque<Usuario>();
		Usuario visitante = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(direccion));
			
			String linea, nombre, gusto;
			int presupuesto;
			double tiempoDisponible;
			String[] datosDeLinea;

			while ((linea = br.readLine()) != null) {
				try {
					datosDeLinea = linea.split(";");
					nombre = datosDeLinea[0];
					gusto = datosDeLinea[1];
					tiempoDisponible = Double.parseDouble(datosDeLinea[2]);
					presupuesto = Integer.parseInt(datosDeLinea[3]);
					visitante = new Usuario(nombre, gusto, tiempoDisponible, presupuesto);
					colaDeVisitantes.offer(visitante);
				} catch (ExcepcionDelUsuario e) {
					System.out.println(e);

				} catch (NumberFormatException e) {
					System.out.println(e + " no es un numero");

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("faltan datos");
				}
			}
			br.close();

		} catch (IOException e) {
			System.out.println("no existe el archivo " + e);
		}
		return colaDeVisitantes;
	}

	public LinkedList<Atraccion> leerArchivoDeAtracciones(String direccion) {

		LinkedList<Atraccion> listaDeAtracciones = new LinkedList<Atraccion>();
		Atraccion unaAtraccion = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(direccion));
			String linea, nombre, tipo;
			double tiempo;
			int cupo, costo;
			String[] datosDeLinea;

			while ((linea = br.readLine()) != null) {
				try {
					datosDeLinea = linea.split(";");
					nombre = datosDeLinea[0];
					costo = Integer.parseInt(datosDeLinea[1]);
					tiempo = Double.parseDouble(datosDeLinea[2]);
					cupo = Integer.parseInt(datosDeLinea[3]);
					tipo = datosDeLinea[4];

					unaAtraccion = new Atraccion(nombre, tipo, costo, tiempo, cupo);
					listaDeAtracciones.add(unaAtraccion);
				} catch (NumberFormatException e) {
					System.out.println(e + " no es un numero");
				} catch (ExcepcionDeProducto e) {
					System.out.println(e);

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("faltan datos" + e);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("no existe el archivo " + e);
		}
		return listaDeAtracciones;
	}

	public LinkedList<Producto> leerArchivoDePromos(String direccion, LinkedList<Atraccion> atraccionesDelSistema) {

		LinkedList<Producto> listaDePromos = new LinkedList<Producto>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(direccion));

			String linea;
			String[] datosDeLinea;

			while ((linea = br.readLine()) != null) {
				try {
					datosDeLinea = linea.split(";");
					Producto prductoPromo = this.generadorDePromo(datosDeLinea, atraccionesDelSistema);
					listaDePromos.add(prductoPromo);

				} catch (ExcepcionDeProducto e) {
					System.out.println(e);

				} catch (NumberFormatException e) {
					System.out.println(e + " no es un numero");
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("no existe el archivo " + e);
		}
		return listaDePromos;

	}

	private Producto generadorDePromo(String[] datosDeLinea, LinkedList<Atraccion> atraccionesDelSistema)
			throws ExcepcionDeProducto {
		Producto unaPromo = null;
		String nombreDePromo = datosDeLinea[1];
		String tipoDeProducto = datosDeLinea[2];
		double tiempoTotal = 0;
		int costoTotal = 0;
		ArrayList<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();
		for (int i = 4; i < datosDeLinea.length; i++) {
			int a = 0;
			boolean interruptor = true;
			for (Atraccion elemento : atraccionesDelSistema) {
				a++;
				if (elemento.getNombreDeProducto().equalsIgnoreCase(datosDeLinea[i])) {
					atraccionesDePromo.add(elemento);
					tiempoTotal += elemento.getTimepoDeProducto();
					costoTotal += elemento.getCostoTotal();
					interruptor = false;
				} else if (atraccionesDelSistema.size() == a && interruptor) {
					throw new ExcepcionDeProducto(datosDeLinea[i] + ": no es una atraccion previamente cargada");
				}
			}
		}
		for (Atraccion elemento : atraccionesDePromo) {
			if (!elemento.getTipoDeProducto().equalsIgnoreCase(tipoDeProducto))
				throw new ExcepcionDeProducto(
						elemento.getNombreDeProducto() + ": no es una atraccion de tipo: " + tipoDeProducto);
		}

		if (datosDeLinea[0].equalsIgnoreCase("porcentual")) {
			double descuento = Double.parseDouble(datosDeLinea[3]);
			unaPromo = new PromoPorcentual(nombreDePromo, tipoDeProducto, descuento, costoTotal, tiempoTotal,
					atraccionesDePromo);

		} else if (datosDeLinea[0].equalsIgnoreCase("AxB")) {
			boolean interruptor2 = true;
			for (Atraccion elemento : atraccionesDePromo) {
				if (datosDeLinea[3].equals(elemento.getNombreDeProducto()))
					interruptor2 = false;
			}
			if (interruptor2)
				throw new ExcepcionDeProducto(datosDeLinea[3] + ": atraccion gratis no incluida");
			String productoGratis = datosDeLinea[3];

			unaPromo = new PromoAxB(nombreDePromo, tipoDeProducto, productoGratis, costoTotal, tiempoTotal,
					atraccionesDePromo);

		} else if (datosDeLinea[0].equalsIgnoreCase("absoluta")) {
			int cosotoDelPack = Integer.parseInt(datosDeLinea[3]);
			unaPromo = new PromoAbsoluta(nombreDePromo, tipoDeProducto, cosotoDelPack, costoTotal, tiempoTotal,
					atraccionesDePromo);
		} else {
			throw new ExcepcionDeProducto(datosDeLinea[0] + ": no es un tipo de promocion");
		}

		return unaPromo;
	}

	public void imprimirItinerario(Usuario visitante) {

		PrintWriter archivo;
		try {
			archivo = new PrintWriter(
					new FileWriter("D:\\Nueva carpeta\\java eclipse\\TierraMedia\\itinerarios\\" + visitante.getId() + ".out"));
			archivo.println(visitante);
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
}
