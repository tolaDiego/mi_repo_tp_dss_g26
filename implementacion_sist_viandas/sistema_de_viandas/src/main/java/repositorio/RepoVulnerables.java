package repositorio;

import domain.personas.Vulnerable;

import java.util.ArrayList;
import java.util.List;

public class RepoVulnerables {

        private final List<Vulnerable> vulnerables = new ArrayList<>();

        public boolean agregarVulnerable(Vulnerable vul) {

            return vulnerables.add(vul);
        }

        public List<Vulnerable> retornarVulnerable() {
            return vulnerables;
        }

        public Vulnerable retornarVulnerablePor(Integer id) {
            return vulnerables.stream()
                    .filter(v -> v.getId()== id.intValue())
                    .findFirst()
                    .orElse(null);
        }
    public boolean eliminarVulnerablesPor(Integer id) {
        return    vulnerables.removeIf(humano->humano.getId()==id.intValue());
    }
}
