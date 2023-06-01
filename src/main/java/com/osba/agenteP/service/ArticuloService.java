package com.osba.agenteP.service;

import com.osba.agenteP.domain.Articulo;
import com.osba.agenteP.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;
    public List<Articulo> getArticulos(){return articuloRepository.findAll();}
}
