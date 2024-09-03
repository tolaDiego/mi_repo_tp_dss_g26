package adapters.notificadores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class Mensaje {
    private  String descripcion;
    private Date fechaNotificacion;

    public Mensaje() {

    }
}
