package com.biblioteca.sistema_biblioteca.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "data_emprestimo")
    private LocalDateTime dataEmprestimo;

    @Column(name = "data_devolucao_prevista")
    private LocalDateTime dataDevolucaoPrevista;

    @Column(name = "data_devolucao_real")
    private LocalDateTime dataDevolucaoReal;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @Column(name = "valor_multa")
    private Double valorMulta = 0.0;

    // Construtores
    public Emprestimo() {
        this.dataEmprestimo = LocalDateTime.now();
        this.dataDevolucaoPrevista = LocalDateTime.now().plusDays(14); // 2 semanas
        this.status = StatusEmprestimo.ATIVO;
    }

    public Emprestimo(Usuario usuario, Livro livro) {
        this();
        this.usuario = usuario;
        this.livro = livro;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public LocalDateTime getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDateTime dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDateTime getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public void setDataDevolucaoPrevista(LocalDateTime dataDevolucaoPrevista) { this.dataDevolucaoPrevista = dataDevolucaoPrevista; }

    public LocalDateTime getDataDevolucaoReal() { return dataDevolucaoReal; }
    public void setDataDevolucaoReal(LocalDateTime dataDevolucaoReal) { this.dataDevolucaoReal = dataDevolucaoReal; }

    public StatusEmprestimo getStatus() { return status; }
    public void setStatus(StatusEmprestimo status) { this.status = status; }

    public Double getValorMulta() { return valorMulta; }
    public void setValorMulta(Double valorMulta) { this.valorMulta = valorMulta; }
}
