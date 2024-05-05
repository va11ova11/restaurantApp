package com.mycompany.pizzeriaapp.controller;

import com.mycompany.pizzeriaapp.dto.WorkerDto;
import com.mycompany.pizzeriaapp.service.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workerS")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }
    @PostMapping
    public WorkerDto addWorker(@RequestBody WorkerDto workerDto) {
        return workerService.addWorker(workerDto);
    }

    @GetMapping
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public WorkerDto getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteWorkerById(@PathVariable Long id) {
        return workerService.deleteWorkerById(id);
    }


    @GetMapping("/search")
    public WorkerDto getWorkerByNameAndSurname(@RequestParam String name,
                                               @RequestParam String surname) {
       return workerService.getWorkerByNameAndSurname(name, surname);
    }
}