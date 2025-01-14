package com.mycompany.pizzeriaapp.service.workers;

import static com.mycompany.pizzeriaapp.Util.CourierMapper.mapToCourierDto;
import static com.mycompany.pizzeriaapp.Util.CourierMapper.mapToCourierEntity;

import com.mycompany.pizzeriaapp.Util.CourierMapper;
import com.mycompany.pizzeriaapp.dto.CourierDto;
import com.mycompany.pizzeriaapp.entity.CourierEntity;
import com.mycompany.pizzeriaapp.exception.NotFoundException;
import com.mycompany.pizzeriaapp.repository.CourierRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourierService {

  private final CourierRepository courierRepository;

  public CourierService(CourierRepository courierRepository) {
    this.courierRepository = courierRepository;
  }


  public CourierDto addCourier(CourierDto courierDto) {
    CourierEntity savedCourier = courierRepository.save(mapToCourierEntity(courierDto));
    return mapToCourierDto(savedCourier);
  }

  public CourierDto getCourierById(Long id) {
      CourierEntity courierEntity = courierRepository.findById(id).
          orElseThrow(() -> new NotFoundException(String.format(
              "Курьера с таким id - %s не существует", id)));

          return mapToCourierDto(courierEntity);
  }

  public CourierDto getCourierByNameAndSurname(String name, String surname) {
    CourierEntity courierEntity = courierRepository.getCourierEntitiesByNameAndSurname(name, surname)
        .orElseThrow(() -> new NotFoundException(String.format(
            "Курьера с таким именем - %s и фамилией - %s не существует", name, surname
        )));
    return mapToCourierDto(courierEntity);
  }

  public List<CourierDto> getCouriers() {
    return courierRepository.findAll().stream()
        .map(CourierMapper::mapToCourierDto)
        .collect(Collectors.toList());
  }

  public ResponseEntity<String> deleteCourier(Long id) {
    if(courierRepository.existsById(id)) {
      courierRepository.deleteById(id);
      return new ResponseEntity<>(String.format("Курьер с id - %s удалён",id), HttpStatus.OK);
    } else
      throw new NotFoundException("Удаляемого курьера с id - %s не существует");
  }
}
