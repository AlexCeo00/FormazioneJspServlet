package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ruoli")
public class Ruoli implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dettagli")
    private String dettagli;//usa sempre integer non int

    @Column(name = "stato")
    private String stato;

    @Column(name = "dele")
    private String dele;

    public Ruoli() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDettagli() {
        return dettagli;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDele() {
        return dele;
    }

    public void setDele(String dele) {
        this.dele = dele;
    }

    public Ruoli(String dettagli, String stato) {
        this.dettagli = dettagli;
        this.stato = stato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruoli ruoli = (Ruoli) o;
        return id == ruoli.id &&
                Objects.equals(dettagli, ruoli.dettagli) &&
                Objects.equals(stato, ruoli.stato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dettagli, stato);
    }
}
