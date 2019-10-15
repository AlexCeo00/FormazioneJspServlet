package app.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "utente")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "passut", nullable = false)
    private String passut;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "datan")
    private Date datan;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) //1 utente puo avere + prenotazioni
    private List <Pren> pren;

    public List<Pren> getPren() {
        return pren;
    }

    public void setPren(List<Pren> pren) {
        this.pren = pren;
    }

    public void setUser(User user) {
        User nuser=new User();
        nuser = user;
    }

    public User(String nome, String email, String passut, String tipologia, Date datan) {
        super();
        this.nome = nome;
        this.email = email;
        this.passut = passut;
        this.tipologia = tipologia;
        this.datan = datan;
    }
    public User(int id, String nome, String email, String passut, String tipologia, Date datan) {
        super();
        this.id=id;
        this.nome = nome;
        this.email = email;
        this.passut = passut;
        this.tipologia = tipologia;
        this.datan = datan;
    }

    public User() {
    }

    public User(String nome, String email, String passut) {
        super();
        this.nome = nome;
        this.email = email;
        this.passut = passut;
    }
    public User(int id, String nome, String email, String passut) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.passut = passut;
    }

    public User(int id, String nome, String email, String passut, String tipologia) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.passut = passut;
        this.tipologia = tipologia;
    }

    public User(String nome, String email, String passut, String tipologia) {
        this.nome = nome;
        this.email = email;
        this.passut = passut;
        this.tipologia = tipologia;
    }

    public User(String nome, String email, String passut, String tipologia, Date datan, List<Pren> pren) {
        this.nome = nome;
        this.email = email;
        this.passut = passut;
        this.tipologia = tipologia;
        this.datan = datan;
        this.pren = pren;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassut() {
        return passut;
    }

    public void setPassut(String passut) {
        this.passut = passut;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Date getDatan() {
        return datan;
    }

    public void setDatan(Date datan) {
        this.datan = datan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                nome.equals(user.nome) &&
                email.equals(user.email) &&
                passut.equals(user.passut) &&
                Objects.equals(tipologia, user.tipologia) &&
                Objects.equals(datan, user.datan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, passut, tipologia, datan);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", passut='" + passut + '\'' +
                ", tipologia='" + tipologia + '\'' +
                ", datan=" + datan +
                '}';
    }
}