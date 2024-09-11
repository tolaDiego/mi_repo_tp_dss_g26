package domain.personas;

import adapters.notificadores.Notificador;
import domain.accesorios.AreaCobertura;
import domain.accesorios.Contacto;
import domain.accesorios.Documento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico {
    @Id
    @GeneratedValue
    private  long id;
    @Column(name = "nombre",columnDefinition = "VARCHAR(50)")
    private String nombre;
    @Column(name = "apellido",columnDefinition = "VARCHAR(50)")
    private String apellido;
    @Embedded
    private Documento documento;
    @OneToOne
    @JoinColumn(name = "id_contacto")
    private Contacto contacto;
    @Embedded
    private AreaCobertura areaCobertura;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_notificador_tecnico")
    private Notificador notificador;
    public  Tecnico(Notificador notif){
    this.notificador=notif ;
    }
}
