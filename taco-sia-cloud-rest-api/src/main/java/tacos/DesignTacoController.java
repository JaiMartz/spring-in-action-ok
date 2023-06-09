package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;

	private TacoRepository designRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		//model.addAttribute("tktn", new Taco());
		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream() // itero el List
				.filter(x -> x.getType().equals(type)) // filtrando los elementos cuyo tipo sea igual a type
				.collect(Collectors.toList()); // y los recojo en otro List
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "tktn")
	public Taco taco() {
		return new Taco();
	}

	// Los meto en el modelo para todas las request
	@ModelAttribute
	public void populateModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();

		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@PostMapping
	public String processDesign(Model model, @Valid @ModelAttribute(name = "tktn") Taco design, Errors errors,
			@ModelAttribute Order order) {

		if (errors.hasErrors()) {
			return "design";
		}
		design.setCreatedAt(new Date());
		Taco saved = designRepo.save(design);
		order.addDesign(saved);

		return "redirect:/orders/current";
	}

}

/*
 * Reemplazado por la version JDBC
 * 
 * @GetMapping public String showDesignForm(Model model) { List<Ingredient>
 * ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla",
 * Type.WRAP), new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new
 * Ingredient("GRBF", "Ground Beef", Type.PROTEIN), new Ingredient("CARN",
 * "Carnitas", Type.PROTEIN), new Ingredient("TMTO", "Diced Tomatoes",
 * Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES), new
 * Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK",
 * "Monterrey Jack", Type.CHEESE), new Ingredient("SLSA", "Salsa", Type.SAUCE),
 * new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
 * 
 * Type[] types = Ingredient.Type.values(); for (Type type : types) {
 * model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,
 * type)); }
 * 
 * model.addAttribute("design", new Taco()); return "design"; }
 */

/*
 * private RedirectAttributesModelMap getObjetToCallAgain() { List<Ingredient>
 * ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla",
 * Type.WRAP), new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new
 * Ingredient("GRBF", "Ground Beef", Type.PROTEIN), new Ingredient("CARN",
 * "Carnitas", Type.PROTEIN), new Ingredient("TMTO", "Diced Tomatoes",
 * Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES), new
 * Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK",
 * "Monterrey Jack", Type.CHEESE), new Ingredient("SLSA", "Salsa", Type.SAUCE),
 * new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
 * 
 * Type[] types = Ingredient.Type.values();
 * 
 * RedirectAttributesModelMap model = new RedirectAttributesModelMap(); for
 * (Type type : types) { model.addAttribute(type.toString().toLowerCase(),
 * filterByType(ingredients, type)); }
 * 
 * model.addAttribute("design", new Taco());
 * 
 * return model; }
 */

/*
 * @Autowired public DesignTacoController(IngredientRepository ingredientRepo) {
 * this.ingredientRepo = ingredientRepo; }
 */

/*
 * @PostMapping public String processDesign(@Valid Taco design, Errors errors) {
 * if (errors.hasErrors()) { //return "redirect:/design"; return "design"; } //
 * Save the taco design... // We'll do this in chapter 3
 * log.info("Processing design: " + design);
 * 
 * return "redirect:/orders/current"; }
 */