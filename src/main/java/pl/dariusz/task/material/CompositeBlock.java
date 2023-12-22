package pl.dariusz.task.material;

import java.util.List;

public interface CompositeBlock extends Block {

  List<Block> getBlocks();
}
