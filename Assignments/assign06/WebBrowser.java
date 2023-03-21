package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {

    private LinkedListStack<URL> forward,backward;
    private URL current;

    /**
     * Default Constructor for WebBrowser
     */
    public WebBrowser(){
        forward = new LinkedListStack<URL>();
        backward = new LinkedListStack<URL>();
        current = null;
    }

    /**
     * Constructor with a linked list history
     * @param history linked list history
     */
    public WebBrowser(SinglyLinkedList<URL> history){

        backward = new LinkedListStack<URL>(history);
    }

    /**
     * Visist a webpage and adds it to history.
     * @param webpage - page.
     */
    public void visit(URL webpage){
        backward.push(current);
        current = webpage;
        forward.clear();
    }

    /**
     * Goes back thru history
     * @return Last URL
     * @throws NoSuchElementException empty history
     */
    public URL back() throws NoSuchElementException {
        if (backward.isEmpty()) throw new NoSuchElementException("My man scrolled to the end of ifunny");
        else {
            forward.push(current);
            current = backward.peek();
            return backward.pop();
        }
    }

    /**
     * Goes forward thru history
     * @return next url
     * @throws NoSuchElementException empty history
     */
    public URL forward() throws NoSuchElementException {
        if (forward.isEmpty()) throw new NoSuchElementException("You're too trendy");
        else {
            backward.push(current);
            current = forward.peek();
            return forward.pop();
        }
    }

    /**
     * Gives comprehensive history including current page.
     * @return hist
     */
    public SinglyLinkedList<URL> history(){
        SinglyLinkedList<URL> hist = new SinglyLinkedList<>(backward);
        hist.insertFirst(current);
        return hist;
    }
}
