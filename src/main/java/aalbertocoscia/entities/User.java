package aalbertocoscia.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private UUID idUser;
    private String nome;
    private String cognome;
    @Column(name = "data_di_nascita")
    private LocalDate dataNascita;

    @OneToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    @Column(unique = true)
    private String email;
    private String password;

    public User() {
    }

    public User(String nome, String cognome, String dataNascita, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = LocalDate.parse(dataNascita);
        this.email = email;
        this.password = password;
    }

    public Tessera creaTessera() {
        return new Tessera(this, LocalDate.now().toString());
    }

    public boolean isAbbonamentoValido() {
        LocalDate scadenzaAbbonamento = this.getTessera().getAbbonamento().getData_scadenza();
        return scadenzaAbbonamento.isAfter(LocalDate.now());
    }

    public UUID getIdUser() {
        return idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", tessera=" + tessera +
                '}';
    }
}
