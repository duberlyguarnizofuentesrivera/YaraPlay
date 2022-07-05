import dao.impl.PersonaDaoImpl;
import model.Persona;
import view.PanelAdmin;
import view.PanelSupervisor;
import view.PanelUsuario;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class YaraAppWindow {
    PanelAdmin panelAdmin;
    PanelSupervisor panelSupervisor;
    PanelUsuario panelUsuario;
    JFrame frame;

    public static void main(String[] args) {
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        List<Persona> personas = personaDao.getAll();
        for (Persona persona : personas) {
            System.out.println(persona);
        }
        //8,Hervey,Coppens,40311837,542551979,hcoppens7@vimeo.com,18198 Forster Drive,2022-02-04 14:28:55.0
        //restamos uno porque las listas están indexadas desde 0
        personaDao.delete(personas.get(7));
        //save
        Persona secondPersona = new Persona();
        secondPersona.setNombre("Hervey Manuel");
        secondPersona.setApellido("Coppens Halle");
        secondPersona.setDni("40311839");
        secondPersona.setTelefono("542551979");
        secondPersona.setEmail("hcoppens7@vimeo.com");
        secondPersona.setDireccion("18198 Forster Drive");
        secondPersona.setFechaCreacion(LocalDateTime.parse("2022-02-04 14:28:55", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //personaDao.save(secondPersona);
    }
}
