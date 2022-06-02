package view;

import util.Configuracion;

public interface PanelYara {
    Configuracion configuracion = new Configuracion();

    public void crearControles();

    public void crearEventos();

    public void crearLayout();

    public void inicializar();

    public void leerConfiguracion();
}
