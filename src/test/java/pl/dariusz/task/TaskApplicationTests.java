package pl.dariusz.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dariusz.task.building.Wall;
import pl.dariusz.task.material.Block;
import pl.dariusz.task.material.BlockMaterial;
import pl.dariusz.task.material.CompositeBlockMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HorusApplicationTests {

  private Wall wall;

  @BeforeEach
  void setWall() {
    List<Block> blocks = new ArrayList<>();
    blocks.add(new BlockMaterial("black", "wood"));
    blocks.add(new BlockMaterial("red", "brick"));
    blocks.add(new BlockMaterial("red", "steel"));

    List<Block> compositeBlocks = new ArrayList<>();
    compositeBlocks.add(new BlockMaterial("White", "concrete"));
    compositeBlocks.add(new BlockMaterial("pink", "metal"));
    compositeBlocks.add(new BlockMaterial("Green", "Clay"));

    Block composite = new CompositeBlockMaterial(compositeBlocks);

    blocks.add(composite);

    wall = new Wall(blocks);

  }

  @Test
  void should_pass_if_red_block_exists() {
    String color = "red";
    Optional<Block> block = wall.findBlockByColor(color);

    assertTrue(block.isPresent(), "Block with color " + color + " should be present");
    assertEquals(color, block.get().getColor());
  }

  @Test
  void should_fail_if_orange_block_doesnt_exist() {
    String color = "orange";
    Optional<Block> block = wall.findBlockByColor(color);

    assertTrue(block.isPresent(), "Block with color " + color + " should not be present");
  }

  @Test
  void should_find_blocks_by_material() {
    String material = "wood";
    List<Block> foundBlocks = wall.findBlocksByMaterial(material);

    assertEquals(1, foundBlocks.size(), "Should find 1 block with material " + material);
    assertTrue(foundBlocks.stream()
            .allMatch(b -> b.getMaterial().equalsIgnoreCase(material)),
        "All found blocks should have material " + material);
  }

  @Test
  void should_fail_when_block_not_found() {
    String material = "water";
    List<Block> foundBlocks = wall.findBlocksByMaterial(material);

    assertEquals(1, foundBlocks.size(), "Should find 1 block with material " + material);
    assertTrue(foundBlocks.stream()
            .allMatch(b -> b.getMaterial().equalsIgnoreCase(material)),
        "All found blocks should have material " + material);
  }

  @Test
  void should_count_total_number_of_blocks() {
    int expectedCount = 7;

    assertEquals(expectedCount, wall.count(), "Incorrect total count of blocks");
  }
}
