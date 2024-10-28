package com.example.sandav.application.services;

import com.example.sandav.domain.model.Faction;
import com.example.sandav.domain.model.Starship;
import com.example.sandav.domain.port.IStarshipRepository;
import com.example.sandav.infrastructure.dto.ResponseListPageable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StarshipServiceTest {

    @Mock
    private IStarshipRepository iStarshipRepository;

    @InjectMocks
    private StarshipService starshipService;

    private Starship starshipMain;

    @BeforeEach
    public void setUp(){
        starshipMain= Starship.builder()
                .id(1)
                .name("Milenium Falcom")
                .faction(Faction.REBEL)
                .build();
    }

    @Test
    void save() {
        when(iStarshipRepository.save(any(Starship.class))).thenReturn(starshipMain);

        Starship savedStarship = starshipService.save(starshipMain);

        assertNotNull(savedStarship);
        assertEquals(starshipMain.getId(), savedStarship.getId());
    }

    @Test
    void getAllStarshipPageable() {
        Pageable pageable = Pageable.unpaged();
        ResponseListPageable<Starship> response = new ResponseListPageable<>();
        response.setStarshipList(Arrays.asList(starshipMain));

        when(iStarshipRepository.getAllStarshipPageable(pageable)).thenReturn(response);

        ResponseListPageable<Starship> result = starshipService.getAllStarshipPageable(pageable);

        assertNotNull(result);
        assertFalse(result.getStarshipList().isEmpty());
        assertEquals(1, result.getStarshipList().size());
    }

    @Test
    void getAllStarshipByName() {
        when(iStarshipRepository.getAllStarshipByName("Milenium Falcom")).thenReturn(Arrays.asList(starshipMain));

        List<Starship> result = starshipService.getAllStarshipByName("Milenium Falcom");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(starshipMain.getName(), result.get(0).getName());
    }

    @Test
    void findById() {
        when(iStarshipRepository.findById(1)).thenReturn(starshipMain);

        Starship result = starshipService.findById(1);

        assertNotNull(result);
        assertEquals(starshipMain.getId(), result.getId());
    }

    @Test
    void update() {
        var updatedStarship = Starship.builder()
                .id(1)
                .name("Dark Falcon")
                .faction(Faction.EMPIRE)
                .build();

        when(iStarshipRepository.findById(1)).thenReturn(starshipMain);
        when(iStarshipRepository.save(any(Starship.class))).thenReturn(updatedStarship);

        Starship result = starshipService.update(1, updatedStarship);

        assertNotNull(result);
        assertEquals(starshipMain.getId(), result.getId());
        assertEquals(updatedStarship.getFaction(), result.getFaction());
    }

    @Test
    void deleteById() {
        doNothing().when(iStarshipRepository).deleteById(1);

        starshipService.deleteById(1);

        verify(iStarshipRepository, times(1)).deleteById(1);
    }
}