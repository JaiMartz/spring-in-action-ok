package tacos;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.AUTO)
	 */
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  //@Column(name="CREATEDAT")
  private Date createdAt;

  @Column(name="INGREDIENTS")
  @ManyToMany(targetEntity=Ingredient.class)
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}




/** JDBC OK
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taco {
	
	private Long id;
	private Date createdAt;
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@NotEmpty(message = "You must choose at least 1 ingredient.")
	private List<Ingredient> ingredients;
	
} */
