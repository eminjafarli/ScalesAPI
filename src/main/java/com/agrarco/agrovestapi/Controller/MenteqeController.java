package com.agrarco.agrovestapi.Controller;

import com.agrarco.agrovestapi.AgrovestEntity.Reference89255;
import com.agrarco.agrovestapi.AgrovestRepository.Reference89255Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menteqe")
public class MenteqeController {

    private final Reference89255Repository reference89255Repository;

    @GetMapping("/all")
    public List<Reference89255> getAll() {
        return reference89255Repository.findAll()
                .stream()
                .peek(r -> {
                    if (r.getMenteqe() != null) {
                        r.setMenteqe(r.getMenteqe().trim());
                    }
                })
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Reference89255> add(@RequestBody Reference89255 reference) {
        if (reference.getMenteqe() == null || reference.getMenteqe().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Reference89255 saved = reference89255Repository.save(reference);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        if (!reference89255Repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reference89255Repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
