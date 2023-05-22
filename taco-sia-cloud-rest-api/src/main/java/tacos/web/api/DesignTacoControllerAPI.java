package tacos.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/rest", produces="application/json") 
@CrossOrigin(origins="*")                          
public class DesignTacoControllerAPI {
	
  private TacoRepository tacoRepo;
  
  @Autowired
  private OrderRepository orderRepo;
  
  public DesignTacoControllerAPI(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }
  
  /**

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {            
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepo.findAll(page).getContent();
  }
  
  /*
   * Devuelve 200 OK aunque no haya ningun objeto 
   
  @GetMapping("/{id}")
  public Taco tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return optTaco.get();
    }
    return null;
  } */
  
  /*
   * Devuelve una ResponseEntity<Taco> apropiada */
  /**
   
  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById404(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  } 
  
  
  @PostMapping(path = "/takos", consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }
  
  
  @PutMapping("/{orderId}")
  public Order putOrder(@RequestBody Order order) {
    return orderRepo.save(order);
  }
  
  
  @DeleteMapping("/{orderId}")
  @ResponseStatus(code=HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
    	orderRepo.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {}
  } */
}