package pzinsta.pizzeria.model.pizza;

import java.util.Objects;

import javax.money.MonetaryAmount;

public class Ingredient {
	private long id;
	private String name;
	private MonetaryAmount price;
	private IngredientType type;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public void setPrice(MonetaryAmount price) {
		this.price = price;
	}

	public IngredientType getType() {
		return type;
	}

	public void setType(IngredientType type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ingredient)) {
			return false;
		}
		Ingredient that = (Ingredient) obj;
		return this.getId() == that.getId();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
