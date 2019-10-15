package app.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "veicolo")
public class Auto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "targa", nullable = false)
    private String targa;

    @Column(name = "prezzo", nullable = false)
    private float prezzo;

    @Column(name = "dispon")
    private String dispon;

    @OneToMany(mappedBy = "auto") //1 auto puo avere + prenotazioni
    private List<Pren> preno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String email) {
        this.tipo = tipo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDispon() {
        return dispon;
    }

    public void setDispon(String dispon) {
        this.dispon = dispon;
    }

    public Auto() {
    }
    public Auto(int id, String nome, String tipo, String targa, float prezzo, String dispon) {
        super();
        this.id=id;
        this.nome = nome;
        this.tipo = tipo;
        this.targa = targa;
        this.prezzo = prezzo;
        this.dispon = dispon;
    }

    public Auto(String nome, String tipo, String targa, float prezzo, String dispon) {
        super();
        this.nome = nome;
        this.tipo = tipo;
        this.targa = targa;
        this.prezzo = prezzo;
        this.dispon = dispon;
    }

    public Auto(String nome, String tipo, String targa, float prezzo, String dispon, List<Pren> preno) {
        this.nome = nome;
        this.tipo = tipo;
        this.targa = targa;
        this.prezzo = prezzo;
        this.dispon = dispon;
        this.preno = preno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id &&
                Float.compare(auto.prezzo, prezzo) == 0 &&
                Objects.equals(nome, auto.nome) &&
                Objects.equals(tipo, auto.tipo) &&
                Objects.equals(targa, auto.targa) &&
                Objects.equals(dispon, auto.dispon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipo, targa, prezzo, dispon);
    }
}
