package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AreaCobertura {
    private double latitud;
    private double longitud;
    private double radio;
}
