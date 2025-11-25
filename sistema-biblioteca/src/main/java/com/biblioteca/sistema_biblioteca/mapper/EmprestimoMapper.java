package com.biblioteca.sistema_biblioteca.mapper;

import com.biblioteca.sistema_biblioteca.dto.response.EmprestimoResponse;
import com.biblioteca.sistema_biblioteca.entity.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {
    
    public EmprestimoResponse toResponse(Emprestimo emprestimo) {
        String statusStr = null;
        try {
            java.lang.reflect.Method m = emprestimo.getClass().getMethod("getStatus");
            Object statusObj = m.invoke(emprestimo);
            statusStr = statusObj != null ? statusObj.toString() : null;
        } catch (Exception e) {
            // If reflection fails, leave statusStr as null
        }

        return new EmprestimoResponse(
            emprestimo.getId(),
            emprestimo.getUsuario().getNome(),
            emprestimo.getLivro().getTitulo(),
            emprestimo.getDataEmprestimo(),
            emprestimo.getDataDevolucaoPrevista(),
            emprestimo.getDataDevolucaoReal(),
            statusStr,
            emprestimo.getValorMulta()
        );
    }
}