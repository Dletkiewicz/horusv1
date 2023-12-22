package pl.dariusz.task.material;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompositeBlockMaterial extends BlockMaterial implements CompositeBlock {

  private List<Block> blocks;

  public CompositeBlockMaterial(String color, String material) {
    super(color, material);
  }

  @Override
  public List<Block> getBlocks() {
    return blocks;
  }
}
