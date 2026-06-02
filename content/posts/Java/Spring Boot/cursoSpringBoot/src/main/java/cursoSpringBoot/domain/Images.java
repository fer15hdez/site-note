package cursoSpringBoot.domain;


import jakarta.persistence.*;

@Entity
public class Images {
    @Id
    @GeneratedValue
    private Integer id;
    private byte[] contenido;
    private String tipoMime;

    @OneToOne
    @JoinColumn(
            name = "product_id"
    )
    private Product product;

    public Images() {
    }

    public Images(byte[] contenido, String tipoMime) {
        this.contenido = contenido;
        this.tipoMime = tipoMime;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public void setTipoMime(String tipoMime) {
        this.tipoMime = tipoMime;
    }
}