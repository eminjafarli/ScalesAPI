package com.agrarco.agrovestapi.Controller;

import com.agrarco.agrovestapi.AgrovestEntity.Reference734;
import com.agrarco.agrovestapi.AgrovestRepository.Reference734Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tedarukcu")
public class TedarukcuController {

    private final Reference734Repository reference734Repository;

    @GetMapping("/all")
    public List<Reference734> getAll() {
        return reference734Repository.findAll()
                .stream()
                .peek(r -> {
                    if (r.getTedarukcu() != null) {
                        r.setTedarukcu(r.getTedarukcu().trim());
                    }
                })
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Reference734> add(@RequestBody Reference734 reference) {
        if (reference.getTedarukcu() == null || reference.getTedarukcu().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Reference734 saved = reference734Repository.save(reference);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        if (!reference734Repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reference734Repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
