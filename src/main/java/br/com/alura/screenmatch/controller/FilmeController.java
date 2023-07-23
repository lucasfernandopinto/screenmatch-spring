package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.DadosAlteracaoFilme;
import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.filme.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// criação do controlador que vai manipular todos os métodos da classe Filme
@Controller
// página padrão
@RequestMapping("/filmes")
public class FilmeController {

    // injeção de dependência
    @Autowired
    private FilmeRepository repository;

    // método carregaPaginaFormulario com a requisição GET para retornar a página de formulário
    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    // método carregaPaginaListagem com a requisição GET para retornar a página de listagem de filmes
    // o Model é uma interface do Spring que carrega vários métodos que podem ser utilizados no código
    @GetMapping
    public String carregaPaginaListagem(Model model){
        // addAtribute recebe 2 parâmetros
        // o primeiro, uma string com o nome do atributo, que deve ser o mesmo utilizado na página HTML (listagem.html)
        // o segundo, um objeto. Neste caso, o objeto FilmeRepository, que está definido no domain, que herda a interface JpaRepository,
        // carregando assim, vários métodos a serem utilizados no código( findAll(), save(), delete() )
        model.addAttribute("listaFilmes", repository.findAll());
        return "filmes/listagem";
    }

    // método cadastraFilme com a requisição POST para atribuir ao repositório (BD) os dados recebidos pelo usuário
    @PostMapping
    @Transactional
    public String cadastraFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        repository.save(filme);

        return "redirect:/filmes";
    }

    // método alteraFilme com a requisição PUT que recebe um novo record(DadosAlteracaoFilme), cria uma variável e substitui
    // os dados do repositório pelos dados do record
    @PutMapping
    @Transactional
    public String alteraFilme(DadosAlteracaoFilme dados){
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

    // método removeFillme com a requisição DELETE para excluir um filme do repositório (BD)
    @DeleteMapping
    @Transactional
    public String removeFilme(Long id){
        repository.deleteById(id);

        return"redirect:/filmes";
    };
}
