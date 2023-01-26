/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios.Imple;

import com.alkemy.alkemyProject.DTO.FiltrosIconoDTO;
import com.alkemy.alkemyProject.DTO.IconoBasicoDTO;
import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.Mapper.IconoMapper;
import com.alkemy.alkemyProject.Repository.Especificaciones.IconoEspecificacion;
import com.alkemy.alkemyProject.Repository.IconosRepository;
import com.alkemy.alkemyProject.Repository.PaisesRepository;
import com.alkemy.alkemyProject.Servicios.IconosService;
import com.alkemy.alkemyProject.Servicios.PaisesService;
import com.alkemy.alkemyProject.entidades.Iconos;
import com.alkemy.alkemyProject.entidades.Paises;
import com.alkemy.alkemyProject.excepciones.ParametroNoEncontrado;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabi
 */
@Service
public class IconosServiceImplementacion implements IconosService {

    @Autowired
    private IconosRepository ir;
    
    @Autowired
    private IconoMapper im;
    
    @Autowired
    private PaisesRepository pr;
    
    @Autowired
    private PaisesService ps;

  private IconoEspecificacion isp;
    
    @Override
    public IconoDTO save(IconoDTO dto,boolean cargarPaises) {
        List<Paises>paises= ps.getAllById(dto.getPaises());
        Iconos entidad = im.DtoAEntidad(dto);
        Iconos entidadGuardada = ir.save(entidad);//esto me devuelve la entidad guardada
        IconoDTO result = im.EntidadADto(entidadGuardada, cargarPaises);//la convierto en DTO

        return result;//lo devuelvo como dto
    }

    
    public IconoDTO modificarIcono(Long id,IconoDTO dto,boolean cargarPaises) {

        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo
        Optional<Iconos> respuesta = ir.findById(id);

        Iconos icon = respuesta.get();

        icon.setAltura(dto.getAltura());
        icon.setFechaCreacion(dto.getFechaCreacion());
        icon.setNombre(dto.getNombre());
        icon.setImagen(dto.getImagen());
        icon.setHistoria(dto.getHistoria());

        Iconos iconoGuardado=ir.save(icon);

        IconoDTO result = im.EntidadADto(iconoGuardado,cargarPaises);

        return result;

    }

    
    @Override
    public List<IconoDTO> getAllIconos(boolean cargarPaises) {//me trae todos los iconos,sin los paises.
        
        return im.EntidadListADtoList(ir.findAll(),cargarPaises);
    }

    public IconoDTO getDetailsById(Long id) {//Para traer un solo icono
        Optional<Iconos> icono = this.ir.findById(id);
if(!icono.isPresent()){
    throw new ParametroNoEncontrado("id del icono no valido");
}
        IconoDTO dto = this.im.EntidadADto(icono.get(), true);
        return dto;

    }

    @Override
    public List<IconoDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        FiltrosIconoDTO filtrosDTO= new FiltrosIconoDTO(name, date, cities, order);
        List<Iconos> entidades = this.ir.findAll(this.isp.getByFilters(filtrosDTO));
        List<IconoDTO> dtos = this.im.EntidadListADtoList(entidades, true);
        return dtos;
    }

    @Override
    public void delete(Long id) {
        this.ir.deleteById(id);
    }

   

    
    public IconoDTO addPais(Long id,List<Long>Idpais, boolean cargarPaises ) {//el id es del icono al que le quiero agregar el pais
        Optional<Iconos> respuesta = ir.findById(id);
                
        Iconos icon = respuesta.get();
        
        List<Paises> PaisesRequeridos = pr.findAllById(Idpais);
        List<Paises> PaisesDeIconos = icon.getPaises();
        List<Paises> concatLists = Stream
                .concat(PaisesDeIconos.stream(), PaisesRequeridos.stream())
                .collect(Collectors.toList());

        icon.setPaises(concatLists);
        ir.save(icon);
        return im.EntidadADto(icon, cargarPaises);
    }
        

}
