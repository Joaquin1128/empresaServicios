package empresaDeServicios;

public class Especialista {

	private int numero; // ID del especialista
	private String nombre;
	private String telefono;
	private String especialidad;

	public Especialista(int numero, String nombre, String telefono, String especialidad) {
		this.numero = numero;
		this.nombre = nombre;
		this.telefono = telefono;
		this.especialidad = especialidad;

	}

	public int getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEspecializacion() {
		return especialidad;
	}

	public void cambiarTelefono(String telefonoNuevo) {

		telefono = telefonoNuevo;
	}

	@Override
	public String toString() {

		return "Especialista N°: " + numero + " " + nombre + "\n" + "Tel: " + telefono + "\n" + "Especialidad: "
				+ especialidad;

	}
}