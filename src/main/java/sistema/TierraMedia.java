package sistema;

import java.util.LinkedList;
import java.util.Scanner;

import dao.AtraccionDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import productos.Atraccion;
import productos.Producto;
import productos.Promo;
import usuario.Usuario;

public class TierraMedia {
	
	private LinkedList<Usuario> colaDeVisitantes = new LinkedList<Usuario>();
	private LinkedList<Producto> productosOfertables = new LinkedList<Producto>();
	private LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
	private LinkedList<Promo> listaDePromos = new LinkedList<Promo>();


	public void sistema() {
		AtraccionDAOImpl coneccionAtraccion = new AtraccionDAOImpl();
		PromocionDAOImpl coneccionPromocion = new PromocionDAOImpl();
		UsuarioDAOImpl coneccionUsuario = new UsuarioDAOImpl();
		
		Scanner teclado = new Scanner(System.in);
		String asd;
		colaDeVisitantes = coneccionUsuario.dameTodos();
		System.out.println("-----------------------------------------");
		atracciones = coneccionAtraccion.dameTodos();
		System.out.println("-----------------------------------------");
		listaDePromos = coneccionPromocion.dameTodos();
		System.out.println("-----------------------------------------");

		productosOfertables.addAll(atracciones);
		productosOfertables.addAll(listaDePromos);
		System.out.println(productosOfertables);
		for(Usuario visitante: colaDeVisitantes) {
			
			OrdenarLista ordenarOfertables = new OrdenarLista(visitante);
			productosOfertables.sort(ordenarOfertables);

			System.out.println("\n-----------------------------------------------------------------------------\n"
					+ "Bienvenido/a a la tierra media: " + visitante.getNombre() + "\nVamos a Comprar");

			for (Producto elemento : productosOfertables) {

				if (visitante.loPuedoComprar(elemento)) {

					System.out.println("\n" + elemento + "\n");
					do {
						System.out.println("Desea comprar el: " + elemento.getNombreDeProducto()
								+ ",  presione (y), si no, presione (n)");
						asd = teclado.nextLine();
					} while (!(asd.equalsIgnoreCase("y") || asd.equalsIgnoreCase("n")));

					if (asd.equalsIgnoreCase("y"))
						visitante.Comprar(elemento);
				}
			}

		}
		teclado.close();
	}
}
