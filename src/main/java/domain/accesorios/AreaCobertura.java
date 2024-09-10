package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class AreaCobertura {

    @Column(name = "latitud", precision = 9, scale = 6)
    private double latitud;

    @Column(name = "longitud", precision = 9, scale = 6)
    private double longitud;

    @Column(name = "radio", precision = 5, scale = 2)
    private double radio;


}
