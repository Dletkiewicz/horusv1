package pl.dariusz.task.material;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BlockMaterial implements Block {

  private String color;
  private String material;

  @Override
  public String getColor() {
    return color;
  }

  @Override
  public String getMaterial() {
    return material;
  }
}
