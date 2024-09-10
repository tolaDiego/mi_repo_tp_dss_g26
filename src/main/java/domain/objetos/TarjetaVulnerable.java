package domain.objetos;

import domain.accesorios.Apertura;
import domain.personas.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "tarjeta_vulnerable")
public class TarjetaVulnerable {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    @JoinColumn(name="id_tarjeta_vulnerable")
    private List<Apertura> aperturas;
    @ManyToOne
    @JoinColumn(name= "id_persona_vulnerable")
    private Vulnerable personaVul;

    public TarjetaVulnerable() {
        this.aperturas = new ArrayList<>();
    }

    public void agregarApertura(Apertura nuevaApertura) {
        if (this.aperturasRestantes() > 0) {
            aperturas.add(nuevaApertura);
        }
    }

    public int aperturasRestantes() {
        Date fechaActual = new Date();

        // Filtrar aperturas del día actual
        List<Apertura> aperturasDeHoy = aperturas.stream()
                .filter(a -> esMismaFecha(a.getFechaDeUso(), fechaActual))
                .toList();

        // Calcular aperturas restantes
        int maxAperturasPermitidas = 4 + personaVul.getMenoresACargo() * 2;
        return Math.max(maxAperturasPermitidas - aperturasDeHoy.size(), 0);
    }

    private boolean esMismaFecha(Date fecha1, Date fecha2) {
        // Comparar solo año, mes y día
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(fecha1).equals(sdf.format(fecha2));
    }

}
