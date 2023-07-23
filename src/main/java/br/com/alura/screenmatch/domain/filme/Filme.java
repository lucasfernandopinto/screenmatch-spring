package br.com.alura.screenmatch.domain.filme;

import jakarta.persistence.*;

// criação da classe 'filmes' de acordo com os campos no BD
@Entity
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracaoEmMinutos;
    private Integer anoLancamento;
    private String genero;

    // atribuição de dados aos atributos da classe Filme
    // estes dados são puxados do record DadosCadastroFilme.java (src > main > java > screenmatch > domain > filme)
    public Filme(DadosCadastroFilme dados){
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
        this.genero = dados.genero();
    }
    //construtor padrão - exigido pelo JPA
    public Filme(){};

    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", duracaoEmMinutos=" + duracaoEmMinutos +
                ", anoLancamento=" + anoLancamento +
                ", genero=" + genero +
                '}';
    }

    // para gerar os construtores, getters, setters..., basta utilizar ALT + INSERT
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void atualizaDados(DadosAlteracaoFilme dados) {
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
        this.genero = dados.genero();
    }
}
