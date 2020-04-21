public class LinkedListDeque<T>{
    private Node sentinel;

    private int size;

    private class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T item){
            this.item = item;
        }
        public Node(Node prev, T item, Node next){
            this.prev = prev;
            this.next = next;
            this.item = item;
        }

    }
    public LinkedListDeque(){
        sentinel = new Node (null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        Node p = sentinel.next;
        sentinel.next = new Node(sentinel, item, p);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        Node p = sentinel.prev;
        sentinel.prev = new Node(p, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;

    }

    public boolean isEmpty(){
        return sentinel.next.item == null;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = sentinel;
        while (p.next.item != null){
            System.out.print(p.next.item.toString()+" ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (sentinel.next.item == null){
            return null;
        }
        sentinel.next = sentinel.next.next;
        size --;
        return sentinel.next.item;
    }

    public T removeLast(){
        T p = sentinel.prev.prev.item;
        sentinel.prev.item = sentinel.prev.prev.item;
        size --;
        return p;
    }

    public T get(int index){
        if (index < 0){
            throw new IllegalArgumentException();
        }
        Node p = sentinel;
        while(index >= 0){
            p = p.next;
            index -- ;
        }
        return p.item;
    }








}
