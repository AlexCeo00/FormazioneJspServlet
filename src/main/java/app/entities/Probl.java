package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "problema")
public class Probl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "dataprob")
    private Date dataprob;

    @ManyToOne() //da problema a prenotazione, 1 problema può averne solo una prenotazione
    @JoinColumn(name = "idpren")
    private Pren pren;

    public Probl(String descrizione, Date dataprob, Pren pren) {
        this.descrizione = descrizione;
        this.dataprob = dataprob;
        this.pren = pren;
    }

    public Probl() {

    }

    public Probl(String descrizione, Date dataprob) {
        this.descrizione = descrizione;
        this.dataprob = dataprob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getDataprob() {
        return dataprob;
    }

    public void setDataprob(Date dataprob) {
        this.dataprob = dataprob;
    }

    public Pren getPren() {
        return pren;
    }

    public void setPren(Pren pren) {
        this.pren = pren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Probl probl = (Probl) o;
        return id == probl.id &&
                Objects.equals(descrizione, probl.descrizione) &&
                Objects.equals(dataprob, probl.dataprob) &&
                Objects.equals(pren, probl.pren);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descrizione, dataprob, pren);
    }

    @Override
    public String toString() {
        return "Probl{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", dataprob=" + dataprob +
                ", pren=" + pren +
                '}';
    }
}
