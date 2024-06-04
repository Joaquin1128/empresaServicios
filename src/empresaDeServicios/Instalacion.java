package empresaDeServicios;

public class Instalacion extends ServicioGasista {

	public Instalacion(int cliente, int especialista, String domicilio, int artefactos, double precioPorArtefacto) {
		super(cliente, especialista, domicilio, artefactos, precioPorArtefacto);
		this.tipoDeServicio = "GasistaInstalacion";

	}

	@Override
	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		costo = materiales + (artefactos * precioPorArtefacto);

		return costo;
	}
}
