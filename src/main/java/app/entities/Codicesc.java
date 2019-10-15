package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "codices")
public class Codicesc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cifra", nullable = false)
    private int cifra;//usa sempre integer non int

    @OneToMany(mappedBy = "codicesc") // un codice sconto può essere utilizato per più prenotazioni
    private List<Pren> codicesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCifra() {
        return cifra;
    }

    public void setCifra(int cifra) {
        this.cifra = cifra;
    }

    public List<Pren> getCodicesc() {
        return codicesc;
    }

    public void setCodicesc(List<Pren> codicesc) {
        this.codicesc = codicesc;
    }

    public Codicesc(int cifra, List<Pren> codicesc) {
        this.cifra = cifra;
        this.codicesc = codicesc;
    }

    public Codicesc() {
    }

    public Codicesc(int cifra) {
        this.cifra = cifra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Codicesc codicesc1 = (Codicesc) o;
        return id == codicesc1.id &&
                cifra == codicesc1.cifra &&
                Objects.equals(codicesc, codicesc1.codicesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cifra, codicesc);
    }
}
