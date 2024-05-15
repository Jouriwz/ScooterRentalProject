package ewa.server.controller;

import ewa.server.model.Pokemon;
import ewa.server.service.PokemonService;
import ewa.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/pokemon/")
public class PokemonController {
    private final PokemonService pokemonService;
    private final UserService userService;
    private final ServletContext servletContext;

    @Autowired
    public PokemonController(PokemonService pokemonService, UserService userService, ServletContext servletContext) {
        this.pokemonService = pokemonService;
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAllPokemons(){
        return ResponseEntity.ok().body(pokemonService.findAllPokemons());
    }

    @GetMapping("starters")
    public ResponseEntity<List<Pokemon>> getStarterPokemons(){
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemonService.findByName("Bulbasaur"));
        pokemons.add(pokemonService.findByName("Charmander"));
        pokemons.add(pokemonService.findByName("Squirtle"));

        return ResponseEntity.ok().body(pokemons);
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable String id) throws IOException {
        var imgFile = new ClassPathResource("images/pokemons/"+id+".gif");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
}
