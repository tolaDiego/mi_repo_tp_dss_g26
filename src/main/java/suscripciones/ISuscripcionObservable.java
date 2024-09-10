package suscripciones;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_suscripcion")
public abstract class ISuscripcionObservable {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    @JoinColumn(name = "id_suscipcion_obervable")
    protected List<PersonaObserver> suscriptores = new ArrayList<>();

    public abstract void notificar(Heladera heladera);

    public void agregarSuscriptor(PersonaObserver personaObserver) {
        suscriptores.add(personaObserver);
    }

    public void eliminarSuscriptor(PersonaObserver personaObserver) {
        suscriptores.remove(personaObserver);
    }
}
