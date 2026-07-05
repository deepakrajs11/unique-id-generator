package com.deepakraj.unique_id_generator.controller;

import com.deepakraj.unique_id_generator.dto.IdResponse;
import com.deepakraj.unique_id_generator.service.UniqueIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ids")
public class IdController {

    private final UniqueIdService uniqueIdService;

    public IdController(UniqueIdService uniqueIdService) {
        this.uniqueIdService = uniqueIdService;
    }

    @GetMapping
    public ResponseEntity<IdResponse> generateId() {

        long id = uniqueIdService.generateId();

        return ResponseEntity.ok(new IdResponse(id));
    }
}
