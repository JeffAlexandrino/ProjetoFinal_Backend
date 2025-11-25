package com.biblioteca.sistema_biblioteca.service;

import com.biblioteca.sistema_biblioteca.dto.request.EmprestimoRequest;
import com.biblioteca.sistema_biblioteca.dto.response.EmprestimoResponse;
import com.biblioteca.sistema_biblioteca.entity.Emprestimo;
import com.biblioteca.sistema_biblioteca.entity.Livro;
import com.biblioteca.sistema_biblioteca.entity.StatusEmprestimo;
import com.biblioteca.sistema_biblioteca.entity.Usuario;
import com.biblioteca.sistema_biblioteca.mapper.EmprestimoMapper;
import com.biblioteca.sistema_biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private LivroService livroService;
    
    @Autowired
    private EmprestimoMapper emprestimoMapper;
    
    public EmprestimoResponse realizarEmprestimo(EmprestimoRequest request) {
        Usuario usuario = usuarioService.buscarUsuarioEntityPorId(request.getUsuarioId());
        Livro livro = livroService.buscarLivroEntityPorId(request.getLivroId());
        
        // Verificar se o livro está disponível
        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Livro não disponível para empréstimo");
        }
        
        // Verificar se usuário tem empréstimos ativos (limite de 3)
        int emprestimosAtivos = emprestimoRepository.countEmprestimosAtivosPorUsuario(usuario.getId());
        if (emprestimosAtivos >= 3) {
            throw new RuntimeException("Usuário atingiu o limite máximo de empréstimos ativos");
        }
        
        // Criar empréstimo
        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        
        // Atualizar quantidade disponível do livro
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livroService.atualizarQuantidadeDisponivel(livro);
        
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        return emprestimoMapper.toResponse(emprestimoSalvo);
    }
    
    public EmprestimoResponse registrarDevolucao(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        
        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            throw new RuntimeException("Este empréstimo já foi devolvido");
        }
        
        // Registrar devolução
        emprestimo.setDataDevolucaoReal(LocalDateTime.now());
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        
        // Calcular multa se houver atraso
        if (emprestimo.getDataDevolucaoReal().isAfter(emprestimo.getDataDevolucaoPrevista())) {
            double multa = calcularMulta(emprestimo);
            emprestimo.setValorMulta(multa);
        }
        
        // Atualizar quantidade disponível do livro
        Livro livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livroService.atualizarQuantidadeDisponivel(livro);
        
        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimo);
        return emprestimoMapper.toResponse(emprestimoAtualizado);
    }
    
    public List<EmprestimoResponse> listarTodosEmprestimos() {
        return emprestimoRepository.findAll().stream()
            .map(emprestimoMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public List<EmprestimoResponse> listarEmprestimosPorUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId).stream()
            .map(emprestimoMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public List<EmprestimoResponse> listarEmprestimosAtivos() {
        return emprestimoRepository.findByStatus(StatusEmprestimo.ATIVO).stream()
            .map(emprestimoMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public List<EmprestimoResponse> listarEmprestimosAtrasados() {
        return emprestimoRepository.findEmprestimosAtrasados(LocalDateTime.now()).stream()
            .map(emprestimoMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    private double calcularMulta(Emprestimo emprestimo) {
        long diasAtraso = java.time.Duration.between(
            emprestimo.getDataDevolucaoPrevista(), 
            emprestimo.getDataDevolucaoReal()
        ).toDays();
        
        return diasAtraso * 2.0; // R$ 2,00 por dia de atraso
    }
}