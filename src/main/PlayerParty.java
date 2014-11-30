package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import character.Model;

//Party of player controlled Models
public class PlayerParty implements ObservableList<Model> {
	//ArrayList to store models
	private ArrayList<Model> party;
	int size;
	private final int MAX_SIZE = 1;
	public PlayerParty() {
		// TODO Auto-generated constructor stub
		party = new ArrayList<Model>();
		size = 0;
	}
	public int maxSize() {
		return MAX_SIZE;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<Model> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(Model e) {
		party.add(e);
		size++;
		return true;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends Model> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends Model> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Model get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Model set(int index, Model element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void add(int index, Model element) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Model remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ListIterator<Model> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<Model> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Model> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean addAll(Model... arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void addListener(ListChangeListener<? super Model> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remove(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean removeAll(Model... arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeListener(ListChangeListener<? super Model> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean retainAll(Model... arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean setAll(Model... arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean setAll(Collection<? extends Model> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
