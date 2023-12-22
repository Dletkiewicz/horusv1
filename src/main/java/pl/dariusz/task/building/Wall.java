package pl.dariusz.task.building;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dariusz.task.material.Block;
import pl.dariusz.task.material.CompositeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Wall implements Structure {

  private final List<Block> blocks;

  // this method returns any block by its color, no matter what's the material of a block - simple or composite
  @Override
  public Optional<Block> findBlockByColor(String color) {
    for (Block block : blocks) {
      if (color != null && color.equalsIgnoreCase(block.getColor())) {
        return Optional.of(block);
      }
      if (block instanceof CompositeBlock) {
        Optional<Block> nestedBlock = findBlockInCompositeByColor((CompositeBlock) block, color);
        if (nestedBlock.isPresent()) {
          return nestedBlock;
        }
      }
    }

    return Optional.empty();
  }

  // this method returns a list of blocks by material, whether simple or composite blocks
  @Override
  public List<Block> findBlocksByMaterial(String material) {
    List<Block> result = new ArrayList<>();
    for (Block block : blocks) {
      if (material != null && material.equalsIgnoreCase(block.getMaterial())) {
        result.add(block);
      }
      if (block instanceof CompositeBlock) {
        result.addAll(findBlocksInComposite((CompositeBlock) block, material));
      }
    }
    return result;
  }

  // this method returns total number of blocks including blocks in composite blocks
  @Override
  public int count() {
    var count = 0;

    for (Block block : blocks) {
      count++;

      if (block instanceof CompositeBlock) {
        count += countBlocksInComposite((CompositeBlock) block);
      }
    }

    return count;
  }

  // this method is used to sum up every block in composite blocks
  private int countBlocksInComposite(CompositeBlock compositeBlock) {
    var count = 0;

    for (Block innerBlock : compositeBlock.getBlocks()) {
      count++;

      if (innerBlock instanceof CompositeBlock) {
        count += countBlocksInComposite((CompositeBlock) innerBlock);
      }
    }

    return count;
  }

  // this method is used to find blocks by color including blocks in composite blocks
  private Optional<Block> findBlockInCompositeByColor(CompositeBlock compositeBlock, String color) {
    for (Block innerBlock : compositeBlock.getBlocks()) {
      if (innerBlock.getColor().equalsIgnoreCase(color)) {
        return Optional.of(innerBlock);
      }
      if (innerBlock instanceof CompositeBlock) {
        var nestedCompositeBlock = findBlockInCompositeByColor((CompositeBlock) innerBlock, color);
        if (nestedCompositeBlock.isPresent()) {
          return nestedCompositeBlock;
        }
      }
    }
    return Optional.empty();
  }

  // this method is used to find all blocks which composite is built from
  private List<Block> findBlocksInComposite(CompositeBlock compositeBlock, String material) {
    var innerBlocks = new ArrayList<Block>();

    for (Block innerBlock : compositeBlock.getBlocks()) {
      if (innerBlock.getMaterial().equalsIgnoreCase(material)) {
        innerBlocks.add(innerBlock);
      }
      if (innerBlock instanceof CompositeBlock) {
        innerBlocks.addAll(findBlocksInComposite((CompositeBlock) innerBlock, material));
      }
    }

    return innerBlocks;
  }
}
