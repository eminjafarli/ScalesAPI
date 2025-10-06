package com.agrarco.agrovestapi.Controller;

import com.agrarco.agrovestapi.AgrovestEntity.Reference921;
import com.agrarco.agrovestapi.AgrovestEntity.Reference921;
import com.agrarco.agrovestapi.AgrovestRepository.Reference921Repository;
import com.agrarco.agrovestapi.AgrovestRepository.Reference921Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/anbar")
public class AnbarController {

    private final Reference921Repository reference921Repository;

    @GetMapping("/all")
    public List<Reference921> getAll() {
        return reference921Repository.findAll()
                .stream()
                .peek(r -> {
                    if (r.getAnbar() != null) {
                        r.setAnbar(r.getAnbar().trim());
                    }
                })
                .toList();
    }
    @PostMapping("/add")
    public ResponseEntity<Reference921> add(@RequestBody Reference921 reference) {
        if (reference.getAnbar() == null || reference.getAnbar().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Reference921 saved = reference921Repository.save(reference);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        if (!reference921Repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reference921Repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
