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
@Entity
@Table(name = "area_cobertura")
public class AreaCobertura {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "latitud", precision = 9, scale = 6)
    private double latitud;

    @Column(name = "longitud", precision = 9, scale = 6)
    private double longitud;

    @Column(name = "radio", precision = 5, scale = 2)
    private double radio;


}
