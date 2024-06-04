package empresaDeServicios;

abstract class ServicioGasista extends Servicio {

	protected int artefactos;
	protected double precioPorArtefacto;

	public ServicioGasista(int cliente, int especialista, String domicilio, int artefactos, double precioPorArtefacto) {

		super(cliente, especialista, domicilio);

		if (artefactos <= 0) {
			throw new RuntimeException("Los artefactos deben ser mas que 0.");
		}
		if (precioPorArtefacto <= 0) {
			throw new RuntimeException("El precio por artefacto debe ser mayor a 0hs.");
		}

		this.artefactos = artefactos;
		this.precioPorArtefacto = precioPorArtefacto;
		this.tipoDeServicio = "ServicioGasista";
	}

	public int getArtefactos() {
		return artefactos;
	}

	public void cambiarEspecialista(int especialista) {
		this.especialista = especialista;
	}

	@Override
	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		costo = materiales + (artefactos * precioPorArtefacto);
		return costo;
	}

	@Override
	public String toString() {
		return "Servicio N°: " + idServicio + "\n" + "Cliente: " + cliente + "\n" + "Especialista: " + especialista
				+ "\n" + "Direccion: " + direccion + "\n" + "Costo: $" + costo;

	}
}