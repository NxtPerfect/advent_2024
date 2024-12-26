import java.util.List;

public class SuperGrid<T> {
  private List<List<T>> row;
  private List<T> col;

  public SuperGrid() {
    super();
  }

  public List<T> get(Integer index) {
    return row.get(index);
  }

  // If SuperGrid.get(i).get(j) => return j-th col from i-th row
  public void put(T elem) {
    col.add(elem);
  }

  public int isInSuperGrid(T elem) {
    if (!col.contains(elem))
      return -1;
    for (int i = 0; i < col.size(); i++) {
      if (col.get(i).equals(elem))
        return i;
    }
    return -1;
  }

  public boolean find(T elem) {
    return col.contains(elem);
  }

  public int countOccurance(T elem) {
    return 0;
  }

  public List<List<T>> getRow(Integer index) {
    return row;
  }

  public List<T> getCol(Integer index) {
    return col;
  }
}
