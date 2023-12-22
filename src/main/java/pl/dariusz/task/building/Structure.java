package pl.dariusz.task.building;

import pl.dariusz.task.material.Block;

import java.util.List;
import java.util.Optional;

public interface Structure {

  Optional<Block> findBlockByColor(String color);

  List<Block> findBlocksByMaterial(String material);

  int count();
}