package domain.colaboraciones;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.CamposArchivo;
import domain.objetos.Heladera;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="distribucion_vianda")
public class DistribucionVianda {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_heldera_origen")
    private Heladera origen;
    @ManyToOne
    @JoinColumn(name = "id_heldera_destino")
    private Heladera destino;
    @Column(name = "cantidad_viandas",columnDefinition ="INTEGER" )
    private int cantidadViandas;
    @Column(name = "motivo",columnDefinition = "TEXT")
    private  String motivo;

    @Column(name = "fecha_distribucion",columnDefinition = "DATETIME")
    private Date fechaDistribucion;

    public DistribucionVianda(CamposArchivo datos){
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.fechaDistribucion=formato.parse(datos.getFechaColaboracion());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.cantidadViandas=1;
    }


}
