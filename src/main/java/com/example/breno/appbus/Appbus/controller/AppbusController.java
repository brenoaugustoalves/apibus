package com.example.breno.appbus.Appbus.controller;

import com.example.breno.appbus.Appbus.dto.AppbusRecords;
import com.example.breno.appbus.Appbus.model.AppbusModel;
import com.example.breno.appbus.Appbus.repository.AppbusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping()
public class AppbusController {

    @Autowired
    AppbusRepository appbusRepository;

    @PostMapping("/cards")
    public ResponseEntity<AppbusModel> saveProduct(@RequestBody @Validated AppbusRecords appbusRecords) {
        var appbusModel = new AppbusModel();
        BeanUtils.copyProperties(appbusRecords, appbusModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(appbusRepository.save(appbusModel));
    }

    @GetMapping("/cards")
    public ResponseEntity<List<AppbusModel>> getAllCards() {
        return ResponseEntity.status(HttpStatus.OK).body(appbusRepository.findAll());
    }

    @GetMapping("/cards{id}")
    public ResponseEntity<Object> getOneCard(@PathVariable (value="id") UUID id){
        Optional<AppbusModel> appbusModel = appbusRepository.findById(id);
        return appbusModel.<ResponseEntity<Object>>map(model -> ResponseEntity.status(HttpStatus.OK).body(model)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found"));
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable(value="id") UUID id) {
        Optional<AppbusModel> appbusModel = appbusRepository.findById(id);
        if(appbusModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
        }
        appbusRepository.delete(appbusModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully.");
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<Object> updateCard(@PathVariable(value="id") UUID id,
                                                @RequestBody @Validated AppbusRecords appbusRecords) {
        Optional<AppbusModel> appbusModel = appbusRepository.findById(id);
        if(appbusModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
        }
        var AppbusModel = appbusModel.get();
        BeanUtils.copyProperties(appbusRecords, appbusModel);
        return ResponseEntity.status(HttpStatus.OK).body(appbusRepository.save(AppbusModel));
    }
}
