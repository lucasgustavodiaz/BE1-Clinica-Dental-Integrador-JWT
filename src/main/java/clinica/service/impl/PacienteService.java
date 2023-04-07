package clinica.service.impl;

import clinica.entities.Paciente;
import clinica.exceptions.BadRequestException;
import clinica.exceptions.ResourceNotFoundException;
import clinica.model.PacienteDTO;
import clinica.repository.PacienteRepository;
import clinica.service.CRUDService;
import clinica.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements CRUDService<PacienteDTO> {
  private final PacienteRepository pacienteRepository;
  private final Mapper mapper;
  
  @Autowired
  public PacienteService(PacienteRepository pacienteRepository, Mapper mapper) {
    this.pacienteRepository = pacienteRepository;
    this.mapper = mapper;
  }
  
  @Override
  public PacienteDTO guardar(PacienteDTO pacienteDTO) throws BadRequestException, ResourceNotFoundException, JsonProcessingException {
    if (pacienteDTO == null) {
      throw new BadRequestException("El paciente no puede ser nulo");
    }
    Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
    return mapper.map(pacienteRepository.save(paciente), PacienteDTO.class);
  }
  
  @Override
  public PacienteDTO buscar(Integer id) throws BadRequestException, ResourceNotFoundException, JsonProcessingException {
    if (id == null || id < 1) {
      throw new BadRequestException("El id no puede ser nulo o negativo");
    }
    Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el paciente con id " + id));
    return mapper.map(paciente, PacienteDTO.class);
  }
  
  @Override
  public PacienteDTO actualizar(PacienteDTO pacienteDTO) throws BadRequestException, ResourceNotFoundException, JsonProcessingException {
    PacienteDTO pacienteActualizado;
    if (pacienteDTO == null) {
      throw new BadRequestException("No se pudo actualizar el paciente null");
    }
    if (pacienteDTO.getId() == null || pacienteDTO.getId() < 1) {
      throw new BadRequestException("El id no puede ser nulo o negativo");
    }
    Optional<Paciente> pacienteEnBD = pacienteRepository.findById(pacienteDTO.getId());
    if (pacienteEnBD.isPresent()) {
      Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
      pacienteActualizado = mapper.map(pacienteRepository.save(paciente), PacienteDTO.class);
    } else {
      throw new ResourceNotFoundException("No se encontró el paciente con id " + pacienteDTO.getId());
    }
    return pacienteActualizado;
  }
  
  @Override
  public void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException {
    if (id == null || id < 1) {
      throw new BadRequestException("El id no puede ser nulo o negativo");
    }
    if (pacienteRepository.existsById(id)) {
        pacienteRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("No se encontró el paciente con id " + id);
    }
  }
  
  @Override
  public List<PacienteDTO> buscarTodos() throws JsonProcessingException {
    List<Paciente> pacientes = pacienteRepository.findAll();
    return mapper.mapList(pacientes, PacienteDTO.class);
  }
}
