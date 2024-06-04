package empresaDeServicios;

public class Cliente {

	private String nombre;
	private int dni;
	private String telefono;

	public Cliente(int dni, String nombre, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void cambiarTelefono(String telefonoNuevo) {

		telefono = telefonoNuevo;
	}

	@Override
	public String toString() {

		return nombre + "\n" + "Dni: " + dni + "\n" + "Tel: " + telefono;
	}
}
