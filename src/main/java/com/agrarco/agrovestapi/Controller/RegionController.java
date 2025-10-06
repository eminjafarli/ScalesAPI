package com.agrarco.agrovestapi.Controller;

import com.agrarco.agrovestapi.AgrovestEntity.Reference89256;
import com.agrarco.agrovestapi.AgrovestRepository.Reference89256Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/region")
public class RegionController {

    private final Reference89256Repository reference89256Repository;

    @GetMapping("/all")
    public List<Reference89256> getAll() {
        return reference89256Repository.findAll()
                .stream()
                .peek(r -> {
                    if (r.getRegion() != null) {
                        r.setRegion(r.getRegion().trim());
                    }
                })
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Reference89256> add(@RequestBody Reference89256 reference) {
        if (reference.getRegion() == null || reference.getRegion().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Reference89256 saved = reference89256Repository.save(reference);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        if (!reference89256Repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reference89256Repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
