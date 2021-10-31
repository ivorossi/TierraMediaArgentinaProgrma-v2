package excepcion;

@SuppressWarnings("serial")
public class ExcepcionDelUsuario extends Exception {

	public ExcepcionDelUsuario(String msj) {
		System.out.println(msj);
	}
}