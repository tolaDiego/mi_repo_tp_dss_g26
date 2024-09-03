package broker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class DatosSolicitudAperturaMqtt {
    private long idTarjeta;
    private Date fechaHora;
}
