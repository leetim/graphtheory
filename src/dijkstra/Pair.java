package dijkstra;

class Pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>>
implements Comparable<Pair<T1, T2>> { 
    T1 object1; 
    T2 object2; 
 
    Pair(T1 one, T2 two) { 
        object1 = one; 
        object2 = two; 
    } 
 
    public T1 getFirstKey() { 
        return object1; 
    } 
 
    public T2 getSecondKey() { 
        return object2; 
    }
    
    public void setFirstKey(T1 o) { 
    	object1 = o;
    }
    
    public void setSecondKey(T2 o) { 
    	object2 = o;
    }

	@Override
	public int compareTo(Pair<T1, T2> o) {
		// TODO Auto-generated method stub
        int cmp = this.getFirstKey().compareTo(o.getFirstKey());
        if (Math.abs(cmp) < 1e-5) cmp = 0;
        if (cmp == 0)
            cmp = this.getSecondKey().compareTo(o.getSecondKey());
        if (Math.abs(cmp) < 1e-5) cmp = 0;
        return cmp;
	} 
} 