/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.time.LocalDate;

/**
 *
 * @author Wallison Carlos
 */
public enum EnumEstadoSelecao implements EstadoSelecao{
    
    ABERTA(1){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            LocalDate inicio = selecao.getInscricao().getPeriodo().getInicio();
            LocalDate termino = selecao.getInscricao().getPeriodo().getTermino();
            if ((inicio.isBefore(LocalDate.now()) || inicio.equals(LocalDate.now())) && termino.isAfter(LocalDate.now())) {
                return this;
            } else {
                return ANDAMENTO.execute(selecao);
            }
        }
    },
    ANDAMENTO(2){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            LocalDate termino = selecao.getInscricao().getPeriodo().getTermino();
            if (termino.isBefore(LocalDate.now()) && (selecao.getUltimaEtapa().getPeriodo().getTermino().isAfter(LocalDate.now()) || selecao.getUltimaEtapa().getPeriodo().getTermino().equals(LocalDate.now()))) {
                return this;
            } else {
                return FINALIZADA.execute(selecao);
            }
        }
    },
    FINALIZADA(3){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            if(selecao.getUltimaEtapa().getPeriodo().getTermino().isBefore(LocalDate.now())){
                return this;
            }else{
                return ANDAMENTO.execute(selecao);
            }
        }
    };
    
    
    
    EnumEstadoSelecao() {
        
    }
    
    EnumEstadoSelecao(int estado) {
        setEstado(estado);
    }

    private int estado;
    
    public int getEstado() {
        return estado;
    }

    
    public void setEstado(int estado) {
        if (estado >= 1 && estado <= 3) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Critério de avaliação deve ser maior igual a um e menor igual a três!");
        }
    }
    
}