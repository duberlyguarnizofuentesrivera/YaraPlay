package view;

import util.Configuracion;

public interface PanelYara {
    Configuracion configuracion = new Configuracion();

    void crearControles();

    void crearEventos();

    void leerConfiguracion();
}
