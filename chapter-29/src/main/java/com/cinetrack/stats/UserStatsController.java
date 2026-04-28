package com.cinetrack.stats;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserStatsController {

    private final UserStatsService service;

    UserStatsController(UserStatsService service) {
        this.service = service;
    }

    @GetMapping("/{id}/stats")
    ResponseEntity<UserStatsResponse> stats(@PathVariable Long id) {
        return service.findByUserId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
