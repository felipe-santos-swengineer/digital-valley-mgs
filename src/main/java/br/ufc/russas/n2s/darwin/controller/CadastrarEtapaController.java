/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Lavínia Matoso
 */
@Controller("cadastrarEtapaController")
@RequestMapping("/cadastrarEtapa")
public class CadastrarEtapaController {

    private EtapaServiceIfc etapaServiceIfc;

    public EtapaServiceIfc getEtapaServiceIfc() {
        return etapaServiceIfc;
    }

    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc")EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }

    @RequestMapping(method = RequestMethod.GET)
<<<<<<< HEAD
    public String getIndex() {
=======
    public String getIndex(){             
>>>>>>> 31b7e0e0346529d3b7731f8051140c5554cc5abd
        return "cadastrar-etapa";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String adiciona(@Valid EtapaBeans etapa, BindingResult result, Model model) {
        List<EtapaBeans> etapas = Collections.synchronizedList(new ArrayList<EtapaBeans>());
        if (!result.hasErrors()) {
            etapas.add(this.getEtapaServiceIfc().adicionaEtapa(etapa));
            model.addAttribute("etapas", etapas);
        }
        return "cadastrar-etapa";
    }

}