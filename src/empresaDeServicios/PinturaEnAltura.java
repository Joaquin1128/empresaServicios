package empresaDeServicios;

class PinturaEnAltura extends ServicioDePintura {

	private int piso;
	private double seguro;
	private double andamios;

	public PinturaEnAltura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado, int piso, double seguro, double alqAndamios) {
		super(dni, nroEspecialista, direccion, metrosCuadrados, precioPorMetroCuadrado);

		if (piso <= 0) {
			throw new RuntimeException("El piso por hora debe ser mayor a 0.");
		}
		if (seguro <= 0) {
			throw new RuntimeException("El costo del seguro debe ser mayor a $0.");
		}
		if (alqAndamios <= 0) {
			throw new RuntimeException("El costo del alquiler de los andamios debe ser mayor a $0.");
		}

		this.piso = piso;
		this.seguro = seguro;
		this.andamios = alqAndamios;
		this.tipoDeServicio = "PinturaEnAltura";
	}

	private double calcularAndamios() {
		if (piso > 5) { // Si el piso es mayor a 5 se aplica el recargo
			andamios = andamios + (andamios * 0.2);
		}
		return andamios;
	}

	@Override
	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		costo = materiales + (metrosCuadrados * precioPorMetroCuadrado) + calcularAndamios() + seguro;
		return costo;
	}

	@Override
	public String toString() {
		return "Servicio N°: " + idServicio + "\n" + "Cliente: " + cliente + "\n" + "Especialista: " + especialista
				+ "\n" + "Direccion: " + direccion + "\n" + "Costo: $" + costo;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
