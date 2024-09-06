package domain.colaboraciones;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.CamposArchivo;
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
@Table(name = "donacion_dinero")
public class DonacionDinero{
    @Id
    @GeneratedValue
    private long id;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    @Column(name = "fecha_donacion",columnDefinition = "DATETIME")
    public Date fechaDonacion;
    @Column(name = "monto", precision = 5, scale = 2)
    public double monto;
    @Column(name = "frecuencia",columnDefinition = "VARCHAR(30)")
    public  String frecuencia;//por dia,mensual,anual, quincenal,etc

    public DonacionDinero(CamposArchivo campos){
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.fechaDonacion=formato.parse(campos.getFechaColaboracion()) ;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


}
