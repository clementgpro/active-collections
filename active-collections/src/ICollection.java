import java.util.List;

public interface ICollection<T> {
	public ICollection<T> union(ICollection<T> b);
	public void add(T element);
}
