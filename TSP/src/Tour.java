public class Tour 
{
	//encapsulating a Node to contain the point 
	private class Node 
	{
		Point point;
		Node next;

		public Node(Point p)
		{
			this.point = p;
		}
	}

	private Node head;
	private int size;
	
	
	/**
	 * create an empty tour 
	 */
	public Tour()
	{
		this.size =0;
		this.head = null;
	}

	/**
	 * create a four-point tour, for debugging
	 * @param a one out of four points 
	 * @param b	two out of four points
	 * @param c	three out of four points
	 * @param d four out of four points
	 */
	public Tour(Point a, Point b, Point c, Point d)
	{
		this.head = new Node(a);
		Node current = this.head;
		current.next = new Node(b);
		current.next.next = new Node(c);
		current.next.next.next = new Node(d);
		current.next.next.next.next = this.head;
		size += 4;
	}

	/**
	 * print tour (one point per line) to std output
	 */
	public void show()
	{
		Node current = this.head;
		for(int i =0;i < size();i++) {
			System.out.println(current.point.toString());
			current = current.next;

		}


	}

	/**
	 * draw the tour using StdDraw
	 */
	public void draw()
	{
		Node current = this.head;

		for(int i =0; i < size();i++)
		{
			current.point.drawTo(current.next.point);
			current = current.next;	
		}

	}

	/** 
	 * return number of nodes in the tour
	 */
	public int size()
	{
		return this.size;
	}

	/** 
	 * return the total distance "traveled",
	 *  from start to all nodes and back to start 
	 */
	public double distance()
	{
		Node current = this.head;
		double total =0;
		for(int i =0; i < size();i++)
		{
			total += current.point.distanceTo(current.next.point);
			current = current.next;
		}

		return total;
	}
	
	/** 
	 * insert p using nearest neighbor heuristic
	 */
	public void insertNearest(Point p) 
	{
		if(size == 0) 
		{
			Node n = new Node(p);
			this.head = n;
			n.next = this.head;
			size++;
		}

		else 
		{
			Node current = this.head;
			Node nearest = this.head;

			for(int i =0; i < size();i++)
			{
				if(nearest.point.distanceTo(p) >= current.point.distanceTo(p))
					nearest = current;
					
				current = current.next;
			}

			Node n = new Node(p);
			n.next = nearest.next;
			nearest.next = n;
			size++;
		}

	}
	/**
	 * interset the element at the end of the list unless the head is null
	 * @param p object form the point class  
	 */
	public void insertInOrder(Point p) {
		Node n = new Node(p);
		
		if (this.head == null) 
		{ 
			this.head = n;
		} 
		else 
		{
			Node current = this.head;
			
			while (current != this.head) 
				current = current.next;
			
			current.next = n;
			
		}
		
		n.next = this.head;
		size++;
		
		
	}
	
	/**
	 * insertSmallest algorithm
	 * 
	 * First checking to see if the size is 0 then add.
	 * 
	 * Else find the smallest distance by traversing 
	 * through it and comparing it by  sum of form the smallest node to point p 
	 * and it's next to p and smallest's next distance 
	 * to point p by using distance Euclidean distance 
	 * and subtract the distance from smallest current node and next node.
	 * 
	 * Delta L(length) = (Nodes current to New point) + (Nodes next to New Point) - Node current to Node next.
	 * 
	 * Compare the change in lengths and see which is smaller if true then assign the current node 
	 * to the new node.
	 * 
	 * Lastly just add to the list with the smallest.
	 */
	
	/**
	 * insert p using smallest increase heuristic
	 * @param p from point class
	 */
	public void insertSmallest(Point p) 
	{
		if(size == 0) 
		{
			Node n = new Node(p);
			this.head = n;
			n.next = this.head;
			size++;
			
		}

		else 
		{
			Node current = this.head;
			Node smallest = this.head;
			
			for(int i =0;i < size();i++)
			{
				double d1 = current.point.distanceTo(p) + current.next.point.distanceTo(p) - current.point.distanceTo(current.next.point);
				double d2 = smallest.point.distanceTo(p) + smallest.next.point.distanceTo(p) - smallest.point.distanceTo(smallest.next.point);
				
				if(d1 <= d2)
					smallest = current;
					
				current = current.next;
			}
			
			Node n = new Node(p);
			n.next = smallest.next;
			smallest.next = n;
			size++;		
		}
	}
	/*
	 * Main Method for debugging
	 */
	public static void main(String[] args) {
		//define 4 points forming a square
		Point a = new Point(100.0, 100.0); Point b = new Point(500.0, 100.0); Point c = new Point(500.0, 500.0); Point d = new Point(100.0, 500.0);
		Tour squareTour = new Tour();
		StdDraw.setXscale(0, 600); StdDraw.setYscale(0, 600);
		
		squareTour.draw();
		squareTour.show(); 
		System.out.println(squareTour.distance());


	}


}